from typing import List
from chroma.client import ChromaClient
from chroma.documents import DocumentManager
from chroma.tags import TagsManager
from markdown.parser import MarkdownParser
from prompts.tag_generation import TagPromptGenerator
from models.schemas import Document, Tag

class TagAgent:
    """
    The core agent responsible for the entire tag generation workflow.
    """
    def __init__(self, chromadb_client: ChromaClient, known_tags: List[Tag]):
        # Initialize core services
        self.doc_manager = DocumentManager(chromadb_client)
        self.tag_manager = TagsManager(chromadb_client)
        self.parser = MarkdownParser()
        self.prompt_generator = TagPromptGenerator()

        # Initialize known vocabulary
        self.tag_manager.initialize_tags(known_tags)
        print("TagAgent initialized and vocabulary loaded.")

    def generate_tags(self, markdown_text: str, doc_id: str) -> List[str]:
        """
        Runs the full tagging workflow for a given markdown text.
        Returns the list of tags generated for the document.
        """
        # 1. Parse markdown content
        document_content = self.parser.parse(markdown_text)
        
        # 2. Query similar documents
        similar_docs = self.doc_manager.query_similar_documents(document_content)
        
        # 3. Query known tags (using the document content as the query)
        known_tags = self.tag_manager.query_tags(document_content)
        
        # 4. Collect similar document tags
        similar_tags = []
        for doc in similar_docs:
            similar_tags.extend(doc['tags'])
            
        # 5. Generate LLM prompt
        prompt = self.prompt_generator.generate_prompt(
            document_content=document_content,
            existing_tags=known_tags,
            similar_document_tags=list(set(similar_tags)) # Use set for unique tags
        )
        
        print("\n--- Generated Prompt for LLM ---")
        print(prompt)
        print("-------------------------------\n")
        
        # 6. SIMULATE LLM CALL and process output
        # In a real implementation, you would send the prompt to an LLM and parse JSON response
        simulated_llm_output = ["python", "authentication", "security", "api", "new_feature_tag"]

        # 7. Update Tag Vocabulary with new tags
        self.tag_manager.add_tag_to_vocabulary(
            new_tags=simulated_llm_output, 
            description_source=document_content
        )

        # 8. Store the new document with generated tags
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