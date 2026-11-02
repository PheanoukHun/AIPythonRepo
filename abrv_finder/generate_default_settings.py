import json
from pathlib import Path


def generate_default_srv_command(server_cmd_path:str):
    srv_command: dict = {
        "FAVORITE": "llama.cpp",
        "llama.cpp": ["llama-server", "-m", "example.gguf"],
        "LM Studio": [],
        "Ollama": [],
        "vLLM": [],
    }

    print(f"\nGenerated JSON Server File Location at: {server_cmd_path}")
    print("Please Change the File Based on your specification.\n")

    with open(server_cmd_path, "w") as file:
        json.dump(srv_command, file)


def generate_env(ENV_PATH: Path) -> None:
    print(f"\nGenerated a .env file at {ENV_PATH}")
    ENV_PATH.write_text(
        "MODEL=GPT5-Nano"
        "BASE_URL=https://api.openai.com/"
        "API=YOUR-API-KEY-HERE"
        "SYSTEM_PROMPT=You are a helpful AI Assistant. Use tools whenever appropriate."
        "FIELD=Computer Science"
        "COMMAND_FILE_LOCATION=example-cfg.json"
    )
