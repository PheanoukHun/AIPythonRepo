class MessageBlock:
    def __init__(
        self,
        model: str = "ACME.gguf",
        max_tokens: int = 9999,
        temperature: float = 1.0,
        stream: bool = False,
        message: str = "",
        sys_prompt: str = ""
    ):
        self.__model: str = model
        self.__message: str = message
        self.__max_token: int = max_tokens
        self.__temperature: float = temperature
        self.__stream: bool = stream
        self.__sys_prompt: str = sys_prompt

    @property
    def message_block(
        self,
    ) -> dict[str, str | int | bool | float | list[dict[str, str]]]:
        return {
            "model": self.__model,
            "messages": [
                {"role": "system", "content": self.__sys_prompt},
                {"role": "user", "content": self.__message}
            ],
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

if __name__ == "__main__":
    message_block = MessageBlock()
    print(message_block)