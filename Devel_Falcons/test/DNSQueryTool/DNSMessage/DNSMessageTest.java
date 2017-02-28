/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DNSQueryTool.DNSMessage;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wayne
 */
public class DNSMessageTest {
    
    byte[] data;
    
    public DNSMessageTest() {
    }
   
    @Before
    public void setUp() {
        data = new byte[]{0,1,1,0,0,1,0,0,0,0,0,0,9,109,121,102,108,111,114,105,100,97,3,99,111,109,0,0,1,0,1};
    }
    
    /**
     * Test of CreateRequest method, of class DNSMessage.
     */
    @Test
    public void testCreateRequest() {
        System.out.println("DNSMessage.CreateRequest");
        int id = 1;
        String domainName = "myflorida.com";
        DNSMessage instance = new DNSMessage();
        byte[] expResult = data;
        byte[] result = instance.CreateRequest(id, domainName);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of ParseResponse method, of class DNSMessage.
     */
    @Test
    public void testParseResponse() {
        System.out.println("DNSMessage.ParseResponse");
        byte[] buffer = data;
        DNSMessage instance = new DNSMessage();
        instance.ParseResponse(buffer);
        assertEquals(1, instance.getHeader().getId());
        assertEquals(0, instance.getHeader().getFlags().getQr());
    }
}
