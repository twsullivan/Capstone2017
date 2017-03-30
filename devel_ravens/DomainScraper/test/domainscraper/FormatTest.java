/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainscraper;

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
     * Test of runHReF method, of class Format.
     */
    @Test
    public void testRunHReF() {
        System.out.println("runHReF");
        Format instance = new Format();
        instance.runHReF();
        
//        String expResult = "https://code.jquery.com/jquery-2.2.4.js";
//        
//        ArrayList localList = instance.getURLs();
//        
//        String result = (String)localList.get(0);
//        
//        assertEquals(expResult, result);
    }

    /**
     * Test of runWeblink method, of class Format.
     */
    @Test
    public void testRunWeblink() {
        System.out.println("runWeblink");
        Format instance = new Format();
        instance.runHReF();
        instance.runWeblink();
//        
//        String expResult = "code.jquery.com";
//        
//        ArrayList localList = instance.getDomains();
//        
//        String result = (String)localList.get(0);
//        
//        assertEquals(expResult, result);
    }

    /**
     * Test of showURLs method, of class Format.
     */
    @Test
    public void testShowURLs() {
        System.out.println("showURLs");
        Format instance = new Format();
        instance.showURLs();
    }

    /**
     * Test of showDomains method, of class Format.
     */
    @Test
    public void testShowDomains() {
        System.out.println("showDomains");
        Format instance = new Format();
        instance.showDomains();
    }
}
