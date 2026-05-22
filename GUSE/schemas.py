"""
All Pydantic models for GUSE v8.
Single source of truth — shared by agents, routers, and the client.
"""
from __future__ import annotations

from enum import Enum
from typing import Any

from pydantic import BaseModel, Field, field_validator


# ── Enums ─────────────────────────────────────────────────────────────────────

class Difficulty(str, Enum):
    easy = "easy"
    medium = "medium"
    hard = "hard"
    expert = "expert"


class AnswerKey(str, Enum):
    A = "A"
    B = "B"
    C = "C"
    D = "D"


class AuditAction(str, Enum):
    fixed = "fixed"
    regenerate = "regenerate"


# ── Pipeline Request ──────────────────────────────────────────────────────────

class PipelineRequest(BaseModel):
    topic: str = Field(..., min_length=2, max_length=200, examples=["The Roman Empire"])
    difficulty: Difficulty = Field(default=Difficulty.medium)
    count: int = Field(default=5, ge=1, le=20)
    language: str = Field(default="en", examples=["en", "fr-CA", "zh-Hans"])
    seen_fingerprints: list[str] = Field(default_factory=list)

    @field_validator("seen_fingerprints")
    @classmethod
    def sanitize_fingerprints(cls, v: list[str]) -> list[str]:
        """Pre-sanitize at the API boundary — mirrors VALIDATOR rule 3."""
        import re
        pattern = re.compile(r"^[a-z0-9-]+\|[a-z0-9-]+\|[a-z0-9-]+$")
        return [fp for fp in v if pattern.match(fp)]


# ── VALIDATOR ─────────────────────────────────────────────────────────────────

class ValidatorSuccess(BaseModel):
    status: str = "valid"
    topic_slug: str
    topic_scope: str
    difficulty: Difficulty
    count: int
    language: str
    seen_fingerprints: list[str]


class ValidatorError(BaseModel):
    status: str = "error"
    code: str
    reason: str


# ── GENERATOR ─────────────────────────────────────────────────────────────────

class QuestionOptions(BaseModel):
    A: str
    B: str
    C: str
    D: str


class Question(BaseModel):
    uid: str = Field(..., pattern=r"^[a-z0-9-]+\|[a-z0-9-]+\|[a-z0-9-]+$")
    stem: str
    options: QuestionOptions
    answer: AnswerKey
    fact: str
    difficulty: Difficulty


class GeneratorOutput(BaseModel):
    questions: list[Question]
    skipped: list[dict[str, Any]] = Field(default_factory=list)


# ── QA ────────────────────────────────────────────────────────────────────────

class AuditEntry(BaseModel):
    uid: str
    action: AuditAction
    reason: str


class QAOutput(BaseModel):
    status: str = "complete"
    count: int
    questions: list[Question]
    regeneration_required: list[AuditEntry] = Field(default_factory=list)
    audit_log: list[AuditEntry] = Field(default_factory=list)


# ── Full Pipeline Response ────────────────────────────────────────────────────

class PipelineResponse(BaseModel):
    run_id: str
    status: str
    validator: ValidatorSuccess
    generator: GeneratorOutput
    qa: QAOutput
    regen_rate_pct: float = Field(description="Regeneration rate as a percentage (target <5%)")


class PipelineErrorResponse(BaseModel):
    run_id: str
    status: str = "failed"
    stage: str
    error: str
    partial: dict[str, Any] = Field(default_factory=dict)
