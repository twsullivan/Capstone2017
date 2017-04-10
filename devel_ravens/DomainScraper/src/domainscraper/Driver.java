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

/**
 *
 * @author Cody Grogan
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
            gainDomainNames();
        }
        catch (IOException ex) {
            Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
        }
        removeDuplicates();
        outputJSONFile();

        //System.out.println("Complete");
    }

    //Uses the JGet and Format classes
    public void gainDomainNames() throws IOException {
        JGet get = new JGet();
        get.setURL(initialDNS);
        visited.add(initialDNS);
        get.runJGet();

        Format format = new Format();
        format.runHReF();
        format.runWeblink();
        setNameList(format.getDomains());
        
        if (nLevels > 0) {
            linkList.addAll(testRemoveDuplicates(format.getURLs()));
            continueDomainNames();
        }

    }

    public void continueDomainNames() throws IOException {
        int count = 0;    
        while (count < nLevels) {
            if (count != 0) {
                linkList = tempList;
            }
            /*
           for (int i = 0; i < linkList.size(); i++) {
                     System.out.println("Number " + i + " with a value of " + linkList.get(i));
           }*/
           for (int i = 0; i < linkList.size(); i++) {
                String tempURL = linkList.get(i).toString();
                //System.out.println(tempURL);
                
                if((!visited.contains(tempURL)) && (visited.size() <= maxNum)){
                    visited.add(tempURL);
                    JGet get = new JGet();
                    get.setURL(tempURL);
                    get.runJGet();
                    
                    Format format = new Format();
                    format.runHReF();
                    format.runWeblink();

                    List tList  = new ArrayList(testRemoveDuplicates(format.getDomains()));
                    for (int j = 0; j < tList.size(); j++) {
                        String temp = tList.get(j).toString();
                        nameList.add(temp);
                    }
                    
                    tList = testRemoveDuplicates(format.getURLs());
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
        JSON j = new JSON();

        j.setNameList(nameList);
        j.setUser(user);
        j.setListID(listId);
        j.setListDescription(listDesc);
        j.setOutput(fileName);
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

    //Removes duplicates from finished nameList
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

    public List testRemoveDuplicates(List in) {
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

    public void showList() {
        for (int i = 0; i < nameList.size(); i++) {
            System.out.println(nameList.get(i));
        }
    }

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
    
    public void setMaxNum(int n){
        maxNum = n;
    }
}
