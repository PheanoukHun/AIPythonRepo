import os
import sys

from agent import Agent
from special_inputs import SPEC_COMS
from tools import register_tools


class SimpleChat:
    def __init__(self):
        self.__chat_client: Agent = Agent(
            model=os.getenv("MODEL", "llama3.2"),
            url=os.getenv("BASE_URL", "http://0.0.0.0:8080/v1"),
        )
        register_tools(self.__chat_client)

    def __called_tool_function(self) -> None:
        print(f"\nNumber of Registered Tools: {self.__chat_client.num_tools}")
        print("Available Tools:")

        for i in range(len(self.__chat_client.tool_names)):
            print(f"{i + 1}. {self.__chat_client.tool_names[i]}")

        print()

    def __exit_function(self) -> None:
        print("\nBye!\n")
        sys.exit(0)

    def __clear_function(self) -> None:
        agent.clear()

    def handle_command(self, *, command: str) -> bool:

        try:
            special: SPEC_COMS = SPEC_COMS(command)
        except ValueError:
            print(f"\nUnknown Command: {command}")
            print("Here are all the Available Commands:")
            for COMS in SPEC_COMS:
                print(f"- {COMS}")
            print()
            return False

        if special is SPEC_COMS.EXIT:
            self.__exit_function()
            return False
        elif special is SPEC_COMS.CLEAR:
            pass
        return True


# Command Handling
def handle_command(command: str) -> bool:
    """Handle a slash command. Returns False when the loop should exit."""
    try:
        special = SPEC_COMS(command)
    except ValueError:
        print(f"Unknown command: {command}")
        return True

    if special is SPEC_COMS.EXIT:
        return False
    elif special is SPEC_COMS.CLEAR:
        agent.clear()
        print("\nConversation cleared.")
    elif special is SPEC_COMS.READ:
        pass

    print()
    return True


# 1. Intitialize Agent Class
agent = Agent(
    model=os.getenv("OPENAI_MODEL", "Llama3.2"),
    url=os.getenv("OPENAI_BASE_URL", "localhost:8080/v1"),
)

# 2. Register every general-purpose tool from tools.py
register_tools(agent)
print("Registered tools:", agent.num_tools)


# 3. Chat Loop
try:
    while True:
        user_input = input("\n> ").strip()

        if (not user_input) or (not user_input.strip()):
            continue

        if user_input.startswith("/"):
            if not handle_command(user_input.lower()):
                break
            continue

        print("\nAI:", agent.chat(user_input))
except KeyboardInterrupt:
    print("\nBye.")
