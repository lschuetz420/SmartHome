package backend.java.Managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WhitelistManager{

    public FileWriter fileWriter;
    public File whitelistFile;

    public WhitelistManager() throws IOException{
        this.whitelistFile = new File("./backend/java/properties/whitelist.txt");
        this.fileWriter = new FileWriter(whitelistFile, true);
        this.whitelistFile.setReadOnly();
    }

    public void addEntry(String entry) throws IOException{
        whitelistFile.setWritable(true);
        fileWriter.write(entry + "\n");
        whitelistFile.setReadOnly();
    }

    public String getWhitelist() throws IOException{
        String whitelist = Files.readString(Path.of("./backend/java/properties/whitelist.txt"));
        return whitelist;
    }

    public boolean ClientOK(String clientdata){
        boolean ok;

        try{
            String whitelist = getWhitelist();
        } catch(IOException e){
            e.printStackTrace();
        }

    }
}