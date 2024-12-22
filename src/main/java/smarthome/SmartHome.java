package smarthome;

import smarthome.dialogs.*;

public class SmartHome {
    public static void main(String[] args) {
        try{
            new Dialog().start(args);
        } catch(Exception e){
            e.printStackTrace();
        }  
    }
}