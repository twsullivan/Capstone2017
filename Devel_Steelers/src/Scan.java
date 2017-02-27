
/*
 * @author Steelers
 *
 */
import java.io.*;
import java.net.InetAddress;
import org.xbill.DNS.*;

public class Scan {
    private FileReader inputFile;
    private FileWriter outputFile;
    private String environment = "";
    private InetAddress environmentIP;
    private String name = "";
    private int queriesPerSecond = 0;
    private boolean quiet = false;
    private SimpleResolver resolve;

    public Scan(String[] inputArgs) throws Exception {

	for (int i = 0; i < inputArgs.length; i += 2)
	    if (inputArgs[i] == "-i")
		setInputFile(inputArgs[i + 1]);
	    else if (inputArgs[i] == "-o")
		setOutputFile(inputArgs[i + 1]);
	    else if (inputArgs[i] == "-e") {
		setEnvironment(inputArgs[i + 1].split(":")[0]);
		setEnvironmentIP(inputArgs[i + 1].split(":")[1]);
	    } else if (inputArgs[i] == "-t")
		setQueriesPerSecond(inputArgs[i + 1]);
	    else if (inputArgs[i] == "-n")
		setName(inputArgs[i + 1]);
	    else if (inputArgs[i] == "-q") {
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
    }

    public void run() throws Exception {
	String query = "www.noagendashow.com";

	System.out.println("Setting DNS Resolver to : " + getEnvironmentIP().getHostAddress());
	resolve = new SimpleResolver();
	resolve.setAddress(getEnvironmentIP());

	Lookup.setDefaultResolver(resolve);
	Lookup lookupObj = new Lookup(query, Type.A, DClass.IN);

	Record[] records = lookupObj.run();
	System.out.println("Code " + lookupObj.getResult() + " received from lookup of host : " + query);

	if (lookupObj.getResult() == 0)
	    for (int i = 0; i < records.length; i++) {
		ARecord a = (ARecord) records[i];
		System.out.println("FOUND : Host " + a.getName().toString() + " has address " + a.rdataToString());
	    }
	else if (lookupObj.getResult() == 3) {
	    System.out.println("Host not found");
	}

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
