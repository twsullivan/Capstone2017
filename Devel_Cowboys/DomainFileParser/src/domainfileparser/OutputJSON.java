/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainfileparser;

/**
 *
 * @author pgabriel
 */
public class OutputJSON {

    public OutputJSON() {
    }

    public static String[] save(String[] domains) {
        
        for(String domain : domains)
        {
            System.out.println(domain);
        }
        
        return new String[]{"OK"};
    }

}
