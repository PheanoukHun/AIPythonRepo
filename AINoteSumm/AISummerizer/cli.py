from argparse import ArgumentParser, Namespace

from config import Config


class ParseOptions:
    def __init__(self, configs: Config) -> None:
        __prog_desc: dict[str, str] = configs.program_description
        __prog_args: dict[str, dict[str, str]] = configs.program_arguments
        __parser: ArgumentParser = self.__create_parser(
            __prog_desc["prog_name"], __prog_desc["description"], __prog_args
        )

    def __create_parser(
        self, prog_name: str, prog_desc: str, prog_args: dict[str, dict[str, str]]
    ) -> ArgumentParser:

        parser: ArgumentParser = ArgumentParser(prog=prog_name, description=prog_desc)

        for arg_name, arg_info in prog_args.items():

            action:str = arg_info.get("action", "store_true")
            alt_name:str = arg_info.get("alt_name", "")
            
            name_or_flags = [arg_name]
            if alt_name != "":
                name_or_flags.append(alt_name)

            parser.add_argument(
                *name_or_flags,
                help=arg_info.get("Description", ""),
                action=action,
            )

        return parser
