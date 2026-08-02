from curses import ERR
from enum import Enum


class SPEC_COMS(Enum):
    CLEAR = "/clear"
    CLEAR_SCREEN = "/clear-screen"
    EXIT = "/exit"
    TOOL = "/tool"
    ERROR = "$$ERROR$$"
    SKIP = "$$SKIP$$"

    @classmethod
    def _missing_(cls, value: object):
        """Resolve aliases so SPECIAL_IN.EXIT also matches "/quit"."""
        if value in ("/exit", "/quit"):
            return cls.EXIT
        elif value in ("/clear", "/clr"):
            return cls.CLEAR
        return None


def list_usr_enums() -> list[str]:
    return [
        option.value
        for option in SPEC_COMS
        if not (option is SPEC_COMS.ERROR or option is SPEC_COMS.SKIP)
    ]
