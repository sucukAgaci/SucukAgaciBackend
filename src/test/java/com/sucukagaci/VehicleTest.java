/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sucukagaci;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author umurgogebakan
 */
public class VehicleTest {
    
    public VehicleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @org.junit.Test
    public void testClass() {
        Vehicle x = new Vehicle("Mercedes", "Green", "06 ag 123", 3);
        assertEquals("model gecersiz", "Mercedes", x.getModel());
        assertEquals("Renk gecersiz", "Green", x.getColor());
        assertEquals("plaka gecersiz", "06 ag 123", x.getPlates());
        assertEquals("seat sayisi gecersiz", 3, x.getSeats());
        
    }
    
}
