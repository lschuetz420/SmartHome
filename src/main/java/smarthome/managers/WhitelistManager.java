package smarthome.managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import smarthome.server.Client;

public class WhitelistManager{
    public FileWriter fileWriter;
    public File whitelistFile;

    public WhitelistManager() throws IOException{
        whitelistFile = new File("C:/Projects/SmartHome/src/main/java/smarthome/properties/Whitelist.properties");
        fileWriter = new FileWriter(whitelistFile);
        whitelistFile.setReadOnly();
    }

    public void addEntry(String value) throws IOException{
        whitelistFile.setWritable(true);
        fileWriter.append(value + "\n");
        fileWriter.close();
        whitelistFile.setReadOnly();
    }

    public void writeEntry(String value) throws IOException{
        whitelistFile.setWritable(true);
        fileWriter.write(value + "\n");
        fileWriter.close();
        whitelistFile.setReadOnly();

    }

    public void addClient(Client client) throws IOException{
        addEntry(client.toString());
    }

    public void deleteClient(Client client) throws IOException{
        String clientData = client.toString();
        String whitelist = "";

        try{
            whitelist = getWhitelist();
        } catch(IOException e){
            e.printStackTrace();
        }

        if (whitelist.contains(clientData)){
            whitelist = whitelist.replace(clientData, "");
            writeEntry(clientData);
        } else {
            System.out.println("Client is not whitelisted");
        }
    }

    public String getWhitelist() throws IOException{
        String whitelist = Files.readString(Path.of("./backend/java/properties/whitelist.txt"));
        return whitelist;
    }

    public boolean ClientOK(Client client){
        boolean ok = false;

        try{
            String whitelist = getWhitelist();
            String clientdata = client.toString();

            if (whitelist.contains(clientdata)){
                ok = true;
            } ok = false;

        } catch(IOException e){
            e.printStackTrace();
        }

        return ok;
    }
}