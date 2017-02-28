import java.io.*;
import java.net.*;

/**JGet is an attempt to implement wGet functionality in java to scrap URLs
 * for multi-platform usability.
 * <p>
 * JGet will take a URL and dump the HTML code into a text file. 
 * <p>
 * Public methods:
 * <ol>
 * <li>runJGet() - Opens the required input/output data streams and writes the 
 * HTML code of a given URL to an output text file. </li>
 * <li>getURL() - returns URL this object is currently set to scrap.</li>
 * <li>setURL(String URL) - sets URL this object will to scrap.</li>
 * <li>getOutput() - returns the output files name.</li>
 * <li>setOutput(String filename) - sets the file name for the HTMLdump.</li>
 * </ol>
 * 
 * The idea was found at - http://alvinalexander.com/blog/post/java/jget-something-like-wget 
 * after failing to locate a usable cURL library for java.
 * 
 * @author Cody Mahaffey
 * Capstone Systems Project - 10215 CIS4595C 201701
 * Team - Devel_Ravens
 * @throws IOExpection
 * @throws MalformedURLException If URL isn't inserted correctly into datastreams. 
 * 
 */


public class JGet {
    private String url;
    private String outFileName;
    private File outFile;
    private FileWriter fWriter;
    private PrintWriter pWriter;
    
   public JGet(){
        url = "www.uwf.edu";
        outFileName = "HTMLDump.txt";
    }
   
    public void runJGet() throws IOException{
        //Variables the website's datastreams.
        URL u;
        InputStream is;
        DataInputStream dis;
        String site;
        
        //Create output file.
        outFile = new File (outFileName);
        fWriter = new FileWriter (outFile, true);
        pWriter = new PrintWriter (fWriter);
       
        buildInput(getURL());
        
        try{
            u = new URL(url);
            is = u.openStream();
            dis = new DataInputStream(new BufferedInputStream(is));
            
            //Reading in the datastream.
            while ((site = dis.readLine()) != null){
                pWriter.println(site);
            }
            
            pWriter.println("------------ END OF DUMP ----------------------------");
            System.out.println(getURL() + " HTML code has been added to the dump file.");
            
            //Closing datastreams.
            dis.close();
            pWriter.close();
            is.close();
        }
        catch (MalformedURLException mue){
            System.err.println("Ouch - a MalformedURLException happened with \"" + getURL() + "\"");
            System.err.println("Consider starting your URL with \"www.\" or \"http://www.\"");
                      
        }
        catch (IOException ioe){
            System.err.println("Oops- an IOException happened.");
        }
    }
        
    //Getters and Setters
    public String getOutput(){return outFileName;}
    public void setOutput(String outFileName){
        this.outFileName = outFileName;
    }
    public String getURL(){return url;}
    
    /**
     * @param url When providing a URL please include "www." or "http://www." 
     *            before the domain or you will receive a MalformedURLException.
     */
    public void setURL(String url){
        this.url = url;
    }
    
    //Builds the correct URL syntax.
    private void buildInput(String str){
        //Building the string for switch case.
        String tmp = str.charAt(0) + "" + str.charAt(1) 
                + "" + str.charAt(2) + "";
        
        switch(tmp){
            case "www":
                setURL("http://" + str);
                break;
            case "htt":
                setURL(str);
                break;
        }
    }
}
