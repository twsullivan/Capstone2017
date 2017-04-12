/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainscraper;
/** Format is an attempt to extract both urls and domains from a web pages HTML code
 * <p>
 * Connects to HTMLdump.txt and scans HTML code for urls. URLs found are extracted and stored.
 * Domain names are then extracted from URLs and stored.
 * input. 
 * <p>
 * Public methods:
 * <ol>
 * <li>Constructors:</li>
    * <li>Format() -  constructor</li>
 * <li>Core Methods:</li>
    * <li>runHReF()) - connects to HTML dump file and collects urls from code found in file</li>
    * <li>checkLine(String input) - tokenizes line, calls checktoken on each part, adds urls to output</li>
    * <li>checkToken(String input) - returns URL if the token includes one</li>
    * <li>isURL(String token) - returns true if passed string matches url patterns</li>
    * <li>runWeblink() - extracts domains from urls found previously</li>
    * <li>endCheck(String line) - determines domain location in url</li>
    * <li>showURLs() - prints to screen all the urls found</li>
    * <li>showDomains() - prints to screen all the domains found</li>
 * <li>Getters:</li>
    * <li>getDomains() - returns the domains found</li>
    * <li>getURLs() - returns the urls found</li>
 * </ol>
 * 
 * 
 * @author Ryan Aguado
 * Capstone Systems Project - 10215 CIS4595C 201701
 * Team - Devel_Ravens
 * 
 */

import java.io.*;
import java.util.*;

public class Format{

    private String inputFileName;
    private ArrayList urls = new ArrayList();
    private ArrayList domains = new ArrayList();
    private ArrayList tokenList = new ArrayList();
    private String line = null;
    private int count = 0;
    
    //************************************************************************************
    //Constructors
    //************************************************************************************

    public Format()
    {
    inputFileName = "HTMLDump.txt";    
    }
    
    //************************************************************************************
    //Core Methods
    //************************************************************************************
    
    //connects to HTML dump file and collects urls from code found in file
    public void runHReF()
    {
        try{
            //establishes connect ot dump file
            File currentFile = new File(inputFileName);
            FileReader fileReader = new FileReader(currentFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            //while lines are found in HTML dump file, checks for urls
            while((line = bufferedReader.readLine()) != null){
                checkLine(line);
                count++;
            }
 
            //Deletes the contents of the HTMLDump.txt file
            currentFile.delete();
            FileWriter out = new FileWriter(currentFile);
            out.write("");
            out.close();
        }
        //If HTML dump file is not able to be found  
        catch(FileNotFoundException e){
            System.out.println("File was not found.");
        }
        
        catch(IOException e){
            System.out.println("IOException caught");
        }
        
            
    }
    
    //tokenizes line, calls checktoken on each part, adds urls to output
    private void checkLine(String input){
        StringTokenizer st = new StringTokenizer(input, "\"");
        
        String tmp = "0";
        
        while(st.hasMoreTokens()){
            tmp = checkToken(st.nextToken());
            
            if(tmp != "0"){
                urls.add(tmp);
            }
        }
    }
    
    //returns URL if the token includes one
    private String checkToken(String input){
        String returnString = "0";
        
        if(input.length() > 5){
            if(isURL(input)){       
                returnString = input;
            }
            else{
                returnString = "0";
            }
        }       
        return returnString;
    }
    
    //returns true if passed string matches url patterns
    private boolean isURL(String token){
        //this is where you can list all cases of url patterns 
        if(token.startsWith("http") || token.startsWith(" http")){
            return true;
        }
        return false;
    }
    
    //extracts domains from urls found previously
    public void runWeblink()
    {  
        try
        {
        for(int i=0; i < urls.size(); i++){
            String line = (String)urls.get(i);
            String[] postSplit = line.split("/");

            try
            {
            if(postSplit.length > 1){
                String newUrl = endCheck(postSplit[2]);
                domains.add(newUrl);
            }
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.err.println("Opps - Improper HTML Link");
            }

        }
        }
        catch(java.lang.StringIndexOutOfBoundsException e)
        {
            System.err.println("Opps - Domain extraction error");
        }
    }
    
    //determines domain location in url
    public String endCheck(String line)
    {
        boolean check = false;
        
        while(check == false)
        {
         int len = line.length();
         char end = line.charAt(len-1);
        
         if(end == '\\')
         {
            String temp  = line.substring(0, len-1);
            line = temp;
         }
         len = line.length();
         end = line.charAt(len-1);
         
         if(end == '\\')
            check = false;
         else
            check = true;
        }
        return line;
    }
    
    //prints to screen all the urls found
    public void showURLs()
    {
        for(int i = 0; i < urls.size(); i++)
        {
            System.out.println(urls.get(i));
        }
    }
    
    //prints to screen all the domains found
    public void showDomains()
    {
        for(int i = 0; i < domains.size(); i++)
        {
            System.out.println(domains.get(i));
        }
    }
    //************************************************************************************
    //Getters
    //************************************************************************************
    
    //Returns domains found
    public ArrayList getDomains()
    {
        return domains;
    }
    
    //Returns urls found
    public ArrayList getURLs()
    {
        return urls;
    }
}
