import java.util.ArrayList;

/**
 * QResults is the overall results setting class, in conjuction with InputFile,
 * QResults sets the variables for each element of the JSON query results file.
 * InputFile holds the JSON array object elements that are unique to each environmentID.
 */

/**
 *
 * @author UWF Capstone, Team Seahawks, Frank Moss, Chase Green, Paul Gartner
 */
public class QResults
{

    private String environmentId;
    private String domainNameListId;
    private String queriesRunBy;
    private ArrayList<InputFile> queryResults;

    public QResults()
    {

    }

    public QResults(String environmentID, String domainNameListId, String queriesRunBy, ArrayList<InputFile> queryResults)
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

    public ArrayList<InputFile> getQueryResults()
    {
        return queryResults;
    }

    public void setQueryResults(ArrayList<InputFile> queryResults)
    {
        this.queryResults = queryResults;
    }

}
