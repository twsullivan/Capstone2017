//Denny Loucks
//Capstone
//DNSQuery

package DNSQueryTool.DNSMessage;

import java.io.UnsupportedEncodingException;


public class DNSNameDecoder {

    public static int position;
    private static String name = "";

    public static void ClearName()
    {
        name = "";
    }
    
    public static String Decode(byte[] buffer, int pos) throws UnsupportedEncodingException 
    {
        int recLen = buffer[pos] & 0xFF;
        pos++;

        while (recLen > 0) 
        {            
            if (recLen > 191) 
            {
                pos--;
                Decode(buffer, ((buffer[pos] & 0x3F) << 8) | (buffer[pos+1] & 0xFF));
                pos += 2;
                break;
            } else 
            {
                byte[] label = new byte[recLen];

                for (int i = 0; i < recLen; i++) {
                    label[i] = buffer[pos];
                    pos++;
                }

                name = (name == "" ? new String(label, "UTF-8") : name + "." + new String(label, "UTF-8"));
            }
            recLen = buffer[pos] & 0xFF;
            pos++;
        }
        DNSNameDecoder.position = pos;
        return name;
    }
}
