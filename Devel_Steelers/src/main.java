/**
 * 
 */

/**
 * @author Steelers
 *
 */
public class main {

	private static String inputFile = "";
	private static String outputFile = "";
	private static String environment = "";
	private static int queriesPerSecond = 0;
	private static boolean[] optionsCheck = { false, false, false, false };

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] testArgs = { "-i", "input.json", "-o", "output.json", "-e", "8.8.8.8", "-t", "100" };
		testInputArgs(testArgs);

	}

	private static void testInputArgs(String[] testArgs) {
		for (int i = 0; i < testArgs.length; i += 2) {
			if (testArgs[i] == "-i") {
				setInputFile(testArgs[i + 1]);
				setOptionsCheck(0);
			} else if (testArgs[i] == "-o") {
				setOutputFile(testArgs[i + 1]);
				setOptionsCheck(1);
			} else if (testArgs[i] == "-e") {
				setEnvironment(testArgs[i + 1]);
				setOptionsCheck(2);
			} else if (testArgs[i] == "-t") {
				setQueriesPerSecond(Integer.parseInt(testArgs[i + 1]));
				setOptionsCheck(3);
			} else
				usageMessage();

		}
		for (int i = 0; i < 4; i++)
			if (getOptionsCheck()[i] == false)
				usageMessage();

	}

	private static void usageMessage() {

		System.out.println(
				"USAGE:  java -jar queryDNS.jar -i <domainNames> -o <results> -e <environment> -t <queriesPerSecond>");
		System.out.println("-i\t inputFile - The file containing the domain name list.");
		System.out.println("-o\t outputFile - The file containing the results of the queries.");
		System.out.println("-e\t environment - The DNS server that will be queried.");
		System.out.println("-t\t queriesPerSecond - Max queries per second.");
		System.exit(0);
	}

	/**
	 * @return the inputFile
	 */
	private static String getInputFile() {
		return inputFile;
	}

	/**
	 * @param inputFile
	 *            the inputFile to set
	 */
	private static void setInputFile(String inputFile) {
		main.inputFile = inputFile;
	}

	/**
	 * @return the outputFile
	 */
	private static String getOutputFile() {
		return outputFile;
	}

	/**
	 * @param outputFile
	 *            the outputFile to set
	 */
	private static void setOutputFile(String outputFile) {
		main.outputFile = outputFile;
	}

	/**
	 * @return the environment
	 */
	private static String getEnvironment() {
		return environment;
	}

	/**
	 * @param environment
	 *            the environment to set
	 */
	private static void setEnvironment(String environment) {
		main.environment = environment;
	}

	/**
	 * @return the queriesPerSecond
	 */
	private static int getQueriesPerSecond() {
		return queriesPerSecond;
	}

	/**
	 * @param queriesPerSecond
	 *            the queriesPerSecond to set
	 */
	private static void setQueriesPerSecond(int queriesPerSecond) {
		main.queriesPerSecond = queriesPerSecond;
	}

	/**
	 * @return the optionsCheck
	 */
	private static boolean[] getOptionsCheck() {
		return optionsCheck;
	}

	/**
	 * @param optionsCheck
	 *            the optionsCheck to set
	 */
	private static void setOptionsCheck(int option) {
		main.optionsCheck[option] = true;
	}
}
