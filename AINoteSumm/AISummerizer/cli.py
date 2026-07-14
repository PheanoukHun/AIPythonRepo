import argparse
from config import Config

class ParseOptions:

    def __init__(self, configs:Config) -> None:
        self.__parser = self.__create_parser()

    def __create_parser(self):
        parser:argparse.ArgumentParser = argparse.ArgumentParser()