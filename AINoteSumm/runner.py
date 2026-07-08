import os
from server_message import MessageServer
from valid_path import PATH_RESPONSE_TYPE, is_valid_path, interpret_results

class Runner:
    def __init__(self, server:MessageServer):
        self.__server = server

    def main_loop(self):
        try:
            while True:
                usr_in = input("> ")
                proc_in = self.__parse_option(usr_in)
                self.__server.message_server(proc_in)
        except KeyboardInterrupt:
            self.__server.quit()

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

        word_list:list[str] = " ".split(text)
        if ("/read" in word_list):
            file_path_index = word_list.index("/read") + 1
            word_list[file_path_index] = self.__read_text_file(word_list[file_path_index])
            word_list.remove("/read")
        return " ".join(word_list)
