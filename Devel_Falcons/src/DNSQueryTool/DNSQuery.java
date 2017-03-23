package DNSQueryTool;

import DNSQueryTool.DNSMessage.DNSMessage;
import DNSQueryTool.DNSMessage.DNSMessageCommonResourceRecord;
import DNSQueryTool.DNSMessage.DNSMessageHeaderFlagRCodes;
import DNSQueryTool.DNSMessage.DNSMessageRecordType;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tim Sullivan
 */
public class DNSQuery implements Runnable {

    private static final int DNS_SERVER_PORT = 53;
    private final String dnsServerAddress;
    private int id = 0;
    private final String domain;
    private final DNSClient parent;
    private DNSMessage message = new DNSMessage();

    public DNSQuery(int id, DNSClient parent, String dnsServerAddress, String domain) {
        this.id = id;
        this.parent = parent;
        this.dnsServerAddress = dnsServerAddress;
        this.domain = domain;
    }

    @Override
    public void run() {
        try {

            byte[] buffer = message.CreateRequest(id, domain);
            byte[] rcvBuffer;
            long startTime;
            int retry = 3;
            boolean error = true;

            do {
                // Send DNS Request Frame
                DatagramSocket socket = new DatagramSocket();
                socket.setSoTimeout(3000);
                InetAddress ipAddress = InetAddress.getByName(dnsServerAddress);
                DatagramPacket dnsReqPacket = new DatagramPacket(buffer, buffer.length, ipAddress, DNS_SERVER_PORT);

                startTime = System.currentTimeMillis();
                socket.send(dnsReqPacket);

                // Await response from DNS server
                rcvBuffer = new byte[1024];

                DatagramPacket packet = new DatagramPacket(rcvBuffer, rcvBuffer.length);

                try {
                    socket.receive(packet);
                    error = false;
                    retry = 0;
                } catch (SocketTimeoutException ex) {
                    //Logger.getLogger(DNSQuery.class.getName()).log(Level.SEVERE, null, ex);
                    error = true;
                    retry--;
                } finally {
                    socket.close();
                }
            } while (retry > 0);

            getResult(rcvBuffer, startTime, error);
        } catch (Exception ex) {
            Logger.getLogger(DNSQuery.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public DNSQueryResult test(byte[] rcvBuffer, long startTime, boolean error) {
        return getResult(rcvBuffer, startTime, error);
    }

    private DNSQueryResult getResult(byte[] rcvBuffer, long startTime, boolean error) {

        DNSQueryResult result = new DNSQueryResult();

        if (!error) {
            // Calculate response time
            long responseTime = System.currentTimeMillis() - startTime;

            message.ParseResponse(rcvBuffer);

            result.setDomainName(domain);

            if (message.getHeader().getFlags().getrCode() == DNSMessageHeaderFlagRCodes.NO_ERROR) {
                for (DNSMessageCommonResourceRecord answer : message.getAnswers()) {
                    if (answer.getType() == DNSMessageRecordType.A) {
                        result.setAddress(answer.getrData().toString());
                        break;
                    }
                }
            } else if(message.getHeader().getFlags().getrCode() == DNSMessageHeaderFlagRCodes.NXDOMAIN){
                result.setAddress("BLOCKED");
            } else {
                result.setAddress("ERROR");
            }

            result.setResponseTime("" + responseTime);
        } else {
            result.setResponseTime("0");
            result.setDomainName(domain);
            result.setAddress("error");
        }

        if (parent != null) {
            parent.setDNSQueryOutputResults(id, result);
            parent.decrementRemainingThreads();
        }
        return result;
    }
}
