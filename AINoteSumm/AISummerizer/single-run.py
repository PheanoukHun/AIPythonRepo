from config import Config
from server_message import MessageServer
from runner import Runner

if __name__ == "__main__":
    configs = Config()
    msg_srv = MessageServer(configs)
    runner = Runner(msg_srv)
    print(runner.single_run())