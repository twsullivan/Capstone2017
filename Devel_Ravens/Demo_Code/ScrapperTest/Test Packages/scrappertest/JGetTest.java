/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrappertest;

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
public class JGetTest {
    
    public JGetTest() {
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
     * Test of runJGet method, of class JGet.
     */
    @Test
    public void testRunJGet() throws Exception {
        System.out.println("runJGet");
        JGet instance = new JGet();
        instance.runJGet();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutput method, of class JGet.
     */
    @Test
    public void testGetOutput() {
        System.out.println("getOutput");
        JGet instance = new JGet();
        String expResult = "";
        String result = instance.getOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutput method, of class JGet.
     */
    @Test
    public void testSetOutput() {
        System.out.println("setOutput");
        String outFileName = "";
        JGet instance = new JGet();
        instance.setOutput(outFileName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getURL method, of class JGet.
     */
    @Test
    public void testGetURL() {
        System.out.println("getURL");
        JGet instance = new JGet();
        String expResult = "";
        String result = instance.getURL();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setURL method, of class JGet.
     */
    @Test
    public void testSetURL() {
        System.out.println("setURL");
        String url = "";
        JGet instance = new JGet();
        instance.setURL(url);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
