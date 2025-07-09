package smarthome.server;

import smarthome.managers.ScreenManager;
import smarthome.util.ErrorHandler;

import java.net.*;
import java.util.ArrayList;
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
            ArrayList<String> request = new ArrayList<String>();

            while (reader.readLine() != null && !reader.readLine().isEmpty()){
                request.add(reader.readLine());
            }
            
            String line0 = request.get(0);

            boolean ContainsGET = line0.contains("GET");

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