package smarthome.server;

import java.io.*;
import java.net.*;
import java.lang.Thread;
import java.time.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import smarthome.dialogs.*;
import smarthome.managers.*;

public class Server{
    private static Server instance;

    private boolean ON = false;
    private int port;
    private int backlog; 
    private int clientCounter;
    
    private ServerSocket serverSocket;

    ArrayList<Client> clients = new ArrayList<Client>();

    public Server(){

    }

    public Server(int port, int backlog){
        this.port = port;
        this.backlog = backlog;
    }

    public static Server getInstance(){
        if (instance == null){
            instance = new Server();
        }

        return instance;
    }

    public void start(){
        try{
            if (ON == false){
                serverSocket = new ServerSocket(port,backlog);

                System.out.println("Server listening on port " + port);

                ON = true;    

                new Thread(new Dialog(serverSocket)).start();

                while(ON){

                    Socket clientSocket = serverSocket.accept();
                    
                    String clientIP = clientSocket.getInetAddress().getHostAddress();
                    Integer clientPort = clientSocket.getPort();
                    String clientName = clientSocket.getInetAddress().getHostName();
                    Client client = new Client(clientIP, clientPort, clientName);
                    clients.add(client);

                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.now();
                    String log = "New user connected. IP: " + clientIP + " Port " + clientPort + " HostName: " + clientName + " Date: " + date + " Time: " + time;
                    new LogFileManager().addLog(log);
                    System.out.println(log);
                    
                    WhitelistManager whitelistManager = new WhitelistManager();

                    if (whitelistManager.ClientOK(client)){
                        new Thread(new ClientHandler(clientSocket)).start();
                    } else{
                        String logNotWhitelisted = "User not whitelisted. Connection refused."; 
                        System.out.println(logNotWhitelisted);
                        new LogFileManager().addLog(logNotWhitelisted);
                    }
                }
            } else {

            }  
        } catch(SocketException e){    
            System.out.println("Server closed");
        } catch(IOException e){
            System.out.println("Server exception");
            e.printStackTrace();
        }
    }   

    public boolean getONState(){
        return this.ON;
    }

    public void close() throws IOException{
        ON = false;
        if (serverSocket != null){
            serverSocket.close();
        } else {
            System.out.println("Server not open");
        }
    }

    public void setClientCounter(Integer clientCounter){
        this.clientCounter = clientCounter;
    }

    public void setPort(int port){
        this.port = port;
    }

    public void setBacklog(int backlog){
        this.backlog = backlog;
    }

    public Integer getCLientCounter(){
        return this.clientCounter;
    }

    public ArrayList<Client> getClients(){
        return this.clients; 
    }

    public void reduceClientCounterByOne(){
        this.clientCounter--;
    }

    public void increaseClientCounterByOne(){
        this.clientCounter++;
    }
    
}