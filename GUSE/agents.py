"""
/agents/* — Individual agent endpoints.
Use these for debugging, testing, or partial pipeline execution.
"""
from fastapi import APIRouter, HTTPException
from pydantic import BaseModel

import agents.validator as validator_agent
import agents.generator as generator_agent
import agents.qa as qa_agent
from models.schemas import (
    GeneratorOutput,
    PipelineRequest,
    QAOutput,
    ValidatorError,
    ValidatorSuccess,
)

router = APIRouter(prefix="/agents", tags=["Agents"])


# ── Validate ──────────────────────────────────────────────────────────────────

@router.post(
    "/validate",
    response_model=ValidatorSuccess | ValidatorError,
    summary="Run GUSE-VALIDATOR only",
    description="Validates and normalises a raw pipeline request. Returns a "
                "ValidatorSuccess (status=valid) or ValidatorError (status=error).",
)
async def validate(request: PipelineRequest) -> ValidatorSuccess | ValidatorError:
    try:
        return validator_agent.run(request)
    except Exception as exc:
        raise HTTPException(status_code=500, detail=str(exc)) from exc


# ── Generate ──────────────────────────────────────────────────────────────────

@router.post(
    "/generate",
    response_model=GeneratorOutput,
    summary="Run GUSE-GENERATOR only",
    description="Generates questions from a pre-validated payload. "
                "You must supply a ValidatorSuccess object as the request body.",
)
async def generate(validated: ValidatorSuccess) -> GeneratorOutput:
    try:
        return generator_agent.run(validated)
    except Exception as exc:
        raise HTTPException(status_code=500, detail=str(exc)) from exc


# ── QA ────────────────────────────────────────────────────────────────────────

class QARequest(BaseModel):
    validated: ValidatorSuccess
    generated: GeneratorOutput


@router.post(
    "/qa",
    response_model=QAOutput,
    summary="Run GUSE-QA only",
    description="Audits a generator batch. Requires both the ValidatorSuccess "
                "payload (for topic_scope) and the GeneratorOutput batch.",
)
async def qa(body: QARequest) -> QAOutput:
    try:
        return qa_agent.run(body.validated, body.generated)
    except Exception as exc:
        raise HTTPException(status_code=500, detail=str(exc)) from exc
