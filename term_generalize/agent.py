from __future__ import annotations

import json
from collections.abc import Callable
from typing import Any, Final, cast

from openai import OpenAI
from openai.types.chat import (
    ChatCompletionFunctionToolParam,
    ChatCompletionMessageParam,
    ChatCompletionMessageToolCall,
    ChatCompletionToolChoiceOptionParam,
    ChatCompletionToolMessageParam,
)
from openai.types.shared_params import FunctionDefinition


class Agent:
    DEFAULT_SYS_PROMPT: Final[str] = (
        "You are a helpful AI Assistant. Use tools whenever appropriate."
    )

    def __init__(
        self,
        *,
        url: str,
        api_key: str | None = None,
        model: str,
        sys_prompt: str = DEFAULT_SYS_PROMPT,
        tool_choice: ChatCompletionToolChoiceOptionParam = "auto",
    ) -> None:

        # Model Info
        self.__client: Final[OpenAI] = OpenAI(
            base_url=self._normalize_base_url(url),
            api_key=api_key or "not-needed",
        )
        self.__model: Final[str] = model
        self.__tool_choice: ChatCompletionToolChoiceOptionParam = tool_choice

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

    @staticmethod
    def _normalize_base_url(url: str) -> str:
        if not url.startswith(("http://", "https://")):
            return f"http://{url}"
        return url

    def tool(self, *, description: str, parameters: dict[str, Any]):
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
                        description=description,
                        parameters=parameters,
                    ),
                )
            )

            return func

        return decorator

    def __execute_tool(self, tool_call: ChatCompletionMessageToolCall) -> str:
        function_name = tool_call.function.name
        function_args: dict[str, Any] = cast(
            dict[str, Any], json.loads(tool_call.function.arguments)
        )

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
                    cast(ChatCompletionMessageParam, cast(object, response_message))
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
                self.__messages.append(
                    cast(ChatCompletionMessageParam, cast(object, response_message))
                )
                return response_message.content or ""

    def clear(self) -> None:
        """Reset the conversation but keep registered tools."""
        self.__messages = [self.__messages[0]]

    def set_sys_prompt(self, sys_prompt: str) -> None:
        """Replace the system prompt for current and future turns."""
        self.__messages[0] = {
            "role": "system",
            "content": sys_prompt,
        }

    def get_sys_prompt(self) -> str:
        return str(self.__messages[0].get("content", ""))

    @property
    def num_tools(self) -> int:
        return len(self.__tool_schemas)

    @property
    def tool_names(self) -> list[str]:
        return [tool["function"]["name"] for tool in self.__tool_schemas]


class AIChat:
    def __init__(
        self,
        *,
        model: str,
        url: str,
        sys_prompt: str = "You are a helpful AI Assistant. Use tools whenever appropriate.",
    ):
        self.__agent = Agent(model=model, url=url, sys_prompt=sys_prompt)
