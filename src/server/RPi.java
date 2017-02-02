/**
*Class:				RPi.java
*Project:           Trial Project
*Author:            Nathaniel Charlebois
*					Jason Van Kerkhoven
*
*Date of Update:    02/02/2016
*Version:           1.0.0
*
*Purpose:           Basically an enum
*					Hold relevant info on the RPi
*
*Update Log:		v1.0.1
*						- structured so all fields are final
*						- accessors removed
*					v1.0.0
*						- built
*/
package server;

import java.net.*;

public class RPi{

    public final InetAddress ip;
    public final int port;


    RPi(InetAddress ip, int port){
        this.ip = ip;
        this.port = port;
    }
}
