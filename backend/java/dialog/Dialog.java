package backend.java.dialog;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.regex.Pattern;
import backend.java.server.*;

public class Dialog implements Runnable{
    private static final Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

    private ServerSocket serverSocket;

    public Dialog(){
        
    }

    public Dialog(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }
   
    public void start(String[] args){
        int port;
        int backlog;

        if (args.length == 1){
            String mode = args[0];

            switch (mode){
                case "start":
                    port = 4200;
                    backlog = 4;
                    new Server(port,backlog).start();                    
                break;

                case "startDefineProperties":
                    try{
                        Scanner scanner = new Scanner(System.in);
                        boolean ok = false;

                        System.out.println("Port:");
                        port = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("Backlog:");
                        backlog = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("IPAddress:");
                        String ip = scanner.nextLine();

                        ok = portOK(port);
                        ok = backlogOK(backlog);

                        boolean ipOK = ipOk(ip);

                        if (ipOK = true){
                            ok = true;
                        } else {
                            System.out.println("Invalid IP");
                            ok = false;
                        }

                        if (ok = true){
                            new Server(port,backlog).start();
                        }

                        scanner.close();
                    } catch(NumberFormatException e){
                        System.out.println("Invalid Port or Number");
                    }
                break;

                case "":
                    System.out.println("Please enter an option");        
                break;

                case "help":
                    System.out.println("Command pattern: java SmartHome 'option'. Options: start, startDefineProperties");
                break;
            }
        } else {
            System.out.println("Invalid command pattern");
        }
    }

    @Override
    public void run() {
        boolean DO = false;
        Scanner scanner = new Scanner(System.in);

        if (serverSocket == null){
            System.out.println("Terminal startet without initialising the Server. Terminal closed.");
            scanner.close();
        } else {
            DO = true;
        }

        while(DO){
            String command = scanner.nextLine();
            boolean commandAccepted = false;

            switch (command){

                case "Close Server":
                    try{
                        serverSocket.close();
                    } catch(IOException e){
                        e.printStackTrace();
                    }
                    
                    commandAccepted = true;
                    DO = false;
                    scanner.close();
                break;

                case "Add-Entry Whitelist":
                    System.out.println("");
                break;

                case "Delete-Entry Whitelist":
                    
                break;
            }

            if (commandAccepted = false){
                System.out.println("Command does not exist");
            }
        }    
    }

    public boolean portOK(int port){
        if (port > 9999){
            System.out.println("Invalid port");
            return false;
        } else if(port < 1000){
            System.out.println("Invalid port");
            return false;
        } return true;
    }
    
    public boolean backlogOK(int backlog){
        if (backlog > 10){
            System.out.println("Invalid backlog. Min: 1 Max: 10");
            return false;
        } else if(backlog < 1){
            System.out.println("Invalid backlog. Min: 1 Max: 10");
            return false;
        } return true;
    }

    public boolean ipOk(String ip) {
        return PATTERN.matcher(ip).matches();
    }
}   
