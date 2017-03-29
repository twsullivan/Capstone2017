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
public class FormatTest {
    
    public FormatTest() {
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
     * Test of runFormat method, of class Format.
     */
    @Test
    public void testRunFormat() {
        System.out.println("runFormat");
        Format instance = new Format();
        instance.runFormat();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runHReF method, of class Format.
     */
    @Test
    public void testRunHReF() {
        System.out.println("runHReF");
        Format instance = new Format();
        instance.runHReF();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runWeblink method, of class Format.
     */
    @Test
    public void testRunWeblink() {
        System.out.println("runWeblink");
        Format instance = new Format();
        instance.runWeblink();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showURLs method, of class Format.
     */
    @Test
    public void testShowURLs() {
        System.out.println("showURLs");
        Format instance = new Format();
        instance.showURLs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showDomains method, of class Format.
     */
    @Test
    public void testShowDomains() {
        System.out.println("showDomains");
        Format instance = new Format();
        instance.showDomains();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDomains method, of class Format.
     */
    @Test
    public void testGetDomains() {
        System.out.println("getDomains");
        Format instance = new Format();
        ArrayList expResult = null;
        ArrayList result = instance.getDomains();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
