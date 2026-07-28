import os
from dotenv import load_dotenv
from chroma.client import ChromaClient
from agents.tag_agent import TagAgent
from models.schemas import Tag
from typing import List

# Load environment variables (e.g., API keys, database connection strings)
load_dotenv()

def main():
    """
    Main function to initialize the TagAgent and run the tagging workflow.
    """
    print("--- Starting Tag Generation System Initialization ---")

    # 1. Define the initial controlled vocabulary (Tags)
    initial_tags: List[Tag] = [
        Tag(name="python", description="Python programming language", category="language"),
        Tag(name="docker", description="Containerization platform", category="deployment"),
        Tag(name="authentication", description="Identity verification and access control", category="security"),
        Tag(name="fastapi", description="FastAPI web framework for Python", category="framework"),
        Tag(name="security", description="Protection against attacks and unauthorized access", category="security")
    ]

    # 2. Initialize ChromaDB Client
    try:
        client = ChromaClient()
        
        # 3. Initialize the TagAgent (This also loads the initial vocabulary)
        agent = TagAgent(chromadb_client=client, known_tags=initial_tags)

        print("\n--- Running Tag Generation Workflow ---")
        
        # Sample markdown content for testing
        sample_markdown = """
# Service Authentication Guide

This service uses OAuth2, JWT tokens, and leverages FastAPI for the API layer. 
It interacts with PostgreSQL and is containerized using Docker.
"""
        
        doc_id = "doc_test_001"

        # 4. Run the tagging process
        print(f"Processing document ID: {doc_id}")
        
        # NOTE: The TagAgent currently returns the known tags for simulation. 
        # In a real implementation, this would return the LLM-generated tags.
        generated_tags = agent.generate_tags(sample_markdown, doc_id)

        print("\n--- Results ---")
        print(f"Successfully processed document {doc_id}.")
        print(f"Simulated/Generated Tags: {generated_tags}")
        
    except Exception as e:
        print(f"An error occurred during setup or execution: {e}")
    finally:
        if 'client' in locals():
            client.close()
            print("\n--- Shutdown Complete ---")

if __name__ == "__main__":
    main()