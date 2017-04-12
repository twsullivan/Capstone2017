/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainfileparser;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses an Internet Log text file array, returns a clean array of domains.
 * 
 * @author Nick Nawoschik
 */
public class InternetLog {
    
    public InternetLog() {
    }
    
    public static String[] parse(String[] lines) {
        
        // Checks if data is in the file
        if(lines.length < 1) {
            return new String[] {"Error", "Parse Error: No lines were received."};
        }
        
        // Prepares an array to store parsed domains
        List<String> lineList = new ArrayList<>();
        
        // Parses the domains getting rid of blank lines and the header
        // information (contained up to line 29).
        for(int i = 0; i < lines.length; i++) {
            if(i > 29 && !lines[i].equals("")) {
                String[] line = lines[i].split(" ");
                
                for (String value : line) {
                    if (value.contains("(")) {
                        if(!value.equals("(0)")) {
                            String tmp = value;
                            tmp = tmp.replaceAll("\\(\\d+\\)", ".");
                            tmp = tmp.substring(1);
                            tmp = tmp.substring(0, tmp.length()-1);
                            lineList.add(tmp);
                        }
                    }
                }
            }
        }
        
        return lineList.toArray(new String[0]); 
    }
}