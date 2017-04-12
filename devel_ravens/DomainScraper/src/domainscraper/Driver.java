/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainscraper;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Driver is an attempt to organize class and method calls, manage transportion of
 * needed data to and from classes and manage the repition of classes and 
 * methods as required.
 * <p>
 * Manages the order of calls to classes and methods, gains and sends information
 * between classes, and determines repition of JGet and Format classes based on command
 * input. 
 * <p>
 * Public methods:
 * <ol>
 * <li>Constructors:</li>
    * <li>Driver() -  empty constructor</li>
    * <li>Driver(String use, String iDNS, String list, String desc, int n, String fileN) - constructor</li>
 * <li>Core Methods:</li>
    * <li>runDriver() - manages the flow of the tool. Is the start and stop of the tool</li>
    * <li>gainDomainNames() - Collects the domains and links through JGet and format classes.
        * Begins repition as needed</li>
    * <li>continueDomainNames() - Manages repetition of JGet and Format classes</li>
    * <li>outputJSONFile() - Outputs nameList, user, listId, listDesc and fileName to JSON class</li>
    * <li>usageDump() - usage dump message to be printed if error occurs</li>
    * <li>removeDuplicates() - removes duplicates found in nameList</li>
    * <li>continuedRemoveDuplicates(List in) - Removes duplicates from various lists used in repeated runs</li>
 * <li>Verification Methods:</li>
    * <li>showList() - prints to screen the entire nameList</li>
    * <li>getNameList() - returns the entire nameList</li>
 * <li>Setters and Getters:</li>
    * <li>setUser(String user) - sets User</li>
    * <li>setInitialDNS(String initialDNS) - Sets intial DNS</li>
    * <li>setListId(String listId) - sets list ID</li>
    * <li>setListDesc(String listDesc) - sets list description</li>
    * <li>setLinkList(ArrayList linkList) - sets linkList</li>
    * <li>setNameList(ArrayList nameList) - sets nameList</li>
    * <li>setnLevels(int nLevels) - sets levels of search</li>
    * <li>setFileName(String fileName) - sets output file name</li>
    * <li>setMaxNum(int n) - sets maximum number of sites visited</li> 
 * </ol>
 * 
 * 
 * @author Cody Grogan
 * Capstone Systems Project - 10215 CIS4595C 201701
 * Team - Devel_Ravens
 * 
 */
public class Driver {

    private String user;
    private String initialDNS;
    private String listId;
    private String listDesc;
    private List linkList;
    private ArrayList nameList;
    private ArrayList tempList;
    private int nLevels;
    private String fileName;
    private List visited;
    private int maxNum;

    //**************************************************************************
    //Constructors
    //**************************************************************************
    //Empty
    public Driver() {
        user = "";
        initialDNS = "";
        listId = "";
        listDesc = "";
        nameList = new ArrayList();
        linkList = new ArrayList();
        tempList = new ArrayList();
        nLevels = 0;
        fileName = "output.json";
        visited = new ArrayList();
    }

    //Parameters
    public Driver(String use, String iDNS, String list, String desc, int n, String fileN) {
        user = use;
        initialDNS = iDNS;
        listId = list;
        listDesc = desc;
        nameList = new ArrayList();
        linkList = new ArrayList();
        tempList = new ArrayList();
        nLevels = n;
        fileName = fileN;
        visited = new ArrayList();

    }

