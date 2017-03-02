package domainfileparser;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses an Adblock text file array, returns a clean array of DNS's
 *
 * @author Zack McColgan
 */
public class AdBlockParse {

    public AdBlockParse() 
    {

    }

    public static String[] parse(String[] lines) 
    {
        if (lines.length < 1) // No lines to parse
        {
            return new String[] {"Error", "Parse Error: No lines were received."};
        }

        List<String> lineList = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) 
        {
            if (lines[i].charAt(0) == '!' || lines[i].charAt(0) == '[') 
            {
                //Skip line
            } 
            else if(lines[i].charAt(0) == '|') 
            {
                lineList.add(lines[i].substring(2, lines[i].length() - 1));
            } 
            else 
            {
               lineList.add(lines[i]); 
            }
        }
        return lineList.toArray(new String[0]);
    }
}