from enum import Enum


class SPECIAL_IN(Enum):
    EXIT = "/exit"
    CLEAR = "/clear"
    TOOL = "/tool"
    READ = "/read"

    @classmethod
    def _missing_(cls, value: object):
        """Resolve aliases so SPECIAL_IN.EXIT also matches "/quit"."""
        if value in ("/exit", "/quit"):
            return cls.EXIT
        elif value in ("/clear", "/clr"):
            return cls.CLEAR
        return None
