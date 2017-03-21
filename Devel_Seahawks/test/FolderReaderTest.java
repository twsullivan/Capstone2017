import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FolderReaderTest 
{
    
    public FolderReaderTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    @Test
    public void testGetJSONSInDirT() throws Exception 
    {
        System.out.println("<<<>>> getJSONSInDirT");
        String path = "pathname";//WILL NEED TO CHANGE AS NEEDED WHEN TESTING
        FolderReader instance = new FolderReader();
        boolean expResult = true;
        boolean result = instance.getJSONSInDirT(path);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetEnvResponseTimesT() throws Exception 
    {
        System.out.println("<<<>>> setEnvResponseTimesT");
        String filePath = "pathname";//WILL NEED TO CHANGE AS NEEDED WHEN TESTING
        File f = new File("filename");
        File[] filelist = {f};
        List<List<Double>> envResponseTimesT = new ArrayList<List<Double>>();
        for(int i = 0; i < 2; i++)  
        {
            envResponseTimesT.add(new ArrayList<Double>());
        }
        envResponseTimesT.get(0).add(1.1);
        envResponseTimesT.get(0).add(1.2);
        envResponseTimesT.get(0).add(1.3);
        envResponseTimesT.get(0).add(1.4);
        envResponseTimesT.get(1).add(2.1);
        envResponseTimesT.get(1).add(2.2);
        envResponseTimesT.get(1).add(2.3);
        envResponseTimesT.get(1).add(2.4);
        FolderReader instance = new FolderReader();
        boolean expResult = true;
        boolean result = instance.setEnvResponseTimesT(filelist, envResponseTimesT);
        assertEquals(expResult, result);
    }
    
}
