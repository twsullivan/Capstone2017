

import java.util.ArrayList;

public class QResults 
{

	private String environmentId;
	private String domainNameListId;
	private String queriesRunBy;
	private ArrayList<QueryResults> queryResults;


    public QResults()
    {
        
    }

    public QResults(String environmentID, String domainNameListId, String queriesRunBy, ArrayList<QueryResults> queryResults) //List<List<String>> qResults
    {
        this.environmentId = environmentID;
        this.domainNameListId = domainNameListId;
        this.queriesRunBy = queriesRunBy;
        this.queryResults = queryResults;
    }

    public String getenvironmentId()
    {
        return environmentId;
    }

    public void setenvironmentId(String environmentId)
    {
        this.environmentId = environmentId;
    }

    public String getDomainNameListId()
    {
        return domainNameListId;
    }

    public void setDomainNameListId(String domainNameListId)
    {
        this.domainNameListId = domainNameListId;
    }

    public String getQueriesRunBy()
    {
        return queriesRunBy;
    }

    public void setQueriesRunBy(String queriesRunBy)
    {
        this.queriesRunBy = queriesRunBy;
    }

//Setting the Query results object Array list below this

    public ArrayList<QueryResults> getQueryResults()
    {
        return queryResults;
    }

    public void setQueryResults(ArrayList<QueryResults> queryResults)
    {
        this.queryResults = queryResults;
    }
       
}
