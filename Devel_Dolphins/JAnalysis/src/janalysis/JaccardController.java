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
        int totalEnvs = environments.size();
        for(int i = 0; i < totalEnvs; i++)
        {
            for(int j = 0; j < totalEnvs; j++)
            {
                if(j>i && j != i)
                {
                    System.out.println("\n["+i+"]: Environment Name: "+ environments.get(i).getName()+ ", Total # Of Blocked Domains: " + environments.get(i).getTotalNumOfDomains() + "\n["+j+"]: Environment Name: "+ environments.get(j).getName() + ", Total # Of Blocked Domains: " + environments.get(j).getTotalNumOfDomains());
                    int total = environments.get(i).getTotalNumOfDomains() + environments.get(j).getTotalNumOfDomains();
                    int cross = environments.get(i).getCrossSection(environments.get(j));
                    System.out.println("Cross Section Of " + environments.get(i).getName()+ " and " + environments.get(j).getName() +": "+ cross);
                    total = total - cross;
                    System.out.println("Unique Environments Blocked In Both (Total Sum):" + total);
                    double jaccard = (double)cross/(double)total;
                    System.out.println("Jaccard Index Of " + environments.get(i).getName()+ "-" + environments.get(j).getName() +": " + jaccard);
                }
            }
        }
    }
}


