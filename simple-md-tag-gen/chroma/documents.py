import json
from models.schemas import Document
from chroma.client import ChromaClient
import chromadb
from typing import List

class DocumentManager:
    """
    Manages the storage and semantic retrieval of markdown documents in ChromaDB.
    """
    def __init__(self, client: ChromaClient):
        self.client = client
        self.documents_collection = self.client.get_collection("document_collection")
        if not self.documents_collection:
            raise Exception("Failed to initialize document_collection.")

    def insert_document(self, document: Document):
        """
        Inserts a markdown document and its metadata into the document collection.
        Serializes list fields to JSON strings for ChromaDB compatibility.
        """
        doc_id = document.id
        
        # Prepare metadata - convert lists to JSON strings
        metadata = document.metadata.copy()
        for key, value in metadata.items():
            if isinstance(value, list):
                metadata[key] = json.dumps(value)
        
        # ChromaDB stores content (document) and metadata (tags, filename)
        self.documents_collection.add(
            ids=[doc_id],
            documents=[document.content],
            metadatas=[metadata]
        )
        print(f"Document {doc_id} inserted successfully.")

    def query_similar_documents(self, query_text: str, n_results: int = 5) -> List[dict]:
        """
        Queries the document collection to find semantically similar documents.
        Returns a list of dictionaries containing the document's metadata (including tags).
        """
        results = self.documents_collection.query(
            query_texts=[query_text],
            n_results=n_results
        )

        # Structure the output for the Agent
        similar_docs = []
        for i in range(len(results['ids'][0])):
            # Assuming metadata structure from schemas.py
            metadata = results['metadatas'][i][0]
            
            # Extract relevant information: filename and tags
            filename = metadata.get('filename', 'unknown_file')
            tags_json = metadata.get('tags', '[]')
            
            # Deserialize tags from JSON string
            try:
                tags = json.loads(tags_json)
            except (json.JSONDecodeError, TypeError):
                tags = []
            
            similar_docs.append({
                "filename": filename,
                "tags": tags
            })
        return similar_docs