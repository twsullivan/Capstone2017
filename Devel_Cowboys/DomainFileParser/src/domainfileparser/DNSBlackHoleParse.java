package domainfileparser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pgabriel
 */
public class DNSBlackHoleParse {

    
    public DNSBlackHoleParse() {
    }

    public static String[] parse(String[] lines)
    {
        if (lines.length < 1) // No lines to parse
        {
            System.exit(666);
        }
        
        List<String> lineList = new ArrayList<>();

        for (int i = 0; i < lines.length; i++)
        {
            if (i > 3)
            {
                lineList.add(lines[i].split("\t")[2]);
            }
        }

        return lineList.toArray(new String[0]);
    }
}