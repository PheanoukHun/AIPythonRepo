import argparse

class ParseOptions:

    def __init__(self) -> None:
        self.__parser = self.__create_parser()

    def __create_parser(self):
        parser:argparse.ArgumentParser = argparse.ArgumentParser()