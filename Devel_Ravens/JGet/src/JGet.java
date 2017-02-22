/*  Capstone Systems Project - 10215 CIS4595C 201701
    Team - Devel_Ravens
    Cody Mahaffey
    
    This is an attempt to implement wGet functionality in java to scrap URLs
    for multi-platform usability. 

    Idea was found at - http://alvinalexander.com/blog/post/java/jget-something-like-wget
    after failing to locate a usable cURL library for java.
 */

import java.io.*;
import java.net.*;
//import java.util.ArrayList;

public class JGet {
    private String url;
    private String outFileName;
    //private ArrayList<String> inputs;
    private File outFile;
    private FileWriter fWriter;
    private PrintWriter pWriter;
    
    public JGet(){
        url = "";
        outFileName = "HTMLDump.txt";
    }
    public JGet(String str) throws IOException{
        super();
        runJGet(str);
    }
    private void runJGet(String str) throws IOException{
        //Variables the website's datastreams.
        URL u;
        InputStream is;
        DataInputStream dis;
        String site;
        
        //Create output file.
        outFile = new File (outFileName);
        fWriter = new FileWriter (outFile, true);
        pWriter = new PrintWriter (fWriter);

        //Building the string for switch case.
        String tmp = str.charAt(0) + "" + str.charAt(1) 
                + "" + str.charAt(2) + "";
        
        switch(tmp){
            case "www":
                url = "http://" + str;
                //System.out.println("test");
                break;
            case "htt":
                url = str;
                //System.out.println("test2");
                break;
            default:
                //System.out.println("default");
                break;
        }
        
        try{
            u = new URL(url);
            is = u.openStream();
            dis = new DataInputStream(new BufferedInputStream(is));
            
            //Reading in the datastream.
            while ((site = dis.readLine()) != null){
                pWriter.println(site);
            }
            
            pWriter.println("------------ END OF DUMP ----------------------------");
            
            //Closing datastreams.
            dis.close();
            pWriter.close();
            is.close();
        }
        catch (MalformedURLException mue){
            System.err.println("Ouch - a MalformedURLException happened.");
            System.exit(2);
        }
        catch (IOException ioe){
            System.err.println("Oops- an IOException happened.");
            System.exit(3);
        }
        finally{
            System.out.println(url + " HTML code has been added to the dump file.");
        }
    }
    
    //Getters and Setters
    public String getOutput(){return outFileName;}
    public void setOutput(String outFileName){
        this.outFileName = outFileName;
    }
    public String getURL(){return url;}
    public void setURL(String url){
        this.url = url;
    }
}
