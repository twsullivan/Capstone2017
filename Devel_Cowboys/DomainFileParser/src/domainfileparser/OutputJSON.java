package domainfileparser;

import java.io.IOException;
import java.io.PrintWriter;
import com.eclipsesource.json.*;

/**
 *
 * @author pgabriel
 */
public class OutputJSON {

    public OutputJSON() {
    }

    public static String[] save(String[] contentArray, String outputFolder, String name, String description) {

        long unixTime = System.currentTimeMillis() / 1000L;
        String timestamp = Long.toString(unixTime);
        
        String id = "id_" + unixTime;
                
        JsonArray domains = Json.array(contentArray);
        JsonObject preparedObject = Json.object().add("domainNameListId", "id_" + timestamp).add("listPreparedBy", name).add("listDescription", description).add("domainNames", domains);
        String output = preparedObject.toString(WriterConfig.PRETTY_PRINT);
        
        try {
            PrintWriter writer = new PrintWriter(outputFolder + "/domains_" + timestamp + ".txt", "UTF-8");
            
            writer.printf(output);
            
            writer.close();
        } catch (IOException e) {
            System.out.println("IO Error");
        }

        return new String[]{"OK"};
    }

}
