"""
GUSE v8 — FastAPI entrypoint.
Run: uvicorn main:app --reload
"""
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from routers import agents, pipeline

app = FastAPI(
    title="GUSE v8 Trivia Pipeline",
    description=(
        "A three-stage AI pipeline for deterministic trivia generation.\n\n"
        "**Stages:** VALIDATOR → GENERATOR → QA\n\n"
        "- `POST /pipeline/run` — full orchestration\n"
        "- `POST /agents/validate` — VALIDATOR only\n"
        "- `POST /agents/generate` — GENERATOR only\n"
        "- `POST /agents/qa` — QA only"
    ),
    version="8.1.0",
    docs_url="/docs",
    redoc_url="/redoc",
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],     # tighten for production
    allow_methods=["*"],
    allow_headers=["*"],
)

app.include_router(pipeline.router)
app.include_router(agents.router)


@app.get("/", tags=["Health"])
async def root() -> dict:
    return {"service": "GUSE v8", "status": "online", "version": "8.1.0"}


@app.get("/health", tags=["Health"])
async def health() -> dict:
    return {"status": "ok"}
