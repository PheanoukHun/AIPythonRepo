import re
import sys

from valid_path import PATH_VALIDITY, interpret_results, is_valid_path


class LinkConverter:
    """
    Converts specific markdown link formats ([Text](../../path))
    into Wikilinks ([[Text]]).
    """

    def __init__(self):
        # Pattern for broken format: [[word1]] word2](url) → [[word1 word2]]
        self.broken_pattern:str = r"\[\[([^\]]+)\]\]\s*([^\]]+)\]\(([^)]+)\)"
        # Pattern for wikilink with URL suffix: [[text]](url) → [[text]]
        self.wiki_url_pattern:str = r"\[\[([^\]]+)\]\]\(([^)]+)\)"
        # Pattern for standard markdown link: [text](url) → [[text]]
        self.markdown_pattern:str = r"\[([^\]]+)\]\(([^)]+)\)"

    def convert_markdown_to_wikilink(self, text: str) -> str:
        """
        Finds all matching links in the text and converts them to Wikilinks.
        Handles three formats:
        1. Broken: [[word1]] word2](url) → [[word1 word2]]
        2. Wikilink with URL: [[text]](url) → [[text]]
        3. Markdown: [text](url) → [[text]]
        """

        def broken_replacement(match):
            text1 = match.group(1).strip()
            text2 = match.group(2).strip()
            return f"[[{text1} {text2}]]"

        def wiki_url_replacement(match):
            return f"[[{match.group(1).strip()}]]"

        def markdown_replacement(match):
            return f"[[{match.group(1).strip()}]]"

        text = re.sub(self.broken_pattern, broken_replacement, text)
        text = re.sub(self.wiki_url_pattern, wiki_url_replacement, text)
        text = re.sub(self.markdown_pattern, markdown_replacement, text)

        return text


if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Usage: python converter.py <file_path>")
        sys.exit(1)

    file_path:str = sys.argv[1]

    is_file_valid:PATH_VALIDITY = is_valid_path(file_path)
    interpret_results(is_file_valid)

    # Read the existing content of the file
    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()

    # Convert the content
    converter = LinkConverter()
    new_content = converter.convert_markdown_to_wikilink(content)

    # Write the converted content back to the file
    with open(file_path, "w", encoding="utf-8") as f:
        _ = f.write(new_content)

    print(f"Successfully converted and saved changes to: {file_path}")