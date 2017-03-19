import org.apache.commons.cli.Options;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.apache.commons.cli.*;

public class MainTest 
{
    
    public MainTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    @Test
    public void testParseCmdsT_1() 
    {
        System.out.println("<<<>>> parseCmdsT_1");
        Options opt = new Options();
        Option inputFile = new Option("f", "input", true, "Input file path");
        inputFile.setRequired(true);
        opt.addOption(inputFile);
        String[] args = {"f"};
        String header = "head";
        String footer = "foot";
        boolean expResult = true;
        boolean result = Main.parseCmdsT(opt, args, header, footer);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testParseCmdsT_2() 
    {
        System.out.println("<<<>>> parseCmdsT_2");
        Options opt = new Options();
        Option inputFile = new Option("q", "input", true, "Input file path");
        inputFile.setRequired(true);
        opt.addOption(inputFile);
        String[] args = {"q"};
        String header = "head";
        String footer = "foot";
        boolean expResult = true;
        boolean result = Main.parseCmdsT(opt, args, header, footer);
        assertEquals(expResult, result);
    }

    @Test
    public void testDisplayHelpT() 
    {
        System.out.println("<<<>>> displayHelpT");
        String header = "head";
        Options options = new Options();
        Option inputFile = new Option("f", "input", true, "Input file path");
        inputFile.setRequired(true);
        options.addOption(inputFile);
        String footer = "foot";
        boolean expResult = true;
        boolean result = Main.displayHelpT(header, options, footer);
        assertEquals(expResult, result);
    }
    
}
