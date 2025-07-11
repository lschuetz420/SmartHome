package smarthome.server;

import smarthome.managers.ScreenManager;
import smarthome.util.ErrorHandler;

import java.net.*;
import java.util.ArrayList;
import java.io.*;
import java.nio.file.*;


public class ClientHandler implements Runnable{
    
    private Socket socket;

    public ClientHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            boolean awaitResponse = true;

            while (awaitResponse){

                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));   
                ArrayList<String> request = new ArrayList<String>();
                
                while (reader.readLine() != null){
                    request.add(reader.readLine());
                }
                
                for (int i = 0; i < request.size(); i++){
                    System.out.println(request.get(i));
                }
                
                String line0 = request.get(0);
                
                if (line0.contains("GET / HTTP/1.1") | line0.contains("GET /index.html HTTP/1.1")){
                    sendFileAsOutput("index.htm", output);
                } else if (line0.contains("GET /style.css")){
                    sendFileAsOutput("style.css", output);   
                } else if (line0.contains("GET /script.js")){
                    sendFileAsOutput("script.js", output);
                } else {
                    sendTextAsOutput("404 Not Found", "404 Not Found", output);
                }
            
            }
        } catch(IOException e){
            new ErrorHandler().printToConsoleAddLog(e);
        }
        
    }

    private void sendFileAsOutput(String fileName, OutputStream output) throws IOException{

        String contentType = "";

        if (fileName.endsWith(".html")){
            contentType = "text/html; charset=UTF-8s";
        }

        if (fileName.endsWith(".css")){
            contentType = "text/css";
        }

        if (fileName.endsWith(".javascript")){
            contentType = "application/javascript";
        }

        if (fileName.endsWith(".png")){
            contentType = "image/png";
        }

        if (fileName.endsWith(".jpeg")){
            contentType = "image/jpeg";
        }

        Path path = Path.of("C:\\Projects\\SmartHome\\frontend\\" + fileName);
        byte [] content = Files.readAllBytes(path);
                    	
        PrintWriter writer = new PrintWriter(output);
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: " + contentType);
        writer.println("Content-Length: " + content.length);
        writer.println();
        writer.flush();

        output.write(content);
        output.flush();
    }

    private void sendTextAsOutput(String HTTPstatus, String text, OutputStream output){
        PrintWriter writer = new PrintWriter(output);
        writer.println("HTTP/1.1 " + HTTPstatus);
        writer.println("Content-Type: text/plain");
        writer.println("Content-Length: " + Integer.toString(text.length()));
        writer.println();
        writer.println(text);
        writer.flush();
    }

}