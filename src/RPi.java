import java.net.*;

public class RPi{

    InetAddress ip;
    int port;


    RPi(InetAddress ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public InetAddress getIP(){
        return ip;
    }

    public int getPort(){
        return port;
    }
}
