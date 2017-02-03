#Jason Van Kerkhoven
#25/01/2017
####################################################

#import stuff
from time import sleep
import pifacedigitalio as pfio
import socket
import sys


pfio.init()
while(True):
	#get user button press
	btn = [0,0,0]
	while(btn[0] == 0 and btn[1] == 0 and btn[2] == 0): 
		btn[0] = pfio.digital_read(0)
		btn[1] = pfio.digital_read(1)
		btn[2] = pfio.digital_read(2)
	
	#wait for buttons to be release
	while(pfio.digital_read(0) != 0 or pfio.digital_read(1) != 0 or pfio.digital_read(2) != 0):
		#waste time
		empty=0
	
	print("btn0: ", btn[0])
	print("btn1: ", btn[1])
	print("btn2: ", btn[2])
	print("-----------")
	
	for i in range(0,3):
		if(btn[i] == 1):
			print("RETURN: ", i)
			print("\n")
