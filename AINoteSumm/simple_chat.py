#!/usr/bin/env python3

import time

import requests

url = "http://0.0.0.0:8080/v1/chat/completions"
system_prompt = """
You are a document classifier.

Your task is to read a Markdown document and assign 5-15 broad subject tags.

Rules:
- Return ONLY a JSON array of strings.
- Tags should describe the overall subjects, not every keyword.
- Prefer academic or knowledge-base categories.
- Merge related concepts into one tag.
- Ignore Markdown formatting, wiki links ([[...]]), code blocks, URLs, examples, and repeated text.
- Rank tags from most important to least important.
- Do not invent topics not supported by the document.
- Do not explain your reasoning.

Good tags:
Programming
Python
JavaScript
Web Development
APIs
Networking
Software Engineering

Bad tags:
requests.get
response.json
document.getElementById
appendChild
window.onload
POST
GET
PUT
DELETE
"""


def chat(prompt: str) -> str:
    payload = {
        "model": "LFM2.5-230M-Q4_0",
        "messages": [
            {"role": "system", "content": system_prompt},
            {"role": "user", "content": prompt},
        ],
        "temperature": 0.7,
        "max_tokens": 1024,
        "stream": False,
    }

    response = requests.post(url, json=payload)
    response.raise_for_status()

    data = response.json()
    return str(data["choices"][0]["message"]["content"])


def main():
    while True:
        prompt = input("You: ")

        if prompt.lower() in {"quit", "exit"}:
            break

        reply = chat(prompt)

        print()
        for char in reply:
            print(char, end="", flush=True)
            time.sleep(0.00625)
        print("\n")


if __name__ == "__main__":
    main()
