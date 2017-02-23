package DNSQueryTool.DNSMessage;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DNSMessageCommonResourceRecord {
    private String name;
    private int type;
    private int rClass;
    private int ttl;
    private int rdLength;
    private Object rData;
    
    public int Parse(byte[] buffer, int pos)
    {
        try 
        {
            DNSNameDecoder.ClearName();
            name = DNSNameDecoder.Decode(buffer, pos);
            pos = DNSNameDecoder.position;
            
        } catch (UnsupportedEncodingException ex) 
        {
            Logger.getLogger(DNSResourceRecordNameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        type = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);
        pos += 2;
        rClass = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);
        pos += 2;
        ttl = ((buffer[pos] & 0xFF) << 24) | ((buffer[pos + 1] & 0xFF) << 16) | ((buffer[pos + 2] & 0xFF) << 8) | (buffer[pos + 3] & 0xFF);
        pos += 4;
        rdLength = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);
        pos += 2;
        switch(getType())
            {
                case DNSMessageRecordType.A :
                    rData = new DNSResourceRecordAddress();
                    ((DNSResourceRecordAddress) getrData()).Parse(buffer, pos);
                    break;
                case DNSMessageRecordType.CNAME :
                    rData = new DNSResourceRecordCononicalName();
                    ((DNSResourceRecordCononicalName) getrData()).Parse(buffer, pos);
                    break;
                case DNSMessageRecordType.MX :
                    rData = new DNSResourceRecordMailExchange();
                    ((DNSResourceRecordMailExchange) getrData()).Parse(buffer, pos);
                    break;
                case DNSMessageRecordType.NS :
                    rData = new DNSResourceRecordNameServer();
                    ((DNSResourceRecordNameServer) getrData()).Parse(buffer, pos);
                    break;
                case DNSMessageRecordType.PTR :
                    rData = new DNSResourceRecordPointer();
                    ((DNSResourceRecordPointer) getrData()).Parse(buffer, pos);
                    break;
                case DNSMessageRecordType.SOA:
                    rData = new DNSResourceRecordStartOfAuthority();
                    ((DNSResourceRecordStartOfAuthority) getrData()).Parse(buffer, pos);
                    break;
                case DNSMessageRecordType.TXT :
                    rData = new DNSResourceRecordText();
                    ((DNSResourceRecordText) getrData()).Parse(buffer, pos);
                    break;
            }
        pos += getRdLength();
        return pos;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @return the rClass
     */
    public int getrClass() {
        return rClass;
    }

    /**
     * @return the ttl
     */
    public int getTtl() {
        return ttl;
    }

    /**
     * @return the rdLength
     */
    public int getRdLength() {
        return rdLength;
    }

    /**
     * @return the rData
     */
    public Object getrData() {
        return rData;
    }
}
