package DNSQueryTool.DNSMessage;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tim Sullivna
 */
public class DNSResourceRecordMailExchange {
    int preference;
    String exchange;
    
    public void Parse(byte[] buffer, int pos)
    {
        preference = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);
        
        pos += 2;
        
        try 
        {
            exchange = DNSNameDecoder.Decode(buffer, pos);
            
        } catch (UnsupportedEncodingException ex) 
        {
            Logger.getLogger(DNSResourceRecordNameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
