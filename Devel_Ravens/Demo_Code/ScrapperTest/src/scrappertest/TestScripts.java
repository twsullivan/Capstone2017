import scrappertest.*;

import java.io.*;

import static org.junit.Assert.*;

import org.junit.*;



/**

 *

 * @author Ryang

 */

public class TestScripts {

    

    Driver d;

    JGet j;

    Format f;

    

    @Before

    public void makeDriver(){

        d = new Driver();      

    }

    

    @Before

    public void makeJGet(){

        j = new JGet();

    }

    

    @Before

    public void makeFormat(){

        f = new Format();

    }

                       

    @Test

    public void JGetTest()throws IOException{

        File currentFile = new File(j.getOutput());

        j.runJGet();

        assertTrue(currentFile.length() != 0);

    }

    

    @Test

    public void FormatTest(){

        

        f.runHReF();

        f.runWeblink();

        assertTrue(f.getDomains().size() != 0);

    }

    

    @Test

    public void JSONTest(){

        System.out.println("JSON has been tested");

    }

    

    @Test

    public void DriverTest(){

        d.runDriver();

        assertTrue(d.getNameList().size() != 0);  

    }

    

}
