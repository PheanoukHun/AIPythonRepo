from __future__ import annotations

import json
import os
import sys
from collections.abc import Callable
from typing import cast

import openai
from openai.resources.beta.responses.responses import OpenAI
from openai.types.chat import (
    ChatCompletionFunctionToolParam,
    ChatCompletionMessageParam,
    ChatCompletionMessageToolCall,
    ChatCompletionToolChoiceOptionParam,
    ChatCompletionToolMessageParam,
    ChatCompletionToolParam,
)
from openai.types.shared_params import FunctionDefinition
from typing_extensions import final


class Agent:
    def __init__(self, *, url: str, model: str, sys_prompt: str, api_key: str) -> None:
        self.__client: OpenAI = openai.OpenAI(base_url=url, api_key=api_key)
        self.__model: str = model
        self.__messages = [
            {
                "role": "system",
                "content": sys_prompt,
            },
        ]
