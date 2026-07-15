class URL:
    def __init__(self, base_URL: str = "localhost", port_num: int = 8080, trailing_URL: str = "/"):
        self.port = port_num
        self.base = base_URL
        self.trailing = trailing_URL

    def __build_url(self) -> str:
        return f"{self.__base}:{self.__port}{self.__trailing}"

    @property
    def base(self) -> str:
        return self.__base

    @base.setter
    def base(self, base_val:str) -> None:
        self.__base = base_val
    
    @property
    def port(self) -> int:
        return self.__port

    @port.setter
    def port(self, port_num:int) -> None:
        if (0 < port_num < 65536):
            self.__port = port_num

    @property
    def trailing(self) -> str:
        return self.__trailing

    @trailing.setter
    def trailing(self, trailing_val:str) -> None:
        self.__trailing = trailing_val

    @property
    def url(self) -> str:
        return self.__build_url()

    def __str__(self) -> str:
        return self.url

if __name__ == "__main__":
    url = URL("127.0.0.1", 8080, "/hello")
    print(url)