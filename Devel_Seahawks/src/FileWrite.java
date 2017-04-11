import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class is responsible for how data from the QStats class is presented.
 * When no output specification is made, this class will present the data to the 
 * console. FileWrite will create and output to a file name when specified in the input
 * parameters.
 */

/**
 *
 * @author UWF Capstone, Team Seahawks, Frank Moss, Chase Green, Paul Gartner
 */
public class FileWrite {

    List<List<Double>> statTable;
    String outputFilePath;
    List<Integer> envCounts;
    List<String> envID;
    
    public FileWrite() 
    {
     
    }
    
    public FileWrite(List<List<Double>> statTable, String outputFilePath, List<Integer> envCounts, List<String> envID)
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
  
    private void writeToConsole()
    {
      
       int countMarker = 0;
        
       for(int i = 0; i < this.statTable.size(); i++)
       {
          System.out.println("--------------------------------------------Results-------------------------------------------");
           
          System.out.println("Env ID: " + this.envID.get(i));
          System.out.println("Count of Normals: " + this.envCounts.get(i+countMarker));
          System.out.println("Count of Blocked: " + this.envCounts.get(i+countMarker+1));
          System.out.println("Count of Unresolved: " + this.envCounts.get(i+countMarker+2));
          countMarker+=2;
          
          for(int k = 0; k < this.statTable.get(i).size(); k++)
          {
            switch(k)
            {
                case 0:
                    System.out.println("Mean: " + this.statTable.get(i).get(k));
                    break;
                case 1:
                    System.out.println("Median: " + this.statTable.get(i).get(k));
                    break;
                case 2:
                    System.out.println("Standard Deviation: " + this.statTable.get(i).get(k));
                    break;
                case 3:
                    System.out.println("98th Percentile: " + this.statTable.get(i).get(k));
                    break;
            }
            
          }
            
       
       }
       System.out.println("----------------------------------------------------------------------------------------------");
       System.out.println();
    
    }
    
    private void writeToCSV(String outputFilePath)
    {

        BufferedWriter writer = null;
        FileWriter csvFile = null;
        
        
        try
        {
            
          String header = "Environment ID" + "," + "Count of Normals" + "," + "Count of Blocked" + "," + "Count of Unresolved" + "," + "MeanRT" + "," + "MedianRT" + "," + "Standard Deviation" + "," + "98th Percentile";
          csvFile = new FileWriter(outputFilePath);
          writer = new BufferedWriter(csvFile);
          writer.write(header);
          
          String data[][] = new String[this.statTable.size()][this.statTable.get(0).size()+4];
          int countMarker = 0;
          
          for(int i = 0; i < this.statTable.size(); i++)
            {      
            data[i][0] = this.envID.get(i);   
            data[i][1] = this.envCounts.get(i+countMarker).toString();
            data[i][2] = this.envCounts.get(i+countMarker+1).toString();
            data[i][3] = this.envCounts.get(i+countMarker+2).toString();
            countMarker+=2;
          
                    for(int k = 0; k < this.statTable.get(i).size(); k++)
                    {
                     double temp = this.statTable.get(i).get(k);
                     data[i][k+4] = String.valueOf(temp);
                    }
            }
            System.out.println("Writing to " + outputFilePath + ".");
            for(int i = 0; i < this.statTable.size(); i++)
              {
                  writer.newLine();
                  for(int k = 0; k < this.statTable.get(i).size()+4; k++)
                  {
                  writer.write(data[i][k] + ",");

                  }
              }
            
        } 
        catch (IOException x) 
        {
        }
        finally
        {
            try
            {
                if (writer != null)
                {
                    writer.close();
                }
                if (csvFile != null)
                {
                    csvFile.close();
                }
            }
            catch (IOException ex)
                    {
                    }
        }
        
    }
    
    private List<List<Double>> getStatTable() {
        return statTable;
    }

    private String getOutputFilePath() {
        return outputFilePath;
    }
    
}
