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
public class DNSNameDecoderTest {
    
    public DNSNameDecoderTest() {
    }

    @Before
    public void setUp() {
        //data = new byte[]{0,1,1,0,0,1,0,0,0,0,0,0,9,109,121,102,108,111,114,105,100,97,3,99,111,109,0,0,1,0,1};
        //data = new byte[]{0,7,-127,-128,0,1,0,1,0,0,0,0,9,109,121,102,108,111,114,105,100,97,3,99,111,109,0,0,1,0,1,-64,12,0,1,0,1,0,0,1,53,0,4,-57,-6,30,-27};
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of Decode method, of class DNSNameDecoder.
     */
    @Test
    public void testDecode() throws Exception {
        System.out.println("DNSNameDecoder.Decode");
        byte[] buffer = new byte[]{0,7,-127,-128,0,1,0,1,0,0,0,0,9,109,121,102,108,111,114,105,100,97,3,99,111,109,0,0,1,0,1,-64,12,0,1,0,1,0,0,1,53,0,4,-57,-6,30,-27};
        int pos = 31; //12;
        String expResult = "myflorida.com";
        String result = DNSNameDecoder.Decode(buffer, pos);
        assertEquals(expResult, result);
    }
    
}
