package DNSQueryTool;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author doittws
 */
public class DNSQuery implements Runnable {

    private static final int DNS_SERVER_PORT = 53;
    private final String dnsServerAddress;
    private int id = 0;
    private final String domain;
    private final DNSClient parent;

    public DNSQuery(int id, DNSClient parent, String dnsServerAddress, String domain) {
        this.id = id;
        this.parent = parent;
        this.dnsServerAddress = dnsServerAddress;
        this.domain = domain;
    }

    @Override
    public void run() {
        try {
            performQuery();
        } catch (Exception ex) {
            Logger.getLogger(DNSQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void performQuery() 
    {
        DNSQueryResult result = new DNSQueryResult();
        
        result.setDomainName(domain);
        result.setAddress("0.0.0.0");
        result.setResponseTime(1);
        
        parent.setDNSQueryOutputResults(id, result);
        parent.decrementRemainingThreads();
    }
}
