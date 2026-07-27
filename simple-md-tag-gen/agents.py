from __future__ import annotations

import json
from typing import Callable

from openai import OpenAI
from openai.types.chat import (
    ChatCompletionMessageParam,
    ChatCompletionToolUnionParam,
)
from openai.types.responses import response


class Agent:
    """
    Generic Agent for any OpenAI-compatible API.
    """

    def __init__(
        self,
        *,
        api_key: str,
        base_url: str,
        model: str,
        system_prompt: str = "You are a helpful AI assistant.",
        temperature: float = 0.7,
    ) -> None:

        self.client = OpenAI(
            api_key=api_key,
            base_url=base_url,
        )

        self.model = model
        self.temperature = temperature

        self.messages: list[ChatCompletionMessageParam] = [
            {
                "role": "system",
                "content": system_prompt,
            }
        ]

        self.tools:list[ChatCompletionToolUnionParam] = []
        self.tool_functions: dict[str, Callable] = {}

    # ---------------------------------------------------------
    # Conversation
    # ---------------------------------------------------------

    def add_user_message(self, message: str):
        self.messages.append(
            {
                "role": "user",
                "content": message,
            }
        )

    def add_assistant_message(self, message: str):
        self.messages.append(
            {
                "role": "assistant",
                "content": message,
            }
        )

    def clear_history(self):
        system = self.messages[0]
        self.messages = [system]

    # ---------------------------------------------------------
    # Tool Registration
    # ---------------------------------------------------------

    def register_tool(
        self,
        *,
        name: str,
        description: str,
        parameters: dict,
        function: Callable,
    ):

        self.tool_functions[name] = function

        self.tools.append(
            {
                "type": "function",
                "function": {
                    "name": name,
                    "description": description,
                    "parameters": parameters,
                },
            }
        )

    # ---------------------------------------------------------
    # Chat
    # ---------------------------------------------------------

    def chat(self, prompt: str) -> str:

        self.add_user_message(prompt)

        while True:
            response = self.client.chat.completions.create(
                model=self.model,
                temperature=self.temperature,
                messages=self.messages,
                tools=self.tools if self.tools else None,
            )

            message = response.choices[0].message

            # No tool calls → finished
            if not message.tool_calls:
                content = message.content or ""
                self.add_assistant_message(content)
                return content

            # Store assistant message containing tool calls
            self.messages.append(message.model_dump())

            for tool_call in message.tool_calls:
                func = self.tool_functions.get(tool_call.function.name)

                if func is None:
                    result = f"Unknown tool: {tool_call.function.name}"
                else:
                    args = json.loads(tool_call.function.arguments)
                    result = func(**args)

                self.messages.append(
                    {
                        "role": "tool",
                        "tool_call_id": tool_call.id,
                        "content": str(result),
                    }
                )

    # ---------------------------------------------------------
    # Streaming
    # ---------------------------------------------------------

    def stream(self, prompt: str):

        self.add_user_message(prompt)

        stream = self.client.chat.completions.create(
            model=self.model,
            messages=self.messages,
            temperature=self.temperature,
            stream=True,
        )

        collected = ""

        for chunk in stream:
            delta = chunk.choices[0].delta.content

            if delta:
                collected += delta
                yield delta

        self.add_assistant_message(collected)


if __name__ == "__main__":
    agent = Agent(
        api_key="API_KEY",
        base_url="http://0.0.0.0:8080/v1",
        model="Gemma 4",
    )

    response = agent.chat("Hello!")
    print(response)
