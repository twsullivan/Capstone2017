/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainscraper;

import java.io.BufferedReader;
import java.io.FileReader;
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
    public void testRunJGet(){
        System.out.println("runJGet");
        try{
        
        JGet instance = new JGet();
        instance.runJGet();
        
        FileReader fileReader = new FileReader(instance.getOutput());
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        
        String expResult = "<!DOCTYPE html>";
        String result = bufferedReader.readLine();
        
        assertEquals(expResult, result);
        }
        catch(Exception e){
            
        }
    }    
}