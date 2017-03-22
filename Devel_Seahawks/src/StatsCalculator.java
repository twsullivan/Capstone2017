import java.util.*;

/**
 *
 * @author Chase Green
 */
public class StatsCalculator extends Main
{
   private static double mean = 0.0;
   private static double median = 0.0;
   private static double standardDev = 0.0;
   private static double percent98 = 0.0;
   private static List<Double> currentLatencyValues = new ArrayList<>();
   
   //CONSTRUCTORS

    /**
     *
     */
   public StatsCalculator()
   {
   
   }
   
    /**
     *
     * @param values
     */
    public StatsCalculator(ArrayList values)
   {
      currentLatencyValues = values; 
   }
   
   //GET

    /**
     *
     * @return
     */
   public double getMean()
   {
      return mean;
   }
   
    /**
     *
     * @return
     */
    public double getMedian()
   {
      return median;
   }
   
    /**
     *
     * @return
     */
    public double getStandardDeviation()
   {
      return standardDev;
   }
   
    /**
     *
     * @return
     */
    public double getPercent98()
   {
      return percent98;
   }
   
    /**
     *
     * @return
     */
    public ArrayList getCurrentValues()
   {
      return (ArrayList) currentLatencyValues;
   }
   
   //SET

    /**
     *
     */
   public static void setMean()
   {
      double sum = 0.0;
      
      for(int x = 0; x < currentLatencyValues.size(); x++)
      {
          sum += currentLatencyValues.get(x);
      }
      
      mean = (sum / currentLatencyValues.size());
   }
   
    /**
     *
     */
    public static void setMedian()
   {
       int mid = (currentLatencyValues.size()/2);
       if((currentLatencyValues.size()) % 2 == 1)
       {
           median =  currentLatencyValues.get(mid);
       }
       else
       {
           median = ((currentLatencyValues.get(mid-1)) + (currentLatencyValues.get(mid))) / 2;
       }   
   }
   
    /**
     *
     */
public static void setStandardDeviation()
    {
        double sum = 0.0;
      
        for(int x = 0; x < currentLatencyValues.size(); x++)
        {
            sum += Math.pow((currentLatencyValues.get(x) - mean), 2.0);
        }
      
        standardDev = Math.sqrt((sum) / (currentLatencyValues.size() - 1));
    }
   
    /**
     *
     */
    public static void setPercent98()
   {
      double x = 0.98 * (currentLatencyValues.size() - 1);
      int index = (int) Math.round(x);      
      percent98 = currentLatencyValues.get(index);     
   }
   
   //OTHER

    /**
     *
     */
   public static void sortLatencyValues()
   {
        Collections.sort(currentLatencyValues);
        
   }
   
    /**
     *
     */
    public void calculateQueryStatistics()
   {
       sortLatencyValues();
       setMean();
       setMedian();
       setStandardDeviation();
       setPercent98();
   } 
}