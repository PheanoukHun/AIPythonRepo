import os

from agent import Agent
from special_inputs import SPECIAL_IN
from tools import register_tools


class SimpleChat:
    def __init__(self):
        self.__chat_client: Agent = Agent(
            model=os.getenv("MODEL", "llama3.2"),
            url=os.getenv("BASE_URL", "http://0.0.0.0:8080/v1"),
        )
        register_tools(self.__chat_client)

    def __called_tool_function(self):
        print(f"\nNumber of Registered Tools: {self.__chat_client.}")


# Command Handling
def handle_command(command: str) -> bool:
    """Handle a slash command. Returns False when the loop should exit."""
    try:
        special = SPECIAL_IN(command)
    except ValueError:
        print(f"Unknown command: {command}")
        return True

    if special is SPECIAL_IN.EXIT:
        return False
    elif special is SPECIAL_IN.CLEAR:
        agent.clear()
        print("\nConversation cleared.")
    elif special is SPECIAL_IN.TOOL:
        print("\nAvailable tools:")
        for name in agent.tool_names:
            print("\t- ", name)
    elif special is SPECIAL_IN.READ:
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
