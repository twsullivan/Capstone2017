package DNSQueryTool.DNSMessage;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tim Sullivan
 */
public class DNSResourceRecordPointer 
{
    String ptrdName;

    public void Parse(byte[] buffer, int pos) {
        try 
        {
            ptrdName = DNSNameDecoder.Decode(buffer, pos);

        } catch (UnsupportedEncodingException ex) 
        {
            Logger.getLogger(DNSResourceRecordNameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
