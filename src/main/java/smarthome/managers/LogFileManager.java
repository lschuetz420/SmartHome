package smarthome.managers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogFileManager{

    private File logFile;
    private String path = "C:/Projects/SmartHome/src/main/java/smarthome/data/log.txt";

    private FileWriter fileWriter;

    public LogFileManager() throws IOException{
        logFile = new File(path);
        logFile.setWritable(true);
        fileWriter = new FileWriter(logFile, true);
        logFile.setReadOnly();
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