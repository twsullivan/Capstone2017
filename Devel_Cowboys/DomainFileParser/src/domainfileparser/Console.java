package domainfileparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


/**
 *
 * @author pgabriel
 */
public class Console {
    
    // Product name to show in welcome message
    private final String PRODUCT_NAME = "Domain File Parser";
    
    // Product version to show in welcome message
    private final String PRODUCT_VERSION = "v1.2";
    
    // Names of options
    private final String[] OPTIONS_NAMES = new String[] {"Input File","Output File Path","Name",
                                                        "Description","File Type","Max Results"};
    
    // File type names
    private final String[] FILE_TYPE_NAMES = new String[] {"adblock", "dnsblackhole", "internetlog"};
    
    // File type classes
    private final String[] FILE_TYPE_CLASSES = new String[] {"AdBlockParse","DNSBlackHoleParse", "InternetLog"};
    
    // Enable (true) or disable (false) debug mode - prints more detailed error messages and diagnostic information when enabled.
    private final boolean DEBUG = false;
    
    // Values of options
    private String[] optionsValues = new String[6];
    
    // Status of options
    private String[] optionsStatus = new String[6];
        
    // Max Number of Results (0 = unlimited)
    private int maxResults = 0;
       

    // Begin normal processing routine
    public Console(String[] args) {
        
        // Print welcome message
        String welcomeMessage = "# " + PRODUCT_NAME + " " + PRODUCT_VERSION + " #";
        String welcomeBorder = new String(new char[welcomeMessage.length()]).replace("\0", "#");
        System.out.println("\n" + welcomeBorder + "\n" + welcomeMessage + "\n" + welcomeBorder + "\n");
        
        // Displays the usage when the user types in help
        if(args.length == 0 || args[0].toLowerCase().equals("help")) {
            displayUsage("", false);
            System.exit(0);
        }
        
        // Check if all arguments are present and valid
        if(checkArguments(args) == false)
        {
            // Arguments are missing or invalid. Display usage and terminate now.
            System.exit(1);
        }
        
        // Arguments are present and valid. Continue processing.
        String[] inputFileContents = parseInputFile();
        
        if(inputFileContents[0].toLowerCase().equals("error"))
        {
            displayError(inputFileContents[1],true);
        }
        
        // Store list of parsed domains
        String[] cleanDomains = new String[]{};
        
        String parserClassName = "";
        
        for(int i = 0; i < FILE_TYPE_NAMES.length; i++)
        {
            if(optionsValues[4].equals(FILE_TYPE_NAMES[i]))
            {
                parserClassName = FILE_TYPE_CLASSES[i];
            }
        }

        try
        {
            Class<?> parserClass = Class.forName("domainfileparser." + parserClassName);
            Method parseMethod = parserClass.getMethod("parse", String[].class);
            Object result = parseMethod.invoke(null, (Object) inputFileContents);
            
            cleanDomains = (String[]) result;
        }
        catch(Exception e)
        {
            if(DEBUG)
            {
                displayError("The parser could not be loaded.\n\nError detail: " + e.toString(), true);
            }
            
            displayError("The parser could not be loaded.", true);
        }
        
        // Error occurred during parsing.
        if(cleanDomains[0].toLowerCase().equals("error"))
        {
            displayError(cleanDomains[1],true);
        }
        
        if(cleanDomains.length == 0)
        {
            displayError("An invalid input file or other parser error caused zero lines to be returned.", true);
        }
        
        String[] cleanRandomSample = randomSample(cleanDomains);
        
        String[] saveOutput = OutputJSON.save(cleanRandomSample, optionsValues[1], optionsValues[2], optionsValues[3]);
        
        // Error occurred during saving.
        if(saveOutput[0].toLowerCase().equals("error"))
        {
            displayError(saveOutput[1],true);
        }
        
        System.out.println("\nFile saved successfully.\n\n");
    }
    
    private void displayError(String message, boolean terminate)
    {
        System.out.println("#########");
        System.out.println("# Error # ");
        System.out.println("#########\n");

        System.out.println(message);
        System.out.println("\n");

        if(terminate == true)
        {
            System.exit(1);
        }
    }
    
    private String[] randomSample(String[] domains)
    {
        // If max results equals 0, reutrn the entire domains array.
        if(maxResults == 0)
        {
            return domains;
        }
        
        // Get indexes to return
        Set<Integer> indexes = new HashSet<>(); //LinkedHashSet<>();
        
        while(indexes.size() < maxResults)
        {
            indexes.add(ThreadLocalRandom.current().nextInt(0,domains.length));
        }
        
        // Build sampled array
        List<String> domainsList = new ArrayList<>();
        for(int i : indexes)
        {
            domainsList.add(domains[i]);
        }
        
        return domainsList.toArray(new String[0]);
    }
    
    private String[] parseInputFile()
    {        
        File inputFile = new File(optionsValues[0]);
                
        if(!inputFile.exists() || !inputFile.isFile())
        {
            return new String[] {"ERROR","Error reading input file."};
        }
        
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(optionsValues[0]));
            List<String> lineList = new ArrayList<>();
            String line;
            
            while( (line = in.readLine()) != null)
            {
                lineList.add(line);
            }
            
            if(lineList.isEmpty())
            {
                return new String[] {"ERROR","Input file is empty."};
            }
            
