from typing import List
import traceback
from chroma.client import ChromaClient
from chroma.documents import DocumentManager
from markdown.parser import MarkdownParser
from prompts.tag_generation import TagPromptGenerator
from models.schemas import Document
from stores.tag_store import TagStore


class TagAgent:
    def __init__(self, chromadb_client: ChromaClient, tag_store: TagStore):
        self.doc_manager = DocumentManager(chromadb_client)
        self.tag_store = tag_store
        self.parser = MarkdownParser()
        self.prompt_generator = TagPromptGenerator()

        all_tags = self.tag_store.get_all_tags()
        print(f"TagAgent initialized with {len(all_tags)} known tags.")

    def generate_tags(self, markdown_text: str, doc_id: str) -> List[str]:
        try:
            document_content = self.parser.parse(markdown_text)

            similar_docs = self.doc_manager.query_similar_documents(document_content)

            existing_tags = self.tag_store.get_all_tags()

            similar_tags = []
            for doc in similar_docs:
                similar_tags.extend(doc['tags'])

            prompt = self.prompt_generator.generate_prompt(
                document_content=document_content,
                existing_tags=existing_tags,
                similar_document_tags=list(set(similar_tags))
            )

            print("\n--- Generated Prompt for LLM ---")
            print(prompt)
            print("-------------------------------\n")

            # TODO: Replace with real LLM call
            simulated_llm_output = ["python", "authentication", "security", "api", "new_feature_tag"]

            self.tag_store.add_tags(simulated_llm_output)

            new_doc = Document(
                id=doc_id,
                content=document_content,
                metadata={
                    "filename": f"{doc_id}.md",
                    "tags": simulated_llm_output
                }
            )
            self.doc_manager.insert_document(new_doc)

            return simulated_llm_output
        except Exception as e:
            print(f"Full traceback: {traceback.format_exc()}")
            raise