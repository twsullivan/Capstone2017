import java.io.IOException;
import java.util.*;

/**
 * This class computes all of the statistical calculations required for the 
 * QStats program.
 */

/**
 *
 * @author UWF Capstone, Team Seahawks, Frank Moss, Chase Green, Paul Gartner
 */
public class StatsCalculator extends Main
{

    private static double mean = 0.0;
    private static double median = 0.0;
    private static double standardDev = 0.0;
    private static double percent98 = 0.0;
    private static List<Double> currentLatencyValues = new ArrayList<>();

    public StatsCalculator()
    {

    }

    public StatsCalculator(ArrayList values)
    {
        currentLatencyValues = values;
    }

    public double getMean()
    {
        return mean;
    }

    public double getMedian()
    {
        return median;
    }

    public double getStandardDeviation()
    {
        return standardDev;
    }

    public double getPercent98()
    {
        return percent98;
    }

    public ArrayList getCurrentValues()
    {
        return (ArrayList) currentLatencyValues;
    }

    public static void setMean()
    {
        double sum = 0.0;

        for (int x = 0; x < currentLatencyValues.size(); x++)
        {
            sum += currentLatencyValues.get(x);
        }

        mean = (sum / currentLatencyValues.size());
    }

    public static void setMedian()
    {
        int mid = (currentLatencyValues.size() / 2);
        if ((currentLatencyValues.size()) % 2 == 1)
        {
            median = currentLatencyValues.get(mid);
        } else
        {
            median = ((currentLatencyValues.get(mid - 1)) + (currentLatencyValues.get(mid))) / 2;
        }
    }

    public static void setStandardDeviation()
    {
        double sum = 0.0;

        for (int x = 0; x < currentLatencyValues.size(); x++)
        {
            sum += Math.pow((currentLatencyValues.get(x) - mean), 2.0);
        }

        standardDev = Math.sqrt((sum) / (currentLatencyValues.size() - 1));
    }

    public static void setPercent98()
    {
        double x = 0.98 * (currentLatencyValues.size() - 1);
        int index = (int) Math.round(x);
        percent98 = currentLatencyValues.get(index);
    }

    public static void sortLatencyValues()
    {
        Collections.sort(currentLatencyValues);

    }

    public void calculateQueryStatistics() throws IOException
    {
        if (!currentLatencyValues.isEmpty())
        {
            sortLatencyValues();
            setMean();
            setMedian();
            setStandardDeviation();
            setPercent98();
        } else
        {
            throw new IOException("One or more files has nothing to evaluate or program is ignoring all results. Please retry with other flag(s) and/or process files individually in input file directory.");
        }
    }
}
