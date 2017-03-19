import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWriter {

    List<List<Double>> statTable;
    String outputFilePath;
    
    public FileWriter() 
    {
        //Default Constructor
    }
    
    public FileWriter(List<List<Double>> statTable, String outputFilePath) 
    {
        this.statTable = statTable;
        this.outputFilePath = outputFilePath;
        writeOutputGlobally(outputFilePath);
    }

    private void writeOutputGlobally(String outputFilePath)
    {
        if(outputFilePath == null)
        {
            writeToConsole();
        }
        else
        {
            writeToCSV(outputFilePath);
        }
    }
  
    private void writeToConsole()
    {
       System.out.println();
       for(int i = 0; i < this.statTable.size(); i++)
       {
          System.out.println("-----------------------Query Statistics----------------------\n");
          
          System.out.println("Environment (" + (i+1) + "/" + this.statTable.size() + ")\n");
          for(int k = 0; k < this.statTable.get(i).size(); k++)
          {
              
              switch (k) {
                  case 0:
                      System.out.print("Mean: ");
                      break;
                  case 1:
                      System.out.print("Median: ");
                      break;
                  case 2:
                      System.out.print("Standard Deviation: ");
                      break;
                  default:
                      System.out.print("98th Percentile: ");
                      break;
              }
              double temp = this.statTable.get(i).get(k);
             System.out.format("%.2f%n",temp);
          }
          System.out.println("-------------------------------------------------------------");
          System.out.println();
       }
    }
    
    private void writeToCSV(String outputFilePath)
    {
        //TBA
    }
    
    private List<List<Double>> getStatTable() {
        return statTable;
    }

    private String getOutputFilePath() {
        return outputFilePath;
    }
    
    //TESTABLE CLONES
    public static boolean writeOutputGloballyT(String outputFilePath, List<List<Double>> statTableT)
    {
        boolean didItWork = false;
        
        if(outputFilePath == null)
        {
            writeToConsoleT(statTableT);
            didItWork = true;
        }
        else
        {
            writeToCSVT(outputFilePath);
            didItWork = true;
        }
        
        return didItWork;
    }
  
    public static boolean writeToConsoleT(List<List<Double>> statTableT)
    {
        boolean didItWork = false;
        
        System.out.println();
        for(int i = 0; i < statTableT.size(); i++)
        {
            System.out.println("-----------------------Query Statistics----------------------\n");
          
            System.out.println("Environment (" + (i+1) + "/" + statTableT.size() + ")\n");
            for(int k = 0; k < statTableT.get(i).size(); k++)
            {
              
                switch (k) 
                {
                    case 0:
                        System.out.print("Mean: ");
                        break;
                    case 1:
                        System.out.print("Median: ");
                        break;
                    case 2:
                        System.out.print("Standard Deviation: ");
                        break;
                    default:
                        System.out.print("98th Percentile: ");
                        break;
                }
                double temp = statTableT.get(i).get(k);
                System.out.format("%.2f%n",temp);
            }
            System.out.println("-------------------------------------------------------------");
            System.out.println();
            didItWork = true;
        }
        
        return didItWork;
    }
    
    public static boolean writeToCSVT(String outputFilePath)
    {
        boolean didItWork = false;
        
        if(outputFilePath != null)
        {
            didItWork = true;
        }
        
        return didItWork;
    }
}
