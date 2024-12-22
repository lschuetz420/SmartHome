package smarthome.server;

import smarthome.server.Client;

public class Client{
    String ip;
    Integer port;
    String hostname;

    public Client(String ip, Integer port, String hostname){
        this.ip = ip;
        this.port = port;
        this.hostname = hostname;
    }

    public Client(String Clientdata){
        String[] values = Clientdata.split(" ");
        
        for (int i = 0; i < values.length; i++){

            switch(values[i]){

                case "IP:":
                    values[i] = values[i + 1];
                break;  

                case "Port:":
                    values[i] = values[i + 1];
                break;

                case "HostName:":
                    values[i] = values[i + 1];
                break;
            }
        }

        values[1] = values[2];
        values[2] = values[3];

        this.ip = values[0];
        this.port = Integer.parseInt(values[1]);
        this.hostname = values[2];
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

    public String toString(){
        String clientData = "IP: " + this.ip + " Port: " + this.port + " HostName: " + this.hostname;
        return clientData;
    }
}