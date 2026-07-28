import chromadb
from typing import Optional

class ChromaClient:
    """
    A wrapper class for interacting with the ChromaDB client.
    """
    def __init__(self, client: Optional[chromadb.Client] = None):
        """
        Initializes the ChromaDB client.
        If no client is provided, it defaults to an in-memory client for development,
        but is designed to allow connecting to a persistent client later.
        """
        self.client = client if client is not None else chromadb.Client()
        print("ChromaDB Client initialized.")

    def get_collection(self, collection_name: str):
        """Retrieves a specific collection from the client."""
        try:
            return self.client.get_collection(name=collection_name)
        except Exception as e:
            print(f"Error accessing collection {collection_name}: {e}")
            return None

    def close(self):
        """Closes the ChromaDB client connection."""
        if self.client:
            self.client.close()
            print("ChromaDB Client closed.")