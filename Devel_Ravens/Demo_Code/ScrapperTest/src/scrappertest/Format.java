/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrappertest;

/**
 *
 * @author Ryan Aguado
 */

import java.io.*;
import java.util.*;

public class Format {

    private String inputFileName;
    private ArrayList urls = new ArrayList();
    private ArrayList domains = new ArrayList();
    private ArrayList tokenList = new ArrayList();
    private String line = null;
    private int count = 0;
    

    public Format()
    {
    inputFileName = "HTMLDump.txt";    
    }
    
    public void runHReF()
    {
        try{
            File currentFile = new File(inputFileName);
            FileReader fileReader = new FileReader(currentFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
        
            while((line = bufferedReader.readLine()) != null){
                checkLine(line);
                count++;
            }
            /*
            //Deletes the contents of the HTMLDump.txt file
            currentFile.delete();
            FileWriter out = new FileWriter(currentFile);
            out.write("");
            out.close();*/
        }
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
    
    public void runWeblink()
    {   
        for(int i=0; i < urls.size(); i++){
            String line = (String)urls.get(i);
            String[] postSplit = line.split("/");
            
            if(postSplit.length > 1){
                domains.add(postSplit[2]);
            }
        }
    }
    
    public void showURLs()
    {
        for(int i = 0; i < urls.size(); i++)
        {
            System.out.println(urls.get(i));
        }
    }
    
    public void showDomains()
    {
        for(int i = 0; i < domains.size(); i++)
        {
            System.out.println(domains.get(i));
        }
    }
    
    public ArrayList getDomains()
    {
        return domains;
    }
    
    public ArrayList getURLs()
    {
        return urls;
    }
}

