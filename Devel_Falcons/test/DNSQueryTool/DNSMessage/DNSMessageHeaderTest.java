package DNSQueryTool.DNSMessage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wayne
 */
public class DNSMessageHeaderTest {
    
    public DNSMessageHeaderTest() {
    }

    /**
     * Test of Parse method, of class DNSMessageHeader.
     */
    @Test
    public void testParse() {
        System.out.println("DNSMessageHeader.Parse");
        byte[] buffer = new byte[]{0,7,-127,-128,0,1,0,1,0,0,0,0,9,109,121,102,108,111,114,105,100,97,3,99,111,109,0,0,1,0,1,-64,12,0,1,0,1,0,0,1,53,0,4,-57,-6,30,-27};
        int pos = 0;
        DNSMessageHeader instance = new DNSMessageHeader();
        int expResult = 12;
        int result = instance.Parse(buffer, pos);
        assertEquals(expResult, result);
    }
}
