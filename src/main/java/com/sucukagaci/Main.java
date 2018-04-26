/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucukagaci;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author umurgogebakan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
//        Storage.getStorage().addLine("Bilkent Kizilay", new Line());
//        Line ln = Storage.getStorage().getLine("Bilkent Kizilay");
//        ln.getDestinations().add(new Destination("Kizilay", 50, 30));
//        ln.getDestinations().add(new Destination("Kutuphane", 80.21, 45));
//        ln.getDestinations().add(new Destination("Bilkent", 100.3, 33.67));
//        Storage.getStorage().save();
        Gson gs = new Gson();
          System.out.println(gs.toJson(Storage.getStorage().listLines()));
          System.out.println(gs.toJson(Storage.getStorage().getLine("Kizilay Bilkent").getDestinations()));
          System.out.println(gs.toJson(Storage.getStorage().getLine("Bilkent Kizilay").getDestinations()));
    }
    
}
