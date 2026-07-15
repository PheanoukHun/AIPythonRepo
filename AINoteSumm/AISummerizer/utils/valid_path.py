import os
from enum import Enum
from pathlib import Path


class PATH_VALIDITY(Enum):
    VALID = 0
    DNE = 1
    NOT_READABLE = 2
    NOT_WRITABLE = 3


def is_valid_path(path) -> PATH_VALIDITY:

    if not os.path.exists(path):
        return PATH_VALIDITY.DNE
    elif not os.access(path, os.R_OK):
        return PATH_VALIDITY.NOT_READABLE
    elif not os.access(path, os.W_OK):
        return PATH_VALIDITY.NOT_WRITABLE

    return PATH_VALIDITY.VALID


def interpret_results(error_result: PATH_VALIDITY) -> None:

    if error_result is PATH_VALIDITY.DNE:
        raise FileNotFoundError()
    elif error_result is PATH_VALIDITY.NOT_READABLE:
        raise PermissionError("Cannot Read From File")
    elif error_result is PATH_VALIDITY.NOT_WRITABLE:
        raise PermissionError("Cannot Write to Directory")


def get_project_path() -> str:
    proj_dir: str = str(Path(__file__).resolve().parent.parent.parent)
    interpret_results(is_valid_path(proj_dir))
    return proj_dir


if __name__ == "__main__":
    curr_file_path: str = str(Path(__file__))
    valdity_results: PATH_VALIDITY = is_valid_path(curr_file_path)
