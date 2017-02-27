package DNSQueryTool.DNSMessage;

public class DNSMessageHeaderFlags {
    int qr = 0;
    int opCode = 0;
    int aa = 0;
    int tc = 0;
    int rd = 0;
    int ra = 0;
    int z = 0;
    int rCode = 0;

    public int Parse(byte[] buffer, int pos)
    {
        int flags = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);
        pos += 2;
        
        qr = (flags & 0x8000) == 0x8000 ? 1 : 0;
        opCode = flags & 0x7800;
        aa = (flags & 0x0400) == 0x0400 ? 1 : 0;
        tc = (flags & 0x0200) == 0x0200 ? 1 : 0;
        rd = (flags & 0x0100) == 0x0100 ? 1 : 0;
        ra = (flags & 0x0080) == 0x0080 ? 1 : 0;
        rCode = flags & 0x000F;
        
        return pos;           
    }
    
    int toInteger() {
        int output = 0;
        output = qr;
        output = output << 4;
        switch(opCode)
        {
            case DNSMessageHeaderFlagOpcodes.QUERY:
                output = (output & 0xFFFF) | 0;
                break;
            case DNSMessageHeaderFlagOpcodes.IQUERY:
                output = (output & 0xFFFF) | 1;
                break;
            case DNSMessageHeaderFlagOpcodes.STATUS:
                output = (output & 0xFFFF) | 2;
                break;
            case DNSMessageHeaderFlagOpcodes.RESERVED:
                output = (output & 0xFFFF) | 3;
                break;
            case DNSMessageHeaderFlagOpcodes.NOTIFY:
                output = (output & 0xFFFF) | 4;
                break;
            case DNSMessageHeaderFlagOpcodes.UPDATE:
                output = (output & 0xFFFF) | 5;
                break;
        }
        output = output << 1;
        output = (output & 0xFFFF) | aa;
        output = output << 1;
        output = (output & 0xFFFF) | tc;
        output = output << 1;
        output = (output & 0xFFFF) | rd;
        output = output << 1;
        output = (output & 0xFFFF) | ra;
        output = output << 7;
        
        switch(rCode)
        {
            case DNSMessageHeaderFlagRCodes.NO_ERROR:
                output = (output & 0xFFFF) | 0;
                break;
            case DNSMessageHeaderFlagRCodes.FORMAT_ERROR:
                output = (output & 0xFFFF) | 1;
                break;
            case DNSMessageHeaderFlagRCodes.SERVER_FAILURE:
                output = (output & 0xFFFF) | 2;
                break;
            case DNSMessageHeaderFlagRCodes.NAME_ERROR:
                output = (output & 0xFFFF) | 3;
                break;
            case DNSMessageHeaderFlagRCodes.NOT_IMPLEMENTED:
                output = (output & 0xFFFF) | 4;
                break;
            case DNSMessageHeaderFlagRCodes.REFUSED:
                output = (output & 0xFFFF) | 5;
                break;
            case DNSMessageHeaderFlagRCodes.YX_DOMAIN:
                output = (output & 0xFFFF) | 6;
                break;
            case DNSMessageHeaderFlagRCodes.YX_RR_SET:
                output = (output & 0xFFFF) | 7;
                break;
            case DNSMessageHeaderFlagRCodes.NX_RR_SET:
                output = (output & 0xFFFF) | 8;
                break;
            case DNSMessageHeaderFlagRCodes.NOT_AUTH:
                output = (output & 0xFFFF) | 9;
                break;
            case DNSMessageHeaderFlagRCodes.NOT_ZONE:
                output = (output & 0xFFFF) | 10;
                break;
                
        }
        return output & 0xFFFF;
    }
}
