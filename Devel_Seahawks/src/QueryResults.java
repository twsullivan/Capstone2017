

import java.util.ArrayList;

public class QueryResults
{

    private String domainName;
    private String responseTime;
    private String queryResult;
    
    public QueryResults()
    {
        
    }

    public QueryResults(String domainName, String responseTime, String queryResult)
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
