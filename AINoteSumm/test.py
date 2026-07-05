import requests
import time

url = "http://127.0.0.1:8080/v1/chat/completions"

def chat(prompt: str) -> str:
    payload = {
        "model": "LFM2.5-230M-Q4_0",
        "messages": [{ "role": "user", "content": prompt }],
        "temperature": 0.7,
        "max_tokens": 1024,
        "stream": False
    }

    response = requests.post(url, json=payload)
    response.raise_for_status()

    data = response.json()
    return data["choices"][0]["message"]["content"]

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
