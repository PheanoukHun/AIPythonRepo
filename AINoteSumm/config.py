import json
import pathlib

try:
    with open(pathlib.Path("config/config.json"), "r") as file:
        data = json.load(file)
except FileNotFoundError:
    default_config = """
    {
      "URL": {
        "baseURL": "http://127.0.0.1",
        "port": 8080,
        "trailingURL": "/v1/chat/completions"
      },

      "SYSTEM_PROMPT_FILE": "/home/procastoh/Git-Repos/launch-llama/optional/prompts/obisidian-summary.md",

      "encoding": "utf-8",

      "model" : {
        "model_dir": "/home/procastoh/Git-Repos/launch-llama/optional/LLM-Models/LLM/"
      }
    }
    """
    with open(pathlib.Path("config/config.json"), "w") as file:
        file.write(default_config)
except json.JSONDecodeError:
    print("Non-Valid Config File")