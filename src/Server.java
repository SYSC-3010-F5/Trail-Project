/**
*Class:             Server.java
*Project:           Trial Project
*Author:            Nathaniel Charlebois
*Date of Update:    30/01/2016
*Version:           1.0.0
*
*Purpose:           Manage the communication between two RPi and relay instructions
*
*Update Log:
*					v1.0.0
*						- Added basic functionality
                        - Requires testing on a windows/linux machine
*/


import java.net.*;
import java.util.Scanner;


public class Server {

  DatagramSocket receiveSocket;
  DatagramSocket sendSocket;
  DatagramPacket receivePacket;
  DatagramPacket sendPacket;

  RPi RPi1;
  RPi RPi2;


  private final static int PACKETSIZE = 100 ;

    Server(){
        receivePacket = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
        sendPacket = new DatagramPacket(new byte[PACKETSIZE], PACKETSIZE);
    }


	public static void main(String[] args)
    {


        //Init server
        Server server = new Server();

        //Set desired receivePort
        Scanner in = new Scanner (System.in);
        System.out.print("Enter the receivePort >");
        int receivePort = Integer.parseInt(in.nextLine());

        //Init Sockets
        server.socketInit(receivePort);


        //Check for initial RPi connection
        server.initConnection();

    }

   private void initConnection(){

       receivePacket();
       byte[] data = receivePacket.getData();
       RPi1 = new RPi(0,null);


   }

   private void sendPacket(byte[] data, InetAddress host, int port){
     try
     {
        sendPacket = new DatagramPacket( data, data.length, host, port ) ;
        sendSocket.send( sendPacket ) ;
     }
     catch( Exception e )
     {
        System.out.println( e ) ;
     }
   }

   private void receivePacket(){
      try
      {
            System.out.println( "Receiving on port " + receiveSocket.getPort() ) ;

            receiveSocket.receive( receivePacket ) ;

            System.out.println( receivePacket.getAddress() + " " + receivePacket.getPort() + ": " + new String(receivePacket.getData()).trim() ) ;

      }
      catch( Exception e )
      {
         System.out.println( e ) ;
      }
   }

   private void shutdown(){
        if( receiveSocket != null )
            receiveSocket.close();
        if( sendSocket != null )
            sendSocket.close();

   }


   //Seems to set both ports to -1 on my mac(strange permissions)
   //Gotta try on a windows machine
   private void socketInit(int port){

       try{
           receiveSocket = new DatagramSocket( port );
           System.out.println("receiveSocket set to port " + receiveSocket.getPort());
       }
       catch(Exception e){
           e.printStackTrace();
       }

       try{
           sendSocket = new DatagramSocket();
           System.out.println("sendSocket set to port " + sendSocket.getPort());
       }
       catch(Exception e){
           e.printStackTrace();
       }
   }
}
