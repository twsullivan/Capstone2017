/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jaccardanalysistool;

import java.util.Scanner;

/**
 *
 * @author Delikarl
 */
public class Test {
    
    public static void main(String[] args){
        choices();
    }
    
    public static void choices(){
        System.out.println("\n[Which Class would you like to test?]");
        System.out.println("1.Test JsonController?\n2.Test JaccardController?\n3.Test Environment?\n4.Exit");
        Scanner sc = new Scanner(System.in);
        while(!sc.hasNextInt()){
            System.out.println("\nInvalid choice, Try again:\n");
            System.out.println("Which Class would you like to do?\n");
            System.out.println("1.Test JsonController?\n2.Test JaccardController?\n3.Test Environment?\n4.Exit");
            sc = new Scanner(System.in);
        }
        int input = sc.nextInt();
        if(input<=4 && input >=1){
            testClasses(input);
        }
        else{
            System.out.println("You only have a choice between 1 through 4. Try Again");
            choices();
        }
        
    }
    
    public static void testClasses(int input){
        switch(input){
            case 1:
                testJson();
                break;
            case 2:
                testJaccard();
                break;
            case 3:
                testEnvironment();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                choices();
        }
        System.out.println("\n");
    }
    
    
    
    public static void testJson(){
        JsonControllerTest jsonTest = new JsonControllerTest();
        System.out.println("\n\n[Which would you like to test?]\n");
        System.out.println("1.Find Json Files\n2.Load Files To List?\n3.Create Json Objects?\n"
                + "4.Create Environments List?\n5.Create Jaccard Controller?\n6.Add block domains?\n7.Exit?");
        Scanner sc = new Scanner(System.in);
        while(!sc.hasNextInt()){
            System.out.println("Which would you like to test?\n");
            System.out.println("1.Find Json Files\n2.Load Files To List?\n3.Create Json Objects?\n"
                    + "4.Create Environments List?\n5.Create Jaccard Controller?\n6.Add block domains?\n7.Exit?");
            sc = new Scanner(System.in);
        }
        int input = sc.nextInt();
        if(input<=7 && input >=1){
            switch(input){
                case 1:
                    jsonTest.testFindJsonFiles();
                    testJson();
                    break;
                case 2:
                    jsonTest.testLoadFilesToList();
                    testJson();
                    break;
                case 3:
                    jsonTest.testCreateJSONObjects();
                    testJson();
                    break;
                case 4:
                    jsonTest.testCreateEnvironmentsList();
                    testJson();
                    break;
                case 5:
                    jsonTest.testCreateJaccardController();
                    testJson();
                    break;
                case 6:
                    jsonTest.testAddBlockedDomains();
                    testJson();
                    break;
                case 7:
                    choices();
                    break;    
                default:
                    choices();
                    //exit(1);
            } 
        }
        else{
            System.out.println("You only have a choice between 1 through 7. Try Again");
            testJson();
        }
    }
    
    
    public static void testJaccard(){
        JaccardControllerTest jaccardTest = new JaccardControllerTest();
        System.out.println("\n\n[Which would you like to test?]\n");
        System.out.println("1.Add Environment?\n2.Get Environment?\n3.Print Environment?\n4.Compute Jaccard Analysis?\n5. Exit");
        Scanner sc = new Scanner(System.in);
        while(!sc.hasNextInt()){
            System.out.println("Which would you like to test?\n");
            System.out.println("1.Add Environment?\n2.Get Environment?\n3.Print Environment?\n4.Compute Jaccard Analysis?\n5. Exit");
            sc = new Scanner(System.in);
        }
        int input = sc.nextInt();
        if(input<=5 && input >=1){
            switch(input){
                case 1:
                    jaccardTest.testAddEnvironment();
                    testJaccard();
                    break;
                case 2:
                    jaccardTest.testGetEnvironment();
                    testJaccard();
                    break;
                case 3:
                    jaccardTest.testPrintEnvironments();
                    testJaccard();
                    break;
                case 4:
                    jaccardTest.testComputeJaccardAnalysis();
                    testJaccard();
                    break;
                case 5:
                    choices();
                    break;
                default:
                    choices();
                    //exit(1);
            } 
        }
        else{
            System.out.println("You only have a choice between 1 through 5. Try Again");
            testJaccard();
        }
    }
    
    
    public static void testEnvironment(){
        EnvironmentTest et = new EnvironmentTest();
        System.out.println("\n\n[Which would you like to test?]\n");
        System.out.println("1.Add domains?\n2.Get total number of domains?\n3.Get cross section?\n"
                + "4.Contains?\n5.Get Name?\n6.Get ID?\n7.Get Domains?\n8.Exit?");
        Scanner sc = new Scanner(System.in);
        while(!sc.hasNextInt()){
            System.out.println("Which would you like to test?\n");
            System.out.println("1.Add domains?\n2.Get total number of domains?\n3.Get cross section?\n"
                + "4.Contains?\n5.Get Name?\n6.Get ID?\n7.Get Domains?\n8.Exit?");
            sc = new Scanner(System.in);
        }
        int input = sc.nextInt();
        if(input<=8 && input >=1){
            switch(input){
                case 1:
                    et.testAddDomain();
                    testEnvironment();
                    break;
                case 2:
                    et.testGetTotalNumOfDomains();
                    testEnvironment();
                    break;
                case 3:
                    et.testGetCrossSection();
                    testEnvironment();
                    break;
                case 4:
                    et.testContains();
                    testEnvironment();
                    break;
                case 5:
                    et.testGetName();
                    testEnvironment();
                    break;
                case 6:
                    et.testGetID();
                    testEnvironment();
                    break;
                case 7:
                    et.testGetDomains();
                    testEnvironment();
                    break;
                case 8:
                    choices();
                    break;
                default:
                    choices();
                    //exit(1);
            }
            
        }
        else{
            System.out.println("You only have a choice between 1 through 8. Try Again");
            testEnvironment();
        }
        
    }
    
    
}
