#Team F5
#Nathaniel Charlebois
#30/01/2017
####################################################

#import libraries
from time import sleep
import pifacedigitalio as pfio
import socket, sys, time, json
import fcntl
import struct

#Setting port and serverIP
host = sys.argv[1]
textport = sys.argv[2]

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = int(textport)
server_address = (host, port)


#Hacky way to get the client IP without an External Package
def get_ip_address(ifname):
    so = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    return socket.inet_ntoa(fcntl.ioctl(
        so.fileno(),
        0x8915,  # SIOCGIFADDR
        struct.pack('256s', ifname[:15])
    )[20:24])

#PROGRAM START
####################################################
#initialize pface
pfio.init()

#Setting up


# json dictionary
data={'ip': get_ip_address('eth0'), 'port': textport}  # '192.168.0.110'}


#flash LEDs
def flashLEDs():
    for i in range(0,7,-1):
        pfio.digital_write(i,0)
