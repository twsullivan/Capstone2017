import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatsCalculatorTest 
{    
    public StatsCalculatorTest() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    @Test
    public void testSetMeanT_1() 
    {
        System.out.println("<<<>>> setMeanT_1");
        double meanT = 0.0;
        List<Double> values = new ArrayList<>();
        values.add(2.3);
        values.add(5.5);
        values.add(13.2);
        double expResult = 7.0;
        double result = StatsCalculator.setMeanT(meanT, values);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testSetMeanT_2() 
    {
        System.out.println("<<<>>> setMeanT_2");
        double meanT = 0.0;
        List<Double> values = new ArrayList<>();
        values.add(2.3);
        values.add(-5.5);
        values.add(13.2);
        double expResult = 7.0;
        double result = StatsCalculator.setMeanT(meanT, values);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testSetMedianT_1() 
    {
        System.out.println("<<<>>> setMedianT_1");
        double medianT = 0.0;
        List<Double> values = new ArrayList<>();
        values.add(2.3);
        values.add(5.5);
        values.add(13.2);        
        double expResult = 5.5;
        double result = StatsCalculator.setMedianT(medianT, values);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testSetMedianT_2() 
    {
        System.out.println("<<<>>> setMedianT_2");
        double medianT = 0.0;
        List<Double> values = new ArrayList<>();
        values.add(2.3);
        values.add(-5.5);
        values.add(13.2);        
        double expResult = 5.5;
        double result = StatsCalculator.setMedianT(medianT, values);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testSetStandardDeviationT_1() 
    {
        System.out.println("<<<>>> setStandardDeviationT_1");
        double standardDevT = 0.0;
        double meanT = 3.0;
        List<Double> values = new ArrayList<>();
        values.add(1.0);
        values.add(3.0);
        values.add(5.0);
        double expResult = 2.0;
        double result = StatsCalculator.setStandardDeviationT(standardDevT, meanT, values);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testSetStandardDeviationT_2() 
    {
        System.out.println("<<<>>> setStandardDeviationT_2");
        double standardDevT = 0.0;
        double meanT = 3.0;
        List<Double> values = new ArrayList<>();
        values.add(1.0);
        values.add(-3.0);
        values.add(5.0);
        double expResult = 2.0;
        double result = StatsCalculator.setStandardDeviationT(standardDevT, meanT, values);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testSetPercent98T_1() 
    {
        System.out.println("<<<>>> setPercent98T_1");
        double percent98T = 0.0;
        List<Double> values = new ArrayList<>();
        values.add(1.0);
        values.add(2.0);
        values.add(3.0);
        values.add(4.0);
        values.add(5.0);
        double expResult = 5.0;
        double result = StatsCalculator.setPercent98T(percent98T, values);
        assertEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testSetPercent98T_2() 
    {
        System.out.println("<<<>>> setPercent98T_2");
        double percent98T = 0.0;
        List<Double> values = new ArrayList<>();
        values.add(1.0);
        values.add(2.0);
        values.add(-3.0);
        values.add(4.0);
        values.add(5.0);
        double expResult = 5.0;
        double result = StatsCalculator.setPercent98T(percent98T, values);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testSortLatencyValuesT() 
    {
        System.out.println("<<<>>> sortLatencyValuesT");
        List<Double> values = new ArrayList<>();
        values.add(3.0);
        values.add(1.0);
        values.add(2.0);
        boolean expResult = true;
        boolean result = StatsCalculator.sortLatencyValuesT(values);
        assertEquals(expResult, result);
    }
    
}
