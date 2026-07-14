from argparse import ArgumentParser, Namespace

from config import Config


class ParseOptions:
    def __init__(self, configs: Config) -> None:
        prog_desc: dict[str, str] = configs.program_description
        prog_args: dict[str, dict[str, str]] = configs.program_arguments
        self.__args: Namespace = self.__create_parser(
            prog_desc["prog_name"], prog_desc["description"], prog_args
        ).parse_args()

    def __create_parser(
        self, prog_name: str, prog_desc: str, prog_args: dict[str, dict[str, str]]
    ) -> ArgumentParser:

        parser: ArgumentParser = ArgumentParser(prog=prog_name, description=prog_desc)

        for arg_name, arg_info in prog_args.items():
            action = "store_true"
            if arg_name == "--input-file":
                action = "store"

            alt_name = arg_info.get("alt_name")
            name_or_flags = [arg_name]
            if alt_name is not None:
                name_or_flags.append(alt_name)

            parser.add_argument(
                *name_or_flags,
                help=arg_info.get("Description", ""),
                action=action,
            )

        return parser
