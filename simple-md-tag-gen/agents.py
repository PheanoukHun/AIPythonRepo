from __future__ import annotations

import inspect
import json
import os
import sys
from collections.abc import Callable
from typing import Any, cast

import openai
from openai.types.chat import (
    ChatCompletionFunctionToolParam,
    ChatCompletionMessageParam,
    ChatCompletionMessageToolCall,
    ChatCompletionToolChoiceOptionParam,
    ChatCompletionToolMessageParam,
    ChatCompletionToolParam,
)
from openai.types.shared_params import FunctionDefinition

DEFAULT_SYSTEM_PROMPT = (
    "You are a helpful AI assistant. Use tools whenever appropriate."
)


class Agent:
    """An OpenAI-compatible agent that can call registered tools.

    Works with any OpenAI-compatible API by pointing ``base_url`` at it
    (e.g. OpenAI, Azure OpenAI, vLLM, Ollama, llama.cpp, local proxies).
    """

    def __init__(
        self,
        model: str,
        client: openai.OpenAI | None = None,
        *,
        system_prompt: str = DEFAULT_SYSTEM_PROMPT,
        base_url: str | None = None,
        api_key: str | None = None,
        functions: dict[str, Callable] | None = None,
        tool_choice: ChatCompletionToolChoiceOptionParam | None = "auto",
    ) -> None:
        if client is None:
            base_url = base_url or os.getenv("OPENAI_BASE_URL")
            client = openai.OpenAI(
                base_url=self._normalize_base_url(base_url),
                api_key=api_key or os.getenv("OPENAI_API_KEY") or "not-needed",
            )
        self.client = client
        self.model = model
        self.tool_choice = tool_choice
        self.functions: dict[str, Callable] = dict(functions or {})
        self.tools: list[ChatCompletionToolParam] = self._build_tools()
        self.messages: list[ChatCompletionMessageParam] = [
            {"role": "system", "content": system_prompt}
        ]

    @staticmethod
    def _normalize_base_url(base_url: str | None) -> str | None:
        if base_url and not base_url.startswith(("http://", "https://")):
            return f"http://{base_url}"
        return base_url

    # -------------------------
    # Tool Registration
    # -------------------------

    def tool(self, description: str, parameters: dict[str, object]):
        """Register a tool using a decorator.

        Example:
            @agent.tool(
                description="Multiply two numbers",
                parameters={
                    "type": "object",
                    "properties": {
                        "a": {"type": "number"},
                        "b": {"type": "number"},
                    },
                    "required": ["a", "b"],
                },
            )
            def multiply(a, b):
                return a * b
        """

        def decorator(func: Callable) -> Callable:
            self.functions[func.__name__] = func
            self.tools.append(
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

    def _build_tools(self) -> list[ChatCompletionToolParam]:
        tools: list[ChatCompletionToolParam] = []
        for name, func in self.functions.items():
            tools.append(self._function_to_tool(name, func))
        return tools

    def _function_to_tool(self, name: str, func: Callable) -> ChatCompletionToolParam:
        signature = inspect.signature(func)
        properties: dict[str, object] = {}
        required: list[str] = []
        for param_name, param in signature.parameters.items():
            properties[param_name] = {"type": self._map_type(param.annotation)}
            if param.default is inspect.Parameter.empty:
                required.append(param_name)

        params_schema: dict[str, object] = {
            "type": "object",
            "properties": properties,
            "required": required,
        }
        return ChatCompletionFunctionToolParam(
            type="function",
            function=FunctionDefinition(
                name=name,
                description=func.__doc__ or "",
                parameters=params_schema,
            ),
        )

    @staticmethod
    def _map_type(annotation: Any) -> str:
        if annotation is int:
            return "integer"
        if annotation is float:
            return "number"
        if annotation is bool:
            return "boolean"
        return "string"

    # -------------------------
    # Chat
    # -------------------------

    def _execute_tool(self, tool_call: ChatCompletionMessageToolCall) -> str:
        function_name = tool_call.function.name
        function_args = json.loads(tool_call.function.arguments)

        if function_name not in self.functions:
            raise ValueError(f"Unknown tool '{function_name}'")

        func = self.functions[function_name]
        result = func(**function_args)
        if isinstance(result, str):
            return result
        return json.dumps(result)

    def chat(self, prompt: str) -> str:
        """Runs the conversation loop until a final response is reached."""
        self.messages.append({"role": "user", "content": prompt})

        while True:
            response = self.client.chat.completions.create(
                model=self.model,
                messages=self.messages,
                tools=self.tools,
                tool_choice=self.tool_choice,
            )
            response_message = response.choices[0].message

            if response_message.tool_calls:
                self.messages.append(
                    cast(ChatCompletionMessageParam, response_message)
                )
                for tool_call in response_message.tool_calls:
                    if not isinstance(tool_call, ChatCompletionMessageToolCall):
                        continue
                    result = self._execute_tool(tool_call)
                    self.messages.append(
                        ChatCompletionToolMessageParam(
                            tool_call_id=tool_call.id,
                            role="tool",
                            content=result,
                        )
                    )
            else:
                return response_message.content or ""

    def run(self, user_input: str) -> str:
        """Alias for :meth:`chat`."""
        return self.chat(user_input)

    # -------------------------
    # Conversation
    # -------------------------

    def clear(self) -> None:
        """Reset the conversation but keep registered tools."""
        self.messages = [self.messages[0]]


if __name__ == "__main__":
    agent = Agent(
        model=os.getenv("OPENAI_MODEL", "gpt-3.5-turbo"),
        base_url=os.getenv("OPENAI_BASE_URL", "localhost:8081/v1"),
    )

    @agent.tool(
        description="Get the weather for a city.",
        parameters={
            "type": "object",
            "properties": {"city": {"type": "string"}},
            "required": ["city"],
        },
    )
    def get_weather(city: str) -> dict[str, str]:
        return {
            "city": city,
            "temperature": "86°F",
            "condition": "Sunny",
        }

    @agent.tool(
        description="Multiply two numbers.",
        parameters={
            "type": "object",
            "properties": {
                "a": {"type": "number"},
                "b": {"type": "number"},
            },
            "required": ["a", "b"],
        },
    )
    def multiply(a: float, b: float) -> float:
        return a * b

    while True:
        user_input = input("> ")

        if user_input.lower() in ("exit", "quit"):
            break

        print("\nAssistant:", agent.chat(user_input))
