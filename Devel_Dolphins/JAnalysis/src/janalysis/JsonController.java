/**
 *
 * @author devel_dolphins
 */
package janalysis;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonController
{
    private String path;
    private ArrayList<String> jsonFileNames = new ArrayList<String>();
    private ArrayList<File> jsonFiles = new ArrayList<File>();
    private ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
    private ArrayList<String> environmentNames = new ArrayList<String>();
    
    public JsonController()
    {
        path = System.getProperty("user.dir");
    }
    
    public JsonController(String inputPath)
    {
        path = inputPath;
    }
    
    
    public int findJsonFiles() //Checks the running directory for .json file names
    {
        try
        {
        File dir = new File(path);  
        String[] filesInDirectory = dir.list();
        if(filesInDirectory == null)
        {
            System.out.println("Could not find any other files in the directory."); //This won't really ever happen
        }
        else
        {
            System.out.println("\nSEARCHING FOR FILES IN DIRECTORY:");  //DEBUG TEXT
            int count = 0;
            for (String fileNames : filesInDirectory)
            {
                //System.out.println("-" + fileNames);
                if(fileNames.contains(".json"))
                {
                    //System.out.println("JSON File ["+fileNames+"] Found. Adding to jsonFiles ArrayList."); //DEBUG TEXT
                    jsonFileNames.add(fileNames);
                    count++;
                }
            }
            System.out.println("-Found ["+count+"] json files.");
            return count;
        }       
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return 0;
        
    }
    
    public void loadFilesToList() //Using the ArrayList<String> of filenames, tries to open each as a File object and adds to the ArrayList<File> 
    {
        System.out.println("CREATING FILE OBJECTS:");
        int count = 0;
        for(String fileNames: jsonFileNames)
        {
            try{
            File temp = new File(path + "\\" + fileNames);
            count++;
            //System.out.println("Made File Object from filename: " +fileNames);  //DEBUG TEXT
            jsonFiles.add(temp);
            }
            catch(Exception e)
            {
                System.out.println(e); //DEBUG FOR IF FILES ARE NOT ABLE TO BE READ
            }
        }
        System.out.println("-Created ["+count+"] File Objects.");
        //System.out.println("\nFILE OBJECTS LOADED TO LIST:");
        //System.out.println(jsonFiles);
    }
    
    public void createJSONObjects()
    {
        System.out.println("PARSING JSON OBJECTS:"); //DEBUG TEXT
        int count = 0;
        for(File filename: jsonFiles)
        {
            try
            {
                String content = new Scanner(filename).useDelimiter("\\Z").next();   //Converts file to String
                JSONObject temp = (JSONObject) new JSONTokener(content).nextValue();
                jsonObjects.add(temp);
                //System.out.println("Added " + filename + " to jsonObjects list."); //DEBUG TEXT
                count++;
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }   
        System.out.println("-Created ["+count+"] JSON Objects.");
    }
    
    public int createEnvironmentsList()
    {
        System.out.println("CREATING ENVIRONMENTS LIST:");
        for(JSONObject obj: jsonObjects)
        {
            String temp = obj.getString("environmentId");
            if(!environmentNames.contains(temp))
            {
                environmentNames.add(temp);
            }              
        }
        System.out.println(environmentNames);
        if(environmentNames.isEmpty())
        {
            System.out.println("No Environments Found.");
        }
        return environmentNames.size();
    }
    
    public JaccardController createJaccardController()
    {
        JaccardController temp = new JaccardController();
        int counter = 0;
        for(String envName: environmentNames)
        {
            Environment e = new Environment(envName,counter);
            temp.addEnvironment(e);
            counter++;
        }
        return temp;
    }
    
    public void addBlockedDomains(JaccardController jCon)
    {
        for(JSONObject obj: jsonObjects)
        {
            try
            {
                JSONArray qResults = obj.getJSONArray("queryResults");      //creates a JSONArray for the queryResults attribute
                String qEnvId = obj.getString("environmentId");         //extracts the name of the environment
                Environment toAddto = jCon.getEnvironment(qEnvId);       //gets the environment object related to the environmentID
                //System.out.println(toAddto);
                for(int i = 0; i < qResults.length(); i++)
                {    
                    JSONObject qResultsObj = (JSONObject)qResults.get(i); 
                    
                    String queryStatus = qResultsObj.getString("queryResult");
                    String domainName = qResultsObj.getString("domainName");
                    if(queryStatus.equals("BLOCKED"))
                    {
                        if(!toAddto.contains(domainName))
                        toAddto.addDomain(domainName);
                    }
                }                
            }
            catch(Exception e)
            {
                System.out.println("Error at:");
                System.out.println(e);
            }
        }
    }
}
