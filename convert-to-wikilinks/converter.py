import re
import sys

from valid_path import PATH_VALIDITY, interpret_results, is_valid_path


class LinkConverter:
    """
    Converts specific markdown link formats ([Text](../../path))
    into Wikilinks ([[Text]]).
    """

    def __init__(self):
        # Regex pattern to find text inside the first set of square brackets [Text].
        # It captures the text (Group 1) that appears before the double-slash link path.
        self.pattern:str = r"\[([^\]]+?)\]\([^)]*\)"

    def convert_markdown_to_wikilink(self, text: str) -> str:
        """
        Finds all matching links in the text and converts them to Wikilinks.
        """

        def replacement_function(match):
            """
            Helper function for re.sub to replace the match with the Wikilink format.
            The captured text is in group 1.
            """
            captured_text = match.group(1).strip()
            return f"[[{captured_text}]]"

        return re.sub(self.pattern, replacement_function, text)


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