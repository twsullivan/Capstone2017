package DNSQueryTool.DNSMessage;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tim Sullivan
 */
public class DNSResourceRecordText {
    String txtData;
    
    public void Parse(byte[] buffer, int pos)
    {
        try 
        {
            txtData = new String(buffer, "UTF-8");
            
        } catch (UnsupportedEncodingException ex) 
        {
            Logger.getLogger(DNSResourceRecordText.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String toString()
    {
        return txtData;
    }
}
