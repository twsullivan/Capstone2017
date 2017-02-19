package Internal;

/*
 * @author lance
 *
 */
import java.io.*;
import java.net.InetAddress;

public class Scan {
	private FileReader inputFile;
	private FileWriter outputFile;
	private String environment = "";
	private InetAddress environmentIP;
	private String name = "";
	private int queriesPerSecond = 0;
	private boolean quiet = false;

	/**
	 * 
	 */
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

		// if (getInputFile().equals(null) || getOutputFile().equals(null)||
		// getEnvironment().isEmpty() || getName().isEmpty()
		// || getEnvironmentIP().getHostAddress() == "" || getQueriesPerSecond()
		// < 1)
		// throw new Exception("Not enough arguments.");
	}

	/**
	 * @return the inputFile
	 */
	public FileReader getInputFile() {
		return inputFile;
	}

	/**
	 * @param inputFile
	 *            the inputFile to set
	 */
	private void setInputFile(String inputFile) throws Exception {

		try {
			this.inputFile = new FileReader(inputFile);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @return the outputFile
	 */
	public FileWriter getOutputFile() {
		return outputFile;
	}

	/**
	 * @param outputFile
	 *            the outputFile to set
	 */
	private void setOutputFile(String outputFile) throws Exception {
		try {
			this.outputFile = new FileWriter(outputFile);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @return the environment
	 */
	public String getEnvironment() {
		return environment;
	}

	/**
	 * @param environment
	 *            the environment to set
	 */
	private void setEnvironment(String environment) {
		this.environment = environment;
	}

	/**
	 * @return the environmentIP
	 */
	public InetAddress getEnvironmentIP() {
		return environmentIP;
	}

	/**
	 * @param environmentIP
	 *            the environmentIP to set
	 */
	private void setEnvironmentIP(String environmentIP) throws Exception {

		try {
			this.environmentIP = InetAddress.getByName(environmentIP);
		} catch (Exception e) {
			throw new Exception(e.getMessage() + " is an invalid IP address.");
		}

	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	private void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the queriesPerSecond
	 */
	public int getQueriesPerSecond() {
		return queriesPerSecond;
	}

	/**
	 * @param queriesPerSecond
	 *            the queriesPerSecond to set
	 */
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

	/**
	 * @return the quiet
	 */
	public boolean isQuiet() {
		return quiet;
	}

	/**
	 * @param quiet
	 *            the quiet to set
	 */
	private void setQuiet(boolean quiet) {
		this.quiet = quiet;
	}

}
