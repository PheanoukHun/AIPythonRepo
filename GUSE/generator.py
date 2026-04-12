"""
GUSE-GENERATOR agent — Stage 2.
Generates trivia questions from a validated payload.
"""
import json

from core.claude import invoke_agent
from models.schemas import GeneratorOutput, ValidatorSuccess
from .prompts import GENERATOR


def run(validated: ValidatorSuccess) -> GeneratorOutput:
    """
    Invoke the GENERATOR agent with the validated payload.
    Returns a typed GeneratorOutput.
    """
    user_content = (
        f"Generate {validated.count} trivia questions using this validated payload:\n"
        + json.dumps(validated.model_dump(), indent=2)
    )
    result = invoke_agent(GENERATOR, user_content)
    return GeneratorOutput(**result)
