import json
import os
from typing import List
import traceback
from chroma.client import ChromaClient
from chroma.documents import DocumentManager
from markdown.parser import MarkdownParser
from prompts.tag_generation import TagPromptGenerator
from models.schemas import Document, TagGenerationOutput
from stores.tag_store import TagStore
from src.agents import Agent


class TagAgent:
    def __init__(self, chromadb_client: ChromaClient, tag_store: TagStore):
        self.doc_manager = DocumentManager(chromadb_client)
        self.tag_store = tag_store
        self.parser = MarkdownParser()
        self.prompt_generator = TagPromptGenerator()

        base_url = os.getenv("LLM_BASE_URL")
        api_key = os.getenv("LLM_API_KEY", "not-needed")
        model = os.getenv("LLM_MODEL", "local-model")

        if not base_url:
            print("WARNING: LLM_BASE_URL not set. Tag generation will use simulated output.")
            self.llm = None
        else:
            self.llm = Agent(
                api_key=api_key,
                base_url=base_url,
                model=model,
                system_prompt="You generate tags for markdown documents. Return JSON only.",
                temperature=0.3,
            )

        all_tags = self.tag_store.get_all_tags()
        print(f"TagAgent initialized with {len(all_tags)} known tags.")

    def _call_llm(self, prompt: str) -> List[str]:
        if self.llm is None:
            return ["python", "authentication", "security", "api", "new_feature_tag"]

        response = self.llm.chat(prompt)
        response = response.strip()
        if response.startswith("```"):
            response = response.split("\n", 1)[1] if "\n" in response else response
            response = response.rsplit("```", 1)[0] if "```" in response else response
            response = response.strip()

        try:
            parsed = TagGenerationOutput(**json.loads(response))
            return parsed.tags
        except (json.JSONDecodeError, Exception) as e:
            print(f"Failed to parse LLM response: {e}")
            print(f"Raw response: {response}")
            return []

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

            generated_tags = self._call_llm(prompt)

            if not generated_tags:
                print("No tags generated, skipping.")
                return []

            self.tag_store.add_tags(generated_tags)

            new_doc = Document(
                id=doc_id,
                content=document_content,
                metadata={
                    "filename": f"{doc_id}.md",
                    "tags": generated_tags
                }
            )
            self.doc_manager.insert_document(new_doc)

            return generated_tags
        except Exception as e:
            print(f"Full traceback: {traceback.format_exc()}")
            raise