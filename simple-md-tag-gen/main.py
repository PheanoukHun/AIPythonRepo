import os
import glob
from typing import List
from dotenv import load_dotenv
from chroma.client import ChromaClient
from agents.tag_agent import TagAgent
from models.schemas import Tag

# Load environment variables (e.g., API keys, database connection strings)
load_dotenv()

def main():
    """
    Main function to scan the repository, tag all markdown files, and update the tag vocabulary.
    """
    print("--- Starting Repository Tagging Process ---")

    # 1. Find all markdown files in the repository
    all_markdown_files = glob.glob("**/*.md", recursive=True)
    print(f"Found {len(all_markdown_files)} markdown files to process.")
    
    if not all_markdown_files:
        print("No markdown files found. Exiting.")
        return

    # 2. Define initial controlled vocabulary (will be expanded dynamically)
    initial_tags: List[Tag] = [
        Tag(name="python", description="Python programming language", category="language"),
        Tag(name="docker", description="Containerization platform", category="deployment"),
        Tag(name="authentication", description="Identity verification and access control", category="security"),
        Tag(name="fastapi", description="FastAPI web framework for Python", category="framework"),
        Tag(name="security", description="Protection against attacks and unauthorized access", category="security")
    ]

    # 3. Initialize ChromaDB Client
    try:
        client = ChromaClient()
        
        # 4. Initialize the TagAgent (which loads/ensures collections exist)
        agent = TagAgent(chromadb_client=client, known_tags=initial_tags)

        print("\n--- Running Tag Generation Workflow for Repository ---")
        
        processed_count = 0
        for file_path in all_markdown_files:
            doc_filename = os.path.basename(file_path)
            doc_id = doc_filename.replace(".md", "")
            print(f"\n[PROCESSING] File: {doc_filename} (ID: {doc_id})")

            try:
                # 5. Load the markdown file content
                with open(file_path, 'r', encoding='utf-8') as f:
                    content_text = f.read()

                # 6. Run the tagging process
                generated_tags = agent.generate_tags(content_text, doc_id)

                print(f"-> SUCCESS: Processed {doc_filename} and generated tags: {generated_tags}")
                processed_count += 1
            except Exception as e:
                print(f"-> ERROR processing {doc_filename}: {e}")
        
        print(f"\n--- Tagging Complete ---")
        print(f"Successfully processed {processed_count} files.")

    except Exception as e:
        print(f"An error occurred during setup or execution: {e}")
    finally:
        if 'client' in locals():
            client.close()
            print("\n--- Shutdown Complete ---")

if __name__ == "__main__":
    main()