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
    ChatCompletionToolMessageParam,
    ChatCompletionToolParam,
)
from openai.types.shared_params import FunctionDefinition


def get_current_weather(location: str, unit: str = "celsius") -> str:
    """Gets the current weather for a given location."""
    if "boston" in location.lower():
        return f"The weather in Boston is 55 degrees {unit}."
    elif "tokyo" in location.lower():
        return f"The weather in Tokyo is 28 degrees {unit}."
    else:
        return f"Sorry, I don't have weather data for {location}."


def calculate_square(number: int) -> str:
    """Calculates the square of a given number."""
    return f"{number} squared is {number * number}."


available_functions: dict[str, Callable] = {
    "get_current_weather": get_current_weather,
    "calculate_square": calculate_square,
}


class Agent:
    """An OpenAI-compatible agent that can call registered tools.

    Works with any OpenAI-compatible API by pointing ``base_url`` at it
    (e.g. OpenAI, Azure OpenAI, vLLM, Ollama, llama.cpp, local proxies).
    """

    def __init__(
        self,
        client: openai.OpenAI | None = None,
        *,
        model: str,
        base_url: str | None = None,
        api_key: str | None = None,
        functions: dict[str, Callable] | None = None,
        tool_choice: str | None = "auto",
    ) -> None:
        if client is None:
            client = openai.OpenAI(
                base_url=base_url or os.getenv("OPENAI_BASE_URL"),
                api_key=api_key or os.getenv("OPENAI_API_KEY") or "not-needed",
            )
        self.client = client
        self.model = model
        self.tool_choice = tool_choice
        self.functions: dict[str, Callable] = functions or {}
        self.tools: list[ChatCompletionToolParam] = self._build_tools()
        self.messages: list[ChatCompletionMessageParam] = []

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

    def _execute_tool(self, tool_call: ChatCompletionMessageToolCall) -> str:
        function_name = tool_call.function.name
        function_args = json.loads(tool_call.function.arguments)

        if function_name not in self.functions:
            raise ValueError(f"Unknown function: {function_name}")

        func = self.functions[function_name]
        return str(func(**function_args))

    def run(self, user_input: str) -> str:
        """Runs the conversation loop until a final response is reached."""
        self.messages.append({"role": "user", "content": user_input})

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


if __name__ == "__main__":
    model = os.getenv("OPENAI_MODEL", "gpt-3.5-turbo")
    try:
        agent = Agent(model=model, functions=available_functions)
    except openai.OpenAIError as e:
        print(f"Error initializing OpenAI client: {e}")
        sys.exit(1)

    final_response = agent.run(
        "What is the weather in Tokyo? Then, calculate the square of 12."
    )
    print(f"\n--- Final AI Response ---\n{final_response}")
