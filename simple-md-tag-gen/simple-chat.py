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
print("Registered tools:", agent.num_tools)

# 2. Chat Loop

try:
    while True:
        user_input = input("> ")

        if user_input.lower() in ("/exit", "/quit"):
            break

        print("\nAssistant:", agent.chat(user_input))
except KeyboardInterrupt:
    print("\nBye.")
