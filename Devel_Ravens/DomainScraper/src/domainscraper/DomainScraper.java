/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainscraper;

/**
 *
 * @author Cody Grogan
 */
public class DomainScraper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   String user;
   String initialDNS;
   String listId;
   String listDesc;
   int nLevels;
   String fileName;
   Driver scrapper = new Driver();
   
   try
   {
       
       if(args.length == 0){
            scrapper.usageDump();
       }
       else{
            user = args [0];
            initialDNS = args[1];
            fileName = args[2];
            listId = args[3];
            listDesc = args[4];
            nLevels = Integer.parseInt(args[5]);
           
            scrapper.setUser(user);
            scrapper.setInitialDNS(initialDNS);
            scrapper.setFileName(fileName);
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
   }
   catch (NumberFormatException e){
       System.err.println("Error: Be sure that levels is a integer value.");
       scrapper.usageDump();
       
   }
    }
    
}