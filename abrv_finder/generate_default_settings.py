import json


def generate_default_srv_command() -> str:
    srv_command: dict = {
        "FAVORITE": "llama.cpp",
        "llama.cpp": ["llama-server", "-m", "example.gguf"],
        "LM Studio": [],
        "Ollama": [],
        "vLLM": [],
    }

    with open("service_command_cfg.json", "w") as file:
        json.dump(srv_command, file)

    return "service_command_cfg.json"

def generate_env() -> None:
    pass