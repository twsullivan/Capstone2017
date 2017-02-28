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
public class DNSResourceRecordTextTest {
    
    public DNSResourceRecordTextTest() {
    }

    /**
     * Test of Parse method, of class DNSResourceRecordText.
     */
    @Test
    public void testParse() {
        System.out.println("DNSResourceRecordText.Parse");
        
        // Arrange
        byte[] buffer = new byte[]{109,121,102,108,111,114,105,100,97};
        int pos = 0;
        String expResult = "myflorida";
        DNSResourceRecordText instance = new DNSResourceRecordText();
        
        // Act
        instance.Parse(buffer, pos);
        String result = instance.toString();
        
        //Assert
        assertEquals(expResult, result);
    }
}
