/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scrappertest;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author Cody Grogan
 */
public class Driver 
{
    private String user;
    private String date;
    private String initialDNS;
    private String listId;
    private String listDesc;
    private ArrayList nameList;
    private int nLevels;
    
    public Driver()
    {
        user = "";
        date = "";
        initialDNS = "";
        listId = "";
        listDesc = "";
        nameList = new ArrayList();
        nLevels = 0;
    }
    
    public Driver(String use, String dat, String iDNS, String list, String desc, int n)
    {
        user = use;
        date = dat;
        initialDNS = iDNS;
        listId = list;
        listDesc = desc;
        nameList = new ArrayList();
        nLevels = n;
    }
    
    public void runDriver()
    {
        try {
            gainDomainNames();
        } catch (IOException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        outputJSONFile();
        
        showList();
    }
    
    public void gainDomainNames()  throws IOException 
    {
        for(int i = -1; i < nLevels; i++)
        {
        System.out.println("Use JGet class, get file updated.");
        
        JGet get = new JGet();
        get.setURL(initialDNS);
        get.runJGet();
        
        System.out.println("Use Format class, updte DNS list.");
        Format format = new Format();
        format.runHReF();
        format.runWeblink();
        //format.getURLs();
        nameList = format.getDomains();
        }
    }
    
    public void outputJSONFile()
    {
        System.out.println("Use JSON Class, export DNS list to JSON file");
    }
    
    public void showList()
    {
        for(int i = 0; i < nameList.size(); i++)
        {
            System.out.println(nameList.get(i));
        }
    }
}
