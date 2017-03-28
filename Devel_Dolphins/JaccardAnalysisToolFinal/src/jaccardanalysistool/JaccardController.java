/**
 * JaccardController.java
 * This class is responsible for handling Environment objects and calculating the Jaccard Index.
 * @author devel_dolphins
 */
package jaccardanalysistool;
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
    
    /*Computes and outputs the jaccard analysis between all of the Environment objects in the environments List.*/
    public void computeJaccardAnalysis()
    {
        int totalEnvs = environments.size();
        for(int i = 0; i < totalEnvs; i++)
        {
            for(int j = 0; j < totalEnvs; j++)
            {
                if(j>i && j != i)
                {
                    System.out.println("\nFor Environment ["+ environments.get(i).getName()+ "] There Are A Total Of [" + environments.get(i).getTotalNumOfDomains()+ "] Blocked Domains.");
                    System.out.println("For Environment ["+ environments.get(j).getName()+ "] There Are A Total Of [" + environments.get(j).getTotalNumOfDomains()+ "] Blocked Domains.");  
                    int total = environments.get(i).getTotalNumOfDomains() + environments.get(j).getTotalNumOfDomains();
                    int cross = environments.get(i).getCrossSection(environments.get(j));
                    System.out.println("Environment ["+environments.get(i).getName() +"] and Environment ["+ environments.get(j).getName() +"] have [" + cross +"] Blocked Domains in Common. (Cross Section)");
                    total = total - cross;
                    System.out.println("Unique Environments Blocked In Both (Total Sum):" + total);
                    double jaccard = ((double)cross/(double)total)*100;
                    System.out.println("The Jaccard Index for Blocked Domains in environments " + environments.get(i).getName()+ " and " + environments.get(j).getName() +" is: ");
                    System.out.printf("%.2f\n", jaccard);
                }
            }
        }
    }
}


