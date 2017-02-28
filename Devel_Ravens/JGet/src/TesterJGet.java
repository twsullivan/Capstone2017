import java.io.IOException;

public class TesterJGet {
    public static void main(String[] args) throws IOException{
        JGet g = new JGet();
        
        for(int i=0; i<3; i++)
        {
            g.setURL("www.stackoverflow.com");
            g.runJGet();
            g.setURL("http://www.uwf.edu");
            g.runJGet();
            g.setOutput("Testing.txt");
            g.setURL("google.com");
            g.runJGet(); 
        }
        
        
    }
}
