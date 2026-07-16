#!/usr/bin/env python

from configurers.cli import CLI_Options
from configurers.config import Config
from server_interacting.runner import Runner
from server_interacting.server_message import MessageServer

if __name__ == "__main__":
    configs = Config()
    cli_opts = CLI_Options(configs)
    msg_srvr = MessageServer(configs)
    runner = Runner(msg_srvr, options=cli_opts.options)
    runner.run()
