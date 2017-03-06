/**
 *
 * @author devel_dolphins
 */
package janalysis;
import java.util.ArrayList;
import java.util.List;

public class JaccardController //Given environments, this is what will do the stuff (algorithm)
{
    public List<Environment> environments = new ArrayList<Environment>();
    
    public JaccardController()
    {
        //default constructor? needed?
    }
    
    /*Adds an environment object to the environments List<environment>*/
    public void addEnvironment(Environment e)
    {
        environments.add(e);
    }
    
    /*Returns an environment object from the environments List<environment> based on if the given string name was found.*/
    public Environment getEnvironment(String name)
    {
        for(Environment e: environments)
        {
            if(e.getName().equalsIgnoreCase(name))
            {
                return e;
            }
            else
            {
                //System.out.println("Could not find environment in list.");
            }
        }
        return null;
    }
    
    /*Prints out all of the environments in the environments List<environment>*/
    public void printEnvironments()
    {
        System.out.println(environments);
    }
    
    
    public void computeJaccardAnalysis()
    {
        //System.out.println(environments.get(0).getName() + " and " + environments.get(1).getName() + " Cross: " +  environments.get(0).getCrossSection(environments.get(1)));
        //Environment e = new Environment();
        int x, y, xID, yID, xDomain, yDomain, total, totalDomain, crossSection;
        String xName, yName;
        for(x=0; x < environments.size(); x++){
            xName = environments.get(x).getName();
            xID = environments.get(x).getID();
            for(y=1; y < environments.size(); y++){
                yName = environments.get(y).getName();
                yID = environments.get(y).getID();
                xDomain = environments.get(x).getTotalNumOfDomains();
                yDomain = environments.get(y).getTotalNumOfDomains();
                crossSection = environments.get(x).getCrossSection(environments.get(y));
                //totalDomain = getTotalDomain(xDomain, yDomain); //khang, we'll be pushing these total to the result class
                //total = totalDomain - crossSection;
                
            }
        }
    }
}


