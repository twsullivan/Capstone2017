import java.awt.BorderLayout;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class FileWriter {

    List<List<Double>> statTable;
    String outputFilePath;
    List<Integer> envCounts;
    List<String> envID;
    
    public FileWriter() 
    {
        //Default Constructor
    }
    
    public FileWriter(List<List<Double>> statTable, String outputFilePath, List<Integer> envCounts, List<String> envID) //changed
    {
        this.statTable = statTable;
        this.outputFilePath = outputFilePath;
        this.envCounts = envCounts;
        this.envID = envID;
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
      JFrame frame = new JFrame("QStats.jar Results");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
      String column[]={"Environment ID","Count Of Domains with RT's","MeanRT","MedianRT","Standard Deviation","98th Percentile"};
      String data[][] = new String[this.statTable.size()][this.statTable.get(0).size()+2];
      
      for(int i = 0; i < this.statTable.size(); i++)
       {      
          data[i][0] = envID.get(i);   
          data[i][1] = this.envCounts.get(i).toString();
          for(int k = 0; k < this.statTable.get(i).size(); k++)
          {
             double temp = this.statTable.get(i).get(k);
             data[i][k+2] = String.valueOf(temp);
          }
       }
       
       System.out.println("\nPrinting results...");
       JTable table = new JTable(data, column);
       JScrollPane scrollPane = new JScrollPane(table);
       frame.add(scrollPane, BorderLayout.CENTER);
       frame.setSize(1000, 200);
       frame.setVisible(true);

       /*
       System.out.println("--------------------------------------Query Statistics------------------------------------------\n");
       System.out.println("Env.#\t\tCount\t\tMeanRT\t\tMedianRT\tSTDV\t\t98th");
       System.out.println("------------------------------------------------------------------------------------------------");
       
       for(int i = 0; i < this.statTable.size(); i++)
       {
          
          System.out.print((i+1)+"\t\t");
          System.out.print(this.envCounts.get(i).toString() + "\t\t");
          
          
          for(int k = 0; k < this.statTable.get(i).size(); k++)
          {
             double temp = this.statTable.get(i).get(k);
             System.out.printf("%3.2f",temp);
             System.out.print("\t\t");
          }
          System.out.println();
          

       }
       System.out.println("------------------------------------------------------------------------------------------------");
       System.out.println();
    */
    }
    
    private void writeToCSV(String outputFilePath)
    {
        //Your code goes here, Frank
    }
    
    private List<List<Double>> getStatTable() {
        return statTable;
    }

    private String getOutputFilePath() {
        return outputFilePath;
    }
    
}
