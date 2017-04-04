/**
 * JsonController.java
 * This class is responsible for creating a JsonController object.
 * The JsonController object is used for reading, parsing, and handling the relevant JSON files.
 * @author devel_dolphins
 */
package jaccardanalysistool;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonController
{
    private String path;    //The path that will be used to check for Json files
    private ArrayList<String> jsonFileNames = new ArrayList<String>(); //A list to contain the names of JSON files found within the path
    private ArrayList<File> jsonFiles = new ArrayList<File>(); //A list of File Objects to be created using the filenames
    private ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>(); //A list of JSON objects to be created from parsing the File Objects list.
    private ArrayList<String> environmentNames = new ArrayList<String>(); //A list to contain the unique "environmentID" attributes found within the JSON Objects
    private JSONArray currentArray; //Keeps track of the current JSONArray that is being handled for printing out more specific error messages.
    private String currentEnvironment; //Keeps track of the current environment that is being handled for printing out more specific error messages.
    private int currentCounter;
    private int currentObjectCounter = 0;
    
    /*Default constructor, creates a JsonController object using the current running path*/
    public JsonController()
    {
        path = System.getProperty("user.dir");
    }
    
    /*Parameterized constructor, creates a JsonController object using an input string path*/
    public JsonController(String inputPath)
    {
        path = inputPath;
    }

    /*Scans the path given to the JsonController for files ending with .json, and returns the number of files found.*/
    public int findJsonFiles()
    {
        try
        {
            File dir = new File(path);  
            String[] filesInDirectory = dir.list();
            if(filesInDirectory == null)
            {
                System.out.println("Could not find any files in the directory.");
            }
            else
            {
                System.out.println("\nSEARCHING FOR FILES IN DIRECTORY:");  //DEBUG TEXT
                int count = 0;
                for (String fileNames : filesInDirectory)
                {
                    if(fileNames.contains(".json"))
                    {
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
    /*Using the ArrayList<String> of filenames, tries to open each as a File object and adds to the ArrayList<File>*/ 
    public void loadFilesToList() 
    {
        for(String fileNames: jsonFileNames)
        {
            try{
            File temp = new File(path + "/" + fileNames);
            jsonFiles.add(temp);
            }
            catch(Exception e)
            {
                System.out.println(e); //DEBUG FOR IF FILES ARE NOT ABLE TO BE READ
            }
        }
    }
    
    /*Parses the File objects in the jsonFiles List into JSONObjects by converting them to strings, then JSONObjects.*/
    /*Adds the valid JSONObjects to the jsonObjects List*/
    public void createJSONObjects()
    {
        System.out.println("PARSING JSON OBJECTS:");
        int count = 0;
        for(File filename: jsonFiles)
        {
            try
            {
                String content = new Scanner(filename).useDelimiter("\\Z").next();
                JSONObject temp = (JSONObject) new JSONTokener(content).nextValue();
                jsonObjects.add(temp);
                count++;
            }
            catch(Exception e)
            {
                System.out.println("ERROR WITH FILE: " + filename.getName());
                System.out.println(e);
            }
        }   
        System.out.println("-Created ["+count+"] JSON Objects.");
    }
    
    /*Goes through the jsonObjects list and attempts to find the "environmentID" tag to create a list of Environments*/
    /*Also returns the number of unique environments found as an integer value."*/
    public int createEnvironmentsList()
    {
        System.out.println("ENVIRONMENTS FOUND:");
        for(JSONObject obj: jsonObjects)
        {
            try
            { 
                String temp = obj.getString("environmentId");   
                if(!environmentNames.contains(temp))
                {
                    environmentNames.add(temp);
                }  
                currentObjectCounter++; 
            }
            catch(Exception e)
            {
               System.out.println("Error at file: " + jsonFileNames.get(currentObjectCounter));  
               System.out.println(e);
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
                currentArray = qResults;
                String qEnvId = obj.getString("environmentId"); //extracts the name of the environment
                currentEnvironment = qEnvId;
                Environment toAddto = jCon.getEnvironment(qEnvId);       //gets the environment object related to the environmentID
                //System.out.println(toAddto);
                for(int i = 0; i < qResults.length(); i++)
                {    
                    currentCounter = i;
                    JSONObject qResultsObj = (JSONObject)qResults.get(i); 
                    
                    String queryStatus = qResultsObj.getString("queryResult");
                    String domainName = qResultsObj.getString("domainName");
                    if(queryStatus.equalsIgnoreCase("BLOCKED"))
                    {
                        if(!toAddto.contains(domainName))
                        toAddto.addDomain(domainName);
                    }
                }                
            }
            catch(Exception e)
            {
                System.out.println("\n[Error] " + e);
                System.out.println("[Details] environment " + currentEnvironment + " at query number " + (currentCounter+1));
                System.out.println(currentArray.get(currentCounter));             
            }
        }
    }
}