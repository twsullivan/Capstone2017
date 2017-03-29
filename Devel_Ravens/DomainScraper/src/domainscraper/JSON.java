/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainscraper;


import java.io.*;
import java.util.*;

	
/**JSON is an attempt to implement JSON format & functionality in java to designated output file
	 * for multi-platform usability.
	 * <p>
	 * Takes all the information and prints in JSON format
	 * <p>
	 * Public methods:
	 * <ol>
	 * <li> writer.println() - *prints list
	 * <li> prints user id & description. </li>
 * <li> Prints list content. </li>
	 * <li>setNameList () - Setter for nameList,uses an arraylist.</li>
	 * <li>setUser (String passed) - Setter for user.</li>
	 * <li>setListID () - Setter for ListID.</li>
	 * <li>setListDescription (String filename) - sets the list description for the domain name.</li>
 * <li>setOutput(String passed) – Setter for output file</li>
	 * </ol>
	 * 
	 * The idea was found at - http://stackoverflow.com/questions/2591098/how-to-parse-json-in-java
	
	 * 
	 * @author Ibraheem Adediran
	 * Capstone Systems Project - 10215 CIS4595C 201701
	 * Team - Devel_Ravens
	 * @ try PrintWriter writer
	 * @catch FileNotFoundException e If File was not found. 
	 * 
	 */
	


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
        writer.println("\t\"domainNames\": [");
        
        for(int i=0; i < nameList.size()-1; i++){
            writer.println("\t\t\"" + nameList.get(i) + "\",");
        }
        
        writer.println("\t\t\"" + nameList.get(nameList.size()-1) + "\"");
        
        writer.println("\t]");
        writer.println("}");
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

