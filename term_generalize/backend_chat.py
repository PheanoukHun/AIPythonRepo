from __future__ import annotations

import asyncio
import os
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
        base_url: str = os.getenv("BASE_URL", "http://0.0.0.0:8081")
        api_key: str = os.getenv("API_KEY", "NONE")

        # Check if the server is up
        health_checker = httpx.Client(
            base_url=f"{base_url}/health",
            headers={
                "Authorization": f"Bearer: {api_key}"
            }
        )
        
        self.__agent = Agent(
            model=os.getenv("MODEL", "llama3.2"),
            url=base_url,
            sys_prompt=os.getenv("SYSTEM_PROMPT", self.DEFAULT_SYS_PROMPT),
            api_key=api_key
        )

    def clear_chat(self) -> None:
        self.__agent.clear()

    def set_sys_prompt(self, prompt: str) -> None:
        self.__agent.set_sys_prompt(prompt)

    def get_sys_prompt(self) -> str:
        return self.__agent.get_sys_prompt()

    async def send(self, prompt: str) -> str:
        await asyncio.sleep(0.5)
        return self.__agent.chat(prompt=prompt)
