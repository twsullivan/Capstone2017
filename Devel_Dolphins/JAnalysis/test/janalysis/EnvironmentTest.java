/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janalysis;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Delikarl
 */
public class EnvironmentTest {
    
    public EnvironmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addDomain method, of class Environment.
     */
    @Test
    public void testAddDomain() {
        System.out.println("addDomain");
        String blockedDomain = "";
        Environment instance = null;
        instance.addDomain(blockedDomain);
    }

    /**
     * Test of getTotalNumOfDomains method, of class Environment.
     */
    @Test
    public void testGetTotalNumOfDomains() {
        System.out.println("getTotalNumOfDomains");
        Environment instance = null;
        int expResult = 0;
        int result = instance.getTotalNumOfDomains();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCrossSection method, of class Environment.
     */
    @Test
    public void testGetCrossSection() {
        System.out.println("getCrossSection");
        Environment B = null;
        Environment instance = null;
        int expResult = 0;
        int result = instance.getCrossSection(B);
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class Environment.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        String name = "";
        Environment instance = null;
        boolean expResult = false;
        boolean result = instance.contains(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Environment.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Environment instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getID method, of class Environment.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Environment instance = null;
        int expResult = 0;
        int result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDomains method, of class Environment.
     */
    @Test
    public void testGetDomains() {
        System.out.println("getDomains");
        Environment instance = null;
        List<String> expResult = null;
        List<String> result = instance.getDomains();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Environment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Environment instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
