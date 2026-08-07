from __future__ import annotations

import asyncio
import json
import os
import subprocess
from typing import Final, cast

from dotenv import load_dotenv

from agent import Agent
from detect import BackendError, DetectedServer, detect_backend

load_dotenv()


class ChatBackend:
    DEFAULT_SYS_PROMPT: Final[str] = (
        "You are a helpful AI Assistant. Use tools whenever appropriate."
    )

    def __init__(self):
        base_url: str = os.getenv("BASE_URL", "http://0.0.0.0:8080")
        api_key: str | None = os.getenv("API_KEY")

        try:
            self.__server: DetectedServer = detect_backend(
                base_url=base_url, api_key=api_key
            )
        except BackendError:
            file_location: str | None = os.getenv("COMMAND_FILE_LOCATION")
            if file_location:
                with open(file_location) as file:
                    comm_lists:dict = json.load(file, json=4)
                    prefered_provider:str = str(comm_lists.get("FAVORITE"))
                    command:list = cast(list, comm_lists.get(prefered_provider))
            else:
                pass
            _ = subprocess.Popen(command)

        self.__agent = Agent(
            model=os.getenv("MODEL", "llama3.2"),
            url=self.__server.openai_base,
            sys_prompt=os.getenv("SYSTEM_PROMPT", self.DEFAULT_SYS_PROMPT),
            api_key=api_key,
        )

    @property
    def server(self) -> DetectedServer:
        return self.__server

    @property
    def backend_label(self) -> str:
        label: str = self.__server.service.value
        if self.__server.model_ids:
            label += f" ({self.__server.model_ids[0]})"
        return f"{label} @ {self.__server.openai_base}"

    def clear_chat(self) -> None:
        self.__agent.clear()

    def set_sys_prompt(self, prompt: str) -> None:
        self.__agent.set_sys_prompt(prompt)

    def get_sys_prompt(self) -> str:
        return self.__agent.get_sys_prompt()

    async def send(self, prompt: str) -> str:
        await asyncio.sleep(0.5)
        return self.__agent.chat(prompt=prompt)
