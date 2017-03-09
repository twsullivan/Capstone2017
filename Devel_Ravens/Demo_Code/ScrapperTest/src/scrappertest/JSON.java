
package scrappertest;

import java.io.*;
import java.util.*;

public class JSON {
    
    ArrayList nameList;
    String user;
    String listID;
    String listDescription;
    File output = new File("JSON.txt");
    
    public JSON()
    {
        user = "";
        listID= "";
        listDescription = "";
    }
    
    
    public void makeJSON(){
        
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
    
    public void setNameList(ArrayList passedList){
        nameList = passedList;
    }
    
    public void setUser(String passed){
        user = passed;
    }
    
    public void setListID(String passed){
        listID = passed;
    }
    public void setListDescription(String passed){
        listDescription = passed;
    }
}
