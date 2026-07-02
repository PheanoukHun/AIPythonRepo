import json
import os
import pathlib

# Load up Config
try:
    with open(pathlib.Path("config/config.json"), "r") as file:
        data = json.load(file)

    # Create Example Config along with
    # Example Directory and Example System
    # Prompt If config file is not found
except FileNotFoundError:
    data = """
    {
      "encoding_format": "utf-8",
      "URL": {
        "baseURL": "http://127.0.0.1",
        "port": 8080,
        "trailingURL": "/v1/chat/completions",
        "api_key": "NULL"
      },
      "server_start_command": {
        "SYSTEM_PROMPT_FILE": "system-prompt-file.md",
        "model_dir": "model-dir",
        "context_size": 100000,
        "gpu_layers": 99,
        "key_quant": "q4_0",
        "value_quant": "q4_0",
        "agent_mode": true
      }
    }
    """

    # Create an Example System Prompt File
    with open("system-prompt-file.md", "w") as file:
        file.write("You are a Minimal Agent running Llama-Server Backup, Say Hello.")

    try:
        os.mkdir("model-dir")
    except FileExistsError:
        pass
    
    with open(pathlib.Path("config/config.json"), "w") as file:
        file.write(data)
except json.JSONDecodeError:
    print("Non-Valid Config File")