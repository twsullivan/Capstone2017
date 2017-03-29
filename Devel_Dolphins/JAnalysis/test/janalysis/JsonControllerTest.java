/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package janalysis;

import java.io.File;
import java.util.ArrayList;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Delikarl
 */
public class JsonControllerTest {
    
    private String path;
    private ArrayList<String> jsonFileNames = new ArrayList<String>();
    private ArrayList<File> jsonFiles = new ArrayList<File>();
    private ArrayList<JSONObject> jsonObjects = new ArrayList<JSONObject>();
    private ArrayList<String> environmentNames = new ArrayList<String>();
    
    public JsonControllerTest() {
    }
    

    /**
     * Test of findJsonFiles method, of class JsonController.
     */
    @Test
    public void testFindJsonFiles() {
        System.out.println("findJsonFiles");
        JsonController instance = new JsonController();
        //int expResult = 0;
        int result = instance.findJsonFiles();
        int count = 0;
        
        try
        {
        File dir = new File(path);  
        String[] filesInDirectory = dir.list();
        if(filesInDirectory == null)
        {
            System.out.println("Could not find any other files in the directory."); 
        }
        else
        {
            //count = 0;
            int expResult = 0;
            for (String fileNames : filesInDirectory)
            {
                if(fileNames.contains(".json"))
                {
                    jsonFileNames.add(fileNames);
                    expResult++;
                }
            }
            //expResult = count;
            assertEquals(expResult, result);
        }       
        }
        catch(Exception e)
        {
            //System.out.println(e);
        }
        //expResult = count;
        
        
    }

    /**
     * Test of loadFilesToList method, of class JsonController.
     */
    @Test
    public void testLoadFilesToList() {
        System.out.println("loadFilesToList");
        JsonController instance = new JsonController();
        instance.loadFilesToList();
    }

    /**
     * Test of createJSONObjects method, of class JsonController.
     */
    @Test
    public void testCreateJSONObjects() {
        System.out.println("createJSONObjects");
        JsonController instance = new JsonController();
        instance.createJSONObjects();
    }

    /**
     * Test of createEnvironmentsList method, of class JsonController.
     */
    @Test
    public void testCreateEnvironmentsList() {
        System.out.println("createEnvironmentsList");
        JsonController instance = new JsonController();
        int expResult = 0;
        int result = instance.createEnvironmentsList();
        assertEquals(expResult, result);
    }

    /**
     * Test of createJaccardController method, of class JsonController.
     */
    @Test
    public void testCreateJaccardController() {
        System.out.println("createJaccardController");
        JsonController instance = new JsonController();
        JaccardController expResult = null;
        JaccardController result = instance.createJaccardController();
        JaccardController temp = result;
        int counter = 0;
        for(String envName: environmentNames)
        {
            Environment e = new Environment(envName,counter);
            temp.addEnvironment(e);
            counter++;
        }
        expResult = temp;
        assertEquals(expResult, result);
    }

    /**
     * Test of addBlockedDomains method, of class JsonController.
     */
    @Test
    public void testAddBlockedDomains() {
        System.out.println("addBlockedDomains");
        JaccardController jCon = null;
        JsonController instance = new JsonController();
        instance.addBlockedDomains(jCon);
    }
    
}
