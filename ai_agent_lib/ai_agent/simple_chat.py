import os
import subprocess
import sys
from time import sleep

from dotenv import load_dotenv

if __package__:
    from .agent import Agent
    from .special_inputs import SPEC_COMS, list_usr_enums
    from .tools import register_tools
else:
    from agent import Agent
    from special_inputs import SPEC_COMS, list_usr_enums
    from tools import register_tools

load_dotenv()


class AIChat:
    def __init__(self, *, model: str, url: str):
        self.__chat_client: Agent = Agent(model=model, url=url)
        register_tools(self.__chat_client)

    def __called_tool_function(self) -> str:
        result = (
            f"Number of Registered Tools: {self.__chat_client.num_tools}\n"
            "Available Tools:\n"
        )

        for i in range(len(self.__chat_client.tool_names)):
            result += f"\n{i + 1}. {self.__chat_client.tool_names[i]}"

        return result

    def __exit_function(self) -> None:
        print("\nBye!\n")
        sys.exit(0)

    def __clear_screen(self) -> None:
        subprocess.run("cls" if os.name == "nt" else "clear", shell=True, check=False)

    def handle_command(self, *, command: str) -> str:
        command_name, _, command_arg = command.partition(" ")

        try:
            special: SPEC_COMS = SPEC_COMS(command_name)
        except ValueError:
            print(f"\nUnknown Command: {command_name}")
            print("Here are all the Available Commands:")
            for option in list_usr_enums():
                print(f"- {option}, Value: {option}")
            print()
            return SPEC_COMS.ERROR.value

        if special is SPEC_COMS.EXIT:
            self.__exit_function()
            return SPEC_COMS.EXIT.value
        elif special is SPEC_COMS.CLEAR:
            self.__chat_client.clear()
            return SPEC_COMS.CLEAR.value
        elif special is SPEC_COMS.CLEAR_SCREEN:
            return SPEC_COMS.CLEAR_SCREEN.value
        elif special is SPEC_COMS.SYSTEM:
            prompt = command_arg.strip()
            if not prompt:
                print(f"\nCurrent System Prompt:\n{self.__chat_client.get_sys_prompt()}\n")
                return SPEC_COMS.SYSTEM.value
            self.__chat_client.set_sys_prompt(prompt)
            print("\nSystem prompt updated.\n")
            return SPEC_COMS.SYSTEM.value
        else:
            return self.__called_tool_function()

    def set_sys_prompt(self, prompt: str) -> None:
        self.__chat_client.set_sys_prompt(prompt)

    def get_sys_prompt(self) -> str:
        return self.__chat_client.get_sys_prompt()

    def chat(self, message: str) -> str:
        message = message.strip()

        if not message:
            return SPEC_COMS.SKIP.value

        if message.startswith("/"):
            return self.handle_command(command=message)

        return self.__chat_client.chat(message)

    def cli_chat(self):
        try:
            while True:
                msg_in: str = input("\n> ")

                chat_res: str = self.chat(msg_in)

                if chat_res in (SPEC_COMS.SKIP.value, SPEC_COMS.ERROR.value):
                    continue
                elif chat_res in (SPEC_COMS.CLEAR.value, SPEC_COMS.CLEAR_SCREEN.value):
                    self.__clear_screen()
                    continue

                print("\nAI: ", end="")
                for char in chat_res:
                    print(char, end="", flush=True)
                    sleep(0.005)

                print()

        except KeyboardInterrupt:
            print()
            self.__exit_function()


if __name__ == "__main__":
    cli_chat = AIChat(
        model=os.getenv("MODEL", "llama3.2"),
        url=os.getenv("BASE_URL", "http://0.0.0.0:8080/v1"),
    )
    cli_chat.cli_chat()
