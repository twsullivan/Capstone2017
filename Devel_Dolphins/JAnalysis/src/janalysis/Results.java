/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janalysis;

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
import java.util.ArrayList;

public class Results 
{
    
    private ArrayList<Integer> results = new ArrayList<Integer>();
    
    public Results()
    {
     
    }
    
    public void JaccardResults(int a, int b, int crossSection){
        int sumdomains = a + b;
        int total = sumdomains - crossSection;
        int result = crossSection/total;
        for(int i: results){
            results.add(result);
        }
        //output(results);
        //addResultsToList(results, result);
        //return result;
        Writer writer = null;
        //
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("JaccardReport.txt"), "utf-8"));
            for(int i: results)
            {
                int result2 = results.get(i);
                writer.write("Jaccard analysis results for " + a + " and " + b + " is: ");
                writer.write(result2);
                writer.write("/n");
            }
            writer.close();
        }
        catch(IOException e)
        {
            //e.printStackTrace();
            System.out.println("Error\n");
        }
    }
    
    /*public ArrayList<Integer> addResultsToList(ArrayList<Integer> tempResult, int result)
    {
        for(int i: tempResult){
            tempResult.add(result);
        }
        return tempResult;
    }*/
    
    /*public void output(ArrayList<Integer> tempResult2)
    {
        Writer writer = null;
        //
        try{
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("JaccardReport.txt"), "utf-8"));
            for(int i: tempResult2)
            {
                int result = tempResult2.get(i);
                writer.write(result);
            }
            writer.close();
        }
        catch(IOException e)
        {
            System.out.println("Error\n");
        }
    }*/
}
