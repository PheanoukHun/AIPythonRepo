import sys
from pathlib import Path

from valid_path import PATH_VALIDITY, interpret_results, is_valid_path


class FileScanner:
    def __init__(self, directory_path: str):
        self.directory_path: Path = Path(directory_path)

    def get_all_files(self) -> list[str]:
        is_valid_dir:PATH_VALIDITY = is_valid_path(str(self.directory_path))
        interpret_results(is_valid_dir)
        if not self.directory_path.is_dir():
            raise NotADirectoryError(f"Not a directory: {self.directory_path}")
        return [str(p) for p in self.directory_path.rglob("*") if p.is_file()]


if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("\nUsage: python file_scanner.py <dir_path>\n")
        sys.exit(1)

    dir_path: str = sys.argv[1]

    print("Files: ")
    print(*FileScanner(dir_path).get_all_files(), sep="\n")
