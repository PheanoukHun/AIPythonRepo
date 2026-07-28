import os
import glob
from dotenv import load_dotenv
from chroma.client import ChromaClient
from agents.tag_agent import TagAgent
from stores.tag_store import TagStore

load_dotenv()

def main():
    print("--- Starting Repository Tagging Process ---")

    all_markdown_files = [
        f for f in glob.glob("**/*.md", recursive=True)
        if not f.startswith('.venv/') and not f.startswith('venv/') and not f.startswith('__pycache__/')
    ]
    print(f"Found {len(all_markdown_files)} markdown files to process.")

    if not all_markdown_files:
        print("No markdown files found. Exiting.")
        return

    tag_store = TagStore("tags_vocabulary.json")

    try:
        client = ChromaClient()
        agent = TagAgent(chromadb_client=client, tag_store=tag_store)

        print("\n--- Running Tag Generation Workflow for Repository ---")

        processed_count = 0
        for file_path in all_markdown_files:
            doc_filename = os.path.basename(file_path)
            doc_id = doc_filename.replace(".md", "")
            print(f"\n[PROCESSING] File: {doc_filename} (ID: {doc_id})")

            try:
                with open(file_path, 'r', encoding='utf-8') as f:
                    content_text = f.read()

                generated_tags = agent.generate_tags(content_text, doc_id)

                print(f"-> SUCCESS: Processed {doc_filename} and generated tags: {generated_tags}")
                processed_count += 1
            except Exception as e:
                print(f"-> ERROR processing {doc_filename}: {e}")

        print(f"\n--- Tagging Complete ---")
        print(f"Successfully processed {processed_count} files.")
        print(f"Tag vocabulary saved to {tag_store.file_path} with {len(tag_store.get_all_tags())} tags.")

    except Exception as e:
        print(f"An error occurred during setup or execution: {e}")
    finally:
        if 'client' in locals():
            client.close()
            print("\n--- Shutdown Complete ---")

if __name__ == "__main__":
    main()