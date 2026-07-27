from typing import Callable, Dict, List, Optional

from openai import OpenAI


class Agent:
    def __init__(
        self,
        base_url: str,
        api_key: str,
        model: str,
        system_prompt: str = "You are a helpful AI assistant.",
        temperature: float = 0.7,
    ) -> None:
        self.client: OpenAI = OpenAI(api_key=api_key, base_url=base_url)
        self.model: str = model
        self.temperature: float = temperature
        self.messages: List[dict] = [{"role": "system", "content": system_prompt}]
