"""
Thin wrapper around the Anthropic SDK.
All agents call `invoke_agent(system, user)` — returns a parsed dict.
"""
import json
import re

import anthropic

from .config import settings

_client = anthropic.Anthropic(api_key=settings.anthropic_api_key)


def invoke_agent(system_prompt: str, user_content: str) -> dict:
    """
    Call Claude with a system prompt and user message.
    Strips any prose preamble and returns the first JSON object found.
    Raises ValueError if no valid JSON is present in the response.
    """
    message = _client.messages.create(
        model=settings.claude_model,
        max_tokens=settings.max_tokens,
        system=system_prompt,
        messages=[{"role": "user", "content": user_content}],
    )

    raw = "".join(
        block.text for block in message.content if hasattr(block, "text")
    )

    # Extract the first JSON object — guards against model preamble
    match = re.search(r"\{[\s\S]*\}", raw)
    if not match:
        raise ValueError(f"No JSON object found in agent response. Raw: {raw[:400]}")

    return json.loads(match.group())