            return lineList.toArray(new String[0]);
        }
        catch (FileNotFoundException e)
        {
            return new String[] {"ERROR","Input file not found."};
        }
        catch(IOException e)
        {
            return new String[] {"ERROR","IOException occurred while reading input file."};
        }
    }
    
    private boolean checkArguments(String[] args)
    {
        // If less than 5 or more than 6 arguments are given, there is a problem.
        if(args.length != 6)
        {
            displayUsage("Invalid number of arguments. Expected 6, received " + args.length + ".", false);
            return false;
        }
        
        // Keep track of any invalid arguments
        boolean optionsAllValid = true;
        
        // Copy args into optionsValues
        System.arraycopy(args, 0, optionsValues, 0, args.length);
                
        // Input File
        if(args[0].length() > 0)
        {            
            File inputFile = new File(args[0]);
            
            optionsStatus[0] = "OK";
            
            // Make sure path exists
            if(inputFile.exists())
            {
                // Make sure path is file, not directory
                if(!inputFile.isFile())
                {
                    optionsAllValid = false;
                    optionsStatus[0] = "INVALID - Path must be a file, not a directory.";
                }
            }
            else
            {
                optionsAllValid = false;
                optionsStatus[0] = "INVALID - File does not exist.";
            }
        }
        
        // Output File Path
        if(args[1].length() > 0)
        {            
            File outputFile = new File(args[1]);
            
            optionsStatus[1] = "OK";
            
            // Make sure path exists
            if(outputFile.exists())
            {
                // Make sure path is directory, not file.
                if(!outputFile.isDirectory())
                {
                    optionsAllValid = false;
                    optionsStatus[1] = "INVALID - Path must be directory, not file.";
                }
            }
            else
            {
                optionsAllValid = false;
                optionsStatus[1] = "INVALID - Directory does not exist.";
            }
        }
        
        // Name
        if(args[2].length() > 0)
        {
            // No validation required.
            optionsStatus[2] = "OK";
        }
        
        // Description
        if(args[3].length() > 0)
        {
            // No validation required.
            optionsStatus[3] = "OK";
        }
        
        // File Type
        
        boolean validFileType = false;
                
        for(String fileTypeName : FILE_TYPE_NAMES)
        {
            if(fileTypeName.equals(args[4].toLowerCase()))
            {
                validFileType = true;
                optionsValues[4] = args[4].toLowerCase();
                optionsStatus[4] = "OK";
            }
        }
        
        if(!validFileType)
        {
            optionsStatus[4] = "INVALID";
            optionsAllValid = false;
        }
        
        // Max Results            
        try
        {
            maxResults = Integer.parseInt(args[5]);
            optionsStatus[5] = "OK";
            
            if(maxResults < 0)
            {
                optionsAllValid = false;
                optionsStatus[5] = "INVALID - Enter only positive integer values.";
            }
        }
        catch(Exception e)
        {
            optionsAllValid = false;
            optionsStatus[5] = "INVALID - Enter only positive integer values.";
        }

        if(optionsAllValid == false)
        {
            displayUsage("One or more arguments is invalid.", true);
        }
        
        return optionsAllValid;
    }
    
    private void displayArguments()
    {
        System.out.println("###################");
        System.out.println("#    ARGUMENTS    #");
        System.out.println("###################\n");
        
        for(int i = 0; i < OPTIONS_NAMES.length; i++)
        {
            System.out.println(OPTIONS_NAMES[i] + ": " + optionsValues[i] + " [" + optionsStatus[i] + "]");
        }
        
        System.out.println("\n");
    }

    private void displayUsage(String message, boolean displayArgs)
    {
        if(message.length() > 0)
        {
            System.out.println("#########");
            System.out.println("# Error # ");
            System.out.println("#########\n");
            
            System.out.println(message + "\n\n");
        }
        
        if(displayArgs == true)
        {
            displayArguments();
        }
        
        System.out.println("###############");
        System.out.println("#    USAGE    #");
        System.out.println("###############\n");
        
        System.out.println("java -jar DomainFileParser.jar <input_file> <output_file_path> <name> <description> <file_type> <max_results>\n\n");
        
        System.out.println("Input File (required): Absolute path of the input file, including filename and extension, from which to extract domains.\n");
        System.out.println("Output File Path (required): Absolute folder path to save the output file containing domains in JSON format. The file name will be generated automatically so it does not need to be included. Output file name example: domainparser_results_1486000110.json\n");
        System.out.println("Name (required): The name of the user that performed the web scrape.\n");
        System.out.println("Description (required): Information about the domain list and its origin.\n");
        System.out.println("File Type (required): Which log format is being processed (AdBlock, DNSBlackHole, InternetLog).\n");
        System.out.println("Max Results (required): Maximum number of results to output. If set to 0, result set is unlimited.\n\n");
        
        System.out.println("Example (Windows): java -jar DomainFileParser.jar \"C:\\Users\\bob\\Desktop\\domains.txt\" "
                         + "\"C:\\Users\\bob\\Desktop\" \"Bob Smith\" \"Domains from AdBlock on 02Feb17\" AdBlock 100\n");
        
        System.out.println("Example (Mac): java -jar DomainFileParser.jar \"/Users/bob/Desktop/domains.txt\" "
                         + "\"/Users/bob/Desktop\" \"Bob Smith\" \"Domains from AdBlock on 02Feb17\" AdBlock 100\n\n");
    }
}