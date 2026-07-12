from AISummerizer.main import msg_srvr
from config import Config
from server_message import MessageServer
from runner import Runner

if __name__ == "__main__":
    configs = Config()
    msg_srvr = MessageServer(configs)
    runner = Runner(msg_srvr)
    print(runner.single_run())