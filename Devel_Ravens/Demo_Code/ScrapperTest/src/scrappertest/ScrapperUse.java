/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrappertest;

import java.util.Scanner;

/**
 *
 * @author Ryang
 */
public class ScrapperUse {
    
    public static void main(String[] args) {
        System.out.println("Start Application\n\n");
        
        Scanner in = new Scanner(System.in);
        
        //information required from users
        String user;
        String date;
        String initialDNS = "www.stackoverflow.com";
        String listId;
        String listDesc;
        int nLevels = 0;
        
        //grabs url, listid and level of search from input arguments
        initialDNS = args[0];
        listId = args[1];
        nLevels = Integer.parseInt(args[2]); 
        
        
        //Gets needed information from user
        System.out.println("Please input user's name: ");
        user = in.nextLine();
        System.out.println("Please input the date: ");
        date = in.nextLine();
        System.out.println("Please input describtion of list: ");
        listDesc = in.nextLine();
        System.out.println();
        
        //runs Driver class
        Driver scrap = new Driver(user, date, initialDNS, listId, listDesc, nLevels);
        
        //usage dump
        if(initialDNS == null || listId == null || nLevels == 0){
            scrap.usageDump();
        }
        
        scrap.runDriver();
        
        System.out.println("\n\nEnd Application");
    }
    
}
