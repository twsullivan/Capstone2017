/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DNSQueryTool.DNSMessage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author doittws
 */
public class DNSResourceRecordAddressTest {
    
    public DNSResourceRecordAddressTest() {
    }

    /**
     * Test of Parse method, of class DNSResourceRecordAddress.
     */
    @Test
    public void testParse() {
        System.out.println("DNSResourceRecordAddress.Parse");
        byte[] buffer = new byte[]{0,7,-127,-128,0,1,0,1,0,0,0,0,9,109,121,102,108,111,114,105,100,97,3,99,111,109,0,0,1,0,1,-64,12,0,1,0,1,0,0,1,53,0,4,-57,-6,30,-27};
        int pos = 43;
        DNSResourceRecordAddress instance = new DNSResourceRecordAddress();
        instance.Parse(buffer, pos);
        String expResult = "199.250.30.229";
        String result = instance.getAddress();
        assertEquals(expResult, result);
    }
}