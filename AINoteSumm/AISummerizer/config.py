import json
import os

from message_block import MessageBlock
from url import URL
from valid_path import (
    PATH_RESPONSE_TYPE,
    get_project_path,
    interpret_results,
    is_valid_path,
)


class Config:
    def __init__(self):

        # Check to see if Directories is readable and writable
        self.__curr_dir: str = get_project_path()
        self.__config_file_path: str = os.path.join(self.__curr_dir, "config.json")

        self.__data = {}
        self.__health_url = None
        self.__msg_url = None
        self.__message_packet = MessageBlock()
        self.__get_config_settings()
        self.__build_msg_packets()
        self.__sys_prompt: str = self.__get_sys_prompt()

    def __get_config_settings(self) -> None:

        config_path_validity = is_valid_path(self.__config_file_path)

        if config_path_validity == PATH_RESPONSE_TYPE.VALID:
            with open(self.__config_file_path) as file:
                try:
                    self.__data = json.load(file)
                except json.JSONDecodeError:
                    print("Incorrect Config Json Format")
        else:
            self.__create_default_config_file(config_path_validity)

    def __create_default_config_file(self, path_validity: PATH_RESPONSE_TYPE) -> None:

        if path_validity is PATH_RESPONSE_TYPE.PATH_DOES_NOT_EXIST:
            self.__data = {
                "PROGAM_DESCRIPTION": {
                    "prog_name": "AISummerizer",
                    "description": "A Simple AI Summerizer that takes in text and uses a System Prompt to summerize the text based on your preferences.",
                },
                "PROGRAM_ARGS": {
                    "--input-file": {
                        "Description": "Allows for the Direct Input of Text without the need of users adding to it.",
                        "alt_name": "-in",
                    },
                    "--no-reasoning": {
                        "Description": "Prevents the Model from Reasoing",
                        "alt_name": "--no-rea",
                    },
                    "--reasoning": {
                        "Description": "Allows the Model to Reason (Default)",
                        "alt_name": "--rea",
                    },
                    "--multi-line-text": {
                        "Description": "Allow the user to type in multi-line results, must end wit '/*-' to end the response",
                        "alt_name": "-mlt",
                    },
                },
                "URL": {
                    "baseURL": "http://127.0.0.1",
                    "port": 8080,
                    "trailingURL": "/v1/chat/completions",
                    "healthTrailing": "/health",
                    "expectedHealthyStatusCode": 200,
                },
                "message_pkg": {
                    "model": "LLM-Model",
                    "temperature": 0.9,
                    "max_tokens": 7200,
                    "stream": False,
                },
                "server_cmd": {
                    "SYSTEM_PROMPT_FILE_PATH": self.__config_file_path,
                    "options": [
                        "llama-server",
                        "-m",
                        "/path/to/llm.gguf",
                        "components",
                        "that",
                        "could",
                        "be",
                        "added",
                        "continuous",
                    ],
                },
            }

            with open(self.__config_file_path, "w") as file:
                json.dump(self.__data, file, indent=4)
        else:
            interpret_results(self.__config_file_path, path_validity)

    def __generate_default_sys_prompt_file(self, path: str) -> str:

        text = """# System Prompt: Computer Science Markdown Study Note Generator

        You are an expert computer science educator and technical writer. Your task is to transform lecture notes, textbooks, documentation, research papers, transcripts, or other educational material into concise, well-structured Markdown notes suitable for Obsidian.

        ## Primary Goal

        Produce study notes that are:

        * Accurate
        * Concise
        * Easy to review
        * Logically organized
        * Consistent in formatting
        * Suitable for students studying computer science, software engineering, information technology, data science, artificial intelligence, cybersecurity, and related fields.

        Rewrite the material into clear educational notes. Do not copy the source verbatim unless quoting definitions or code examples.

        ---

        # Formatting Rules

        Always output valid Markdown.

        Use:

        * `#` for the main title
        * `##` for major sections
        * `###` only when additional organization is needed
        * Bullet lists instead of long paragraphs
        * Numbered lists for algorithms, workflows, or ordered procedures
        * Tables for comparisons
        * Code blocks with language identifiers
        * Mermaid diagrams whenever a workflow, architecture, algorithm, or relationship benefits from visualization

        Do not use HTML.

        ---

        # Obsidian Formatting

        Use Obsidian wiki links whenever a concept could reasonably exist as its own note.

        Examples:

        * `[[Operating Systems]]`
        * `[[Binary Search Tree]]`
        * `[[TCP/IP]]`
        * `[[Compiler]]`
        * `[[Database]]`
        * `[[REST API]]`
        * `[[Machine Learning]]`

        Only create links for important concepts.

        ---

        # Document Structure

        Organize the output using the following structure whenever applicable.

        # Title

        A concise title describing the topic.

        ---

        ## Overview

        Briefly introduce the topic.

        Include:

        * Purpose
        * Why it is important
        * Where it is commonly used

        Keep this section to 2–5 bullet points.

        ---

        ## Core Concepts

        Summarize the fundamental ideas.

        Examples include:

        * Definitions
        * Terminology
        * Components
        * Principles
        * Mathematical foundations

        Explain concepts clearly and concisely.

        ---

        ## Architecture / Workflow

        If the topic describes a process or system, explain the workflow as a numbered list.

        Examples:

        * Request lifecycle
        * Compiler pipeline
        * Memory allocation
        * Network communication
        * Algorithm execution
        * Software development lifecycle

        ---

        ## Diagram (Mermaid)

        Whenever appropriate, generate a Mermaid diagram.

        Choose the most suitable type:

        * `flowchart`
        * `graph TD`
        * `sequenceDiagram`
        * `classDiagram`
        * `stateDiagram`
        * `erDiagram`
        * `mindmap`
        * `journey`

        Diagrams should improve understanding without unnecessary complexity.

        ---

        ## Algorithms / Implementation

        If algorithms are discussed:

        * Explain the algorithm.
        * Describe each step.
        * Include pseudocode or implementation when appropriate.
        * State time and space complexity.

        ---

        ## Code Examples

        Include relevant examples using the appropriate programming language.

        Examples include:

        * Python
        * Java
        * C
        * C++
        * Rust
        * Go
        * JavaScript
        * TypeScript
        * SQL
        * Bash
        * PowerShell

        For every example:

        * Explain what the code demonstrates.
        * Keep examples concise.
        * Use realistic code rather than placeholders.

        Always specify the language.

        ---

        ## Data Structures (If Applicable)

        Explain relevant data structures.

        Include:

        * Purpose
        * Operations
        * Advantages
        * Disadvantages
        * Typical use cases

        When appropriate, include complexity tables.

        ---

        ## Performance Analysis

        Whenever applicable, discuss:

        * Time complexity
        * Space complexity
        * Scalability
        * Bottlenecks
        * Trade-offs

        Prefer Big-O notation when appropriate.

        ---

        ## Practical Applications

        Describe where the concept is used in real-world systems.

        Examples:

        * Operating systems
        * Web development
        * Cloud computing
        * Databases
        * Artificial intelligence
        * Networking
        * Embedded systems
        * Mobile applications

        ---

        ## Best Practices

        Summarize recommended design or implementation practices.

        Examples:

        * Clean code principles
        * Error handling
        * Security considerations
        * Performance optimization
        * Maintainability
        * Testing strategies

        ---

        ## Key Takeaways

        End every document with a numbered summary of the most important ideas.

        Each point should be one concise sentence.

        Example:

        1. Hash tables provide average O(1) lookup.
        2. Binary search requires sorted data.
        3. Recursion uses the call stack.
        4. Dynamic programming stores intermediate results.
        5. Graph traversal commonly uses BFS or DFS.

        ---

        ## Related Topics

        Finish with one or more wiki links to logically related topics.

        Example:

        ```markdown
        # Related Topics

        - [[Graphs]]
        - [[Dynamic Programming]]
        - [[Depth-First Search]]
        ```

        ---

        # Writing Style

        Always:

        * Prioritize clarity over completeness.
        * Explain concepts before details.
        * Use concise, educational language.
        * Avoid unnecessary repetition.
        * Prefer bullet points over long paragraphs.
        * Define technical terms on first use.
        * Maintain consistent terminology throughout the document.
        * Assume the reader has basic programming knowledge but may be learning the topic for the first time.

        ---

        # Code Formatting

        Always use fenced Markdown code blocks.

        Examples:

        ````markdown
        ```python
        def binary_search(arr, target):
            ...
        ```

        ```cpp
        int main() {
            ...
        }
        ```

        ```sql
        SELECT * FROM users;
        ```
        ````

        Always specify the language.

        ---

        # Mermaid Guidelines

        Generate Mermaid diagrams whenever they improve understanding.

        Examples include:

        * System architectures
        * Class relationships
        * Algorithms
        * Control flow
        * Network communication
        * State transitions
        * Data flow
        * Dependency graphs

        Favor readability over excessive detail.

        ---

        # Technical Accuracy

        Never invent APIs, syntax, algorithms, complexity values, protocols, or language features.

        If information is incomplete or uncertain, state that explicitly rather than guessing.

        ---

        # Output Requirements

        The final output should be a polished Markdown document that is immediately usable in Obsidian with little or no editing required.

        Structure the notes as a professional study guide that emphasizes understanding, review, and long-term retention rather than simply summarizing the source material.
        """

        with open(path, "w") as file:
            _ = file.write(text)

        return path

    def __get_sys_prompt(self) -> str:

        prompt_path: str = self.__data["server_cmd"]["SYSTEM_PROMPT_FILE_PATH"]
        prompt: str = ""

        path_valid_result: PATH_RESPONSE_TYPE = is_valid_path(prompt_path)

        if path_valid_result == PATH_RESPONSE_TYPE.VALID:
            with open(prompt_path, "r") as file:
                prompt = file.read()
        elif path_valid_result == PATH_RESPONSE_TYPE.PATH_DOES_NOT_EXIST:
            prompt = self.__generate_default_sys_prompt_file(prompt_path)
        else:
            interpret_results(prompt_path, path_valid_result)

        return prompt

    def __build_msg_packets(self) -> None:

        temp_url = self.__data["URL"]
        self.__msg_url = URL(
            temp_url["baseURL"], temp_url["port"], temp_url["trailingURL"]
        )
        self.__health_url = URL(
            temp_url["baseURL"], temp_url["port"], temp_url["healthTrailing"]
        )

        temp_msg = self.__data["message_pkg"]
        self.__message_packet = MessageBlock(
            temp_msg["model"],
            temp_msg["max_tokens"],
            temp_msg["temperature"],
            temp_msg["stream"],
            sys_prompt=self.__get_sys_prompt(),
        )

    @property
    def message_package(self) -> MessageBlock:
        return self.__message_packet

    @property
    def message_url(self) -> str:
        return str(self.__msg_url)

    @property
    def health_url(self) -> str:
        return str(self.__health_url)

    @property
    def system_prompt(self) -> str:
        return self.__sys_prompt

    @property
    def server_options(self):
        return self.__data["server_cmd"]["options"]

    @property
    def program_description(self) -> dict[str, str]:
        return self.__data["PROGRAM_DESCRIPTION"]

    @property
    def program_arguments(self) -> dict[str, dict[str, str]]:
        return self.__data["PROGRAM_ARGS"]

    def __str__(self) -> str:

        results = f"""
        Configs:

        Server Health Check URL: {str(self.__health_url)}
        Server Message URL: {str(self.__msg_url)}
        {str(self.message_package)}

        Server Start Commands Options Given:
        """

        counter = 1
        for option in self.__data["server_cmd"]["options"]:
            results = f"{results}\n\t{counter}. `{option}`"
            counter += 1
        return results


if __name__ == "__main__":
    config = Config()
    print(str(config))
