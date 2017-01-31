/*  Capstone Systems Project - 10215 CIS4595C 201701
    Team - Devel_Ravens
    Cody Mahaffey
    
    This is an attempt to implement wGet functionality in java to scrap URLs
    for multi-platform usability. 

    Idea was found at - http://alvinalexander.com/blog/post/java/jget-something-like-wget
    after failing to locate a usable cURL library for java.
 */
package jget;

import java.io.*;
import java.net.*;

public class JGet {

    public static void main(String[] args) throws IOException
  {

    if ( (args.length != 1) )
    {
      System.err.println( "\nUsage: java JGet [urlToGet]" );
      System.exit(1);
    }

    String url = args[0];
    
    File outFile = new File ("HTMLDump.txt");
    FileWriter fWriter = new FileWriter (outFile, true);
    PrintWriter pWriter = new PrintWriter (fWriter);
    
    URL u;
    InputStream is = null;
    DataInputStream dis;
    String s;

    try
    {
      u = new URL(url);
      is = u.openStream();
      dis = new DataInputStream(new BufferedInputStream(is));
      while ((s = dis.readLine()) != null)
      {
        pWriter.println(s);
      }
      
      pWriter.println("------------ END OF DUMP ----------------------------");
      pWriter.close();
    }
    catch (MalformedURLException mue)
    {
      System.err.println("Ouch - a MalformedURLException happened.");
      mue.printStackTrace();
      System.exit(2);
    }
    catch (IOException ioe)
    {
      System.err.println("Oops- an IOException happened.");
      ioe.printStackTrace();
      System.exit(3);
    }
    finally
    {
      try
      {
        is.close();
      }
      catch (IOException ioe)
      {
          System.out.println("Output file not found.");
      }
    }

  }

}