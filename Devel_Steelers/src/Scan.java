
/*
 * @author Steelers
 *
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.net.InetAddress;
import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xbill.DNS.ARecord;
import org.xbill.DNS.DClass;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.Type;

public class Scan {
    private FileReader inputFile;
    private FileWriter outputFile;
    private String environment = "";
    private InetAddress environmentIP;
    private String name = "";
    private int queriesPerSecond = 0;
    private boolean quiet = false;
    private SimpleResolver resolve;
    private InputJSON json;
    JSONObject finalJSON = new JSONObject();

    public Scan(String[] inputArgs) throws Exception {

	for (int i = 0; i < inputArgs.length; i += 2)
	    if (inputArgs[i].equalsIgnoreCase("-i")) {
		setInputFile(inputArgs[i + 1]);
	    } else if (inputArgs[i].equalsIgnoreCase("-o")) {
		setOutputFile(inputArgs[i + 1]);
	    } else if (inputArgs[i].equalsIgnoreCase("-e")) {
		setEnvironment(inputArgs[i + 1].split(":")[0]);
		setEnvironmentIP(inputArgs[i + 1].split(":")[1]);
	    } else if (inputArgs[i].equalsIgnoreCase("-t")) {
		setQueriesPerSecond(inputArgs[i + 1]);
	    } else if (inputArgs[i].equalsIgnoreCase("-n")) {
		setName(inputArgs[i + 1]);
	    } else if (inputArgs[i].equalsIgnoreCase("-q")) {
		setQuiet(true);
		i--;
	    } else
		throw new Exception("Invalid arguments provided (duplicate or unknown).");

	// !!FIX TOO FEW ARGS!!
	// if (getInputFile().equals(null) || getOutputFile().equals(null)||
	// getEnvironment().isEmpty() || getName().isEmpty()
	// || getEnvironmentIP().getHostAddress() == "" || getQueriesPerSecond()
	// < 1)
	// throw new Exception("Not enough arguments.");
	this.json = new InputJSON(getInputFile());
    }

    @SuppressWarnings("unchecked")
    public void run() throws Exception {

	setResolve(new SimpleResolver());
	resolve.setAddress(getEnvironmentIP());
	Lookup.setDefaultResolver(resolve);

	if (isQuiet())
	    System.out.close();
	int queryRate = 1000 / getQueriesPerSecond();

	System.out.println("Setting DNS Resolver to : " + getEnvironmentIP().getHostAddress());
	System.out.println("=========================================\n");
	System.out.println("Domain List ID   : " + getJSON().getDomainNameListId());
	System.out.println("List Prepared By : " + getJSON().getListPreparedBy());
	System.out.println("List Description : " + getJSON().getListDescription());

	Record[] records;
	Lookup dnsJob;
	Iterator<String> iterDomainJSON;
	String query;
	int result;
	iterDomainJSON = getJSON().getDomainNames().iterator();
	JSONArray resultArray = new JSONArray();
	JSONObject resultObj;
	Instant start, end;

	while (iterDomainJSON.hasNext()) {
	    query = iterDomainJSON.next();
	    dnsJob = new Lookup(query, Type.A, DClass.IN);

	    start = Instant.now();
	    records = dnsJob.run();
	    result = dnsJob.getResult();
	    end = Instant.now();

	    resultObj = new JSONObject();
	    resultObj.put("domainName", query);
	    resultObj.put("responseTimeMs", String.valueOf(Duration.between(start, end).toMillis()));
	    if (result == 0) {
		System.out.println("\n\nCode 0 (SUCCESS) received from lookup of host : " + query);
		for (Record record : records)
		    System.out.println(
			    "FOUND : Host " + record.getName().toString() + " has address " + record.rdataToString());
		resultObj.put("queryResult", ((ARecord) records[0]).rdataToString());
	    } else if (result == 1) {
		System.out.println("\n\nCode 1 (UNRECOVERABLE / BLOCKED) received from lookup of host : " + query);
		resultObj.put("queryResult", "BLOCKED");

	    } else if (result == 3) {
		System.out.println("\n\nCode 3 (HOST NOT FOUND) received from lookup of host : " + query);
		resultObj.put("queryResult", "UNRESOLVED");
	    }
	    resultArray.add(resultObj);

	    while (Duration.between(start, Instant.now()).toMillis() < queryRate)
		;
	}

	finalJSON.put("environmentId", getEnvironment());
	finalJSON.put("domainNameListId", getJSON().getDomainNameListId());
	finalJSON.put("queriesRunBy", getName());
	finalJSON.put("queryResults", resultArray);
    }

    public void save() throws Exception {
	getOutputFile().write(finalJSON.toJSONString());
	getOutputFile().close();
    }

    public InputJSON getJSON() {
	return json;
    }

    public FileReader getInputFile() {
	return inputFile;
    }

    private void setInputFile(String inputFile) throws Exception {
	this.inputFile = new FileReader(inputFile);
    }

    public FileWriter getOutputFile() {
	return outputFile;
    }

    private void setOutputFile(String outputFile) throws Exception {
	this.outputFile = new FileWriter(outputFile);
    }

    public String getEnvironment() {
	return environment;
    }

    private void setEnvironment(String environment) {
	this.environment = environment;
    }

    public InetAddress getEnvironmentIP() {
	return environmentIP;
    }

    private void setEnvironmentIP(String environmentIP) throws Exception {
	this.environmentIP = InetAddress.getByName(environmentIP);
    }

    public String getName() {
	return name;
    }

    private void setName(String name) {
	this.name = name;
    }

    public int getQueriesPerSecond() {
	return queriesPerSecond;
    }

    private void setQueriesPerSecond(String queriesPerSecond) throws Exception {
	int i = Integer.parseInt(queriesPerSecond);

	if (i < 1)
	    throw new Exception("Queries per second must be > 0.");

	this.queriesPerSecond = i;
    }

    public boolean isQuiet() {
	return quiet;
    }

    private void setQuiet(boolean quiet) {
	this.quiet = quiet;
    }

    public SimpleResolver getResolve() {
	return resolve;
    }

    private void setResolve(SimpleResolver resolve) {
	this.resolve = resolve;
    }

}
