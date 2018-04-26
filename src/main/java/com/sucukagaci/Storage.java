/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucukagaci;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author umurgogebakan
 */
public class Storage implements Serializable {
    private Map<String, Line> lines = new HashMap<>();
    private Map<String, Trip> trips = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private Map<String, Driver> drivers = new HashMap<>();
    private static Storage storage = null;
    
    private static final String FILE_NAME  = "/Users/umurgogebakan/Desktop/ProjectData/SucukAgaciData"; 
    
    static {
        File fl;
        fl = new File(FILE_NAME);
        if( fl.exists() && fl.isFile()) {
            try {
                storage = load();
            } catch (IOException ex) {
                Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Storage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if ( storage == null )
            storage = new Storage();
    }
    private Storage() {

    }
    
    public static Storage getStorage() {
        return storage;
    }
    
    public boolean addLine(String str, Line ln) {
        lines.put(str, ln);
        return true;
    }
    
    public boolean deleteLine(String str) {
        
               lines.remove(str);
               return true;
          
    }
    
    public String[] listLines() {
        Set<String> st = lines.keySet();
        String[] result = new String[st.size()];
        int i = 0;
        for ( String ks:st ) {
            result[i] = ks;
            i++;
        }
        return result;
    }
    
    public Line getLine(String name ) {
        return lines.get(name);
    }
    
    public boolean addTrip( String str, Trip trp) {
        trips.put(str, trp);
        return true;
    }
    
        public boolean deleteTrip(String str) {
        
        trips.remove(str);
        return true;
    }
        
    public boolean addUser( String str , User usr) {
        users.put(str, usr);
        return true;
    }
    
    public User getUser( String key ) {
        return users.get(key);
    }
    
    public boolean userExist( String key ) {
        return users.containsKey(key);
    }
    
    public boolean addDriver( String key, Driver driver ) {
        drivers.put(key, driver);
        return true;
    }
    
    public Driver getDriver( String key ) {
        return drivers.get(key);
    }
    
    public boolean driverExists( String key ) {
        return drivers.containsKey(key);
    }
        
       
    public void save() throws IOException {
        File fl;
        fl = new File(FILE_NAME);
        FileOutputStream fos = new FileOutputStream(fl);
        ObjectOutputStream stream = new ObjectOutputStream(fos);
        stream.writeObject(this);
        stream.close();
        fos.close();
        
    }
    
    public static Storage load() throws IOException, ClassNotFoundException {
        Storage strg;
        File fl;
        fl = new File(FILE_NAME);
        FileInputStream ins = new FileInputStream(fl);
        ObjectInputStream stream = new ObjectInputStream(ins);
        strg = (Storage) stream.readObject();
        return strg;
    }
        
    
}
