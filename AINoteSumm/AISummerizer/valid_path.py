import os
from enum import Enum
from pathlib import Path


class PATH_RESPONSE_TYPE(Enum):
    VALID = 0
    PATH_DOES_NOT_EXIST = 1
    PATH_NOT_READABLE = 2
    PATH_NOT_WRITABLE = 3


def is_valid_path(path) -> PATH_RESPONSE_TYPE:

    if not os.path.exists(path):
        return PATH_RESPONSE_TYPE.PATH_DOES_NOT_EXIST
    elif not os.access(path, os.R_OK):
        return PATH_RESPONSE_TYPE.PATH_NOT_READABLE
    elif not os.access(path, os.W_OK):
        return PATH_RESPONSE_TYPE.PATH_NOT_WRITABLE

    return PATH_RESPONSE_TYPE.VALID


def interpret_results(error_result: PATH_RESPONSE_TYPE) -> None:

    if error_result is PATH_RESPONSE_TYPE.PATH_DOES_NOT_EXIST:
        raise FileNotFoundError()
    elif error_result is PATH_RESPONSE_TYPE.PATH_NOT_READABLE:
        raise PermissionError("Cannot Read From File")
    elif error_result is PATH_RESPONSE_TYPE.PATH_NOT_WRITABLE:
        raise PermissionError("Cannot Write to Directory")


def get_project_path() -> str:
    proj_dir: str = str(Path(__file__).resolve().parent.parent)
    interpret_results(is_valid_path(proj_dir))
    return proj_dir


if __name__ == "__main__":
    curr_file_path: str = str(Path(__file__))
    valdity_results: PATH_RESPONSE_TYPE = is_valid_path(curr_file_path)
