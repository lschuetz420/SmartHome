package smarthome.server;

import java.net.*;

import smarthome.managers.ScreenManager;
import smarthome.util.ErrorHandler;

import java.io.*;
import java.io.IOException;


public class ClientHandler implements Runnable{

    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
        
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));   
            
            String request = reader.readLine();

            if (request == null){
                return;
            }

            boolean ContainsGET = request.contains("GET");

            if (ContainsGET = false){
                return;
            }

            if (request.contains("login")){
                new ScreenManager(output).showLogin();
            } else {
                output.write("Site not found".getBytes());
                return;
            }
        
        } catch(IOException e){
            new ErrorHandler().printToConsoleAddLog(e);
        }
        
    }

    public void awaitResponse(){

    }
}