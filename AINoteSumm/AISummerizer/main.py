#!/usr/bin/env python

from config import Config
from server_message import MessageServer
from runner import Runner
from cli import CliOptions, ParseOptions

if __name__ == "__main__":
    configs = Config()
    parse_opts = ParseOptions(configs)
    cli_opts = CliOptions(parse_opts)
    msg_srvr = MessageServer(configs)
    runner = Runner(msg_srvr, options=cli_opts.options)
    runner.run()
