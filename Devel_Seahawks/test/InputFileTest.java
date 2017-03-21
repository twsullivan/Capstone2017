import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputFileTest 
{
    
    public InputFileTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    @Test
    public void testSetDomainNameT_1() 
    {
        System.out.println("<<<>>> setDomainNameT_1");
        String domainName = "www.website.com";
        boolean expResult = true;
        boolean result = InputFile.setDomainNameT(domainName);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetDomainNameT_2() 
    {
        System.out.println("<<<>>> setDomainNameT_2");
        String domainName = "not_a_URL";
        boolean expResult = true;
        boolean result = InputFile.setDomainNameT(domainName);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetResponseTimeT_1() 
    {
        System.out.println("<<<>>> setResponseTimeT_1");
        String responseTime = "7";
        boolean expResult = true;
        boolean result = InputFile.setResponseTimeT(responseTime);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetResponseTimeT_2() 
    {
        System.out.println("<<<>>> setResponseTimeT_2");
        String responseTime = "Q";
        boolean expResult = true;
        boolean result = InputFile.setResponseTimeT(responseTime);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetQueryResultT() 
    {
        System.out.println("<<<>>> setQueryResultT");
        String queryResult = "result";
        boolean expResult = true;
        boolean result = InputFile.setQueryResultT(queryResult);
        assertEquals(expResult, result);
    }
    
}
