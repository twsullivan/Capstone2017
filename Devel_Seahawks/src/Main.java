import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;

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

    /**
     *
     * @param args
     */
    public static void main(String[] args) { // changed
         
        final String header = "----------------------------QStats----------------------------";
        final String footer = "--------------------------Version 1.6-------------------------";
        
        Options options = new Options();

        Option inputFile = new Option("f", "input", true, "Input file path -No Quotes-");
        inputFile.setRequired(true);
        options.addOption(inputFile);

        Option outputFile = new Option("o", "output", true, "Output file path (if specified) -No Quotes-");
        outputFile.setRequired(false);
        options.addOption(outputFile);
        
        Option verboseFlag = new Option("v", "Verbose output (Debug Mode)");
        verboseFlag.setRequired(false);
        options.addOption(verboseFlag);
        
        Option ignoreUnresolvedFlag = new Option("u", "Ignores unresolved domains");
        ignoreUnresolvedFlag.setRequired(false);
        options.addOption(ignoreUnresolvedFlag);
        
        Option ignoreBlockedFlag = new Option("b", "Ignores blocked domains");
        ignoreBlockedFlag.setRequired(false);
        options.addOption(ignoreBlockedFlag);

        parseCmds(options, args, header, footer); 
    }
    
    private static void parseCmds(Options opt, String[] args, String header, String footer)//Changed
    {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
         
        try {
            cmd = parser.parse(opt, args);
            
            
            String inputFilePath = cmd.getOptionValue("input");
            String outputFilePath = cmd.getOptionValue("output");
            boolean verboseFlagIndicator = cmd.hasOption('v');
            boolean ignoreUnresolvedIndicator = cmd.hasOption('u');
            boolean ignoreBlockedIndicator = cmd.hasOption('b');
            
            FolderReader jsonReader = new FolderReader(inputFilePath, verboseFlagIndicator, ignoreBlockedIndicator, ignoreUnresolvedIndicator);
            List<List<Double>> responseTimes = jsonReader.getEnvResponseTimes();  
            List<List<Double>> statisticsForOutput = new ArrayList();
            
            System.out.println("\nProcessing data...");
                for(int i = 0; i < responseTimes.size(); i++)
                {
                    ArrayList<Double> parsedTempVals = new ArrayList();
                    for(int k = 0; k < responseTimes.get(i).size(); k++)
                    {
                       parsedTempVals.add(responseTimes.get(i).get(k));
                    }
                    StatsCalculator statsObj = new StatsCalculator(parsedTempVals);
                    statsObj.calculateQueryStatistics();
                    List<Double> listOfStatistics = new ArrayList();
                    
                        listOfStatistics.add(statsObj.getMean());
                        listOfStatistics.add(statsObj.getMedian());
                        listOfStatistics.add(statsObj.getStandardDeviation());
                        listOfStatistics.add(statsObj.getPercent98());
                        
                  statisticsForOutput.add(listOfStatistics);
                }                
                List<Integer> tempObjs = jsonReader.getEnvCounts();
                List<String> tempEnvID = jsonReader.getEnvID();
                FileWrite writeObj = new FileWrite(statisticsForOutput,outputFilePath,tempObjs,tempEnvID);
                
        }
        catch (IOException | ParseException | ArithmeticException | NumberFormatException | JsonSyntaxException e)
        {
            System.out.println("\nError: " + e.getMessage() + "\n");
            displayHelp(header, opt, footer);
        }
    }
    
    private static void displayHelp(String header, Options options, String footer) //changed
    {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar qstats.jar [-f] <FILEPATH> [-v]", header, options, footer);
    }  
}
