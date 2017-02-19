package DNSQueryTool;

/**
 *
 * @author Tim Sullivan
 */
public class DNSQueryResult implements java.io.Serializable 
{
//    private transient short Transaction_ID;
    private String domainName;
    private long responseTimeMs;
    private String queryResult;

    /**
     * Overrides default toString method to provide useful information
     * @return 
     */
    @Override
    public String toString()
    {
        return "Domain Name: " + getDomainName() + "\n" +
               "Response Time: " + getResponseTime() + "\n" +
               "IP Address: " + getAddress();
    }

    /**
     * @return the DomainName
     */
    public String getDomainName() {
        return domainName;
    }

    /**
     * @param DomainName the DomainName to set
     */
    public void setDomainName(String DomainName) {
        this.domainName = DomainName;
    }

    /**
     * @return the ResponseTime
     */
    public long getResponseTime() {
        return responseTimeMs;
    }

    /**
     * @param ResponseTime the ResponseTime to set
     */
    public void setResponseTime(long ResponseTime) {
        this.responseTimeMs = ResponseTime;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return queryResult;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.queryResult = Address;
    }

}
