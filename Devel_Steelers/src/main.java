/**
 * 
 */

/**
 * @author lance
 *
 */
public class main {

	private static String inputFile = "";
	private static String outputFile = "";
	private static String environment = "";
	private static int queriesPerSecond = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] testArgs = { "-i", "input.json", "-o", "output.json", "-e", "8.8.8.8", "-t", "100" };

		for (int i = 0; i < testArgs.length; i += 2) {
			if (testArgs[i] == "-i")
				setInputFile(testArgs[i + 1]);
			else if (testArgs[i] == "-o")
				setOutputFile(testArgs[i + 1]);
			else if (testArgs[i] == "-e")
				setEnvironment(testArgs[i + 1]);
			else if (testArgs[i] == "-t")
				setQueriesPerSecond(Integer.parseInt(testArgs[i + 1]));
			else
				usageMessage();

		}

	}

	private static void usageMessage() {

		System.out.println(
				"USAGE:  java -jar queryDNS.jar -i <domainNames> -o <results> -e <environment> -t <queriesPerSecond>");
		System.out.println("-i\t inputFile - The file containing the domain name list.");
		System.out.println("-o\t outputFile - The file containing the results of the queries.");
		System.out.println("-e\t environment - The DNS server that will be queried.");
		System.out.println("-t\t queriesPerSecond - Max queries per second.");

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
	public static void setInputFile(String inputFile) {
		main.inputFile = inputFile;
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
	public static void setOutputFile(String outputFile) {
		main.outputFile = outputFile;
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
	public static void setEnvironment(String environment) {
		main.environment = environment;
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
	public static void setQueriesPerSecond(int queriesPerSecond) {
		main.queriesPerSecond = queriesPerSecond;
	}
}
