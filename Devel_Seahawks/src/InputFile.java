
/**
 * InputFile is a helper class of the QResults class. 
 * This class declares the set and get methods for domainName, responseTime, and queryResults.
 * These three results are unique within each query and are arrays within the 
 * JSON files.
 */

/**
 *
 * @author UWF Capstone, Team Seahawks, Frank Moss, Chase Green, Paul Gartner
 */

public class InputFile
{

    private String domainName;
    private String responseTime;
    private String queryResult;

    public InputFile()
    {

    }

    public InputFile(String domainName, String responseTime, String queryResult)
    {
        this.domainName = domainName;
        this.responseTime = responseTime;
        this.queryResult = queryResult;
    }

    public String getDomainName()
    {
        return domainName;
    }

    public void setDomainName(String domainName)
    {
        this.domainName = domainName;
    }

    public String getResponseTime()
    {
        return responseTime;
    }

    public void setResponseTime(String responseTime)
    {
        this.responseTime = responseTime;
    }

    public String getQueryResult()
    {
        return queryResult;
    }

    public void setQueryResult(String queryResult)
    {
        this.queryResult = queryResult;
    }

}
