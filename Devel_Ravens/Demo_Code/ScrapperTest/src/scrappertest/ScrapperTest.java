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
   try
   {
       initialDNS = args[0];
       fileName = args[1];
       nLevels = Integer.parseInt(args[2]);
   }
   catch (ArrayIndexOutOfBoundsException e)
   {
       System.out.println ("Please ensure that the command includes the follow arguements:");
       System.out.println ("<URL> <output file name> <Number of Levels>");
   }
   
   Scanner input = new Scanner(System.in);
   System.out.println("Please input user's name:");
   user = input.nextLine();
   System.out.println("Please input list ID:");
   listId = input.nextLine();
   System.out.println("Please input list description:");
   listDesc = input.nextLine();
   
   
    Driver scrapper = new Driver(user, initialDNS, listId, listDesc, nLevels, fileName);
    
    scrapper.runDriver();
}
    
}
