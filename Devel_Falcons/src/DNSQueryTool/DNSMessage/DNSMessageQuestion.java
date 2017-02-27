//Denny Loucks
//Capstone
//DNSQuery

package DNSQueryTool.DNSMessage;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DNSMessageQuestion {

    String qname;
    int qtype;
    int qclass;

    public int Parse(byte[] buffer, int pos) {
        try 
        {
            qname = DNSNameDecoder.Decode(buffer, pos);
            pos = DNSNameDecoder.position;
            
        } catch (UnsupportedEncodingException ex) 
        {
            Logger.getLogger(DNSResourceRecordNameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        qtype = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);

        pos += 2;
        qclass = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);

        pos += 2;
        return pos;
    }
}
