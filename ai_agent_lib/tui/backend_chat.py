import asyncio
import os
import sys
from pathlib import Path

from dotenv import load_dotenv

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

from ai_agent.simple_chat import AIChat

load_dotenv()


class ChatBackend:
    def __init__(self):
        self.__chat: AIChat = AIChat(
            model=os.getenv("MODEL", "llama3.2"),
            url=os.getenv("BASE_URL", "http://localhost:8080/v1"),
        )

    async def send(self, message: str) -> str:
        await asyncio.sleep(0.5)
        return f" AI: {self.__chat.chat(message=message)}"
