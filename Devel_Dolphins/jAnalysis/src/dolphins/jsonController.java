/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dolphins;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author acm52
 */
public class jsonController
{
    /*
    public readFilesToList();
    public convertFilesToStrings();
    public convertStringsToJson();
    public createEnvironmentsList();
    public sortBlockedResults();
    */
    private ArrayList<String> jsonFileNames = new ArrayList<String>();
    private ArrayList<File> jsonFiles = new ArrayList<File>();
    private ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
    private ArrayList<String> environmentNames = new ArrayList<String>();
    public jsonController(){}
    
    public int findJsonFiles() //Checks the running directory for .json file names
    {
        try
        {
        File dir = new File(System.getProperty("user.dir"));  
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
            File temp = new File(fileNames);
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
    
    public void validateJSONObjects()  //checks each object has no problems
    {
        for(JSONObject obj: jsonObjects)
        {
            //make sure the parameters are valid i guess
        }
       
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
    
    public void createEnvironmentObjects()
    {
        //using the names from the list of environments, creates environment objects
    }
    
    public void addBlockedDomains()
    {
        //using the JSOBObjects list, based on the environment for that object, returns the STRING value of domainName for cases where queryResult = Blocked
    }
   
}
