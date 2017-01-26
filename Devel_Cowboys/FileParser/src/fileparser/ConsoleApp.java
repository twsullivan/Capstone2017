package fileparser;

import java.util.Scanner;

public class ConsoleApp 
{
  
    public ConsoleApp()
    {   
    }
    
    
    public void parseInput(String input)
    {
         //Handle the users input
    }
    
    public void printUsageDump()
    {
        // Print usage dump here
        System.out.println("Usage:");
        System.out.println("java -jar domainparser.jar <input file> <output file> <max results (int)> <filetype [csv | json | other]>");
        
    }
   
    /**
     * Starts a console application that reads input
     */
    public void startConsole()
    {
        System.out.println("File Parser");
        System.out.println("-----------");
        
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        
        if(input != null)
        {
            if(input.equals("help"))
            {
                printUsageDump();
            }
            parseInput(input);
        }
    }
    
    
}
