package domain.scraper;

import java.io.*;
import java.util.*;

public class Format {

    String inputFileName;
    File inputFile;
    ArrayList urls = new ArrayList();
    ArrayList domains = new ArrayList();
    ArrayList tokenList = new ArrayList();
    String line = null;
    int count = 0;

    public ArrayList format(){
        
        try{
            FileReader fileReader = new FileReader(inputFileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
        
            while((line = bufferedReader.readLine()) != null){
                checkLine(line);
                count++;
            }
            /* //Commented out because testing is easier without it
            //Empty the file
            PrintWriter writer = new PrintWriter(inputFile);
            writer.print("");
            writer.close();
            */
        }
        catch(FileNotFoundException e){
            System.out.println("File was not found.");
        }
        catch(IOException e){
            System.out.println("IOException caught");
        }
            
            
        return urls;
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
    
    public void generateDomains(ArrayList input){
        
        ArrayList domainList = new ArrayList();
        
        for(int i=0; i < input.size(); i++){
            String line = (String)input.get(i);
            String[] postSplit = line.split("/");
            
            //domainList.add(postSplit[2]);
            domains.add(postSplit[2]);
        }
        
        //return domainList;
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
            
    //just for testing
    private void printTokens(StringTokenizer tokens){
        
        int tokenCount = tokens.countTokens();
        
        for(int i=0; i<tokenCount; i++){
            System.out.print(" |Token " + i + " =| " +tokens.nextToken());
        }
        System.out.println("");
    }
    
    private ArrayList removeDuplicates(ArrayList input){
      ArrayList duplicateList = new ArrayList(); //ArrayList duplicateList = input;
      ArrayList returnList = new ArrayList();
      boolean duplicate = false;
      
      duplicateList.add("1");
      
      for(int i=0; i<input.size(); i++){
         for(int j=0; j<duplicateList.size(); j++){
            if(input.get(i) == duplicateList.get(j)){
               duplicate = true;
               break;
            }
         }
         
         if(duplicate == true){
            duplicateList.add(input.get(i));
            duplicate = false;
         }
         else{
             returnList.add(input.get(i));
         }
      }
      
      return returnList;
    }

    public void setInputFile(String inputFileName){
        this.inputFileName = inputFileName;
    }
    
    public File getInputFile(){
        return inputFile;
    }
    
    public ArrayList getUrls(){
        return urls;
    }
    
    public ArrayList getDomains(){
        return domains;
    }  
}
