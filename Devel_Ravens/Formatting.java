import java.io.*;
import java.util.*;

public class formatting {

    public ArrayList output = new ArrayList();
    ArrayList tokenList = new ArrayList();
    String line = null;
    int count = 0;

    public ArrayList format(String fileName){
        
        try{
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
        
            while((line = bufferedReader.readLine()) != null){
                checkLine(line);
                count++;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File was not found.");
        }
        catch(IOException e){
            System.out.println("IOException caught");
        }
            
            
        return output;
    }
    
    //tokenizes line, calls checktoken on each part, adds urls to output
    private void checkLine(String input){
        StringTokenizer st = new StringTokenizer(input, "\"");
        //just for testing //System.out.println("Line " + count + " has " + st.countTokens() + " token(s).");
        //just for testing //printTokens(st);
        
        String tmp = "0";
        
        while(st.hasMoreTokens()){
            tmp = checkToken(st.nextToken());
            
            if(tmp != "0"){
                output.add(tmp);
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
            
    //just for testing
    private void printTokens(StringTokenizer tokens){
        
        int tokenCount = tokens.countTokens();
        
        for(int i=0; i<tokenCount; i++){
            System.out.print(" |Token " + i + " =| " +tokens.nextToken());
        }
        System.out.println("");
    }
            
    public static void main(String[] args) {       
        Formatting tester = new Formatting();
        
        tester.format("htmlDump.txt");
        
        System.out.println(tester.output.size() + " domains were found.");
        
        //print the final list
        for(int i=0; i < tester.output.size(); i++){
            System.out.println("Domain " + i + ": " + tester.output.get(i));
        }
    }  
}
