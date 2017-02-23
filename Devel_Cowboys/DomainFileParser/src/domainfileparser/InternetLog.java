/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainfileparser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nick Nawoschik
 */
public class InternetLog {
    public InternetLog() {
        
    }
    
    public static String[] parse(String[] lines) {
        List<String> lineList = new ArrayList<>();
        
        for (int i = 0; i < lines.length; i++)
        {
            lineList.add(lines[i]);
        }
        
        return lineList.toArray(new String[0]);
    }
}