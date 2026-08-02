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

    BINDINGS = [("ctrl+c", "quit", "Quit")]

    async def on_mount(self) -> None:
        self.query_one("#input", Input).focus()

    def __init__(self):
        super().__init__()
        self.backend = ChatBackend()

    def compose(self) -> ComposeResult:
        yield Header()

        with Container():
           yield RichLog(id="chat", wrap=True, highlight=True, markup=True) 

           with Horizontal():
               yield Input(
                   placeholder="Type a Message...",
                   id="input"
               )

        yield Footer()

    async def on_input_submitted(self, event: Input.Submitted):
        message = event.value.strip()

        if not message:
            return

        chat = self.query_one("#chat", RichLog)
        input_box = self.query_one("#input", Input)

        chat.write(f"[bold cyan]You:[/] {message}")
        
        input_box.value = ""
        input_box.disabled = True

        response = await self.backend.send(message)

        chat.write("")
        chat.write(f"[bold green]Assistant:[/] {response}")
        chat.write("")

        input_box.disabled = False
        input_box.focus()
            