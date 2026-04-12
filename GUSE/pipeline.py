"""
/pipeline/* — Full orchestration endpoint.
Runs VALIDATOR → GENERATOR → QA in sequence with strict hand-off logic.
"""
import uuid

from fastapi import APIRouter, HTTPException
from fastapi.responses import JSONResponse

import agents.validator as validator_agent
import agents.generator as generator_agent
import agents.qa as qa_agent
from models.schemas import (
    PipelineErrorResponse,
    PipelineRequest,
    PipelineResponse,
    ValidatorError,
)

router = APIRouter(prefix="/pipeline", tags=["Pipeline"])


@router.post(
    "/run",
    summary="Run the full GUSE v8 pipeline",
    description=(
        "Executes VALIDATOR → GENERATOR → QA in sequence. "
        "If VALIDATOR rejects the payload, returns HTTP 422 immediately — "
        "GENERATOR and QA are never invoked. "
        "Returns a PipelineResponse on success or PipelineErrorResponse on "
        "any mid-pipeline failure."
    ),
    responses={
        200: {"description": "Pipeline completed successfully", "model": PipelineResponse},
        422: {"description": "Validation rejected the payload", "model": PipelineErrorResponse},
        500: {"description": "Agent error during generation or QA", "model": PipelineErrorResponse},
    },
)
async def run_pipeline(request: PipelineRequest) -> JSONResponse:
    run_id = str(uuid.uuid4())
    partial: dict = {}

    # ── Stage 1: VALIDATOR ────────────────────────────────────────────────────
    try:
        val_result = validator_agent.run(request)
    except Exception as exc:
        return JSONResponse(
            status_code=500,
            content=PipelineErrorResponse(
                run_id=run_id,
                stage="validator",
                error=str(exc),
            ).model_dump(),
        )

    if isinstance(val_result, ValidatorError):
        return JSONResponse(
            status_code=422,
            content=PipelineErrorResponse(
                run_id=run_id,
                stage="validator",
                error=f"[{val_result.code}] {val_result.reason}",
                partial={"validator": val_result.model_dump()},
            ).model_dump(),
        )

    partial["validator"] = val_result.model_dump()

    # ── Stage 2: GENERATOR ────────────────────────────────────────────────────
    try:
        gen_result = generator_agent.run(val_result)
    except Exception as exc:
        return JSONResponse(
            status_code=500,
            content=PipelineErrorResponse(
                run_id=run_id,
                stage="generator",
                error=str(exc),
                partial=partial,
            ).model_dump(),
        )

    partial["generator"] = gen_result.model_dump()

    # ── Stage 3: QA ───────────────────────────────────────────────────────────
    try:
        qa_result = qa_agent.run(val_result, gen_result)
    except Exception as exc:
        return JSONResponse(
            status_code=500,
            content=PipelineErrorResponse(
                run_id=run_id,
                stage="qa",
                error=str(exc),
                partial=partial,
            ).model_dump(),
        )

    # ── Compute regen rate ────────────────────────────────────────────────────
    total_generated = len(gen_result.questions)
    regen_count = len(qa_result.regeneration_required)
    regen_rate = round((regen_count / total_generated * 100), 2) if total_generated else 0.0

    return JSONResponse(
        status_code=200,
        content=PipelineResponse(
            run_id=run_id,
            status="complete",
            validator=val_result,
            generator=gen_result,
            qa=qa_result,
            regen_rate_pct=regen_rate,
        ).model_dump(),
    )
