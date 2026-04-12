"""
GUSE-VALIDATOR agent — Stage 1.
Validates and normalises the raw pipeline request.
"""
import json

from core.claude import invoke_agent
from models.schemas import PipelineRequest, ValidatorError, ValidatorSuccess
from .prompts import VALIDATOR


def run(request: PipelineRequest) -> ValidatorSuccess | ValidatorError:
    """
    Invoke the VALIDATOR agent with the raw pipeline request payload.
    Returns a typed ValidatorSuccess or ValidatorError.
    """
    user_content = json.dumps(request.model_dump(), indent=2)
    result = invoke_agent(VALIDATOR, user_content)

    if result.get("status") == "valid":
        return ValidatorSuccess(**result)

    return ValidatorError(**result)
