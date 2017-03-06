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
        //
    }
}


