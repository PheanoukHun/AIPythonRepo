import os
from server_message import MessageServer
from valid_path import PATH_RESPONSE_TYPE, is_valid_path, interpret_results
import time
from enum import Enum

class RUN_TYPE(Enum):
    SINGLE = 0
    REPEATED = 1

class Runner:
    def __init__(self, server:MessageServer):
        self.__server = server
        self.__term_height: int = 100

    def __continuous_loop(self) -> None:
        while True:
            self.__type_writer_print(self.__single_run())
        
    def __single_run(self) -> str:
        try:
            user_in = input("\n> ")
            proc_in = self.__parse_option(user_in)
    
            if (proc_in == "$$CLEAR$$"):
                return ""
    
            result = self.__server.message_server(proc_in)
            return result
        except KeyboardInterrupt:
            self.__server.quit()
            return ""

    def run(self, run_type: RUN_TYPE = RUN_TYPE.REPEATED) -> None:
        if run_type is RUN_TYPE.SINGLE:
            self.__single_run()
        elif run_type is RUN_TYPE.REPEATED:
            self.__continuous_loop()
        
    def __read_text_file(self, path:str) -> str:
        path_validity_results:PATH_RESPONSE_TYPE = is_valid_path(path)
        interpret_results(path, path_validity_results)

        with open(path, "r") as file:
            data = file.read()

        return data

    def __parse_option(self, text:str) -> str:

        if text == "/quit" or text == "/exit":
            self.__server.quit()
            os._exit(0)

        if text == "/clear":
            for _ in range(int(self.__term_height * 1.5)):
                print()
            return "$$CLEAR$$"

        word_list:list[str] = text.split(" ")
        if ("/read" in word_list):
            file_path_index = word_list.index("/read") + 1
            word_list[file_path_index] = self.__read_text_file(word_list[file_path_index])
            word_list.remove("/read")
        return " ".join(word_list)

    def __type_writer_print(self, text:str) -> None:
        print()
        for char in text:
            print(char, end = "", flush=True)
            time.sleep(0.00625)
        print()