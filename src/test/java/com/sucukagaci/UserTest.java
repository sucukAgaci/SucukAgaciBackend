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
public class UserTest {
    
    public UserTest() {
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

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        User x = new User("abc", "abc", "abc", "abc");
        x.addFeedback(new Feedback(3, "Merhaba"));
        assertEquals("Email hatali", "abc", x.getEmail());
        assertEquals("Password hatali", "abc", x.getPassword());
        assertEquals("Name hatali", "abc", x.getName());
        assertEquals("Surnma hatali", "abc", x.getSurname());
        assertEquals("Feedback sayisi hatali", 1, x.getFeedbacks().size());
        assertEquals("Feedback yanlis", new Feedback(3, "Merhaba"), x.getFeedbacks().get(0));
        
    }
    
}
