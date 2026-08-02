import asyncio


class ChatBackend:
    async def send(self, message: str) -> str:
        await asyncio.sleep(0.5)
        return f"You said: {message}"