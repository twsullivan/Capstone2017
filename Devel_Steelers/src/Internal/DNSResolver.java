package Internal;

/**
 * @author Steelers
 *
 */
import org.xbill.DNS.*;

public class DNSResolver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] testArgs = { "-i", "input.json", "-o", "output.json", "-q", "-e", "TEST:8.8.8.8", "-t", "50", "-n",
				"John Smith" };

		try {
			Scan scn = new Scan(testArgs);
			System.out.println(scn.getEnvironment());
			System.out.println(scn.getEnvironmentIP().getHostAddress());
			System.out.println(scn.getInputFile());
			System.out.println(scn.getOutputFile());
			System.out.println(scn.getName());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			usageMessage();
		}
	}

	private static void usageMessage() {

		System.out.println(
				"\nUSAGE:  java -jar queryDNS.jar -i <domainNames> -o <results> -e <environment> -t <queriesPerSecond> <-q>");
		System.out.println("-i\t inputFile - The file containing the domain name list.");
		System.out.println("-o\t outputFile - The file containing the results of the queries.");
		System.out.println("-e\t environment - The DNS server that will be queried.");
		System.out.println("-t\t queriesPerSecond - Max queries per second.");
		System.out.println("-q\t quietFlag - Disables message regarding remaining queries.");
		System.exit(0);
	}
}