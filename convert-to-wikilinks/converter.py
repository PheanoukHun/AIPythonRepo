import re
import sys

class LinkConverter:
    """
    Converts specific markdown link formats ([Text](../../path)) 
    into Wikilinks ([[Text]]).
    """
    def __init__(self):
        # Regex pattern to find text inside the first set of square brackets [Text]. 
        # It captures the text (Group 1) that appears before the double-slash link path.
        self.pattern = r'\[([^\]]+)\]\([^)]+\)'
        
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
            
        # Apply the regex substitution across the entire text content
        return re.sub(self.pattern, replacement_function, text)

if __name__ == '__main__':
    if len(sys.argv) != 2:
        print("Usage: python converter.py <file_path>")
        sys.exit(1)
        
    file_path = sys.argv[1]
    
    try:
        # Read the existing content of the file
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()
            
        # Convert the content
        converter = LinkConverter()
        new_content = converter.convert_markdown_to_wikilink(content)
        
        # Write the converted content back to the file
        with open(file_path, 'w', encoding='utf-8') as f:
            f.write(new_content)
            
        print(f"Successfully converted and saved changes to: {file_path}")
        
    except FileNotFoundError:
        print(f"Error: File not found at {file_path}")
    except Exception as e:
        print(f"An error occurred: {e}")
