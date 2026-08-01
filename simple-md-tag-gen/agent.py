class SimpleAIAgents:
    def __init__(self, name="AI Agent"):
        self.name = name
        self.memory = []
        print(f"Initializing {self.name}")

    def process_input(self, input_data):
        """Processes the input data and generates a response."""
        print(f"[{self.name}] Received input: {input_data}")
        # Simple processing logic
        response = f"Processed '{input_data}' by {self.name}"
        self.memory.append(input_data)
        return response

    def get_status(self):
        """Returns the current status and memory length."""
        return {"name": self.name, "memory_size": len(self.memory)}
