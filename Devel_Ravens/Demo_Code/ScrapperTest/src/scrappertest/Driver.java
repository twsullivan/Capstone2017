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
    private String initialDNS;
    private String listId;
    private String listDesc;
    private ArrayList linkList;
    private ArrayList nameList;
    private int nLevels;
    private String fileName;
    
    //**************************************************************************
    //Constructors
    //**************************************************************************
    
    //Empty
    public Driver()
    {
        user = "";
        initialDNS = "";
        listId = "";
        listDesc = "";
        nameList = new ArrayList();
        linkList = new ArrayList();
        nLevels = 0;
        fileName = "output.json";
    }
    
    //Parameters
    public Driver(String use, String iDNS, String list, String desc, int n, String fileN)
    {
        user = use;
        initialDNS = iDNS;
        listId = list;
        listDesc = desc;
        nameList = new ArrayList();
        linkList = new ArrayList();
        nLevels = n;
        fileName = fileN;

    }
    
    //**************************************************************************
    //Core Methods--------------------------------------------------------------
    //**************************************************************************
    
    //Main layout for class
    public void runDriver()
    {
        try {
            gainDomainNames();
        } catch (IOException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        outputJSONFile();
        
        System.out.println("Complete");
    }
    
    //Uses the JGet and Format classes
    public void gainDomainNames()  throws IOException 
    {
        //System.out.println("Use JGet class, get file updated.");
        JGet get = new JGet();        
        get.setURL(initialDNS);
        get.runJGet();
        
        //System.out.println("Use Format class, update DNS list.");
        Format format = new Format();
        format.runHReF();
        format.runWeblink();
        setNameList(format.getDomains());
        
        /*if(nLevels > 0)
        {
            linkList = format.getURLs();
            continueDomainNames();
        }*/
        
    }
    
    public void continueDomainNames()
    {
        int count = 0;
        
        while(count < nLevels)
        {
            
            count++;
        }
    }
    
    //Uses JSON class
    public void outputJSONFile()
    {
        //System.out.println("Use JSON Class, export DNS list to JSON file");
        JSON j = new JSON();
        
        j.setNameList(nameList);
        j.setUser(user);
        j.setListID(listId);
        j.setListDescription(listDesc);
        j.setOutput(fileName);
        j.makeJSON();
    }
    
    //**************************************************************************
    //Verification methods (Dev use only)---------------------------------------
    //**************************************************************************
    
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
    
    //Usage dump
    public void usageDump(){
        System.out.println("Usage dump:");
        System.out.println("Please try the command again with the following format");
        System.out.println("java -jar scrappertest.jar  <\"user name\"> <URL> <listID> <\"List Description\"> <Max number of domains>");
        System.exit(0);
    }
    
    //**************************************************************************
    //Setters and Getters-------------------------------------------------------
    //**************************************************************************

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @param initialDNS the initialDNS to set
     */
    public void setInitialDNS(String initialDNS) {
        this.initialDNS = initialDNS;
    }

    /**
     * @param listId the listId to set
     */
    public void setListId(String listId) {
        this.listId = listId;
    }

    /**
     * @param listDesc the listDesc to set
     */
    public void setListDesc(String listDesc) {
        this.listDesc = listDesc;
    }

    /**
     * @param linkList the linkList to set
     */
    public void setLinkList(ArrayList linkList) {
        this.linkList = linkList;
    }

    /**
     * @param nameList the nameList to set
     */
    public void setNameList(ArrayList nameList) {
        this.nameList = nameList;
    }

    /**
     * @param nLevels the nLevels to set
     */
    public void setnLevels(int nLevels) {
        this.nLevels = nLevels;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
}
