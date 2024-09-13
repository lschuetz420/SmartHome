package backend.java.main;

import backend.java.dialog.*;

public class SmartHome {
    public static void main(String[] args) {
        try{
            new Dialog().start(args);
        } catch(NullPointerException e){
            e.printStackTrace();
        } catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        } catch(ArithmeticException e){
            e.printStackTrace();    
        } catch(IllegalArgumentException e){
            e.printStackTrace();
        } catch (IllegalStateException e){
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }  
    }
}