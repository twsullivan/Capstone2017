import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWriter {

    List<List<Double>> statTable;
    String outputFilePath;
    List<Integer> envCounts;
    
    public FileWriter() 
    {
        //Default Constructor
    }
    
    public FileWriter(List<List<Double>> statTable, String outputFilePath, List<Integer> envCounts) 
    {
        this.statTable = statTable;
        this.outputFilePath = outputFilePath;
        this.envCounts = envCounts;
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
  
    private void writeToConsole() //changed
    {
       System.out.println();
       for(int i = 0; i < this.statTable.size(); i++)
       {
          System.out.println("-----------------------Query Statistics----------------------\n");
          
          System.out.println("Environment (" + (i+1) + "/" + this.statTable.size() + ")\n");
          System.out.println("Count of Domains: " + this.envCounts.get(i).toString() + "\n");
          
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
    
}
