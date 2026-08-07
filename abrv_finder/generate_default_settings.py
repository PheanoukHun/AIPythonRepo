def generate_default_srv_command():
    srv_command:dict = {
        "FAVORITE": "llama.cpp",
        "llama.cpp": [
            "llama-server",
            "-m",
            ""
        ]
    }