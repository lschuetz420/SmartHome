package backend.java.Managers;

import java.net.*;
import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class ScreenManager{

    private OutputStream output;
    
    public ScreenManager(OutputStream output){
        this.output = output;
    }

    public void showLogin(){
        try {
            File file = new File("./frontend/html/loginscreen.html");
            Document doc = Jsoup.parse(file, "UTF-8");
            output.write(doc.outerHtml().getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}