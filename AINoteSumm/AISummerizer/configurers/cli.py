from argparse import ArgumentParser, Namespace
from .config import Config

class CLI_Options:
    def __init__(self, configs: Config):
        self.__parser = self.__create_parser(
            configs.program_name, configs.program_description, configs.program_arguments
        )
        self.__parsed_args: Namespace = self.__parser.parse_args()
        self.__options: dict[str, bool | str] = self.__get_dict_options(
            self.__parsed_args
        )

    def __create_parser(
        self, prog_name: str, prog_desc: str, prog_args: dict[str, dict[str, str]]
    ) -> ArgumentParser:

        parser: ArgumentParser = ArgumentParser(prog=prog_name, description=prog_desc)

        for arg_name, arg_info in prog_args.items():
            action: str = arg_info.get("action", "store_true")
            alt_name: str = arg_info.get("alt_name", "")

            name_or_flags = [arg_name]
            if alt_name != "":
                name_or_flags.append(alt_name)

            parser.add_argument(
                *name_or_flags,
                help=arg_info.get("Description", ""),
                action=action,
            )

        return parser

    def __get_dict_options(
        self, parsed_args: Namespace
    ) -> dict[str, bool | str]:
        return {
            key: value
            for key, value in vars(parsed_args).items()
            if value not in (None, False)
        }

    @property
    def options(self):
        return self.__options
