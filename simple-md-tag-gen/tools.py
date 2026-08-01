import os
from typing import Dict, Any, Callable, List

# Define the available tools the AI agent can call
available_tools: Dict[str, Callable] = {}

def read_file(file_path: str) -> str:
    """
    Reads the content of a specified file and returns it as a string.

    Args:
        file_path: The path to the file to be read.

    Returns:
        The content of the file as a string.
        Raises IOError if the file cannot be found or read.
    """
    if not os.path.exists(file_path):
        raise IOError(f"File not found at path: {file_path}")
    
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            return f.read()
    except Exception as e:
        raise IOError(f"Error reading file: {e}")

available_tools["read_file"] = read_file

# NOTE: In a real agent implementation, you would then use this dictionary
# to populate the 'tools' list for the OpenAI API call.
