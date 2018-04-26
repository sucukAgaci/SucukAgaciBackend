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

/**
 *
 * @author umurgogebakan
 */
public class Driver implements Serializable {
    private User usr;
    private List<Vehicle> vehicles = new ArrayList<>();

    public Driver(User usr) {
        this.usr = usr;
    }

    public Driver() {
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public List<Vehicle> getVehicles() {
        ArrayList<Vehicle> x = new ArrayList<>();
        x.addAll(vehicles);
        return x;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles.addAll(vehicles);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.usr);
        hash = 29 * hash + Objects.hashCode(this.vehicles);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Driver other = (Driver) obj;
        if (!Objects.equals(this.usr, other.usr)) {
            return false;
        }
        if (!Objects.equals(this.vehicles, other.vehicles)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Driver{" + "usr=" + usr + ", vehicles=" + vehicles + '}';
    }
    
    
    
}
