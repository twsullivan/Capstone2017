package Internal;

/*
 * @author lance
 *
 */
public class Scan {
	private static String inputFile = "";
	private static String outputFile = "";
	private static String environment = "";
	private static String environmentIP = "";
	private static String name = "";
	private static int queriesPerSecond = 0;
	private static boolean quiet = false;

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
	public static String getInputFile() {
		return inputFile;
	}

	/**
	 * @param inputFile
	 *            the inputFile to set
	 */
	private static void setInputFile(String inputFile) {
		Scan.inputFile = inputFile;
	}

	/**
	 * @return the outputFile
	 */
	public static String getOutputFile() {
		return outputFile;
	}

	/**
	 * @param outputFile
	 *            the outputFile to set
	 */
	private static void setOutputFile(String outputFile) {
		Scan.outputFile = outputFile;
	}

	/**
	 * @return the environment
	 */
	public static String getEnvironment() {
		return environment;
	}

	/**
	 * @param environment
	 *            the environment to set
	 */
	private static void setEnvironment(String environment) {
		Scan.environment = environment;
	}

	/**
	 * @return the environmentIP
	 */
	public static String getEnvironmentIP() {
		return environmentIP;
	}

	/**
	 * @param environmentIP
	 *            the environmentIP to set
	 */
	private static void setEnvironmentIP(String environmentIP) {
		Scan.environmentIP = environmentIP;
	}

	/**
	 * @return the name
	 */
	public static String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	private static void setName(String name) {
		Scan.name = name;
	}

	/**
	 * @return the queriesPerSecond
	 */
	public static int getQueriesPerSecond() {
		return queriesPerSecond;
	}

	/**
	 * @param queriesPerSecond
	 *            the queriesPerSecond to set
	 */
	private static void setQueriesPerSecond(int queriesPerSecond) {
		Scan.queriesPerSecond = queriesPerSecond;
	}

	/**
	 * @return the quiet
	 */
	public static boolean isQuiet() {
		return quiet;
	}

	/**
	 * @param quiet
	 *            the quiet to set
	 */
	private static void setQuiet(boolean quiet) {
		Scan.quiet = quiet;
	}

}
