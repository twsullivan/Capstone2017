import java.io.FileReader;
import java.util.Iterator;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class InputJSON {
    private JSONParser parser = new JSONParser();
    private String domainNameListId, listPreparedBy, listDescription;
    private JSONArray domainNamesJSON;
    private LinkedList<String> domainNames = new LinkedList<String>();

    public InputJSON(FileReader file) throws Exception {

	Object obj = parser.parse(file);
	JSONObject jsonObject = (JSONObject) obj;

	setDomainNameListId((String) jsonObject.get("domainNameListId"));
	setListPreparedBy((String) jsonObject.get("listPreparedBy"));
	setListDescription((String) jsonObject.get("listDescription"));

	setDomainNamesJSON((JSONArray) jsonObject.get("domainNames"));

	@SuppressWarnings("unchecked")
	Iterator<String> i = getDomainNamesJSON().iterator();
	while (i.hasNext())
	    domainNames.add(i.next());
	setDomainNamesJSON(null);

    }

    public LinkedList<String> getDomainNames() {
	return domainNames;
    }

    private JSONArray getDomainNamesJSON() {
	return domainNamesJSON;
    }

    private void setDomainNamesJSON(JSONArray domainNamesJSON) {
	this.domainNamesJSON = domainNamesJSON;
    }

    public String getDomainNameListId() {
	return domainNameListId;
    }

    private void setDomainNameListId(String domainNameListId) {
	this.domainNameListId = domainNameListId;
    }

    public String getListPreparedBy() {
	return listPreparedBy;
    }

    private void setListPreparedBy(String listPreparedBy) {
	this.listPreparedBy = listPreparedBy;
    }

    public String getListDescription() {
	return listDescription;
    }

    private void setListDescription(String listDescription) {
	this.listDescription = listDescription;
    }

}
