import time
from enum import Enum

from server_message import MessageServer
from valid_path import (
    PATH_RESPONSE_TYPE,
    interpret_results,
    is_valid_path,
)


class RUN_TYPE(Enum):
    SINGLE = 0
    REPEATED = 1
    INPUT_FILE = 2


class Runner:
    
    def __init__(self, server: MessageServer):
        self.__server = server
        self.__term_height: int = 100

    def __continuous_loop(self) -> str:

        outputs = []

        while True:
            result:str = self.__single_run()

            if result == "$$QUIT$$":
                break
            
            self.__type_writer_print(result)
            outputs.append(result)

        return "\n".join(outputs)

    def __single_run(self) -> str:
        try:
            user_in = input("\n> ")
            proc_in = self.__parse_option(user_in)

            if proc_in == "$$CLEAR$$":
                return ""
            elif proc_in == "$$QUIT$$":
                return proc_in

            result = self.__server.message_server(proc_in)
            return result
        except KeyboardInterrupt:
            self.__server.quit()
            exit(0)

    def run(self, run_type: RUN_TYPE = RUN_TYPE.REPEATED) -> str:

        text:str = ""

        if run_type is RUN_TYPE.SINGLE:
            text = self.__single_run()
        elif run_type is RUN_TYPE.REPEATED:
            text = self.__continuous_loop()
        elif run_type is RUN_TYPE.INPUT_FILE:
            text = self.__read_text_file("")

        return text

    def __read_text_file(self, path: str) -> str:
        path_validity_results: PATH_RESPONSE_TYPE = is_valid_path(path)
        interpret_results(path, path_validity_results)

        with open(path, "r") as file:
            data = file.read()

        return data

    def __parse_option(self, text: str) -> str:

        if text == "/quit" or text == "/exit":
            self.__server.quit()
            return "$$QUIT$$"

        if text == "/clear":
            print("\033[2J\033[H", end="")
            return "$$CLEAR$$"

        word_list: list[str] = text.split(" ")
        if "/read" in word_list:
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
