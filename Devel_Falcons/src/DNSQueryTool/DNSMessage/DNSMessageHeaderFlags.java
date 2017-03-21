package DNSQueryTool.DNSMessage;

public class DNSMessageHeaderFlags {
    private int qr = 0;
    private int opCode = 0;
    private int aa = 0;
    private int tc = 0;
    private int rd = 0;
    private int ra = 0;
    private int z = 0;
    private int rCode = 0;

    public int Parse(byte[] buffer, int pos)
    {
        int flags = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);
        pos += 2;
        
        setQr((flags & 0x8000) == 0x8000 ? 1 : 0);
        setOpCode(flags & 0x7800);
        setAa((flags & 0x0400) == 0x0400 ? 1 : 0);
        setTc((flags & 0x0200) == 0x0200 ? 1 : 0);
        setRd((flags & 0x0100) == 0x0100 ? 1 : 0);
        setRa((flags & 0x0080) == 0x0080 ? 1 : 0);
        setrCode(flags & 0x000F);
        
        return pos;           
    }
    
    int toInteger() {
        int output = 0;
        output = getQr();
        output = output << 4;
        switch(getOpCode())
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
        output = (output & 0xFFFF) | getAa();
        output = output << 1;
        output = (output & 0xFFFF) | getTc();
        output = output << 1;
        output = (output & 0xFFFF) | getRd();
        output = output << 1;
        output = (output & 0xFFFF) | getRa();
        output = output << 7;
        
        switch(getrCode())
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
            case DNSMessageHeaderFlagRCodes.NXDOMAIN:
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

    /**
     * @return the qr
     */
    public int getQr() {
        return qr;
    }

    /**
     * @param qr the qr to set
     */
    public void setQr(int qr) {
        this.qr = qr;
    }

    /**
     * @return the opCode
     */
    public int getOpCode() {
        return opCode;
    }

    /**
     * @param opCode the opCode to set
     */
    public void setOpCode(int opCode) {
        this.opCode = opCode;
    }

    /**
     * @return the aa
     */
    public int getAa() {
        return aa;
    }

    /**
     * @param aa the aa to set
     */
    public void setAa(int aa) {
        this.aa = aa;
    }

    /**
     * @return the tc
     */
    public int getTc() {
        return tc;
    }

    /**
     * @param tc the tc to set
     */
    public void setTc(int tc) {
        this.tc = tc;
    }

    /**
     * @return the rd
     */
    public int getRd() {
        return rd;
    }

    /**
     * @param rd the rd to set
     */
    public void setRd(int rd) {
        this.rd = rd;
    }

    /**
     * @return the ra
     */
    public int getRa() {
        return ra;
    }

    /**
     * @param ra the ra to set
     */
    public void setRa(int ra) {
        this.ra = ra;
    }

    /**
     * @return the z
     */
    public int getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * @return the rCode
     */
    public int getrCode() {
        return rCode;
    }

    /**
     * @param rCode the rCode to set
     */
    public void setrCode(int rCode) {
        this.rCode = rCode;
    }
}
