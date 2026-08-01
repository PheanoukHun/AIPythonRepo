import openai
import json
from typing import Callable, Dict, Any, List

# Initialize the OpenAI client.
# NOTE: Ensure you have set the OPENAI_API_KEY environment variable.
try:
    client = openai.OpenAI()
except Exception as e:
    print(f"Error initializing OpenAI client: {e}")
    exit()

# --- Define the functions (tools) the AI can call ---

def get_current_weather(location: str, unit: str = "celsius") -> str:
    """Gets the current weather for a given location."""
    if "boston" in location.lower():
        return f"The weather in Boston is 55 degrees {unit}."
    elif "tokyo" in location.lower():
        return f"The weather in Tokyo is 28 degrees {unit}."
    else:
        return f"Sorry, I don't have weather data for {location}."

def calculate_square(number: int) -> str:
    """Calculates the square of a given number."""
    return f"{number} squared is {number * number}."

# Map of function names to actual callable objects
available_functions: Dict[str, Callable] = {
    "get_current_weather": get_current_weather,
    "calculate_square": calculate_square,
}

# --- Main Agent Logic ---

def run_chat(messages: List[Dict[str, str]]):
    """
    Runs the conversation loop, handling tool calls and responses.
    
    Args:
        messages: Initial list of user/system messages.
    """
    print("Agent starting conversation...")
    
    # Convert Python functions into OpenAI tool schema format
    tools = [
        {
            "type": "function",
            "function": {"name": name, "description": func.__doc__, "parameters": func.__annotations__}
            for name, func in available_functions.items()
        }
    ]
    
    while True:
        # 1. Call the API
        response = client.chat.completions.create(
            model="gpt-3.5-turbo", # Using a common model for demonstration
            messages=messages,
            tools=tools,
            tool_choice="auto",
        )
        
        response_message = response.choices[0].message
        
        # 2. Check for tool calls
        if response_message.tool_calls:
            messages.append(response_message)
            
            tool_outputs = []
            for tool_call in response_message.tool_calls:
                function_name = tool_call.function.name
                function_args = json.loads(tool_call.function.arguments)
                
                if function_name not in available_functions:
                    raise ValueError(f"Unknown function: {function_name}")

                function_to_call = available_functions[function_name]
                
                # Execute the function and get the result
                function_response = function_to_call(**function_args)
                
                # Append the function response to messages for the next API call
                tool_outputs.append({
                    "tool_call_id": tool_call.id,
                    "name": function_name,
                    "content": function_response,
                })
            
            # 3. Send tool results back to the API
            messages.append(response_message) # First message is the request with tool calls
            for output in tool_outputs:
                messages.append({
                    "tool_call_id": output["tool_call_id"],
                    "role": "tool",
                    "name": output["name"],
                    "content": output["content"],
                })
            
        else:
            # No tool calls, final response received
            print("\n--- Final AI Response ---")
            print(response_message.content)
            break

if __name__ == "__main__":
    # Example Usage:
    initial_messages = [
        {"role": "user", "content": "What is the weather in Tokyo? Then, calculate the square of 12."}
    ]
    run_chat(initial_messages)