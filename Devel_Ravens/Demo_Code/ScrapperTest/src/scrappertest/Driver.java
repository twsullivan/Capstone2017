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
    
    public void outputJSONFile()
    {
        System.out.println("Use JSON Class, export DNS list to JSON file");
        JSON j = new JSON();
        
        j.setNameList(nameList);
        j.setUser(user);
        j.setListID(listId);
        j.setListDescription(listDesc);
        j.setDate(date);
        j.makeJSON();
    }
    
    public void showList()
    {
        for(int i = 0; i < nameList.size(); i++)
        {
            System.out.println(nameList.get(i));
        }
    }
    
    public ArrayList getNameList(){
        return nameList;
    }
    
    public void usageDump(){
        System.out.println("Usage dump");
        System.out.println("Java -jar domainscraper.jar  <URL> <output file name><Max number of domains>");
        System.exit(0);
    }
}
