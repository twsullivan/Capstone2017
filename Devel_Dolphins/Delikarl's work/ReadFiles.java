/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readfiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author djj5
 */

/*******************************************************************************************
*Test code for reading multiple (soon to be JSON) Files from a directory
* Written by Delikarl Jean-baptiste
*******************************************************************************************/
public class ReadFiles {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        readFilesFromDirectory("H:/Capstone");
    }
    
    public static void readFilesFromDirectory(String directory){
        File dir = new File(directory);
        String[] files = dir.list();
        ArrayList<String> subFiles = new ArrayList<String>();
        String search = ".txt";
        //search.toCharArray();
        if(files == null){
            System.out.println("Nothing in directory");
        }
        else{
            for(int i=0;i<files.length; i++){
                String fileNames = files[i];
                System.out.println("\n"+fileNames);
                
                if(fileNames.contains(search)){
                    System.out.println("\nfound");
                    subFiles.add(fileNames);
                }
                    
            }
        }
        for (String x : subFiles){
            System.out.println("\nblah:"+x);
        }
        
        
    }
    
}
