/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrappertest;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryang
 */
public class JSONTest {
    
    public JSONTest() {
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

    /**
     * Test of makeJSON method, of class JSON.
     */
    @Test
    public void testMakeJSON() {
        System.out.println("makeJSON");
        JSON instance = new JSON();
        instance.makeJSON();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNameList method, of class JSON.
     */
    @Test
    public void testSetNameList() {
        System.out.println("setNameList");
        ArrayList passedList = null;
        JSON instance = new JSON();
        instance.setNameList(passedList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUser method, of class JSON.
     */
    @Test
    public void testSetUser() {
        System.out.println("setUser");
        String passed = "";
        JSON instance = new JSON();
        instance.setUser(passed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setListID method, of class JSON.
     */
    @Test
    public void testSetListID() {
        System.out.println("setListID");
        String passed = "";
        JSON instance = new JSON();
        instance.setListID(passed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setListDescription method, of class JSON.
     */
    @Test
    public void testSetListDescription() {
        System.out.println("setListDescription");
        String passed = "";
        JSON instance = new JSON();
        instance.setListDescription(passed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
