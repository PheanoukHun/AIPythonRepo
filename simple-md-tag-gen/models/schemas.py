from pydantic import BaseModel, Field
from typing import List, Optional

class Document(BaseModel):
    """Schema for a stored markdown document."""
    id: str
    content: str = Field(..., description="The raw text content of the document.")
    metadata: dict = Field(default_factory=dict, description="Metadata associated with the document, including existing tags.")

class Tag(BaseModel):
    """Schema for a controlled vocabulary tag."""
    name: str
    description: str
    category: str

class TagGenerationOutput(BaseModel):
    """Schema for the LLM's final tag generation output."""
    tags: List[str] = Field(description="The list of generated tags for the document.")