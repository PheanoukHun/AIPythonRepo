"""
GUSE-QA agent — Stage 3.
Audits, fixes, or rejects the generator batch.
"""
import json

from core.claude import invoke_agent
from models.schemas import GeneratorOutput, QAOutput, ValidatorSuccess
from .prompts import QA


def run(validated: ValidatorSuccess, generated: GeneratorOutput) -> QAOutput:
    """
    Invoke the QA agent with the full generator batch plus the original
    topic_scope and seen_fingerprints for scope enforcement.
    """
    user_content = (
        f'Audit this question batch.\n'
        f'topic_scope: "{validated.topic_scope}"\n'
        f'seen_fingerprints: {json.dumps(validated.seen_fingerprints)}\n\n'
        f'Batch:\n{json.dumps(generated.model_dump(), indent=2)}'
    )
    result = invoke_agent(QA, user_content)
    return QAOutput(**result)
