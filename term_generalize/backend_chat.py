from __future__ import annotations

import asyncio
import os
from pathlib import Path
from typing import Final

import httpx
from dotenv import load_dotenv

from agent import Agent

load_dotenv()


class ChatBackend:
    DEFAULT_SYS_PROMPT: Final[str] = (
        "You are a helpful AI Assistant. Use tools whenever appropriate."
    )

    def __init__(self):
        base_url: str = os.getenv("BASE_URL", "http://0.0.0.0:8080/v1")

        self.__agent = Agent(
            model=os.getenv("MODEL", "llama3.2"),
            url=base_url,
            sys_prompt=os.getenv("SYSTEM_PROMPT", self.DEFAULT_SYS_PROMPT),
        )

    # def clear_chat(self) -> None:
    #     self.__chat.handle_command(command="/clear")

    # def set_sys_prompt(self, prompt: str) -> None:
    #     self.__chat.set_sys_prompt(prompt)

    # def get_sys_prompt(self) -> str:
    #     return self.__chat.get_sys_prompt()

    # async def send(self, message: str) -> str:
    #     await asyncio.sleep(0.5)
    #     return self.__chat.chat(message=message)
