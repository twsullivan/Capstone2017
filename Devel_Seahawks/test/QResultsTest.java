
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class QResultsTest 
{
    
    public QResultsTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    @Test
    public void testSetenvironmentIdT() 
    {
        System.out.println("<<<>>> setenvironmentIdT");
        String environmentId = "12345";
        boolean expResult = true;
        boolean result = QResults.setenvironmentIdT(environmentId);
        assertEquals(expResult, result);
    }
    @Test
    public void testSetDomainNameListIdT() 
    {
        System.out.println("<<<>>> setDomainNameListIdT");
        String domainNameListId = "67890";
        boolean expResult = true;
        boolean result = QResults.setDomainNameListIdT(domainNameListId);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetQueriesRunByT() 
    {
        System.out.println("<<<>>> setQueriesRunByT");
        String queriesRunBy = "";
        boolean expResult = true;
        boolean result = QResults.setQueriesRunByT(queriesRunBy);
        assertEquals(expResult, result);
    }

    @Test
    public void testSetQueryResultsT() 
    {
        System.out.println("<<<>>> setQueryResultsT");
        ArrayList<InputFile> queryResults = new ArrayList<>();
        queryResults.add(null);
        boolean expResult = true;
        boolean result = QResults.setQueryResultsT(queryResults);
        assertEquals(expResult, result);
    }
    
}
