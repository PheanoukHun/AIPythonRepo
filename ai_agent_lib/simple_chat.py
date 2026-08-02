import os
import subprocess
import sys

from agent import Agent
from special_inputs import SPEC_COMS
from tools import register_tools


class SimpleCLIChat:
    def __init__(self, *, model: str, url: str):
        self.__chat_client: Agent = Agent(model=model, url=url)
        register_tools(self.__chat_client)

    def __called_tool_function(self) -> None:
        print(f"\nNumber of Registered Tools: {self.__chat_client.num_tools}")
        print("Available Tools:")

        for i in range(len(self.__chat_client.tool_names)):
            print(f"{i + 1}. {self.__chat_client.tool_names[i]}")

    def __exit_function(self) -> None:
        print("\nBye!\n")
        sys.exit(0)

    def __clear_function(self) -> None:
        self.__clear_screen()
        self.__chat_client.clear()

    def __clear_screen(self) -> None:
        subprocess.run("cls" if os.name == "nt" else "clear", shell=True, check=False)

    def handle_command(self, *, command: str) -> None:

        try:
            special: SPEC_COMS = SPEC_COMS(command)
        except ValueError:
            print(f"\nUnknown Command: {command}")
            print("Here are all the Available Commands:")
            for COMS in SPEC_COMS:
                print(f"- {COMS}, Value: {COMS.value}")
            print()
            return

        if special is SPEC_COMS.EXIT:
            self.__exit_function()
        elif special is SPEC_COMS.CLEAR:
            self.__clear_function()
        elif special is SPEC_COMS.CLEAR_SCREEN:
            self.__clear_screen()
        else:
            self.__called_tool_function()

    def chat(self) -> None:
        try:
            user_input: str = input("\n> ").strip()

            if not user_input:
                return

            if user_input.startswith("/"):
                self.handle_command(command=user_input)
                return

            print("\nAI:", self.__chat_client.chat(user_input), "\n")
        except KeyboardInterrupt:
            self.__exit_function()

    def chat_loop(self):
        while True:
            self.chat()


if __name__ == "__main__":
    cli_chat = SimpleCLIChat(
        model=os.getenv("MODEL", "llama3.2"),
        url=os.getenv("BASE_URL", "http://0.0.0.0:8080/v1"),
    )
    cli_chat.chat_loop()
