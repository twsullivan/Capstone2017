/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DNSQueryTool;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author doittws
 */
public class DNSQueryTest {
    
    /**
     * Test of test method, of class DNSQuery.
     */
    @Test
    public void testTest() {
        System.out.println("DNSQuery.test");
        // Arrange
        byte[] rcvBuffer = new byte[]{0,7,-127,-128,0,1,0,1,0,0,0,0,9,109,121,102,108,111,114,105,100,97,3,99,111,109,0,0,1,0,1,-64,12,0,1,0,1,0,0,1,53,0,4,-57,-6,30,-27};
        long startTime = System.currentTimeMillis();
        boolean error = false;
        DNSQuery instance = new DNSQuery(0, null, "8.8.8.8", "myflorida.com");
        DNSQueryResult expResult = new DNSQueryResult();
        expResult.setAddress("199.250.30.229");
        expResult.setDomainName("myflorida.com");
        expResult.setResponseTime(0);
        
        // Act
        DNSQueryResult result = instance.test(rcvBuffer, startTime, error);
        
        // Assert
        assertEquals(expResult.getAddress(), result.getAddress());
        assertEquals(expResult.getDomainName(), result.getDomainName());
    }
    
}
