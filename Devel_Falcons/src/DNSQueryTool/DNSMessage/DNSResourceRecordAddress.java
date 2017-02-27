//Denny Loucks
//Capstone
//DNSQuery

package DNSQueryTool.DNSMessage;

public class DNSResourceRecordAddress {

    private String address;

    public String getAddress() {
        return address;
    }

    public void Parse(byte[] buffer, int pos) {
        StringBuilder address = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            address.append("" + String.format("%d", (buffer[pos+i] & 0xFF)) + ".");
        }
        address.deleteCharAt(address.length() - 1);
        this.address = address.toString();
    }
    
    @Override
    public String toString()
    {
        return address;
    }
}
