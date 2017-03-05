
package scrappertest;

import java.io.*;
import java.util.*;

public class JSON {
    
    ArrayList nameList;
    String user;
    String date;
    String initialDNS;
    String listID;
    String listDescription;
    File output = new File("JSON.txt");
    
   
    
    
    public void makeJSON(){
        
        printInfo();
        printList();       
    }
    
    public void printInfo(){
        try{
        PrintWriter writer = new PrintWriter(output);
        
        writer.println("{");
        writer.println("\"domainNameListId\": \"" + listID + "\",");
        writer.println("\"listPreparedBy\" : \"" + user + "\",");
        writer.println("\"listDescription\" : \"" + listDescription + "\",");
        
        writer.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File was not found.");
        }
    }
    
    public void printList(){
        try{
        PrintWriter w = new PrintWriter(output);
        
        w.println("domainNames");
        
        for(int i=0; i < nameList.size(); i++){
            w.println("\"" + nameList.get(i) + "\",");
        }
        w.close();
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
    public void setDate(String passed){
        date = passed;
    }
    public void setListID(String passed){
        listID = passed;
    }
    public void setListDescription(String passed){
        listDescription = passed;
    }
}
