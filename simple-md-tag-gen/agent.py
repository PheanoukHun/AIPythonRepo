from __future__ import annotations

import json
import os
import sys
from collections.abc import Callable
from typing import Any, cast

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


class Agent:
    def __init__(self, *, url: str, model: str, sys_prompt: str, api_key: str) -> None:

        # Model Info
        self.__client: OpenAI = openai.OpenAI(base_url=url, api_key=api_key)
        self.__model: str = model

        # List of Past Messages
        self.__messages = [
            {
                "role": "system",
                "content": sys_prompt,
            },
        ]

        # List of Tools
        self.__tools: dict[str, Callable[..., Any]] = {}
        self.__tool_schemas: list[dict[Any, Any]] = []

    def tool(self, *, desc: str, params: dict[str, Any]):
        """
        Register a tool using a decorator.

        Example:

        @agent.tool(
            description="Multiply two numbers",
            parameters={
                "type": "object",
                "properties": {
                    "a": {"type": "number"},
                    "b": {"type": "number"},
                },
                "required": ["a", "b"]
            }
        )
        def multiply(a, b):
            return a * b
        """

        def decorator(func: Callable[..., Any]):
            self.__tools[func.__name__] = func

            self.__tool_schemas.append(
                {
                    "type": "function",
                    "function": {
                        "name": func.__name__,
                        "description": desc,
                        "parameters": params,
                    },
                }
            )

            return func

        return decorator

    def chat(self, prompt: str) -> str:
        self.__messages.append(
            {
                "role": "user",
                "content": prompt,
            }
        )

        while True:

            response = self.__client.chat.completions.create(
                model=self.__model,
                
            )