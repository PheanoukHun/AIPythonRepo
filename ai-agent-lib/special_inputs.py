from enum import Enum


class SPEC_COMS(Enum):
    CLEAR = "/clear"
    CLEAR_SCREEN = "/clear-screen"
    EXIT = "/exit"
    READ = "/read"
    TOOL = "/tool"
    ERROR = "$$ERROR$$"

    @classmethod
    def _missing_(cls, value: object):
        """Resolve aliases so SPECIAL_IN.EXIT also matches "/quit"."""
        if value in ("/exit", "/quit"):
            return cls.EXIT
        elif value in ("/clear", "/clr"):
            return cls.CLEAR
        return None
