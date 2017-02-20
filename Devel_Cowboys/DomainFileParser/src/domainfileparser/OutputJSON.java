/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public static String[] save(String[] domains, String outputFolder) {

        try {
            PrintWriter writer = new PrintWriter(outputFolder + "/test1.txt", "UTF-8");

            for (String domain : domains) {
                writer.println(domain);
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("IO Error");
        }

        return new String[]{"OK"};
    }

}
