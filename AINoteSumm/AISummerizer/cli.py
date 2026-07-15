from argparse import ArgumentParser, Namespace

from config import Config


class ParseOptions:
    def __init__(self, configs: Config) -> None:
        self.__prog_desc: dict[str, str] = configs.program_description
        self.__prog_args: dict[str, dict[str, str]] = configs.program_arguments
        self.__parser: ArgumentParser = self.__create_parser(
            self.__prog_desc["prog_name"],
            self.__prog_desc["description"],
            self.__prog_args,
        )
        self.__parsed_args: Namespace = self.__parse_args(self.__parser)

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

    def __parse_args(self, parser: ArgumentParser) -> Namespace:
        return parser.parse_args()

    @property
    def parsed_args(self) -> Namespace:
        return self.__parsed_args


class CliOptions:
    def __init__(self, options: ParseOptions):
        self.__parsed_args: Namespace = options.parsed_args
        self.__options: dict[str, bool | str] = self.__get_dict_options(self.__parsed_args)

    def __get_dict_options(self, parsed_args: Namespace) -> dict[str, bool | str]:
        result = {}

        for arg_name, arg_val in vars(parsed_args).items():
            if isinstance(arg_val, bool):
                if arg_val:
                    result[arg_name] = arg_val
            elif arg_val is not None:
                result[arg_name] = arg_val

        return result

    @property
    def options(self):
        return self.__options