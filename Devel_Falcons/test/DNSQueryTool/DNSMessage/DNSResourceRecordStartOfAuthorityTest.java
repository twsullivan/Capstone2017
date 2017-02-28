package DNSQueryTool.DNSMessage;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tim Sullivan
 */
public class DNSResourceRecordStartOfAuthorityTest {
    
    /**
     * Test of Parse method, of class DNSResourceRecordStartOfAuthority.
     */
    @Test
    public void testParse() {
        System.out.println("DNSResourceRecordStartOfAuthority.Parse");
        
        // Arrange
        byte[] buffer = new byte[]{9,109,121,102,108,111,114,105,100,97,3,99,111,109,0,0,9,109,121,102,108,111,114,105,100,97,3,99,111,109,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int pos = 0;
        DNSResourceRecordStartOfAuthority instance = new DNSResourceRecordStartOfAuthority();
        String expResult = "myflorida.com";
        
        // Act
        instance.Parse(buffer, pos);
        String result = instance.getrName();
        
        // Assert
        assertEquals(expResult, result);
    }
    
}
