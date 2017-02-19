/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dolphins;	

/**
 *
 * @author devel_dolphins
 */
public class JAnalysis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception
    {
        if(args.length > 0)
        {
            for(String s: args)
            {
                if(s.equalsIgnoreCase("-help"))
                    printHelp();
                
                //System.out.println(s);
            }
            //does something if we have commands i guess
        }
        else //if no commands runs default
        {
            jsonController start = new jsonController();
            if(start.findJsonFiles() > 0)
            {
                start.loadFilesToList();
                start.createJSONObjects();
                if(start.createEnvironmentsList() >= 2)
                {
                    //next step
                }
                else
                {
                    System.out.println("For the jaccard index to be computed between two environments, the .json files must contain 2 unique EnvironmentID attributes.");
                }
            }
            else
            {
                System.out.println("No .json files were found in the local directory.");
            }
        }
    }
    
    public static void printHelp()
    {
        System.out.println("Help Message");
    }
}
