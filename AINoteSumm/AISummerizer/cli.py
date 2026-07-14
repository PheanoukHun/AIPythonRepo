from argparse import ArgumentParser

from config import Config


class ParseOptions:
    def __init__(self, configs: Config) -> None:
        prog_desc: dict[str, str] = configs.program_description
        prog_args: dict[str, dict[str, str]] = configs.program_arguments
        self.__parse: ArgumentParser = self.__create_parser(
            prog_desc["prog_name"], prog_desc["description"], prog_args
        )

    def __create_parser(
        self, prog_name: str, prog_desc: str, prog_args: dict[str, dict[str, str]]
    ) -> ArgumentParser:

        parser: ArgumentParser = ArgumentParser(prog=prog_name, description=prog_desc)

        parser.add_argument()

        return parser
