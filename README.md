RevShell
========

A collection of easy to understand reverse shells in many different languages.

hark
====

RevShell's Universal Listener. - Currently only netcat wrapper

jRevShell
=========

Java reverse shells:

* jRevCat - Light weight Netcat reverse shell in java. Downloads the correct ncat version/architecture for the OS (Windows or x86/i686 Linux for now).

* jRevBash - Light weight bash reverse shell in java. Tries a couple of different techniques to force bash to forward (nix only).

PLANNED
=======
* jRevShell - Complete reverse shell, nothing excluded. Tries many techniques to get the shell through including; netcat (local and download), bash, raw sockets, xterm, and eventually more. Windows, Linux, OS X, BSD.

* jRevLCat - A heavier version of jRevCat that packages netcat inside specified JAR (or inside the java itself as variable).

* jRevMet - Attempt to get a meterpreter reverse shell by downloading the executable.

* jRevMetL - Package meterpreter shell into JAR (or inside the java itsef).

* jRevWeb - Creates a website to pass on commands (IDS evasion). 

pyRevShell
=========

Python reverse shells:

rRevShell
=========

Ruby reverse shells:

Licensing
=========

See LICENSE file for more info. The ncat tools that are packaged with some of the code are under CCv3 (see more here: http://nmap.org/book/man-legal.html#nmap-copyright )
