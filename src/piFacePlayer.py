#Jason Van Kerkhoven
#25/01/2017
####################################################

#import stuff
from time import sleep
import pifacedigitalio as pfio
import socket
import sys



#PROGRAM CONSTANTS
####################################################
PORT = 3010
IP = "10.0.0.1"
DUMMY_DATA = "sudo ./init_sysc3010"



#PROGRAM VARIABLES
####################################################
port = 0
gpSocket = 0



#DEFINING FUNCTIONS
####################################################
#send data to IP, on port specified at runtime
def sendPacket(toSend):
	gpSocket.sendto(toSend.encode('utf-8'), (IP, port))
	
	
	
#send data to IP, on port specified at runtime
def sendPacketByteArr(byteArr):
	gpSocket.sendto(byteArr, (IP, port))
	
	
	
#receive data on gpsocket
def receivePacket():
	print("Waiting for packet on port: ", gpSocket.getsockname()[1])
	data, address = gpSocket.recvfrom(port)
	print("Packet received!")
	return data



#get a button press (with debouncing)
def getButton():
	#get user button press
	btn = [0,0,0]
	while(btn[0] == 0 and btn[1] == 0 and btn[2] == 0): 
		btn[0] = pfio.digital_read(3)
		btn[1] = pfio.digital_read(2)
		btn[2] = pfio.digital_read(1)
	
	#wait for buttons to be release
	while(pfio.digital_read(3) != 0 or pfio.digital_read(2) != 0 or pfio.digital_read(1) != 0):
		#waste time
		sleep(0.125)

	for i in range(0,3):
		if(btn[i] == 1):
			return i



#process packet
def processPacket(data):
	#flash lights
	for d in data:
		pfio.digital_write(d,1)
		sleep(1)
		pfio.digital_write(d,0)
		sleep(0.5)
	
	#iterate through entire byte array 
	for b in data:
		#get user button press
		button = getButton()
		
		#button pushed was NOT correct
		if (button != b):
			return False
	
	#user completed entire pattern correct
	return True
	


#PROGRAM START
####################################################
#initialize pface
pfio.init()
for i in range(0,7,-1):
    pfio.digital_write(i,0)
   
#get server port
while(True):
	try:
		port = input("When server is live, please enter to port used\nPort: ")
		port = int(port)
		break;
	except ValueError:
		print("ERROR: Port number must be a valid 32bit integer\n")

#init socket
print("Initializing inSocket...")
gpSocket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
#gpSocket.bind(('localhost', 0))

#connect to server
print("Attempting to connect to server...")
sendPacket(DUMMY_DATA)

#gameplay loop
while(True):
	#wait for pattern info from server and process
	data = receivePacket()
	patternCorrect = processPacket(data)
			
	#have user add to pattern
	if(patternCorrect):
		print("Correct!\nPlease enter NEW button")
		
		#get next button in pattern
		button = getButton()

		#append new button to pattern
		toSend = bytearray()
		for d in data:
			toSend.append(d)
		toSend.append(button)
		
		#send
		sendPacketByteArr(toSend)
	#user answered wrong error
	else:
		print("Incorrect!")
		toSend = "!"

		#send
		sendPacket(toSend)



#1 2 0 0 1


