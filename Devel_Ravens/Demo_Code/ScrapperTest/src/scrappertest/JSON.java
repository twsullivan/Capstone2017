
package scrappertest;

import java.io.*;
import java.util.*;

public class JSON {
    
    ArrayList nameList;
    String user;
    String listID;
    String listDescription;
    File output;
    
    //Blank Constructor
    public JSON()
    {
        user = "";
        listID= "";
        listDescription = "";
        output = new File("output.json");
    }
    
    //Constructor with values for variables
    public JSON(String u, String id, String desc, String file)
    {
        user = u;
        listID= id;
        listDescription = desc;
        output = new File(file);
    }
            
    //Takes all the information and prints in JSON format
    //to designated file
    public void makeJSON()
    {
        
        try{
        PrintWriter writer = new PrintWriter(output);
        
        // *prints list, user id, & description
        writer.println("{");
        writer.println("\t\"domainNameListId\": \"" + listID + "\",");
        writer.println("\t\"listPreparedBy\" : \"" + user + "\",");
        writer.println("\t\"listDescription\" : \"" + listDescription + "\",");
        
        // * Prints list content...
        writer.println("\tdomainNames\": [");
        
        for(int i=0; i < nameList.size(); i++){
            writer.println("\t\t\"" + nameList.get(i) + "\",");
        }
        writer.println("\t]");
        writer.close(); 
        
        
        
        writer.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File was not found.");
        }
    }
    
    //Setter for nameList
    public void setNameList(ArrayList passedList){
        nameList = passedList;
    }
    
    //Setter for user
    public void setUser(String passed){
        user = passed;
    }
    
    //Setter for ListID
    public void setListID(String passed){
        listID = passed;
    }
    
    //Setter for ListDescription
    public void setListDescription(String passed){
        listDescription = passed;
    }
    
    //Setter for output
    public void setOutput(String passed)
    {
        output = new File(passed);
    }
}
