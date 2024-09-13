package backend.java.server;

import java.io.*;
import java.net.*;
import java.lang.Thread;
import java.time.*;
import java.util.logging.LogManager;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.*;

import backend.java.Managers.*;
import backend.java.dialog.Dialog;

public class Server{

    private boolean ON = false;
    private int port;
    private int backlog; 
    private int clientCounter;

    private ServerSocket serverSocket;

    public Server(int port, int backlog){
            this.port = port;
            this.backlog = backlog;
    }

    public void start(){
        try{
            if (this.ON = false){
                serverSocket = new ServerSocket(port,backlog);

                Client Clients[] = new Client[backlog];

                System.out.println("Server listening on port " + port);

                ON = true;    

                new Thread(new Dialog(serverSocket)).start();

                while(ON){

                    Socket clientSocket = serverSocket.accept();
                    
                    String clientIP = clientSocket.getInetAddress().getHostAddress();
                    String clientName = clientSocket.getInetAddress().getHostName();
                    Integer clientPort = clientSocket.getPort();
                    
                    LocalDate date = LocalDate.now();
                    LocalTime time = LocalTime.now();
                    String log = "New User connected. IP: " + clientIP + " Port " + clientPort + " HostName: " + clientName + " Date: " + date + " Time: " + time;
                    new LogFileManager().addLog(log);
                    System.out.println(log);
                    
                    WhitelistManager whitelistManager = new WhitelistManager();
                    String clientData = "IP: " + clientIP + " Port " + clientPort + " HostName: " + clientName;
                    
                    if (whitelistManager.ClientOK(clientData)){
                        new Thread(new ClientHandler(clientSocket)).start();
                    } else{
                        String logNotWhitelisted = "User not whitelisted. Connection refused."; 
                        System.out.println(logNotWhitelisted);
                        new LogFileManager().addLog(logNotWhitelisted);
                    }
                }
            } else {

            }    
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

    public Integer getCLientCounter(){
        return this.clientCounter;
    }

    public void reduceClientCounterByOne(){
        this.clientCounter--;
    }

    public void increaseClientCounterByOne(){
        this.clientCounter++;
    }
    
}