package DNSQueryTool.DNSMessage;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wayne
 */
public class DNSMessageHeaderFlagsTest {
    
    public DNSMessageHeaderFlagsTest() {
    }

    /**
     * Test of Parse method, of class DNSMessageHeaderFlags.
     */
    @Test
    public void testParse() {
        System.out.println("DNSMessageHeaderFlags.Parse");
        byte[] buffer = new byte[]{0,7,-127,-128,0,1,0,1,0,0,0,0,9,109,121,102,108,111,114,105,100,97,3,99,111,109,0,0,1,0,1,-64,12,0,1,0,1,0,0,1,53,0,4,-57,-6,30,-27};
        int pos = 2;
        DNSMessageHeaderFlags instance = new DNSMessageHeaderFlags();
        int expResult = 4;
        int result = instance.Parse(buffer, pos);
        assertEquals(expResult, result);
    }

    /**
     * Test of toInteger method, of class DNSMessageHeaderFlags.
     */
    @Test
    public void testToInteger() {
        System.out.println("DNSMessageHeaderFlags.toInteger");
        DNSMessageHeaderFlags instance = new DNSMessageHeaderFlags();
        int expResult = 0;
        int result = instance.toInteger();
        assertEquals(expResult, result);
    }
    
}
