from .message_block import MessageBlock
from configurers.config import Config
import requests
import subprocess

class MessageServer:
    def __init__(self, configs:Config):

        self.__message_block:MessageBlock = configs.message_package
        self.__health_url:str = str(configs.health_url)
        self.__dst_url:str = str(configs.message_url)
        self.__server = self.__check_server_health(configs)

    def __check_server_health(self, configs:Config):

        results = None
        
        try:
            _ = requests.get(str(self.__health_url))
        except requests.exceptions.ConnectionError:
            results = subprocess.Popen(self.__build_srvr_cmd(configs), stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL)

        return results

    def __build_srvr_cmd(self, configs:Config) -> list[str]:

        return configs.server_options

    def message_server(self, message:str) -> str:
        self.__message_block.message = message

        response = requests.post(self.__dst_url,
            json=self.__message_block.message_block)

        response.raise_for_status()

        data = response.json()
        return data["choices"][0]["message"]["content"]

    def quit(self):
        if (self.__server):
            print("\nStopping the Server Binary\n")
            self.__server.kill()
        print("\033[2J\033[H", end="")