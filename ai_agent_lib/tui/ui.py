from backend_chat import ChatBackend
from rich.markdown import Markdown
from textual import events
from textual.app import App, ComposeResult
from textual.containers import Container, Horizontal
from textual.message import Message
from textual.widgets import Footer, Header, RichLog, TextArea


class ChatInput(TextArea):
    class Submitted(Message):
        def __init__(self, value: str) -> None:
            super().__init__()
            self.value = value

    def _submit_value(self) -> None:
        value = self.text.strip()
        if value:
            self.post_message(self.Submitted(value))
        self.clear()

    async def _on_key(self, event: events.Key) -> None:
        if event.key == "enter":
            event.stop()
            event.prevent_default()
            self._submit_value()
            return
        if event.key == "shift+enter":
            event.stop()
            event.prevent_default()
            self.insert("\n")
            return
        await super()._on_key(event)


class ChatApp(App):
    CSS = """
    Screen {
        layout: vertical;
    }

    #chat {
        height: 1fr;
        border: solid cyan;
    }

    ChatInput {
        width: 1fr;
        max-height: 10;
        border: solid gray;
        padding: 0 1;
    }

    Horizontal {
        height: auto;
        max-height: 12;
    }
    """

    BINDINGS = [("ctrl+c", "quit", "Quit")]

    async def on_mount(self) -> None:
        self.query_one(ChatInput).focus()

    def __init__(self):
        super().__init__()
        self.backend = ChatBackend()

    def compose(self) -> ComposeResult:
        yield Header()

        with Container():
            yield RichLog(id="chat", wrap=True, highlight=True, markup=True)

            with Horizontal():
                yield ChatInput(
                    placeholder="Type a Message... (Enter to send, Shift+Enter for newline)",
                    id="input"
                )

        yield Footer()

    async def on_chat_input_submitted(self, event: ChatInput.Submitted):
        message = event.value

        chat = self.query_one("#chat", RichLog)
        input_box = self.query_one("#input", ChatInput)

        chat.write(f"[bold cyan]You:[/] {message}")

        input_box.disabled = True

        response = await self.backend.send(message)

        chat.write("")
        chat.write("[bold green]Assistant:[/]")
        chat.write(Markdown(response))
        chat.write("")

        input_box.disabled = False
        input_box.focus()
