import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileWriterTest 
{
    
    public FileWriterTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    @Test
    public void testWriteOutputGloballyT() 
    {
        System.out.println("<<<>>> writeOutputGloballyT");
        String outputFilePath = "filename.txt"; 
        List<List<Double>> statTableT = new ArrayList<List<Double>>();
        for(int i = 0; i < 2; i++)  
        {
            statTableT.add(new ArrayList<Double>());
        }
        statTableT.get(0).add(1.1);
        statTableT.get(0).add(1.2);
        statTableT.get(0).add(1.3);
        statTableT.get(0).add(1.4);
        statTableT.get(1).add(2.1);
        statTableT.get(1).add(2.2);
        statTableT.get(1).add(2.3);
        statTableT.get(1).add(2.4);
        boolean expResult = true;
        boolean result = FileWriter.writeOutputGloballyT(outputFilePath, statTableT);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testWriteToConsoleT() 
    {
        System.out.println("<<<>>> writeToConsoleT");
        List<List<Double>> statTableT = new ArrayList<List<Double>>();
        for(int i = 0; i < 2; i++)  
        {
            statTableT.add(new ArrayList<Double>());
        }
        statTableT.get(0).add(1.1);
        statTableT.get(0).add(1.2);
        statTableT.get(0).add(1.3);
        statTableT.get(0).add(1.4);
        statTableT.get(1).add(2.1);
        statTableT.get(1).add(2.2);
        statTableT.get(1).add(2.3);
        statTableT.get(1).add(2.4);
        boolean expResult = true;
        boolean result = FileWriter.writeToConsoleT(statTableT);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testWriteToCSVT_1() 
    {
        System.out.println("<<<>>> writeToCSVT_1");
        String outputFilePath = "filename.txt";       
        boolean expResult = true;
        boolean result = FileWriter.writeToCSVT(outputFilePath);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testWriteToCSVT_2() 
    {
        System.out.println("<<<>>> writeToCSVT_2");
        String outputFilePath = "filename.png";       
        boolean expResult = true;
        boolean result = FileWriter.writeToCSVT(outputFilePath);
        assertEquals(expResult, result);
    }
    
}
