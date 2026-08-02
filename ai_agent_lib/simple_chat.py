import os
import subprocess
import sys

from dotenv import load_dotenv

from agent import Agent
from special_inputs import SPEC_COMS, list_usr_enums
from tools import register_tools

load_dotenv()


class AIChat:
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
            for option in list_usr_enums():
                print(f"- {option}, Value: {option}")
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

    def chat(self, message:str) -> str:
        message = message.strip()

        if not message:
            return SPEC_COMS.SKIP.value

        if message.startswith("/"):
            self.handle_command(command=message)
            return SPEC_COMS.SKIP.value

        return self.__chat_client.chat(message)

    def chat_loop(self):
        while True:
            msg_in:str = input("\n> ")
            print(f"\nAI: {self.chat(msg_in)}")


if __name__ == "__main__":
    cli_chat = AIChat(
        model=os.getenv("MODEL", "llama3.2"),
        url=os.getenv("BASE_URL", "http://0.0.0.0:8080/v1"),
    )
    cli_chat.chat_loop()
