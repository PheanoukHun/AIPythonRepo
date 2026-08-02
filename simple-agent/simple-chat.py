"""Minimal interactive chat that wires up tools from tools.py."""

import os

from agent import Agent
from tools import register_tools

agent = Agent(
    model=os.getenv("OPENAI_MODEL", "Llama3.2"),
    url=os.getenv("OPENAI_BASE_URL", "localhost:8080/v1"),
)

# 1. Register every general-purpose tool from tools.py
register_tools(agent)

# 2. Register custom tools inline with the decorator
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


print("Registered tools:", agent.num_tools)

try:
    while True:
        user_input = input("> ")

        if user_input.lower() in ("exit", "quit"):
            break

        print("\nAssistant:", agent.chat(user_input))
except KeyboardInterrupt:
    print("\nBye.")
