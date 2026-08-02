"""Minimal interactive chat that wires up tools from tools.py."""

import os

from agent import Agent
from special_inputs import SPECIAL_IN
from tools import register_tools

agent = Agent(
    model=os.getenv("OPENAI_MODEL", "Llama3.2"),
    url=os.getenv("OPENAI_BASE_URL", "localhost:8080/v1"),
)

# 1. Register every general-purpose tool from tools.py
register_tools(agent)
print("Registered tools:", agent.num_tools)

# 2. Chat Loop


def handle_command(command: str) -> bool:
    """Handle a slash command. Returns False when the loop should exit."""
    try:
        special = SPECIAL_IN(command)
    except ValueError:
        print(f"Unknown command: {command}")
        return True

    if special is SPECIAL_IN.EXIT:
        return False
    if special is SPECIAL_IN.CLEAR:
        agent.clear()
        print("\nConversation cleared.")
        return True
    if special is SPECIAL_IN.TOOL:
        print("\nAvailable tools:")
        for name in agent.tool_names:
            print("  -", name)
        return True
    return True


try:
    while True:
        user_input = input("\n> ").strip()

        if not user_input:
            continue

        if user_input.startswith("/"):
            if not handle_command(user_input):
                break
            continue

        print("\nAI:", agent.chat(user_input))
except KeyboardInterrupt:
    print("\nBye.")
