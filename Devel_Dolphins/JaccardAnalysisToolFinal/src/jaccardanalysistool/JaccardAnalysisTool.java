/**
 *
 * @author devel_dolphins
 */

package jaccardanalysistool;	
public class JaccardAnalysisTool
{   
    public static void main(String[] args) throws Exception
    {
        if(args.length > 0)
        {
            if(args[0].equalsIgnoreCase("--help") || args[0].equalsIgnoreCase("help"))
                printHelp();
            else
                startProcess(args[0]);
        }
        else
            startProcess(System.getProperty("user.dir"));
    }
    
    public static void startProcess(String path)
    {
        System.out.println("Starting with path: \"" + path + "\"");
        JsonController start = new JsonController(path);
        if(start.findJsonFiles() > 0)
        {
            start.loadFilesToList();
            start.createJSONObjects();
            if(start.createEnvironmentsList() >= 2)
            {
                JaccardController calculator = start.createJaccardController();                    
                start.addBlockedDomains(calculator);
                //calculator.printEnvironments();
                calculator.computeJaccardAnalysis();
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
    
    public static void printHelp()
    {
        System.out.println("===================================================================");
        System.out.println("\t\t\t ~~jAnalysis Help~~");
        System.out.println("Description: jAnalysis will search a given directory for .json files \nfollowing the valid DNS requirements format specified.");
        System.out.println("\nUsage: java -jar jAnalysis.jar [path]");
        System.out.println("\n[path] is the command parameter for a specified path to use for .json file analysis.");
        System.out.println("Example: java -jar jAnalysis.jar C:\\Users\\acm52\\Desktop");
        System.out.println("If left empty, the path chosen will be the current running directory.");
        System.out.println("===================================================================");
    }
}


