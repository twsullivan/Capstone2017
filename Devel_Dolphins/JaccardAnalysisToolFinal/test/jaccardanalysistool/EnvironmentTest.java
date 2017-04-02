/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaccardanalysistool;

import java.util.ArrayList;
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
    
    private String name; //name of the environment
    private int id; //will be useful when we're comparing environment 1 to 2, 3 to 8, etc. gives us an index.
    private List<String> domains = new ArrayList<String>(); //list of blocked domains
    
    public EnvironmentTest() {
    }
    

    /**
     * Test of addDomain method, of class Environment.
     */
    @Test
    public void testAddDomain() {
        System.out.println("addDomain");
        String blockedDomain = "";
        Environment instance = new Environment(name, id);
        instance.addDomain(blockedDomain);
    }

    /**
     * Test of getTotalNumOfDomains method, of class Environment.
     */
    @Test
    public void testGetTotalNumOfDomains() {
        System.out.println("getTotalNumOfDomains");
        Environment instance = new Environment(name, id);
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
        Environment B = new Environment("blah", 1);
        Environment instance = new Environment(name, id);
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
        //String name = "";
        Environment instance = new Environment(name, id);
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
        Environment instance = new Environment(name, id);
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getID method, of class Environment.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Environment instance = new Environment(name, id);
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
        Environment instance = new Environment(name, id);
        List<String> expResult = new ArrayList<>();
        List<String> result = instance.getDomains();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Environment.
     */
    /*
    @Test
    public void testToString() {
        System.out.println("toString");
        Environment instance = new Environment(name, id);
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }*/
    
}
