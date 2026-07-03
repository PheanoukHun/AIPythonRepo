from ctypes import FormatError
import json
import os
import pathlib

class ConfigVal:
    def __init__ (self):
        self.get_config_settings()

    def get_config_settings(self):
        # Load up Config
        try:
            with open(pathlib.Path("config/config.json"), "r") as file:
                data = json.load(file)

            # Create Example Config along with
            # Example Directory and Example System
            # Prompt If config file is not found
        except FileNotFoundError:
            data = ""

            with open(pathlib.Path("config/config.json"), "w") as file:
                file.write(data)

        except json.JSONDecodeError:
            print("Incorrect JSON Format")
            exit(1)

    def create_default_config_file()