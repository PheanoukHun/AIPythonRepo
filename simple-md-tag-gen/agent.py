from __future__ import annotations

import json
import os
import sys
from collections.abc import Callable
from typing import Any, cast

import openai
from openai.types.chat import (
    ChatCompletionFunctionToolParam,
    ChatCompletionMessageParam,
)

DEFAULT_SYS_PROMPT = "You are a helpful AI Assitant. Use tools whenever appropriate."


class Agent:
    def __init__(self, *, url: str, api_key: str = "NULL", model: str, sys_prompt: str = DEFAULT_SYS_PROMPT) -> None:

        # Model Info
        self.__client: openai.OpenAI = openai.OpenAI(base_url=url, api_key=api_key)
        self.__model: str = model

        # List of Past Messages
        self.__messages: list[ChatCompletionMessageParam] = [
            {
                "role": "system",
                "content": sys_prompt,
            },
        ]

        # List of Tools
        self.__tools: list[ChatCompletionMessageParam] | None = None
        self.__tool_schemas: list[ChatCompletionFunctionToolParam] = []

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
                messages=self.__messages,
                tools=self.__tool_schemas,
                tool_choice="auto",
            )
