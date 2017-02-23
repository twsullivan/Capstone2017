package DNSQueryTool.DNSMessage;

public class DNSMessageHeader {
    private int id;
    private DNSMessageHeaderFlags flags;
    private int qdCount;
    private int anCount;
    private int nsCount;
    private int arCount;

    public DNSMessageHeader()
    {
        flags = new DNSMessageHeaderFlags();
    }
    
    public int Parse(byte[] buffer, int pos)
    {
        id = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);
        pos += 2;
        pos = flags.Parse(buffer, pos);
        qdCount = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);
        pos += 2;
        anCount = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);
        pos += 2;
        nsCount = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);
        pos += 2;
        arCount = ((buffer[pos] & 0xFF) << 8) | (buffer[pos + 1] & 0xFF);
        pos += 2;
        
        return pos;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the flags
     */
    public DNSMessageHeaderFlags getFlags() {
        return flags;
    }

    /**
     * @param flags the flags to set
     */
    public void setFlags(DNSMessageHeaderFlags flags) {
        this.flags = flags;
    }

    /**
     * @return the qdCount
     */
    public int getQdCount() {
        return qdCount;
    }

    /**
     * @param qdCount the qdCount to set
     */
    public void setQdCount(int qdCount) {
        this.qdCount = qdCount;
    }

    /**
     * @return the anCount
     */
    public int getAnCount() {
        return anCount;
    }

    /**
     * @param anCount the anCount to set
     */
    public void setAnCount(int anCount) {
        this.anCount = anCount;
    }

    /**
     * @return the nsCount
     */
    public int getNsCount() {
        return nsCount;
    }

    /**
     * @param nsCount the nsCount to set
     */
    public void setNsCount(int nsCount) {
        this.nsCount = nsCount;
    }

    /**
     * @return the arCount
     */
    public int getArCount() {
        return arCount;
    }

    /**
     * @param arCount the arCount to set
     */
    public void setArCount(int arCount) {
        this.arCount = arCount;
    }
}
