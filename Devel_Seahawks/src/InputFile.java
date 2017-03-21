import java.util.ArrayList;

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
    
    //TESTABLE CLONES
    public static boolean setDomainNameT(String domainName)
    {
        boolean didItWork = false;
        
        domainName = domainName;
        
        didItWork = true;
        
        return didItWork;
    }
    
    public static boolean setResponseTimeT(String responseTime)
    {
        boolean didItWork = false;
        
        responseTime = responseTime;
        
        didItWork = true;
        
        return didItWork;
    }

    public static boolean setQueryResultT(String queryResult)
    {
        boolean didItWork = false;
        
        queryResult = queryResult;
        
        didItWork = true;
        
        return didItWork;
    }
}
