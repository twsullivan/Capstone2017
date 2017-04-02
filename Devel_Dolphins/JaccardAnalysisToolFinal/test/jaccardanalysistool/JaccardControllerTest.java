/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaccardanalysistool;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Delikarl
 */
public class JaccardControllerTest {
    
    public JaccardControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of addEnvironment method, of class JaccardController.
     */
    @Test
    public void testAddEnvironment() {
        System.out.println("addEnvironment");
        Environment e = null;
        JaccardController instance = new JaccardController();
        instance.addEnvironment(e);
    }

    /**
     * Test of getEnvironment method, of class JaccardController.
     */
    @Test
    public void testGetEnvironment() {
        System.out.println("getEnvironment");
        String name = "";
        JaccardController instance = new JaccardController();
        Environment expResult = null;
        Environment result = instance.getEnvironment(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of printEnvironments method, of class JaccardController.
     */
    @Test
    public void testPrintEnvironments() {
        System.out.println("printEnvironments");
        JaccardController instance = new JaccardController();
        instance.printEnvironments();
    }

    /**
     * Test of computeJaccardAnalysis method, of class JaccardController.
     */
    @Test
    public void testComputeJaccardAnalysis() {
        System.out.println("computeJaccardAnalysis");
        JaccardController instance = new JaccardController();
        instance.computeJaccardAnalysis();
    }
    
}
