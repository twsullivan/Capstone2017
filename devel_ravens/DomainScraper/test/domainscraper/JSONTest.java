/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainscraper;

import java.io.BufferedReader;
import java.io.FileReader;
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
    public void testMakeJSON() throws Exception {
        System.out.println("makeJSON");
        JSON instance = new JSON();
        
        ArrayList nameList = new ArrayList();
        nameList.add("code.jquery.com");
        nameList.add("goargos.com");
        
        instance.setNameList(nameList);
        instance.setUser("User");
        instance.setListID("List ID");
        instance.setListDescription("List Description");
        instance.setOutput("output.json");
        instance.makeJSON();
        
        FileReader fileReader = new FileReader("output.json");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        String expResult = "{";
        String result = bufferedReader.readLine();
        
        assertEquals(expResult, result);
    }    
}
