
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
    
    public JSON()
    {
        user = "";
        date = "";
        initialDNS = "";
        listID= "";
        listDescription = "";
    }
    
    
    public void makeJSON(){
        
        printInfo();
        //printList();       
    }
    
    public void printInfo(){
        try{
        PrintWriter writer = new PrintWriter(output);
        
        writer.println("{");
        writer.println("\t\"domainNameListId\": \"" + listID + "\",");
        writer.println("\t\"listPreparedBy\" : \"" + user + "\",");
        writer.println("\t\"listDescription\" : \"" + listDescription + "\",");
        
        // ORIGINALLY INTENTED FOR OTHER METHOD
        writer.println("\tdomainNames\": [");
        
        for(int i=0; i < nameList.size(); i++){
            writer.println("\t\t\"" + nameList.get(i) + "\",");
        }
        writer.println("\t]");
        writer.close(); 
        //END OF TROUBLESHOOTING SECTION
        
        
        writer.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File was not found.");
        }
    }
    
    public void printList(){
        try{
        PrintWriter w = new PrintWriter(output);
        
        w.println("\tdomainNames\": [");
        
        for(int i=0; i < nameList.size(); i++){
            w.println("\t\t\"" + nameList.get(i) + "\",");
        }
        w.println("\t]");
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
