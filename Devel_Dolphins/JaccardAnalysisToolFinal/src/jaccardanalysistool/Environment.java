/**
 * Environment.java
 * This class is the container for Environment objects.
 * Environment objects contain a String variable to hold a name, an int variable to hold an id,
 * and a list of strings to hold the list of blocked domains found in that environment.
 * @author devel_dolphins
 */

package jaccardanalysistool;
import java.util.ArrayList;
import java.util.List;

public class Environment
{
    private String name; //name of the environment
    private int id; //will be useful when we're comparing environment 1 to 2, 3 to 8, etc. gives us an index.
    private List<String> domains = new ArrayList<String>(); //list of blocked domains
    
    /*Default constructor, creates a blank Environment object.*/
    public Environment()
    {
        name = "";
        id = -1;
    }
    
    /*Parameterized constructor, creates an Environment object with the passed parameters.*/
    public Environment(String inputName, int inputID) //sets name and number of environment
    {
        name = inputName;
        id = inputID;
    }
    
    /*Adds a domain to the Environment object's domains List<String>.*/
    public void addDomain(String blockedDomain)
    {
        domains.add(blockedDomain);
    }
    
    /*Returns the total number of domains stored in the domains List<String>.*/
    public int getTotalNumOfDomains() //returns the total count of domains in the "domains" list.
    {
        return domains.size();
    }
    
    /*Compares the current environment object with the environment object passed in to the parameters, by counting the number of similar domains found in both.*/
    public int getCrossSection(Environment B) //compares "domains" for number of matching domains
    {
        List<String> temp = B.getDomains();
        int count = 0;
        for(String domainOne: domains)
        {
            for(String domainTwo: temp)
            {
                if(domainOne.equals(domainTwo))
                {
                    count++;
                }
            }
        }  
        return count;
    }
    
    /*Returns the results of if the passed in String exists in the domains List<String>*/
    public boolean contains(String name)
    {
        return domains.contains(name);
    }
    
    /*Returns the domain List<String> variable of the Environment object.*/
    public List<String> getDomains() 
    {
        return domains;
    }
        
    public String getName()
    {
        return name;
    }
    
    public int getID()
    {
        return id;
    }

    /*Prints out information relevant to the Environment Object*/
    public String toString()
    {
        return "\nEnvironment Name: " + getName() + "\nTotal Domains In Environment: " + getTotalNumOfDomains() + "\nDomain Info:" + getDomains() + "\n";
    }
}
