package DNSQueryTool;

import java.io.IOException;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DNSClient {

    private static final int CORE_POOL_SIZE = 50;
    private static final int MAXIMUM_POOL_SIZE = 100;
    private static final long KEEP_ALIVE_TIME = 10;
    private static final int WORK_QUEUE_SIZE = 100;

//    private static final int DNS_SERVER_PORT = 53;

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

    private void run(String args[]) throws InterruptedException, SocketException, UnknownHostException, IOException {
        
        int throttleTime = throttle > 0 ? 1000 / throttle : 0;

        // Get the default thread factory
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        // Creat thread pool
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME, TimeUnit.SECONDS, new ArrayBlockingQueue<>(WORK_QUEUE_SIZE),
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

        FileWriter writer = null;
        
        try {
            // Write results to output file   
            writer = new FileWriter(outFileName);
            writer.write(gson.toJson(dnsQueryOutput));
        } catch (IOException ex) {
            System.out.println("File error: -o " + ex.getMessage());
            UsageDump();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    private void processArgs(String[] args) {
        int count = 0;

        if (args.length < 10 || args.length > 11) {
            System.out.println("Wrong number of arguments.");
            UsageDump();
        }
        while (count < args.length) {
            if ("-i".equals(args[count])) {
                count++;
                try {
                    BufferedReader br = new BufferedReader(new FileReader(args[count]));

                    dnsQueryInput = gson.fromJson(br, DNSQueryInput.class);
                    dnsQueryOutput.domainNameListId = dnsQueryInput.domainNameListId;
                    dnsQueryOutput.queryResults = new DNSQueryResult[dnsQueryInput.domainNames.length];
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found: -i ('" + args[count] + "' does not exist.)");
                    UsageDump();
                } catch (com.google.gson.JsonSyntaxException ex) {
                    System.out.println("Syntax error: -i (Syntax error in file: " + args[count] + ")");
                    UsageDump();
                }
            } else
            if ("-o".equals(args[count])) {
                count++;
                if(args[count].split("\\.").length == 2 && args[count].split("\\.")[1].equals("json")) {
                    
                    FileWriter writer = null;
                            
                    try {
                        // Write results to output file   
                        writer = new FileWriter(args[count]);
                    
                        writer.write(gson.toJson(dnsQueryOutput));
                        writer.close();
                        outFileName = args[count];
                    } catch (IOException ex) {
                        System.out.println("File error: -o " + ex.getMessage());
                        UsageDump();
                    } finally {
                        try {
                            if (writer != null) {
                                writer.close();
                            }
                        } catch (IOException ex) {}
                    }                  
                } else {
                    System.out.println("Improper formatting: -o  (File '" + args[count] + "' does not end with 'json' file extension.)");
                    UsageDump();
                }
            } else
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
            } else
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
            } else
            if ("-n".equals(args[count])) {
                count++;
                dnsQueryOutput.queriesRunBy = args[count];
            } else
            if ("-q".equals(args[count])) {
                quiet = true;
            } else {
                    System.out.println("Invalid switch: " + args[count]);
                    UsageDump();
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
