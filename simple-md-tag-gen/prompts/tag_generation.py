from typing import List

class TagPromptGenerator:
    """
    Generates the structured prompt for the LLM based on the tagging workflow.
    """
    @staticmethod
    def generate_prompt(
        document_content: str, 
        existing_tags: List[str], 
        similar_document_tags: List[str]
    ) -> str:
        """
        Constructs the full prompt template for the Tag Generation Agent.
        """
        prompt = f"""
You generate tags for markdown documents.

Existing tags:
{chr(10).join([f' "{tag}"' for tag in existing_tags])}

Similar document tags:
{chr(10).join([f' "{tag}"' for tag in similar_document_tags])}

Document:
-----------
{document_content}
-----------

Return 5-10 tags.
Prefer existing tags.
Create new tags only when necessary.

Return JSON only.
"""
        return prompt.strip()
