
package DNSQueryTool;

import java.io.IOException;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DNSClient {

    // Constants Initialization
    private static final int CORE_POOL_SIZE = 2;
    private static final int MAXIMUM_POOL_SIZE = 4;
    private static final long KEEP_ALIVE_TIME = 10;
    private static final int WORK_QUEUE_SIZE = 2;

    // Variable Initialization
    private String dnsServerAddress;
    private String outFileName;
    private boolean quiet = false;
    private int throttle = 0;
    private int queryCount = 0;
    private int remainingThreads = 0;
    private DNSQueryInput dnsQueryInput;
    private final DNSQueryOutput dnsQueryOutput = new DNSQueryOutput();
    private final Gson gson = new Gson();

    public static void main(String[] args) throws IOException, InterruptedException {
        DNSClient program = new DNSClient();
        program.processArgs(args);
        program.run(args);
    }

    public synchronized void setDNSQueryOutputResults(int id, DNSQueryResult result) {
        dnsQueryOutput.queryResults[id] = result;
    }

    public synchronized void decrementRemainingThreads() {
        remainingThreads--;
    }

    private void run(String args[]) throws InterruptedException 
    {    
        int throttleTime = throttle > 0 ? 1000 / throttle : 0;

        // Get the default thread factory
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // Creat thread pool
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(WORK_QUEUE_SIZE),
                threadFactory);

        MonitorThread monitor = new MonitorThread(executor, 1);
        Thread monitorThread = new Thread(monitor);

        if (!quiet) {
            // Start the monitor thread
            monitorThread.start();
        }

        // Start worker threads
        for (String domain : dnsQueryInput.domainNames) {

            DNSQuery thread = new DNSQuery(queryCount, this, dnsServerAddress, domain);
            executor.execute(thread);

            queryCount++;
            remainingThreads++;

            // Time delay between thread activation
            Thread.sleep(throttleTime);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        if (!quiet) {
            Thread.sleep(1000);
            monitor.shutdown();
        }

//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        
//        System.out.println("\n" + gson.toJson(dnsQueryOutput));

        try {
            // Write results to output file   
            FileWriter writer = new FileWriter(outFileName);
            writer.write(gson.toJson(dnsQueryOutput));
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processArgs(String[] args) throws FileNotFoundException {
        int count = 0;

        if (args.length < 10 || args.length > 11) {
            UsageDump();
        }
        while (count < args.length) {
            if ("-i".equals(args[count])) {
                count++;
                File f = new File(args[count]);
                if (f.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(args[count]));

                    dnsQueryInput = gson.fromJson(br, DNSQueryInput.class);
                    dnsQueryOutput.domainNameListId = dnsQueryInput.domainNameListId;
                    dnsQueryOutput.queryResults = new DNSQueryResult[dnsQueryInput.domainNames.length];
                } else {
                    System.out.println("File: " + args[count] + " does not exist.");
                    UsageDump();
                }
            }
            if ("-o".equals(args[count])) {
                count++;
                outFileName = args[count];
            }
            if ("-e".equals(args[count])) {
                count++;
                if (args[count].split(":").length == 2)
                {
                    dnsQueryOutput.environmentId = args[count].split(":")[0];
                    dnsServerAddress = args[count].split(":")[1];
                } else {
                    System.out.println("Improper formatting: -e " + args[count]);
                    UsageDump();
                }
            }
            if ("-t".equals(args[count])) {
                count++;
                try
                {
                throttle = Integer.parseInt(args[count]);
                }
                catch (NumberFormatException ex)
                {
                    System.out.println("Not a number: -t " + args[count]);
                    UsageDump();
                }
            }
            if ("-n".equals(args[count])) {
                count++;
                dnsQueryOutput.queriesRunBy = args[count];
            }
            if ("-q".equals(args[count])) {
                quiet = true;
            } else {
                quiet = false;
            }
            count++;
        }
    }

    private void UsageDump() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nUsage: java -jar DNSQueryTool.jar -i <domainNames> -o <queryResults> -e <dnsEnvironment> -t <queriesPerSecond> -n <\"Name\"> -q (optional)\n\n");
        sb.append("-i    Input filename - The file containing the domain names.\n");
        sb.append("-o    Output filename - The file containing the results of the queries.\n");
        sb.append("-e    Environment - The DNS environment that will be queried. (GCA:8.8.8.8)\n");
        sb.append("-t    Throttle value - Max queries per second.\n");
        sb.append("-n    Name - Name of person running queries\n");
        sb.append("-q    Optional flag; quiet if in command line. IF not there then queries left displayed.\n\n");

        System.out.print(sb);
        System.exit(0);
    }
}
