package smarthome.managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogFileManager{

    File logFile;
    String path = "./data/logs/Serverlogs.txt";
    FileWriter fileWriter;

    public LogFileManager() throws IOException{
        this.logFile = new File(path);
        this.fileWriter = new FileWriter(logFile, true);
        this.logFile.setReadOnly();
    }

    public void addLog(String log) throws IOException{
        logFile.setWritable(true);
        fileWriter.write(log + "\n");
        logFile.setReadOnly();
    }

    public String getLogs() throws IOException{
        String logs = Files.readString(Path.of(path));
        return logs;
    }
}