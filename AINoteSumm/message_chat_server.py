import requests


class MessageBlock:
    def __init__(
        self,
        model: str,
        max_tokens: int,
        temperature: float,
        stream: bool = False,
        message: str = "",
    ):
        self.__model: str = model
        self.__message: str = message
        self.__max_token: int = max_tokens
        self.__temperature: float = temperature
        self.__stream: bool = stream

    def send_msg_server(self, url: str, msg: str) -> str:
        self.message = msg

        response = requests.get(url, json=self.message_block)
        response.raise_for_status()

        data = response.json()
        return data["choices"][0]["message"]["content"]

    @property
    def message_block(
        self,
    ) -> dict[str, str | int | bool | float | list[dict[str, str]]]:
        return {
            "model": self.__model,
            "messages": [{"role": "user", "content": self.__message}],
            "temperature": self.__temperature,
            "max_tokens": self.__max_token,
            "stream": self.__stream,
        }

    @property
    def message(self) -> str:
        return self.__message

    @message.setter
    def message(self, new_message: str):
        self.__message = new_message

    def __str__(self):
        return f"""
        Message Package:
            Model: {self.__model}
            Messages:
                Role: User
                Content: {self.__message}
            Max_Tokens: {self.__max_token}
            stream: {self.__stream}
        """
