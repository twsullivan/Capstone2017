//Denny Loucks
//Capstone
//DNSQuery

package DNSQueryTool.DNSMessage;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DNSResourceRecordCononicalName {
    String CName;
    
    public void Parse(byte[] buffer, int pos)
    {
        try 
        {
            CName = DNSNameDecoder.Decode(buffer, pos);
            
        } catch (UnsupportedEncodingException ex) 
        {
            Logger.getLogger(DNSResourceRecordNameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toString()
    {
        return CName;
    }
}
