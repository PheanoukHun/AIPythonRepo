import json
import os
from typing import cast

import default_cfg
from server_interacting.message_block import MessageBlock
from server_interacting.url import URL

from .valid_path import (
    PATH_VALIDITY,
    get_project_path,
    interpret_results,
    is_valid_path,
)


class Config:
    def __init__(self):

        # Check to see if Directories is readable and writable
        self.__curr_dir: str = get_project_path()
        self.__config_file_path: str = os.path.join(self.__curr_dir, "config.json")

        # Getting Configurations
        self.__cfg_file_paths: dict[str, str] = self.__get_cfg_file_paths()

        config_dir:str = self.__cfg_file_paths["CFG_DIR"]

        srv_cfg_path = os.path.join(config_dir, self.__cfg_file_paths["SRV_CFG_PATH"])
        self.__build_srv_cfg(srv_cfg_path)

    def __get_cfg_file_paths(self) -> dict[str, str]:

        main_cfg: dict[str, str | dict[str, str]] = {}

        config_path_validity = is_valid_path(self.__config_file_path)
        if config_path_validity == PATH_VALIDITY.VALID:
            with open(self.__config_file_path) as file:
                try:
                    main_cfg = json.load(file)
                except json.JSONDecodeError:
                    print("Incorrect Config Json Format")
        else:
            main_cfg = default_cfg.default_main_cfg(self.__config_file_path)

        self.__prog_name = main_cfg.get("PROGRAM_NAME")
        self.__VERSION = main_cfg.get("VERSION")
        self.__prog_desc = main_cfg.get("PROGRAM_DESCRIPTION")

        cfg_file_paths: dict[str, str] = cast(dict[str, str], main_cfg.get("CFG_FILES"))

        return cfg_file_paths

    def __build_srv_cfg(self, srv_cfg_path:str):
        pass

    def __cfg_urls(self, url_info: dict[str, str | int]) -> None:
        self.__msg_url = URL(
            base_URL=cast(str, url_info.get("baseURL")),
            port_num=cast(int, url_info.get("port")),
            trailing_URL=cast(str, url_info["trailingURL"]),
        )

        self.__health_url = URL(
            base_URL=cast(str, url_info["baseURL"]),
            port_num=cast(int, url_info["port"]),
            trailing_URL=cast(str, url_info["healthTraling"]),
        )

    def __get_sys_prompt(self, prompt_path: str) -> str:

        prompt: str = ""

        path_valid_result: PATH_VALIDITY = is_valid_path(prompt_path)
        if path_valid_result == PATH_VALIDITY.VALID:
            with open(prompt_path, "r") as file:
                prompt = file.read()
        elif path_valid_result == PATH_VALIDITY.DNE:
            prompt = default_cfg.default_sys_prompt(prompt_path)
        else:
            interpret_results(path_valid_result)

        return prompt

    def __build_msg_packets(self) -> None:

        temp_url = self.__data["URL"]
        self.__msg_url = URL(
            temp_url["baseURL"], temp_url["port"], temp_url["trailingURL"]
        )
        self.__health_url = URL(
            temp_url["baseURL"], temp_url["port"], temp_url["healthTrailing"]
        )

        temp_msg = self.__data["message_pkg"]
        self.__message_packet = MessageBlock(
            temp_msg["model"],
            temp_msg["max_tokens"],
            temp_msg["temperature"],
            temp_msg["stream"],
            sys_prompt=self.__get_sys_prompt(),
        )

    @property
    def message_package(self) -> MessageBlock:
        return self.__message_packet

    @property
    def message_url(self) -> str:
        return str(self.__msg_url)

    @property
    def health_url(self) -> str:
        return str(self.__health_url)

    @property
    def system_prompt(self) -> str:
        return self.__sys_prompt

    @property
    def server_options(self):
        return self.__data["server_cmd"]["options"]

    @property
    def program_description(self) -> dict[str, str]:
        return self.__data["PROGAM_DESCRIPTION"]

    @property
    def program_arguments(self) -> dict[str, dict[str, str]]:
        return self.__data["PROGRAM_ARGS"]

    def __str__(self) -> str:

        results = f"""
        Configs:

        Server Health Check URL: {str(self.__health_url)}
        Server Message URL: {str(self.__msg_url)}
        {str(self.message_package)}

        Server Start Commands Options Given:
        """

        counter = 1
        for option in self.__data["server_cmd"]["options"]:
            results = f"{results}\n\t{counter}. `{option}`"
            counter += 1
        return results


if __name__ == "__main__":
    config = Config()
    print(str(config))
