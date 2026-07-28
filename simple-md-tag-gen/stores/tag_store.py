import json
import os
from typing import List


class TagStore:
    def __init__(self, file_path: str = "tags_vocabulary.json"):
        self.file_path = file_path
        self.tags: List[str] = []
        self._load()

    def _load(self):
        if os.path.exists(self.file_path):
            with open(self.file_path, 'r') as f:
                data = json.load(f)
                self.tags = data.get('tags', [])
            print(f"Loaded {len(self.tags)} tags from {self.file_path}")

    def save(self):
        with open(self.file_path, 'w') as f:
            json.dump({"tags": self.tags}, f, indent=2)

    def get_all_tags(self) -> List[str]:
        return self.tags.copy()

    def add_tags(self, new_tags: List[str]) -> List[str]:
        added = []
        for tag in new_tags:
            if tag not in self.tags:
                self.tags.append(tag)
                added.append(tag)
        if added:
            self.save()
            print(f"Added {len(added)} new tag(s) to {self.file_path}: {added}")
        return added
