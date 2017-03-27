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
 * @author CodyM
 */
public class DriverTest {
    
    public DriverTest() {
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
     * Test of runDriver method, of class Driver.
     */
    @Test
    public void testRunDriver() {
        System.out.println("runDriver");
        Driver instance = new Driver();
        instance.runDriver();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gainDomainNames method, of class Driver.
     */
    @Test
    public void testGainDomainNames() throws Exception {
        System.out.println("gainDomainNames");
        Driver instance = new Driver();
        instance.gainDomainNames();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of continueDomainNames method, of class Driver.
     */
    @Test
    public void testContinueDomainNames() {
        System.out.println("continueDomainNames");
        Driver instance = new Driver();
        instance.continueDomainNames();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of outputJSONFile method, of class Driver.
     */
    @Test
    public void testOutputJSONFile() {
        System.out.println("outputJSONFile");
        Driver instance = new Driver();
        instance.outputJSONFile();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showList method, of class Driver.
     */
    @Test
    public void testShowList() {
        System.out.println("showList");
        Driver instance = new Driver();
        instance.showList();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameList method, of class Driver.
     */
    @Test
    public void testGetNameList() {
        System.out.println("getNameList");
        Driver instance = new Driver();
        ArrayList expResult = null;
        ArrayList result = instance.getNameList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of usageDump method, of class Driver.
     */
    @Test
    public void testUsageDump() {
        System.out.println("usageDump");
        Driver instance = new Driver();
        instance.usageDump();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
