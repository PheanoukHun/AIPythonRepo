from markdown_it import MarkdownIt
from typing import List

class MarkdownParser:
    """
    A utility class to parse markdown text into a clean string suitable for embedding.
    """
    def __init__(self):
        self.md = MarkdownIt()

    def parse(self, markdown_text: str) -> str:
        """
        Parses markdown text and concatenates the content of all tokens.
        """
        tokens = self.md.parse(markdown_text)
        
        text_parts = []
        for token in tokens:
            if token.content:
                text_parts.append(token.content)
        
        return "\n".join(text_parts)