    //**************************************************************************
    //Core Methods--------------------------------------------------------------
    //**************************************************************************
    //Main layout for class
    public void runDriver() {
        try {
            //collects domains and links through JGet and Format classes
            gainDomainNames();
        }
        catch (IOException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        //removes duplicates from nameList which holds the domains
        removeDuplicates();
        //calls method for outputing to JSON
        outputJSONFile();
    }

    //Uses the JGet and Format classes
    public void gainDomainNames() throws IOException {
        //creates instance of JGet class
        JGet get = new JGet();
        //sets initial DNS in JGet and tracks the url
        get.setURL(initialDNS);
        visited.add(initialDNS);
        //runs JGet
        get.runJGet();
        //creates new istance of Format class
        Format format = new Format();
        //
        format.runHReF();
        //
        format.runWeblink();
        //collects domains found and adds them to nameList
        setNameList(format.getDomains());
        
        //checks if repition is needed
        if (nLevels > 0) {
            //collects non duplicate urls from Format class for continued use
            linkList.addAll(continuedRemoveDuplicates(format.getURLs()));
            //manages proper repetition control
            continueDomainNames();
        }

    }
    
    //Manages repition of JGet and Format classes as needed
    public void continueDomainNames() throws IOException {
        //begins count for continued use that stops at levels designated
        int count = 0;
        
        while (count < nLevels) {
            //if this is not the first time the loop has gone
            if (count != 0) {
                //linkList now contains non-duplicated links from tempList
                linkList = continuedRemoveDuplicates(tempList);
                //empties tempList
                tempList = new ArrayList();
                
            }
            
           //travels through linkList
           for (int i = 0; i < linkList.size(); i++) {
               //collects url from linkList
                String tempURL = linkList.get(i).toString();
                //if the url has not been visited nor has the maximum number of site been visited
                if((!visited.contains(tempURL)) && (visited.size() <= maxNum)){
                    //add url to visited urls
                    visited.add(tempURL);
                    
                    //run through Jget
                    JGet get = new JGet();
                    get.setURL(tempURL);
                    get.runJGet();
                   
                    //run through Format
                    Format format = new Format();
                    format.runHReF();
                    format.runWeblink();
                    
                    //collect domain names, remove duplicates and add them to nameList
                    List tList  = new ArrayList(continuedRemoveDuplicates(format.getDomains()));
                    for (int j = 0; j < tList.size(); j++) {
                        String temp = tList.get(j).toString();
                        nameList.add(temp);
                    }
                    
                    //collect urls, remove duplicates and add them to tempList
                    tList = continuedRemoveDuplicates(format.getURLs());
                    for (int j = 0; j < tList.size(); j++) {
                        String temp = tList.get(j).toString();
                        tempList.add(temp);
                    }
                 
                }
                
            }

           count++;
        }
    }

    //Uses JSON class
    public void outputJSONFile() {
        //creates instance of JSON class
        JSON j = new JSON();
        
        //sets variable in instance
        j.setNameList(nameList);
        j.setUser(user);
        j.setListID(listId);
        j.setListDescription(listDesc);
        j.setOutput(fileName);
        
        //runs method to output file
        j.makeJSON();
    }

    //Usage dump
    public void usageDump() {
        System.out.println("Usage dump:");
        System.out.println("Please try the command again with the following format");
        System.out.println("java -jar DomainScraper.jar  <\"user name\"> <URL> <output filename> <listID> <\"List Description\"> <Levels of scrapping> <Max number of URLs visited>");
        System.out.println("NOTE: Unless the output filename renamed it will amend to the same <filename>.JSON file specified in the argument.");
        System.exit(0);
    }

    //Removes duplicates from nameList
    public void removeDuplicates() {
        ArrayList holder = new ArrayList();
        boolean duplicate = false;
        //Determines duplicates
        for (int i = 0; i < nameList.size(); i++) {
            String temp = nameList.get(i).toString();
            if (i == 0) {
                holder.add(nameList.get(0));
            }
            for (int j = 0; j < holder.size(); j++) {
                String holdTemp = holder.get(j).toString();
                if (temp.equalsIgnoreCase(holdTemp)) {
                    duplicate = true;
                }
            }
            if (duplicate == false) {
                holder.add(nameList.get(i));
            } else {
                duplicate = false;
            }
        }

        //empties and refills nameList
        nameList = new ArrayList();
        for (int i = 0; i < holder.size(); i++) {
            String temp = holder.get(i).toString();
            nameList.add(temp);
        }
    }
    
    //removes duplicates from list in repeated runs
    public List continuedRemoveDuplicates(List in) {
        List<String> out = in;
        Set<String> hs = new HashSet<>();
        hs.addAll(out);
        out.clear();
        out.addAll(hs);
        return out;
    }
    //**************************************************************************
    //Verification methods (Dev use only)---------------------------------------
    //**************************************************************************
    //Shows entire nameList
    public void showList() {
        for (int i = 0; i < nameList.size(); i++) {
            System.out.println(nameList.get(i));
        }
    }
    
    //collects entire nameList
    public List getNameList() {
        return nameList;
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
    
    /**
     * @param n the n to set
     */
    public void setMaxNum(int n){
        maxNum = n;
    }
}
