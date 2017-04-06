/**
 * @author Steelers
 *
 */

public class DNSResolver {

    public static void main(String[] args) {

	try {
	    Scan scn = new Scan(args);
	    scn.run();
	    scn.save();
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	    usageMessage();

	}

    }

    public static void usageMessage() {
	System.out.println(
		"\nUSAGE:  java -jar DNSResolver.jar -i <domainNames> -o <results> -e <environment>:<IP> -t <queriesPerSecond> [<-q> <-nw> [<-cip> IP]]");
	System.out.println("-i\t Input File - The file containing the domain name list(JSON).");
	System.out.println("-o\t Output File - The file containing the results of the queries(JSON).");
	System.out.println("-e\t Environment and Target IP - The DNS server that will be queried:IP address.");
	System.out.println("-t\t Queries Per Second - Max queries per second.");
	System.out.println("-q\t Quiet - Disables messages.");
	
	System.exit(1);
    }
}