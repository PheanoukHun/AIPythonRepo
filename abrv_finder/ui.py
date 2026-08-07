from rich.markdown import Markdown
from rich.markup import escape
from textual import events
from textual.app import App, ComposeResult
from textual.containers import Container, Horizontal
from textual.message import Message
from textual.widgets import Header, RichLog, TextArea

from backend_chat import ChatBackend
from special_inputs import SPEC_IN


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
        if event.key in ("shift+enter", "ctrl+j"):
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
        self.sub_title = self.backend.backend_label

    def compose(self) -> ComposeResult:
        yield Header()

        with Container():
            yield RichLog(id="chat", wrap=True, highlight=True, markup=True)

            with Horizontal():
                yield ChatInput(
                    placeholder="Type a Message... (Enter to send, Shift+Enter / Ctrl+J for newline)",
                    id="input",
                )

    async def on_chat_input_submitted(self, event: ChatInput.Submitted):
        message = event.value

        chat = self.query_one("#chat", RichLog)
        input_box = self.query_one("#input", ChatInput)

        command_text, _, command_arg = message.partition(" ")

        try:
            command = SPEC_IN(command_text)
        except ValueError:
            command = None

        if command is SPEC_IN.CLEAR:
            self.backend.clear_chat()
            chat.clear()
            input_box.focus()
            return

        if command is SPEC_IN.CLEAR_SCREEN:
            chat.clear()
            input_box.focus()
            return

        if command is SPEC_IN.SYSTEM:
            prompt = command_arg.strip()
            if prompt:
                self.backend.set_sys_prompt(prompt)
                chat.write("[bold yellow]System prompt updated.[/]")
            else:
                chat.write(
                    f"[bold yellow]Current system prompt:[/]\n{escape(self.backend.get_sys_prompt())}"
                )
            input_box.focus()
            return

        if command is SPEC_IN.EXIT:
            self.backend.exit()
            return

        chat.write(f"[bold cyan]You:[/] {message}")

        input_box.disabled = True

        try:
            response = await self.backend.send(message)
        except Exception as exc:
            chat.write(f"[bold red]Error:[/] {escape(str(exc))}")
            input_box.disabled = False
            input_box.focus()
            return

        chat.write("")
        chat.write("[bold green]Assistant:[/]")
        chat.write(Markdown(response))
        chat.write("")

        input_box.disabled = False
        input_box.focus()
