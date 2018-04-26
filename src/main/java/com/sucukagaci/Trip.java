/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucukagaci;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author umurgogebakan
 */
public class Trip implements Serializable {

    private Driver driver;
    private Vehicle car;
    private int emptySeats;
    private List<Destination> points = new ArrayList<>();

    public Trip() {
    }

    public Trip(Driver drvr, Vehicle car, Line ln) {
        this.driver = drvr;
        this.car = car;
        this.emptySeats = car.getSeats();
        points.addAll(ln.getDestinations());
        
        if(!driver.getVehicles().contains(car))
            Logger.getLogger("trip").log(Level.SEVERE, "Arac Surucuye Ait Gozukmuyor {0} {1}", new Object[]{driver, car});
    }

    public List<Destination> getPoints() {
        ArrayList<Destination> x = new ArrayList<>();
        x.addAll(points);
        return x;
    }
    
    
    
    
}
