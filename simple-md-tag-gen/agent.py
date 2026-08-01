from __future__ import annotations

import json
from collections.abc import Callable
from typing import Any, cast

import openai
from openai.types.chat import (
    ChatCompletionFunctionToolParam,
    ChatCompletionMessageParam,
    ChatCompletionMessageToolCall,
    ChatCompletionToolChoiceOptionParam,
    ChatCompletionToolMessageParam,
)
from openai.types.shared_params import FunctionDefinition


class Agent:
    DEFAULT_SYS_PROMPT: str = (
        "You are a helpful AI Assistant. Use tools whenever appropriate."
    )

    def __init__(
        self,
        *,
        url: str,
        api_key: str | None = None,
        model: str,
        sys_prompt: str = DEFAULT_SYS_PROMPT,
        tool_choice: ChatCompletionToolChoiceOptionParam | None = "auto",
    ) -> None:

        # Model Info
        self.__client: openai.OpenAI = openai.OpenAI(
            base_url=url, api_key=api_key or "not-needed"
        )
        self.__model: str = model
        self.__tool_choice = tool_choice

        # List of Past Messages
        self.__messages: list[ChatCompletionMessageParam] = [
            {
                "role": "system",
                "content": sys_prompt,
            },
        ]

        # List of Tools
        self.__tools: dict[str, Callable[..., Any]] = {}
        self.__tool_schemas: list[ChatCompletionFunctionToolParam] = []

    def tool(self, *, desc: str, params: dict[str, Any]):
        """
        Register a tool using a decorator.

        Example:

        @agent.tool(
            desc="Multiply two numbers",
            params={
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
                ChatCompletionFunctionToolParam(
                    type="function",
                    function=FunctionDefinition(
                        name=func.__name__,
                        description=desc,
                        parameters=params,
                    ),
                )
            )

            return func

        return decorator

    def __execute_tool(self, tool_call: ChatCompletionMessageToolCall) -> str:
        function_name = tool_call.function.name
        function_args: dict[str, Any] = json.loads(tool_call.function.arguments)

        if function_name not in self.__tools:
            raise ValueError(f"Unknown tool '{function_name}'")

        result = self.__tools[function_name](**function_args)
        if isinstance(result, str):
            return result
        return json.dumps(result)

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
                tool_choice=self.__tool_choice,
            )

            response_message = response.choices[0].message

            if response_message.tool_calls:
                self.__messages.append(
                    cast(ChatCompletionMessageParam, response_message)
                )
                for tool_call in response_message.tool_calls:
                    if not isinstance(tool_call, ChatCompletionMessageToolCall):
                        continue
                    result = self.__execute_tool(tool_call)
                    self.__messages.append(
                        ChatCompletionToolMessageParam(
                            tool_call_id=tool_call.id,
                            role="tool",
                            content=result,
                        )
                    )
            else:
                return response_message.content or ""

    def clear(self) -> None:
        """Reset the conversation but keep registered tools."""
        self.__messages = [self.__messages[0]]
