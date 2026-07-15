import json

from typing import Any

from valid_path import PATH_VALIDITY, is_valid_path


def write_to_file(cfg_path:str, cfg: dict[Any, Any]):
    if is_valid_path(cfg_path) is PATH_VALIDITY.DNE:
        with open(cfg_path, "w") as f:
            


def default_main_cfg(cfg_path: str) -> dict[str, dict[str, str] | str]:
    cfg = {
        "PROGRAM_NAME": "AISummerizer",
        "VERSION": "v1.0",
        "PROGRAM_DESCRIPTION": "A Simple AI Summerizer that takes in text and uses a System Prompt to summerize the text based on your preferences.",
        "CFG_FILES": {
            "CFG_DIR": "/home/procastoh/Git-Repos/AIPythonRepo/AINoteSumm/configs",
            "ARGS_CFG_PATH": "args.json",
            "SRV_CFG_PATH": "server_cfg.json",
            "SRV_CMD_PATH": "server_cmd.json",
        },
    }

    if is_valid_path is PATH_VALIDITY.DNE:
        with open(cfg_path, "w") as file:
            json.dump(cfg, file, indent=4)

    return cfg


def default_args_cfg(cfg_path) -> dict[str, dict[str, str]]:
    cfg = {
      "--input-file": {
        "Description": "Allows for the Direct Input of Text without the need of users adding to it.",
        "alt_name": "-in",
        "action": "store"
      },
      "--no-reasoning": {
        "Description": "Prevents the Model from Reasoing",
        "alt_name": "--no-rea",
        "action": "store_true"
      },
      "--reasoning": {
        "Description": "Allows the Model to Reason (Default)",
        "alt_name": "--rea",
        "action": "store_true"
      },
      "--multi-line-text": {
        "Description": "Allow the user to type in multi-line results, must end wit '/*-' to end the response",
        "alt_name": "-mlt",
        "action": "store_true"
      },
      "--single-response": {
        "Description": "Allow the user to specify to only run the program once and stops",
        "alt_name": "-sr",
        "action": "store_true"
      }
    }
    
    return cfg