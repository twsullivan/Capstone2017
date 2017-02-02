
/*
 * @author lance
 *
 */
import java.net.InetAddress;

public class Scan {
	private String inputFile = "";
	private String outputFile = "";
	private String environment = "";
	private String environmentIP = "";
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
				setQueriesPerSecond(Integer.parseInt(inputArgs[i + 1]));
			else if (inputArgs[i] == "-n")
				setName(inputArgs[i + 1]);
			else if (inputArgs[i] == "-q") {
				setQuiet(true);
				i--;
			} else
				throw new Exception("Invalid arguments provided (duplicate or unknown).");

		if (getInputFile().isEmpty() || getOutputFile().isEmpty() || getEnvironment().isEmpty() || getName().isEmpty()
				|| getEnvironmentIP().isEmpty() || getQueriesPerSecond() < 1)
			throw new Exception("Not enough arguments.");
	}

	/**
	 * @return the inputFile
	 */
	public String getInputFile() {
		return inputFile;
	}

	/**
	 * @param inputFile
	 *            the inputFile to set
	 */
	private void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * @return the outputFile
	 */
	public String getOutputFile() {
		return outputFile;
	}

	/**
	 * @param outputFile
	 *            the outputFile to set
	 */
	private void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
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
	public String getEnvironmentIP() {
		return environmentIP;
	}

	/**
	 * @param environmentIP
	 *            the environmentIP to set
	 */
	private void setEnvironmentIP(String environmentIP) {
		this.environmentIP = environmentIP;
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
	private void setQueriesPerSecond(int queriesPerSecond) {
		this.queriesPerSecond = queriesPerSecond;
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
