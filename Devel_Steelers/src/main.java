/**
 * 
 */

/**
 * @author lance
 *
 */
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("TEST\n");

		usageMessage();

	}

	private static void usageMessage() {

		System.out.println(
				"USAGE:  java -jar queryDNS.jar -i <domainNames> -o <results> -e <environment> -t <queriesPerSecond>");
		System.out.println("-i\t inputFile - The file containing the domain names.");
		System.out.println("-o\t outputFile - The file containing the results of the queries.");
		System.out.println("-e\t environment - The DNS server that will be queried.");
		System.out.println("-t\t queriesPerSecond - Max queries per second.");

	}
}
