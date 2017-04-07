import com.google.gson.*;
import java.io.*;
import java.util.*;

public final class FolderReader //changed
{
    File[] fileList;
    private List<List<Double>> envResponseTimes;
    private List<Integer> envCounts = new ArrayList();
    private List<String> envID = new ArrayList();
    
    public FolderReader()
    {
        //Empty
    }
    
    public FolderReader(String filePath, boolean verbose, boolean ignoreBlocked, boolean ignoreUnresolved, boolean ignoreNormals) throws IOException, NumberFormatException //changed
    {
        fileList = getJSONSInDir(filePath); //fileList is a file array
        setEnvResponseTimes(this.fileList, verbose, ignoreBlocked, ignoreUnresolved, ignoreNormals);
    }

    public List<Integer> getEnvCounts() {
        return envCounts;
    }

    public List<List<Double>> getEnvResponseTimes() {
        return envResponseTimes;
    }
    
    public List<String> getEnvID() {
        return envID;
    }
        
    
    private File[] getJSONSInDir(String path) throws IOException //filepath passed here as path
    {
      File dir = new File(path); //the path is the directory to look in
      
      if (dir.exists() && dir.list().length > 0)
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
          throw new IOException("Directory not found.");
      }
    }
    
    private boolean validateIPAddress(String ip) //changed
    {
        if ( ip == null || ip.isEmpty() ) {
            return false;
        }

        String[] parts = ip.split( "\\." );
        if ( parts.length != 4 ) {
            return false;
        }

        for ( String s : parts ) {
            int i = Integer.parseInt( s );
            if ( (i < 0) || (i > 255) ) {
                return false;
            }
        }
        if ( ip.endsWith(".") ) {
            return false;
        }
        
        return true;
    }
    
    public void setEnvResponseTimes(File[] filelist, boolean verbose, boolean ignoreBlocked, boolean ignoreUnresolved, boolean ignoreNormals) throws FileNotFoundException, NumberFormatException, JsonSyntaxException
    {  //changed
       List<List<Double>> queryEnvironments = new ArrayList<>();
        
        for (File fileListIter : fileList) {
            
            
            if(verbose)
            {
                System.out.println("\n-Opening File: " + fileListIter + "-");
            }
            
            Reader reader = new FileReader(fileListIter);

            JsonParser parser = new JsonParser();
            JsonObject obj = (JsonObject)parser.parse(reader);
            JsonArray arr = obj.getAsJsonArray("queryResults");
            Gson gson = new Gson();

            String eId = obj.get("environmentId").getAsString();
            this.envID.add(eId);
            
            ArrayList qRes = gson.fromJson(arr, ArrayList.class);
       
            Object[] tempArr = qRes.toArray();
            String[] stringOfJsonObjectsArr = new String[tempArr.length];
            ArrayList<String[]> queryResultElements = new ArrayList<>();
            
            List<Double> responseTimes = new ArrayList<>();
            int countOfNormalDomains = 0;
            int countOfBlocked = 0;
            int countOfUnresolved = 0;
            
            for(int i = 0; i < tempArr.length; i++)
            {
                stringOfJsonObjectsArr[i] = tempArr[i].toString();
            }
            
            for (String stringOfJsonObjectsArr1 : stringOfJsonObjectsArr) 
            {
                
                StringBuffer buff;
                String[] splitElement = stringOfJsonObjectsArr1.split(", ");
                String tempQueryResult = splitElement[splitElement.length-1];
 
                
                boolean validIp = validateIPAddress(tempQueryResult.substring(12,tempQueryResult.length()-1));
                
                if(validIp)
                {
                    if(ignoreNormals)
                    {
                        if(verbose == true)
                        {
                            System.out.println("IgnoreN: " + stringOfJsonObjectsArr1);
                        }
                    }
                    else
                    {
                    
                        if(verbose == true)
                        {
                            System.out.println("Reading: " + stringOfJsonObjectsArr1);
                        }
                        
                         buff = new StringBuffer(splitElement[1]);
                         buff.delete(0, 15);
                         String buffConverted = buff.toString();  
                         Double val = Double.parseDouble(buffConverted);
                         if(val < 0)
                         {
                             throw new ArithmeticException("Negative value(s) in query data.");
                         }
                         
                         
                         responseTimes.add(val);
                         countOfNormalDomains++;
                    }
                }
                else if((tempQueryResult.substring(12).equals("BLOCKED}")))
                {
                    
                    if(ignoreBlocked)
                    {
                        if(verbose == true)
                        {
                            System.out.println("IgnoreB: " + stringOfJsonObjectsArr1);
                        }
                    }
                    else
                    {
                        if(verbose == true)
                        {
                            System.out.println("Reading: " + stringOfJsonObjectsArr1);
                        }
                    
                         buff = new StringBuffer(splitElement[1]);
                         buff.delete(0, 15);
                         String buffConverted = buff.toString();  
                         Double val = Double.parseDouble(buffConverted);
                         if(val < 0)
                         {
                             throw new ArithmeticException("Negative value(s) in query data.");
                         }
                         responseTimes.add(val);
                         countOfBlocked++;
                    }
                }
                else if((tempQueryResult.substring(12).equals("UNRESOLVED}")))
                {
                    if(ignoreUnresolved)
                    {
                      if(verbose == true)
                        {
                            System.out.println("IgnoreU: " + stringOfJsonObjectsArr1);
                        }  
                    }
                    else
                    {
                        if(verbose == true)
                        {
                            System.out.println("Reading: " + stringOfJsonObjectsArr1);
                        }
                        countOfUnresolved++;
                    }
                }
                else
                {
                    throw new NumberFormatException("One or more .JSON file(s) are not formatted correctly. Please see documentation for correct format.");
                }
            }
            Integer intObjOfNormalCounts = countOfNormalDomains;
            Integer intObjOfBlockedCounts = countOfBlocked;
            Integer intObjOfUnresolvedCounts = countOfUnresolved;
            
            
            
            queryEnvironments.add(responseTimes);
            envCounts.add(intObjOfNormalCounts);
            envCounts.add(intObjOfBlockedCounts);
            envCounts.add(intObjOfUnresolvedCounts);
            
        }
        this.envResponseTimes = queryEnvironments;
    }
}


