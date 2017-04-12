package domainfileparser;

import java.io.IOException;
import java.io.PrintWriter;
import com.eclipsesource.json.*;
import java.io.File;

/**
 * Outputs the array and other related information passed to it into a
 * JSON formatted file.
 * 
 * @author pgabriel
 */
public class OutputJSON {

    public OutputJSON() {
    }

    public static String[] save(String[] contentArray, String outputFolder, String name, String description, String parseType) {

        long unixTime = System.currentTimeMillis() / 1000L;
        String timestamp = Long.toString(unixTime);
                
        JsonArray domains = Json.array(contentArray);
        JsonObject preparedObject = Json.object().add("domainNameListId", "id_" + timestamp).add("listPreparedBy", name).add("listDescription", description).add("domainNames", domains);
        String output = preparedObject.toString(WriterConfig.PRETTY_PRINT);
        
        if(outputFolder.charAt(outputFolder.length() - 1) == '/' || outputFolder.charAt(outputFolder.length() - 1) == '\\')
        {
            outputFolder = outputFolder.substring(0, outputFolder.length() - 1);
        }
        
        String savePath;
        
        if(parseType.equals("adblock")) {
            savePath = outputFolder + File.separator + "AdBlock.json";
        } else if(parseType.equals("dnsblackhole")) {
            savePath = outputFolder + File.separator + "DNSBlackHole.json";
        } else if (parseType.equals("internetlog")) {
            savePath = outputFolder + File.separator + "InternetLog.json";
        } else {
            return new String[]{"Error", "Error saving output file. The parsing type was not recognized."};
        }
        
        try (PrintWriter writer = new PrintWriter(savePath, "UTF-8")) {
            writer.println(output);
        } catch (IOException e) {
            
            return new String[]{"Error", "Error saving output file."};
        }
        
        return new String[]{"OK",savePath};
    }
}
