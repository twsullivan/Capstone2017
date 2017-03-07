/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Khang
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.*;

public class Results 
{
    public Results(int total, int crossSection)
    {
     double JCard;
     JCard = crossSection/total;
    }
    
    public void output(double JCard)
    {
    
    File file = new File("JaccardIndexReport.txt");
        if (file.createNewFile())
        {
        System.out.println("File is created!");
        }
        else{
        System.out.println("File already exists.");
        }
        
    //FileWriter fileWriter = new FileWriter(file);
        try (Writer writer = new BufferedWriter(new FileWriter(file))) 
        {
            String contents = (environments.get(0).getName() + " and " + environments.get(1).getName() + " Cross: " +  environments.get(0).getCrossSection(environments.get(1)));
            System.in.println("Jaccard Index:" + JCard + "\n");
            writer.write(contents);
        } catch (IOException e) {
            System.out.println("Error:Cannot output to file.\n");
        }
    
        
    FileWriter writer = new FileWriter(file);
    writer.write("Test data");
    writer.close();
 
    }
}
