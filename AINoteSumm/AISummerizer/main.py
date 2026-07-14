from config import Config
from server_message import MessageServer
from runner import Runner
from cli import ParseOptions

if __name__ == "__main__":
    configs = Config()
    cli = ParseOptions(configs)
    msg_srvr = MessageServer(configs)
    runner = Runner(msg_srvr)
    runner.run()