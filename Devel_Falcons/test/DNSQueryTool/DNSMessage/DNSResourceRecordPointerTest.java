/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DNSQueryTool.DNSMessage;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author doittws
 */
public class DNSResourceRecordPointerTest {
    
    /**
     * Test of Parse method, of class DNSResourceRecordPointer.
     */
    @Test
    public void testParse() {
        System.out.println("DNSResourceRecordPointer.Parse");
        
        // Arrange
        byte[] buffer = new byte[]{0,7,-127,-128,0,1,0,1,0,0,0,0,9,109,121,102,108,111,114,105,100,97,3,99,111,109,0,0,1,0,1,-64,12,0,1,0,1,0,0,1,53,0,4,-57,-6,30,-27};
        int pos = 12;
        DNSResourceRecordPointer instance = new DNSResourceRecordPointer();
        String expResult = "myflorida.com";
        
        // Act
        instance.Parse(buffer, pos);
        String result = instance.toString();
        
        // Assert
        assertEquals(expResult, result);
    }
    
}
