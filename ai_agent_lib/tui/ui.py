import asyncio

from chat import ChatBackend
from textual.app import App, ComposeResult
from textual.containers import Container, Horizontal
from textual.widgets import Footer, Header, Input, RichLog


class ChatApp(App):
    CSS = """
    Screen {
        layout: vertical;
    }

    #chat {
        height: 1fr;
        border: solid cyan;
    }

    #input {
        width: 1fr;
    }

    Horizontal {
        height: 3;
    }
    """

    BINDINGS: Final[list[tuple[str]]] = [("ctrl+c", "quit", "Quit")]

    def __init__(self):
        super().__init__()
        self.backend = ChatBackend()
