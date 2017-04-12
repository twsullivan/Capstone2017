package domainfileparser;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses an DNS BlackHole Project text file array, returns a 
 * clean array of domains.
 * 
 * @author pgabriel
 * @author tjk5
 */
public class DNSBlackHoleParse {

    public DNSBlackHoleParse() {
    }

    public static String[] parse(String[] lines)
    {
        if (lines.length < 1) // No lines to parse
        {
            return new String[] {"Error", "Parse Error: No lines were received."};
        }
        
        List<String> lineList = new ArrayList<>();
        
        //Original blackhole format
        if (lines[0].charAt(0) == '#')
        {	
            for (int i = 0; i < lines.length; i++)
            {
                if (i > 3)
                {   	
                    lineList.add(lines[i].split("\t")[2]);	
                }
            }
        }
        
        //Updated blackhole list
        if (Character.isDigit(lines[0].charAt(0)))
        {
        	for (int i = 0; i < lines.length; i++)
            {
                if (i > 1)
                {	
                    lineList.add(lines[i].split("\t")[1]);	  	
                }
            }
        }
        
        return lineList.toArray(new String[0]);
    }
}