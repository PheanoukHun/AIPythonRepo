import json
import pathlib

try:
    with open(pathlib.Path("config/config.json")) as file:
        data = json.load(file)
except:
    