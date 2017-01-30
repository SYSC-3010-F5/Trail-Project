import java.net.*;

public class RPi{

    InetAddress ip;
    int port;


    RPi(int port, InetAddress ip){
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
