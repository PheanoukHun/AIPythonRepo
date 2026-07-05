import json
import os
import pathlib
from url import URL
from message_chat_server import Message

class Config:
    def __init__(self):

        self.__curr_dir = str(pathlib.Path(__file__).resolve().parent)
        self.__config_file_path = str(pathlib.Path(self.__curr_dir, "config.json"))

        self.__data = {}
        self.__health_url = None
        self.__msg_url = None
        self.__message_packet = None
        self.__get_config_settings()
        self.__build_msg_packets()
        self.__sys_prompt:str = self.__get_sys_prompt()

    def __viable_path(self, path: str) -> bool:
        return (
            os.path.exists(path)
            and os.access(path, os.R_OK)
            and os.access(path, os.W_OK)
        )

    def __get_config_settings(self) -> None:

        if self.__viable_path(self.__curr_dir) and self.__viable_path(
            self.__config_file_path
        ):
            with open(self.__config_file_path) as file:
                try:
                    self.__data = json.load(file)
                except json.JSONDecodeError:
                    print("Incorrect Config Json Format")
        else:
            self.__create_default_config_file()

    def __create_default_config_file(self) -> None:

        if not self.__viable_path(self.__config_file_path):
            self.__data = {
                "URL": {
                    "baseURL": "http://127.0.0.1",
                    "port": 8080,
                    "trailingURL": "/v1/chat/completions/",
                    "api_key": "NULL",
                },
                "SYSTEM_PROMPT_FILE_PATH": "/home/procastoh/Git-Repos/AIPythonRepo/AINoteSumm/prompt/system-prompt.md",
                "server_start_command": {
                    "model_dir": "/home/procastoh/llama-cpp/models/llms",
                    "model": "LFM2.5-230M-Q4_0.gguf",
                    "temperature": 0.9,
                    "max_tokens": 7200,
                    "gpu_layers": 99,
                    "key_quant": "q4_0",
                    "value_quant": "q4_0",
                    "agent_mode": True,
                    "stream": False,
                },
            }

            with open(self.__config_file_path, "w") as file:
                json.dump(self.__data, file, indent=4)

    def __generate_default_sys_prompt_file(self, path: str) -> str:

        text = """
        **Prompt for Summarization System:**

        **Title:**
        *"Generate a concise summary of a given text*

        **Objective:**
        Produce a clear, accurate, and well-organized summary of a text that meets the specified criteria.

        **Key Requirements:**
        - Ensure the summary remains relevant and relevant to the original text.
        - Maintain readability and clarity.
        - Avoid excessive verbosity or unnecessary details.
        - Ensure the summary is concise and does not include redundant information.
        - Align the summary with the target audience (e.g., technical, academic, non-technical readers).
        - Provide a minimum of **2–5 sentences** on average for readability.
        - Include a brief introduction to the text, key points, and a conclusion.

        **Example Prompt:**
        > *"Please provide a short summary of a report on climate change impacts, focusing on current trends, key statistics, and recommendations for mitigation strategies."*
        """

        with open(path, "w") as file:
            _ = file.write(text)

        return path

    def __get_sys_prompt(self) -> str:

        prompt_path: str = self.__data["SYSTEM_PROMPT_FILE_PATH"]
        prompt: str = ""

        if self.__viable_path(prompt_path):
            with open(prompt_path, "r") as file:
                prompt = file.read()
        else:
            prompt = self.__generate_default_sys_prompt_file(prompt_path)

        return prompt

    def __build_msg_packets(self) -> None:

        temp_url = self.__data['URL']
        self.__msg_url = URL(temp_url['baseURL'], temp_url['port'], temp_url['trailingURL'])
        self.__health_url = URL(temp_url['baseURL'], temp_url['port'], temp_url['healthTrailing'])

        temp_msg = self.__data["server_start_command"]
        self.__message_packet = Message(temp_msg["model"], temp_msg["max_tokens"], temp_msg["temperature"], temp_msg["stream"])

    @property
    def message_package(self):
        return self.__message_packet

    @property
    def message_url(self) -> str:
        return str(self.__msg_url)

    @def 

    @property
    def system_prompt(self) -> str:
        return self.__sys_prompt

    def __str__(self) -> str:
        return f"""
        Configs:

        Server Health Check URL: {str(self.__health_url)}
        Server Message URL: {str(self.__msg_url)}
        {str(self.message_package)}
        System Prompt: {self.system_prompt}

        Llama-Server Settings if no Server Started:
            Model Dir: {self.__data['server_start_command']['model_dir']}
            Numer of GPU Layers: {self.__data['server_start_command']['gpu_layers']}
            Key Quant: {self.__data['server_start_command']['key_quant']}
            Value Quant: {self.__data['server_start_command']['value_quant']}
            Agent Mode: {self.__data['server_start_command']['agent_mode']}
        
        """

if __name__ == "__main__":
    config = Config()
    print(str(config))