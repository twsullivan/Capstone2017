/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dolphins;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author acm52
 */
public class environment
{
    private String name; //name of the environment
    private int id; //will be useful when we're comparing environment 1 to 2, 3 to 8, etc. gives us an index.
    private List<String> domains = new ArrayList<String>(); //list of blocked domains
    
    public environment(String inputName, int inputID) //sets name and number of environment
    {
        //
    }
    
    public void addDomain(String blockedDomain)
    {
        //adds the blocked domain to the domains list
    }
    
    public int getTotalNumOfDomains() //returns the total count of domains in the "domains" list.
    {
        return 0;
    }
    
    public int getCrossSection(environment B) //compares "domains" for number of matching domains
    {
        return 0;
    }
    
    public List<String> getDomains() //returns the list of domains
    {
        return domains;
    }
}
