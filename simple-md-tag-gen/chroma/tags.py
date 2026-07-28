from models.schemas import Tag, TagGenerationOutput
from chroma.client import ChromaClient
import chromadb
from typing import List

class TagsManager:
    """
    Manages the controlled vocabulary of tags and handles their embeddings in ChromaDB.
    """
    def __init__(self, client: ChromaClient):
        self.client = client
        self.tags_collection = self.client.get_collection("tag_collection")
        if not self.tags_collection:
            raise Exception("Failed to initialize tag_collection.")

    def initialize_tags(self, tags: List[Tag]):
        """
        Inserts the initial controlled vocabulary into the tag collection.
        Generates embeddings from the tag descriptions.
        """
        ids = []
        documents = []
        metadatas = []

        for i, tag_obj in enumerate(tags):
            tag_name = tag_obj.name
            ids.append(f"tag_{tag_name}")
            documents.append(tag_obj.description)
            metadatas.append({"name": tag_name, "category": tag_obj.category})
        
        # ChromaDB expects embedding generation handled by the client/model.
        # For this implementation, we assume a default embedding function is configured 
        # or will be provided when the client is set up.
        # NOTE: In a real implementation, embedding generation would happen here using the vectorizer.
        # For now, we simulate the data insertion structure.
        self.tags_collection.add(
            ids=ids,
            documents=documents,
            metadatas=metadatas
        )
        print(f"Successfully inserted {len(tags)} tags into tag_collection.")

    def query_tags(self, query_text: str, n_results: int = 10) -> List[str]:
        """
        Queries the tag collection to find the semantically closest existing tags
        based on the query text (e.g., extracted markdown content).
        Returns a list of tag names.
        """
        results = self.tags_collection.query(
            query_texts=[query_text],
            n_results=n_results
        )
        
        # Extract and return only the tag names
        tag_names = [meta['name'] for r in results['metadatas'] for meta in r if 'name' in meta]
        return tag_names
