from __future__ import annotations

import json
from collections.abc import Callable
from typing import Any, cast

from openai import OpenAI
from openai.types.chat import (
    ChatCompletionMessageParam,
    ChatCompletionToolUnionParam,
)
from openai.types.chat.chat_completion_assistant_message_param import (
    ChatCompletionAssistantMessageParam,
)
from openai.types.chat.chat_completion_message_function_tool_call import (
    ChatCompletionMessageFunctionToolCall,
)


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

        if not base_url.startswith(("http://", "https://")):
            raise ValueError(
                "base_url must start with http:// or https://"
            )

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

        self.tools: list[ChatCompletionToolUnionParam] = []

        self.tool_functions: dict[
            str,
            Callable[..., Any],
        ] = {}

    # ---------------------------------------------------------
    # Conversation
    # ---------------------------------------------------------

    def add_user_message(self, message: str) -> None:
        self.messages.append(
            {
                "role": "user",
                "content": message,
            }
        )

    def add_assistant_message(self, message: str) -> None:
        self.messages.append(
            {
                "role": "assistant",
                "content": message,
            }
        )

    def clear_history(self) -> None:
        system_message = self.messages[0]
        self.messages = [system_message]

    # ---------------------------------------------------------
    # Tool Registration
    # ---------------------------------------------------------

    def register_tool(
        self,
        *,
        name: str,
        description: str,
        parameters: dict[str, Any],
        function: Callable[..., Any],
    ) -> None:

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
                tools=self.tools,
            )

            message = response.choices[0].message

            # Finished response
            if not message.tool_calls:

                content = message.content or ""

                self.add_assistant_message(content)

                return content

            # Store assistant tool-call message
            assistant_message: ChatCompletionAssistantMessageParam = {
                "role": "assistant",
                "content": message.content,
                "tool_calls": [
                    {
                        "id": tool_call.id,
                        "type": "function",
                        "function": {
                            "name": tool_call.function.name,
                            "arguments": tool_call.function.arguments,
                        },
                    }
                    for tool_call in message.tool_calls
                    if tool_call.type == "function"
                ],
            }

            self.messages.append(assistant_message)

            # Execute tools
            for tool_call in message.tool_calls:

                if tool_call.type != "function":
                    continue

                function_call = cast(
                    ChatCompletionMessageFunctionToolCall,
                    tool_call,
                )

                function = self.tool_functions.get(
                    function_call.function.name
                )

                if function is None:

                    result = (
                        f"Unknown tool: "
                        f"{function_call.function.name}"
                    )

                else:

                    args = json.loads(
                        function_call.function.arguments
                    )

                    result = function(**args)

                self.messages.append(
                    {
                        "role": "tool",
                        "tool_call_id": function_call.id,
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
        model="Gemma-4",
    )

    response = agent.chat("Hello!")

    print(response)