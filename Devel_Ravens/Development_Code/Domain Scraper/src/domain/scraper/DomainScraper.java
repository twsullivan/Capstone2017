package domain.scraper;

import java.io.IOException;


public class DomainScraper {
    public static void main(String[] args) throws IOException {
        String out = "HTMLDump.txt";
        
        JGet g = new JGet();
        
        g.setOutput(out);
        g.setURL("www.facebook.com");
        g.runJGet();
        
        Format f = new Format();
        f.setInputFile(out);
        f.format();
        f.generateDomains(f.urls);
        
        for(int i=0; i<f.urls.size(); i++){
            System.out.println("URL " + i + ": " + f.urls.get(i));
        }
        
    }
    
}
