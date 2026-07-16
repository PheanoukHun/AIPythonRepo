import json
import os
from typing import cast

from typing_extensions import Any, Callable

from .default_cfg import (
    default_args_cfg,
    default_main_cfg,
    default_msg_pkg,
    default_srv_cfg,
    default_sys_prompt
)
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
        self.__setup_srv_cfg(srv_cfg_path)

        msg_pkg_cfg_path = os.path.join(config_dir, self.__cfg_file_paths["MSG_PKG_PATH"])
        self.__cfg_msg_pkg(msg_pkg_cfg_path)

        args_cfg_path = os.path.join(config_dir, self.__cfg_file_paths["ARGS_CFG_PATH"])
        self.__setup_args_cfg(args_cfg_path)

    def __cfg_urls(self, url_info: dict[str, str | int]) -> None:
        self.__msg_url = URL(
            base_URL=cast(str, url_info.get("baseURL")),
            port_num=cast(int, url_info.get("port")),
            trailing_URL=cast(str, url_info["trailingURL"]),
        )

        self.__health_url = URL(
            base_URL=cast(str, url_info["baseURL"]),
            port_num=cast(int, url_info["port"]),
            trailing_URL=cast(str, url_info["healthTrailing"]),
        )

    def __get_sys_prompt(self, prompt_path: str) -> str:
        prompt:str = self.__get_cfg(prompt_path, default_sys_prompt)
        return prompt

    def __get_cfg(self, cfg_path:str, default_prog: Callable[[str], Any]) -> Any:
        path_valid_res:PATH_VALIDITY = is_valid_path(cfg_path)
        if path_valid_res is PATH_VALIDITY.VALID:
            with open(cfg_path, "r") as file:
                if ".json" in cfg_path:
                    data = json.load(file)
                else:
                    data = file.read()
        elif path_valid_res is PATH_VALIDITY.DNE:
            data = default_prog(cfg_path)
        else:
            interpret_results(path_valid_res)
            exit(0)
        return data

    def __get_cfg_file_paths(self) -> dict[str, str]:

        main_cfg = self.__get_cfg(self.__config_file_path, default_main_cfg)

        self.__prog_name:str = str(main_cfg.get("PROGRAM_NAME"))
        self.__version:str = str(main_cfg.get("VERSION"))
        self.__prog_desc:str = str(main_cfg.get("PROGRAM_DESCRIPTION"))

        cfg_file_paths: dict[str, str] = cast(dict[str, str], main_cfg.get("CFG_FILES"))

        return cfg_file_paths

    def __setup_srv_cfg(self, srv_cfg_path:str) -> None:
        data = self.__get_cfg(srv_cfg_path, default_srv_cfg)
        self.__cfg_urls(cast(dict[str, str|int], data.get("urls")))
        self.__sys_prompt:str = self.__get_sys_prompt(cast(str, data.get("SYSTEM_PROMPT_FILE_PATH")))
        self.__server_cmd_components:list[str] = cast(list[str], data.get("options"))

    def __cfg_msg_pkg(self, msg_pkg_cfg_path:str) -> None:
        data = self.__get_cfg(msg_pkg_cfg_path, default_msg_pkg)
        self.__message_packet:MessageBlock = MessageBlock(
            model=str(data["model"]),
            temperature=float(data["temperature"]),
            max_tokens=int(data["max_tokens"]),
            stream=bool(data["stream"])
        )

    def __setup_args_cfg(self, args_cfg_path:str) -> None:
        self.__cli_args = self.__get_cfg(args_cfg_path, default_args_cfg)
 
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
    def server_options(self) -> list[str]:
        return self.__server_cmd_components

    @property
    def program_name(self) -> str:
        return self.__prog_name

    @property
    def program_version(self) -> str:
        return self.__version

    @property
    def program_description(self) -> str:
        return self.__prog_desc

    @property
    def program_arguments(self) -> dict[str, dict[str, str]]:
        return self.__cli_args

    def __str__(self) -> str:
        results = ""
        return results


if __name__ == "__main__":
    config = Config()
    print(str(config))
