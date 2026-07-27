from typing import Callable

from openai import OpenAI


class Agent:
    def __init__(
        self,
        *,
        base_url: str,
        api_key: str,
        model: str,
        system_prompt: str = "You are a helpful AI assistant.",
        temperature: float = 0.7,
    ) -> None:

        # Client Caller
        self.client: OpenAI = OpenAI(api_key=api_key, base_url=base_url)

        # Model Info
        self.model: str = model
        self.temperature: float = temperature

        # Message Lists
        self.messages: list[dict] = [
            {
                "role": "system",
                "content": system_prompt,
            },
        ]

        # Basic Function Calls
        self.tools = []
        self.tool_functions: dict[str, Callable] = {}

    def add_user_mesg(self, message: str) -> None:
        self.messages.append(
            {}
        )

    def add_assitant_message(self, message:str) -> None:
        self.messages.append(
            {
                "role": "assistant",
                "content": message,
            }
        )

    def clear_history(self) -> None:
        system = self.messages[0]
        self.messages = [system]

    def register_tool(
        self, *, name: str, description: str, parameters: dict, function: Callable
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

    def chat(self, prompt: str) -> None:
        self.add_user_mesg(prompt)

        while True:
            response = self.client.chat.completions.create(
                model=self.model,
                temperature=self.temperature,
                messages=self.messages,
                tools=self.tools if self.tools else None,
            )

            message = response.choices[0].message

            if not message.tool_calls:
                content = message.content or ""
                self.add
