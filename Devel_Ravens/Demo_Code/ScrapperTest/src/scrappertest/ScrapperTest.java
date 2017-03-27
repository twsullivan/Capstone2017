/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrappertest;
import java.util.*;
/**
 *
 * @author Cody Grogan
 */
public class ScrapperTest 
{

public static void main(String[] args)
{
   String user = "Test";
   String initialDNS = "www.uwf.edu";
   String listId = "T1";
   String listDesc = "Test 1";
   int nLevels = 0;
   String fileName = "output.json";
   Driver scrapper = new Driver();
   
   try
   {
       
       if(args.length == 0){
            scrapper.usageDump();
       }
       else{
            user = args [0];
            initialDNS = args[1];
            listId = args[2];
            listDesc = args[3];
            nLevels = Integer.parseInt(args[4]);
           /*
           for(int i=0; i<args.length; i++){
               System.out.println(i + " " + args[i]);
           }
           */
           
            scrapper.setUser(user);
            scrapper.setInitialDNS(initialDNS);
            scrapper.setListId(listId);
            scrapper.setListDesc(listDesc);
            scrapper.setnLevels(nLevels);

            scrapper.runDriver();
       }
       
   }
   catch (ArrayIndexOutOfBoundsException e)
   {
       System.err.println("Error: No and/or malformed arguments.");
       scrapper.usageDump();
       //System.out.println ("Please ensure that the command includes the follow arguements:");
       //System.out.println ("<URL> <output file name> <Number of Levels>");
   }
   catch (NumberFormatException e){
       System.err.println("Error: Be sure that levels is a integer value.");
       scrapper.usageDump();
       
   }
    /*
    Scanner input = new Scanner(System.in);
    System.out.println("Please input user's name:");
    user = input.nextLine();
    System.out.println("What URL would you like to scrap?");
    initialDNS = input.nextLine();
    System.out.println("Please input list ID:");
    listId = input.nextLine();
    System.out.println("Please input list description:");
    listDesc = input.nextLine();
    System.out.println("How many levels do you wish to scrap?");
    nLevels = input.nextInt();
    */
    
    
   
   
    //scrapper = new Driver(user, initialDNS, listId, listDesc, nLevels, fileName);
    
    //scrapper.runDriver();
}
    
}
