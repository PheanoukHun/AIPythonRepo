import time
from enum import Enum

from configurers.valid_path import (
    PATH_VALIDITY,
    interpret_results,
    is_valid_path,
)

from .server_message import MessageServer


class Options:
    READ_IN:str = "/read"
    EXIT_IN:list[str] = ["/exit", "/quit"]
    CLEAR_IN:list[str] = ["/clear", "/cls"]
    EXIT_OUT: str = "$$EXIT$$"
    CLEAR_OUT: str = "$$CLEAR$$"


class MULTILINE_INPUT(Enum):
    ALLOW = 0
    DISABLE = 1


class RUN_TYPE(Enum):
    SINGLE = 0
    REPEATED = 1
    INPUT_FILE = 2


class Runner:
    def __init__(
        self, server: MessageServer, options: dict[str, bool | str] | None = None
    ):
        self.__server = server
        self.__options = options or {}

    def __continuous_loop(self, allow_multiline: MULTILINE_INPUT) -> str:
        outputs = []
        while True:
            result: str = self.__single_run(allow_multiline)
            if result == Options.EXIT_OUT:
                break
            self.__type_writer_print(result)
            outputs.append(result)
        return "\n".join(outputs)

    def __input(self, allow_multiline: MULTILINE_INPUT) -> str:
        res: str = input("\n> ")
        while "/*-" in res and allow_multiline is MULTILINE_INPUT.ALLOW:
            res = res + input("\n>")
        return res

    def __single_run(self, allow_multiline: MULTILINE_INPUT) -> str:
        try:
            user_in = self.__input(allow_multiline)
            proc_in = self.__parse_option(user_in)

            if proc_in == Options.CLEAR_OUT:
                return Options.CLEAR_OUT
            elif proc_in == Options.EXIT_OUT:
                return proc_in

            result = self.__server.message_server(proc_in)
            return result
        except KeyboardInterrupt:
            self.__server.quit()
            exit(0)

    def __multi_line_input(self) -> str:
        lines = []
        print("\nEnter text (end with '/*-' on its own line):")
        while True:
            line = input()
            if line.strip() == "/*-":
                break
            lines.append(line)
        return "\n".join(lines)

    def run(self, run_type: RUN_TYPE = RUN_TYPE.REPEATED) -> str:

        text: str = ""

        if "input_file" in self.__options:
            text = self.__read_text_file(str(self.__options["input_file"]))
            text = self.__server.message_server(text)
            self.__type_writer_print(text)
            return text

        if run_type is RUN_TYPE.SINGLE:
            text = self.__single_run()
        elif run_type is RUN_TYPE.REPEATED:
            text = self.__continuous_loop()
        elif run_type is RUN_TYPE.INPUT_FILE:
            text = self.__read_text_file("")

        return text

    def __read_text_file(self, path: str) -> str:
        path_validity_results: PATH_VALIDITY = is_valid_path(path)
        interpret_results(path_validity_results)

        with open(path, "r") as file:
            data = file.read()

        return data

    def __parse_option(self, text: str) -> str:

        text = text.lower()

        if text in Options.EXIT_IN:
            self.__server.quit()
            return Options.EXIT_OUT

        if text in Options.CLEAR_IN:
            print("\033[2J\033[H", end="")
            return Options.CLEAR_OUT

        word_list: list[str] = text.split(" ")
        if text == Options.READ_IN:
            file_path_index = word_list.index("/read") + 1
            word_list[file_path_index] = self.__read_text_file(
                word_list[file_path_index]
            )
            word_list.remove("/read")
        return " ".join(word_list)

    def __type_writer_print(self, text: str) -> None:
        print()
        for char in text:
            print(char, end="", flush=True)
            time.sleep(0.00625)
        print()
