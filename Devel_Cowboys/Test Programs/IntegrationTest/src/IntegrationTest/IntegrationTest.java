/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IntegrationTest;

import com.eclipsesource.json.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Nick Nawoschik
 */
public class IntegrationTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // File locations, to be converted into collection of arguments passed through command line.
        String cowboysFile = args[0];
        String falconsFile = args[1];
        
        // Content of the files.
        String cowboysContent = "";
        String falconsContent = "";
        
        try {
            cowboysContent = new String(Files.readAllBytes(Paths.get(cowboysFile)));
            falconsContent = new String(Files.readAllBytes(Paths.get(falconsFile)));
        } catch(IOException e) {
            System.out.println("There was an error accessing the files.\nError: " + e);
        }
        
        // Content of files converted into JSON Object
        JsonObject cowboysJSON = Json.parse(cowboysContent).asObject();
        JsonObject falconsJSON = Json.parse(falconsContent).asObject();
        
        // Test ID name matches in each JSON file.
        System.out.println("ID Name Matching Test:");
        if(falconsJSON.get("domainNameListId").asString().equals(cowboysJSON.get("domainNameListId").asString())) {
            System.out.println("\tPASS: The list ID matches the Falcon and Cowboys files.");
        } else {
            System.out.println("\tFAIL: THE list ID does not match the Falcon and Cowboys files.");
        }
        
        // Test if all domains in Cowboys output were tested in Falcons tool.
        System.out.println("\nDomain Count Test:");
        JsonArray cowboysResults = cowboysJSON.get("domainNames").asArray();
        JsonArray falconsResults = falconsJSON.get("queryResults").asArray();
        
        if(cowboysResults.size() == falconsResults.size()) {
            System.out.println("\tPASS: The JSON files have the same number of domains in the two lists.");
        } else {
            System.out.println("\tFAIL: There are " + cowboysResults.size() + " domains in the Cowboys JSON file and " + falconsResults.size() + " domains in the Falcons JSON file.");
        }
        
    }
}