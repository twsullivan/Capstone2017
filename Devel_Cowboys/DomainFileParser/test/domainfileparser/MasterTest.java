/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainfileparser;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nick Nawoschik
 */
public class MasterTest {
    
    public MasterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test
    public void firstTest() {
        String[] exampleDomains = {"test.com", "test2.com", "test3.com"};
        OutputJSON.save(exampleDomains, "/Users/nicholasnawoschik/Desktop/", "Nick", "Description");
        
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
