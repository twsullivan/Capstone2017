import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class DirectoryReader
{
    File[] fileList;
    private List<List<Double>> envResponseTimes;
        
    public DirectoryReader()
    {
        
    }
    
    public DirectoryReader(String filePath) throws IOException  
    {
        fileList = getJSONSInDir(filePath); //fileList is a file array
        setEnvResponseTimes(this.fileList);
    }

    public List<List<Double>> getEnvResponseTimes() {
        return envResponseTimes;
    }
    
    private File[] getJSONSInDir(String path) throws IOException //filepath passed here as path
    {
      File dir = new File(path); //the path is the directory to look it
      
      if (dir.exists())
      {
          File[] tempFileList = dir.listFiles(new FilenameFilter() //this filter finds all jsons in folder and puts in a file array
                  {
                      @Override
                      public boolean accept(File dir, String name)
                      {
                          return name.endsWith(".JSON") || name.endsWith(".json");
                      }
                  });
          return tempFileList; //returns tempFileList array to fileList array
      }
      else
      {
          throw new IOException("Error: Directory not found.");
      }
    }
    
    public void setEnvResponseTimes(File[] filelist) throws FileNotFoundException
    {  
       List<List<Double>> queryEnvironments = new ArrayList<>();
        
        for (File fileListIter : fileList) {
            Reader reader = new FileReader(fileListIter);

            JsonParser parser = new JsonParser();
            JsonObject obj = (JsonObject)parser.parse(reader);
            JsonArray arr = obj.getAsJsonArray("queryResults");
            Gson gson = new Gson();

            String eId = obj.get("environmentId").getAsString();
            String dListId = obj.get("domainNameListId").getAsString();
            String qRunBy = obj.get("queriesRunBy").getAsString();
            ArrayList qRes = gson.fromJson(arr, ArrayList.class);
       
            Object[] tempArr = qRes.toArray();
            String[] stringOfJsonObjectsArr = new String[tempArr.length];
            ArrayList<String[]> queryResultElements = new ArrayList<>();
            
            List<Double> responseTimes = new ArrayList<>();
            
            
            for(int i = 0; i < tempArr.length; i++)
            {
                stringOfJsonObjectsArr[i] = tempArr[i].toString();
            }
            
            for (String stringOfJsonObjectsArr1 : stringOfJsonObjectsArr) 
            {
                StringBuffer buff;
                String[] splitElement = stringOfJsonObjectsArr1.split(", ");
                String tempQueryResult = splitElement[1];
                if(tempQueryResult.substring(0,14).equals("responseTimeMs"))
                {
                    buff = new StringBuffer(tempQueryResult);
                    buff.delete(0, 15);
                    String buffConverted = buff.toString();  
                    Double val = Double.parseDouble(buffConverted);
                    responseTimes.add(val);
                }
            }
            queryEnvironments.add(responseTimes);
        }
        this.envResponseTimes = queryEnvironments;
    }
}


