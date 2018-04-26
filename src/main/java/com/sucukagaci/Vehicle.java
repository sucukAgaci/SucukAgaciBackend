/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucukagaci;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author umurgogebakan
 */
public class Vehicle implements Serializable {
    private String model;
    private String color;
    private String plates;
    private int    seats;

    public Vehicle() {
    }

    public Vehicle(String model, String color, String plates, int seats) {
        this.model = model;
        this.color = color;
        this.plates = plates;
        this.seats = seats;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getPlates() {
        return plates;
    }

    public int getSeats() {
        return seats;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.model);
        hash = 97 * hash + Objects.hashCode(this.color);
        hash = 97 * hash + Objects.hashCode(this.plates);
        hash = 97 * hash + this.seats;
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
        final Vehicle other = (Vehicle) obj;
        if (this.seats != other.seats) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.plates, other.plates)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "model=" + model + ", color=" + color + ", plates=" + plates + ", seats=" + seats + '}';
    }
    
    
    
    
    
}
