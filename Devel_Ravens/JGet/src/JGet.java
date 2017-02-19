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

public class JGet {
    
    public JGet() throws IOException{
        JGet g = new JGet("www.uwf.edu");
    }
    
    public JGet(String str) throws IOException{
        //Building the String for parsing.
        String url = "";
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
    
        //Create output file.
        File outFile = new File ("HTMLDump.txt");
        FileWriter fWriter = new FileWriter (outFile, true);
        PrintWriter pWriter = new PrintWriter (fWriter);
    
        //Opening datastreams for HTML Dump.
        URL u;
        InputStream is = null;
        DataInputStream dis;
        String s;

        try{
            u = new URL(url);
            is = u.openStream();
            dis = new DataInputStream(new BufferedInputStream(is));
            
            //Reading in the datastream.
            while ((s = dis.readLine()) != null){
                pWriter.println(s);
            }
            
            pWriter.println("------------ END OF DUMP ----------------------------");
            
            //Closing datastreams.
            dis.close();
            pWriter.close();
            is.close();
        }
        catch (MalformedURLException mue){
            System.err.println("Ouch - a MalformedURLException happened.");
            mue.printStackTrace();
            System.exit(2);
        }
        catch (IOException ioe){
            System.err.println("Oops- an IOException happened.");
            ioe.printStackTrace();
            System.exit(3);
        }
        finally{
            System.out.println(url + " HTML code has been added to the dump file.");
        }
        
    }
}
