
/*
 * @author Steelers
 *
 */
import java.io.*;
import java.net.InetAddress;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.xbill.DNS.*;
import java.util.Iterator;

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

    @SuppressWarnings("unchecked")
    public Scan(String[] inputArgs) throws Exception {

	for (int i = 0; i < inputArgs.length; i += 2)
	    if (inputArgs[i] == "-i") {
		setInputFile(inputArgs[i + 1]);
		finalJSON.put("Input File", inputArgs[i + 1]);
	    } else if (inputArgs[i] == "-o") {
		setOutputFile(inputArgs[i + 1]);
		finalJSON.put("Output File", inputArgs[i + 1]);
	    } else if (inputArgs[i] == "-e") {
		setEnvironment(inputArgs[i + 1].split(":")[0]);
		finalJSON.put("Environment Name", inputArgs[i + 1].split(":")[0]);
		setEnvironmentIP(inputArgs[i + 1].split(":")[1]);
		finalJSON.put("Environment IP", inputArgs[i + 1].split(":")[1]);

	    } else if (inputArgs[i] == "-t") {
		setQueriesPerSecond(inputArgs[i + 1]);
		finalJSON.put("Queries Per Second", inputArgs[i + 1]);
	    } else if (inputArgs[i] == "-n") {
		setName(inputArgs[i + 1]);
		finalJSON.put("Scan Run By", inputArgs[i + 1]);

	    } else if (inputArgs[i] == "-q") {
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
	System.out.println("Setting DNS Resolver to : " + getEnvironmentIP().getHostAddress());
	System.out.println("=========================================\n");
	resolve = new SimpleResolver();
	resolve.setAddress(getEnvironmentIP());
	Lookup.setDefaultResolver(resolve);
	System.out.println("Domain List ID   : " + getJSON().getDomainNameListId());
	System.out.println("List Prepared By : " + getJSON().getListPreparedBy());
	System.out.println("List Description : " + getJSON().getListDescription());

	Record[] records;
	ARecord a;
	Lookup dnsJob;
	Iterator<String> iterDomainJSON;
	String query;
	int result;
	iterDomainJSON = getJSON().getDomainNames().iterator();
	JSONArray recordArray;
	JSONObject hostEntry = new JSONObject();

	while (iterDomainJSON.hasNext()) {
	    query = iterDomainJSON.next();
	    dnsJob = new Lookup(query, Type.A, DClass.IN);
	    records = dnsJob.run();
	    result = dnsJob.getResult();
	    JSONObject hostResult = new JSONObject();
	    if (result == 0) {
		System.out.println("\n\nCode 0 (SUCCESS) received from lookup of host : " + query);
		hostResult.put("Result Code", 0);
		hostResult.put("Result Description", "Success");
		hostResult.put("Host", query);
		recordArray = new JSONArray();
		for (int i = 0; i < records.length; i++) {
		    a = (ARecord) records[i];
		    System.out.println("FOUND : Host " + a.getName().toString() + " has address " + a.rdataToString());
		    recordArray.add(a.rdataToString());
		}
		hostResult.put("IP", recordArray);

	    } else if (result == 3) {
		System.out.println("\n\nCode 3 (HOST NOT FOUND) received from lookup of host : " + query);
		hostResult.put("Result Code", 3);
		hostResult.put("Result Description", "Host Not Found");
	    }
	    hostEntry.put(query, hostResult);
	    finalJSON.put("Host", hostEntry);
	}
    }

    @SuppressWarnings("unchecked")
    public void save() throws Exception {
	finalJSON.put("Domain List ID", getJSON().getDomainNameListId());
	finalJSON.put("List Prepared By", getJSON().getListPreparedBy());

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

	try {
	    this.inputFile = new FileReader(inputFile);
	} catch (Exception e) {
	    throw e;
	}
    }

    public FileWriter getOutputFile() {
	return outputFile;
    }

    private void setOutputFile(String outputFile) throws Exception {
	try {
	    this.outputFile = new FileWriter(outputFile);
	} catch (Exception e) {
	    throw e;
	}
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
	try {
	    this.environmentIP = InetAddress.getByName(environmentIP);
	} catch (Exception e) {
	    throw new Exception(e.getMessage() + " is an invalid IP address.");
	}

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
	int i = 0;
	try {
	    i = Integer.parseInt(queriesPerSecond);
	    if (i < 1)
		throw new Exception("Queries per second must be > 0.");
	} catch (Exception e) {
	    throw e;
	}

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

    public void setResolve(SimpleResolver resolve) {
	this.resolve = resolve;
    }

}
