import os
from pathlib import Path
from enum import Enum

class PATH_RESPONSE_TYPE(Enum):

    VALID = 0
    PATH_DOES_NOT_EXIST = 1
    PATH_NOT_READABLE = 2
    PATH_NOT_WRITABLE = 3
    UNKNOWN = 4

def is_valid_path(path) -> PATH_RESPONSE_TYPE:

    if (not os.path.exists(path)):
        return PATH_RESPONSE_TYPE.PATH_DOES_NOT_EXIST
    elif (not os.access(path, os.R_OK)):
        return PATH_RESPONSE_TYPE.PATH_NOT_READABLE
    elif (not os.access(path, os.W_OK)):
        return PATH_RESPONSE_TYPE.PATH_NOT_WRITABLE
    else:
        return PATH_RESPONSE_TYPE.VALID

def interpret_results(path:str, error_result: PATH_RESPONSE_TYPE) -> None:

    if (error_result is PATH_RESPONSE_TYPE.VALID):
        return
    elif (error_result is PATH_RESPONSE_TYPE.PATH_DOES_NOT_EXIST):
        reason = f"{path} does not exist"
    elif (error_result is PATH_RESPONSE_TYPE.PATH_NOT_READABLE):
        reason = f"{path} is not readable"
    elif (error_result is PATH_RESPONSE_TYPE.PATH_NOT_WRITABLE):
        reason = f"{path} is not writable"
    else:
        reason = f"{path} has an unknown error"

    raise FileExistsError(reason)

if __name__ == "__main__":
    curr_file_path:str = str(Path(__file__))
    valdity_results:PATH_RESPONSE_TYPE = is_valid_path(curr_file_path)