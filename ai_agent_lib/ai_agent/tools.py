"""General-purpose tools for the Agent.

Each tool is a plain function that takes JSON-serializable arguments and
returns a JSON-serializable result (str/int/float/bool/dict/list), so it can
be used with OpenAI-compatible function calling.

Usage with :class:`agent.Agent`:

    from tools import register_tools

    agent = Agent(url=..., model=...)
    register_tools(agent)
"""

from __future__ import annotations

import datetime as _dt
import heapq
import inspect
import json
import os
import platform
import re
import urllib.request
from collections import Counter
from collections.abc import Callable
from pathlib import Path

# -------------------------
# Math
# -------------------------


def add(a: float, b: float) -> float:
    """Add two numbers."""
    return float(a) + float(b)


def subtract(a: float, b: float) -> float:
    """Subtract b from a."""
    return float(a) - float(b)


def multiply(a: float, b: float) -> float:
    """Multiply two numbers."""
    return float(a) * float(b)


def divide(a: float, b: float) -> float:
    """Divide a by b."""
    if float(b) == 0:
        raise ZeroDivisionError("Cannot divide by zero.")
    return float(a) / float(b)


# -------------------------
# Strings
# -------------------------


def reverse_string(text: str) -> str:
    """Reverse a string."""
    return text[::-1]


def string_length(text: str) -> int:
    """Return the number of characters in a string."""
    return len(text)


def to_uppercase(text: str) -> str:
    """Convert a string to uppercase."""
    return text.upper()


def to_lowercase(text: str) -> str:
    """Convert a string to lowercase."""
    return text.lower()


# -------------------------
# Files
# -------------------------


def read_file(file_path: str) -> str:
    """Read the contents of a file as text and return them."""
    path = Path(file_path)
    if not path.is_file():
        raise OSError(f"Not a file: {file_path}")
    return path.read_text(encoding="utf-8")


def write_file(file_path: str, content: str) -> str:
    """Write text content to a file. Returns the path of the written file."""
    path = Path(file_path)
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(content, encoding="utf-8")
    return str(path)


def list_files(directory: str = ".") -> str:
    """List the names of files in a directory, one per line."""
    entries = sorted(os.listdir(directory))
    return "\n".join(entries)


def glob(pattern: str, directory: str = ".") -> str:
    """Find all files matching a glob pattern (e.g. '**/*.py'), one per line."""
    matches = sorted(Path(directory).glob(pattern))
    return "\n".join(str(path) for path in matches)


# -------------------------
# System / Time
# -------------------------


def current_datetime() -> str:
    """Return the current date and time as an ISO-8601 string."""
    return _dt.datetime.now(_dt.timezone.utc).isoformat()


def current_working_directory() -> str:
    """Return the current working directory."""
    return os.getcwd()


def system_info() -> str:
    """Return basic information about the operating system."""
    return json.dumps(
        {
            "system": platform.system(),
            "release": platform.release(),
            "version": platform.version(),
            "machine": platform.machine(),
            "processor": platform.processor(),
        }
    )


# -------------------------
# JSON
# -------------------------


def json_pretty(data: str) -> str:
    """Pretty-print a JSON string."""
    return json.dumps(json.loads(data), indent=4)


def json_validate(data: str) -> bool:
    """Check whether a string is valid JSON."""
    try:
        json.loads(data)
        return True
    except json.JSONDecodeError:
        return False


# -------------------------
# Text Summarization
# -------------------------

_STOPWORDS: frozenset[str] = frozenset(
    """a an and are as at be by for from has he her his in is it its of on
    or she that the their them they this to was we were will with you your
    i me my our ours""".split()
)

_WORD_RE = re.compile(r"[\w']+")
_SENTENCE_RE = re.compile(r"[^.!?\n]+[.!?]*[\s]*")


def _tokenize(text: str) -> list[str]:
    return _WORD_RE.findall(text.lower())


def _split_sentences(text: str) -> list[str]:
    sentences = [s.strip() for s in _SENTENCE_RE.findall(text)]
    return [s for s in sentences if s]


def summarize_text(text: str, max_sentences: int = 3) -> str:
    """Return an extractive summary of the given text.

    Picks the most representative sentences based on word frequency, with a
    small bonus for sentences appearing earlier in the text, and returns them
    in their original order.
    """
    sentences = _split_sentences(text)
    if not sentences:
        return ""
    if len(sentences) <= max_sentences:
        return " ".join(sentences)

    words = _tokenize(text)
    freq = Counter(w for w in words if w not in _STOPWORDS)
    if not freq:
        freq = Counter(words)

    scored: list[tuple[float, int, str]] = []
    for index, sentence in enumerate(sentences):
        score = sum(freq.get(word, 0) for word in _tokenize(sentence))
        score += 0.5 * (1.0 - index / max(len(sentences) - 1, 1))
        scored.append((score, index, sentence))

    top = heapq.nlargest(max_sentences, scored)
    selected = sorted(top, key=lambda item: item[1])
    return " ".join(sentence for _, _, sentence in selected)


# -------------------------
# Web
# -------------------------


def fetch_url(url: str) -> str:
    """Fetch the text content of a URL."""
    with urllib.request.urlopen(url, timeout=15) as response:
        return response.read().decode("utf-8", errors="replace")


# -------------------------
# Registration
# -------------------------

TOOLS: dict[str, Callable] = {
    name: func
    for name, func in globals().items()
    if callable(func)
    and not name.startswith("_")
    and getattr(func, "__module__", None) == __name__
}

TOOL_SCHEMAS: list[dict] = [
    {
        "type": "function",
        "function": {
            "name": name,
            "description": func.__doc__ or "",
            "parameters": {"type": "object", "properties": {}, "required": []},
        },
    }
    for name, func in TOOLS.items()
]


def register_tools(agent) -> None:
    """Register every general-purpose tool onto an Agent instance."""
    for func in TOOLS.values():
        signature = inspect.signature(func)
        annotations = inspect.get_annotations(func, eval_str=True)
        properties: dict[str, object] = {}
        required: list[str] = []
        for param_name, param in signature.parameters.items():
            annotation = annotations.get(param_name, str)
            if annotation is int:
                json_type = "integer"
            elif annotation is float:
                json_type = "number"
            elif annotation is bool:
                json_type = "boolean"
            else:
                json_type = "string"
            properties[param_name] = {"type": json_type}
            if param.default is inspect.Parameter.empty:
                required.append(param_name)

        if hasattr(agent, "tool"):
            agent.tool(
                description=func.__doc__ or "",
                parameters={
                    "type": "object",
                    "properties": properties,
                    "required": required,
                },
            )(func)
