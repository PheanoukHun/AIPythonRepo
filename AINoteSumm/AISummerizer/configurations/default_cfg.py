import json

from typing import Any

from utils.valid_path import PATH_VALIDITY, is_valid_path, interpret_results


def write_to_file(cfg_path:str, cfg: dict[Any, Any]):

    is_valid: PATH_VALIDITY = is_valid_path(cfg_path)
    
    if is_valid is PATH_VALIDITY.DNE:
        with open(cfg_path, "w") as f:
            json.dump(cfg, f, indent=4)
    else:
        interpret_results(is_valid)


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

    write_to_file(cfg_path, cfg)

    return cfg


def default_args_cfg(cfg_path:str) -> dict[str, dict[str, str]]:
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

    write_to_file(cfg_path, cfg)
    
    return cfg

def default_server_cfg(cfg_path:str) -> dict[str, str|dict[str, str|int]]:
    cfg = {
      "urls": {
        "baseURL": "http://127.0.0.1",
        "port": 8080,
        "trailingURL": "/v1/chat/completions",
        "healthTrailing": "/health",
        "expectedHealthyStatusCode": 200
      },
      "SYSTEM_PROMPT_FILE_PATH": "/home/procastoh/Git-Repos/AIPythonRepo/AINoteSumm/prompt/system-prompt.md",
      "options": [
        "/home/procastoh/llama-cpp/build/bin/llama-server",
        "-m /home/procastoh/llama-cpp/models/llms/LFM2.5-230M-Q4_0.gguf",
        "-c 100000 -ngl 99",
        "-ctk q4_0 -ctv q4_0 -fa on",
        "--agent -rea off",
        "--port 8080"
      ]
    }

    write_to_file(cfg_path, cfg)

    return cfg

def default_msg_pkg(cfg_path:str) -> dict[str, str|float|bool|int]:
    cfg = {
      "model": "LFM2.5-230M-Q4_0",
      "temperature": 0.9,
      "max_tokens": 7200,
      "stream": False
    }

    write_to_file(cfg_path, cfg)

    return cfg