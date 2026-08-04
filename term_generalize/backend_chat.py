import asyncio
import os
import httpx
from pathlib import Path

from dotenv import load_dotenv
from agent import AIChat

load_dotenv()



class ChatBackend:
    def __init__(self):
        base_url:str = os.getenv("BASE_URL", "http://0.0.0.0:8080/v1")
        

    
        
    # def clear_chat(self) -> None:
    #     self.__chat.handle_command(command="/clear")

    # def set_sys_prompt(self, prompt: str) -> None:
    #     self.__chat.set_sys_prompt(prompt)

    # def get_sys_prompt(self) -> str:
    #     return self.__chat.get_sys_prompt()

    # async def send(self, message: str) -> str:
    #     await asyncio.sleep(0.5)
    #     return self.__chat.chat(message=message)
