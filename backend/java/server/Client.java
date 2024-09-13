package backend.java.server;

public class Client{
    String ip;
    Integer port;
    String hostname;

    public Client(String ip, Integer port, String hostname){
        this.ip = ip;
        this.port = port;
        this.hostname = hostname;
    }

    public void setIp(String ip){
        this.ip = ip;
    }

    public void setPort(Integer port){
        this.port = port;
    }

    public void setHostname(String hostname){
        this.hostname = hostname;
    }

    public String getIp(){
        return ip;
    }

    public Integer getPort(){
        return port;
    }

    public String getHostname(){
        return hostname;
    }
}