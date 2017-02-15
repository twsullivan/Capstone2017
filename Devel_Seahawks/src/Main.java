import java.util.Arrays;
import org.apache.commons.cli.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chase Green
 */
public class Main {
    public static void main(String[] args) {
         
        String header = "----------------------------QStats----------------------------";
        String footer = "--------------------------Version 1.0-------------------------";
        
        Options options = new Options();

        Option inputFile = new Option("f", "input", true, "Input file path");
        inputFile.setRequired(true);
        options.addOption(inputFile);

        Option outputFile = new Option("o", "output", true, "Output file path (if specified)");
        outputFile.setRequired(false);
        options.addOption(outputFile);
        
        Option verboseFlag = new Option("v", "Verbose statistics output");
        verboseFlag.setRequired(false);
        options.addOption(verboseFlag);
        
        Option ignoreUnresolvedFlag = new Option("u", "Ignores unresolved domains");
        ignoreUnresolvedFlag.setRequired(false);
        options.addOption(ignoreUnresolvedFlag);
        
        Option ignoreBlockedFlag = new Option("b", "Ignores blocked domains");
        ignoreBlockedFlag.setRequired(false);
        options.addOption(ignoreBlockedFlag);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        

        try {
            cmd = parser.parse(options, args);
            
            String inputFilePath = cmd.getOptionValue("input");
            String outputFilePath = cmd.getOptionValue("output");
            boolean verboseFlagIndicator = cmd.hasOption('v');
            boolean ignoreUnresolvedIndicator = cmd.hasOption('u');
            boolean ignoreBlockedIndicator = cmd.hasOption('b');
            
            
            
            System.out.println("---------SEND THIS INFORMATION TO OTHER CLASSES---------");
            System.out.println(inputFilePath);
            System.out.println("Output file (if set)?:" + outputFilePath);
            System.out.println("Verbose output?:" +verboseFlagIndicator);
            System.out.println("Ignore unresolved domains?:" + ignoreUnresolvedIndicator);
            System.out.println("Ignore blocked domains?:" + ignoreBlockedIndicator);
            
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("java -jar qstats.jar [-f] <FILEPATH> [-v] [-o] <FILEPATH>", header, options, footer);
        }
    }
}
