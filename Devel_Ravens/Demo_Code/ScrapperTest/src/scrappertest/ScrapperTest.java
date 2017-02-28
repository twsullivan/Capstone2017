/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrappertest;

import java.util.Scanner;

/**
 *
 * @author Cody Grogan
 */
public class ScrapperTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Start Application\n\n");
        
        Scanner in = new Scanner(System.in);
        
        //information required from users
        String user;
        String date;
        String initialDNS = "www.stackoverflow.com";
        String listId;
        String listDesc;
        int nLevels;
        
        //Gets needed information from user
        System.out.println("Please input user's name: ");
        user = in.nextLine();
        System.out.println("Please input the date: ");
        date = in.nextLine();
        //System.out.println("Please input desired website address: ");
        //initialDNS = in.nextLine();
        System.out.println("Please input desired list ID: ");
        listId = in.nextLine();
        System.out.println("Please input describtion of list: ");
        listDesc = in.nextLine();
        System.out.println("Please input the level of search: ");
        nLevels = in.nextInt();
        System.out.println();
        
        //runs Driver class
        Driver scrap = new Driver(user, date, initialDNS, listId, listDesc, nLevels);
        scrap.runDriver();
        
        System.out.println("\n\nEnd Application");
    }
    
}
