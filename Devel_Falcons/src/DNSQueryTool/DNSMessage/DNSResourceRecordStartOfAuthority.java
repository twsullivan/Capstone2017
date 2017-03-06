package DNSQueryTool.DNSMessage;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tim Sullivan, Austin Bolstridge
 */
public class DNSResourceRecordStartOfAuthority {
    String mName;
    String rName;
    int serial;
    int refresh;
    int retry;
    int expire;
    int minimum;
    
    public void Parse(byte[] buffer, int pos)
    {
        try 
        {
            mName = DNSNameDecoder.Decode(buffer, pos);
            pos = DNSNameDecoder.position;
            
            rName = DNSNameDecoder.Decode(buffer, pos);
            pos = DNSNameDecoder.position;
            
        } catch (UnsupportedEncodingException ex) 
        {
            Logger.getLogger(DNSResourceRecordNameServer.class.getName()).log(Level.SEVERE, null, ex);
        } 
        serial = (int) (((buffer[pos] & 0xFF) << 24) | ((buffer[pos + 1] & 0xFF) << 16) | ((buffer[pos + 2] & 0xFF) << 8) | (buffer[pos + 3] & 0xFF));
        pos += 4;
        refresh = (int) (((buffer[pos] & 0xFF) << 24) | ((buffer[pos + 1] & 0xFF) << 16) | ((buffer[pos + 2] & 0xFF) << 8) | (buffer[pos + 3] & 0xFF));
        pos += 4;
        retry = (int) (((buffer[pos] & 0xFF) << 24) | ((buffer[pos + 1] & 0xFF) << 16) | ((buffer[pos + 2] & 0xFF) << 8) | (buffer[pos + 3] & 0xFF));
        pos += 4;
        expire = (int) (((buffer[pos] & 0xFF) << 24) | ((buffer[pos + 1] & 0xFF) << 16) | ((buffer[pos + 2] & 0xFF) << 8) | (buffer[pos + 3] & 0xFF));
        pos += 4;
        minimum = (int) (((buffer[pos] & 0xFF) << 24) | ((buffer[pos + 1] & 0xFF) << 16) | ((buffer[pos + 2] & 0xFF) << 8) | (buffer[pos + 3] & 0xFF));
        pos += 4;
    }
    
    /**
     * @return the mName
     */
    public String getmName() {
        return mName;
    }

    /**
     * @return the rName
     */
    public String getrName() {
        return rName;
    }

    /**
     * @return the serial
     */
    public int getSerial() {
        return serial;
    }

    /**
     * @return the refresh
     */
    public int getRefresh() {
        return refresh;
    }

    /**
     * @return the retry
     */
    public int getRetry() {
        return retry;
    }

    /**
     * @return the expire
     */
    public int getExpire() {
        return expire;
    }

    /**
     * @return the minimum
     */
    public int getMinimum() {
        return minimum;
    }
}
