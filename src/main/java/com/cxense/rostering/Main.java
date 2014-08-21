package com.cxense.rostering;

import com.cxense.rostering.algorithm.Algorithm;
import com.cxense.rostering.algorithm.SimpleAlgorithm;
import com.cxense.rostering.io.*;
import com.cxense.rostering.parsing.Parser;
import com.cxense.rostering.parsing.SimpleParser;
import com.cxense.rostering.report.EmployerReportWriter;
import com.cxense.rostering.report.ReportWriter;
import com.cxense.rostering.schedule.WeekSchedule;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;


import java.io.File;

/**
 * Main class which is responsible for receiving CLI parameters and assembling the app
 */
public class Main {
    /**
     * Only CLI option for this app.
     */
    private static final Option SOURCE_FILE = new Option("f",true,"Input file with employees' preferences.");

    /**
     * Main method parses CLI arguments and creates spring context with robot app.
     *
     * @param args command line arguments
     */
    public static void main(String[] args){
        if(!isValidArguments(args)){
            return;
        }
        String fileName = getFileName(args);
        Input input = getInput(fileName);
        Parser parser = new SimpleParser();
        parser.setInput(input);
        Parser.Product product = parser.parse();
        Algorithm algorithm = new SimpleAlgorithm();
        WeekSchedule schedule = algorithm.run(product.weekPool, product.employees);
        Output output = new StandardOutput();
        ReportWriter reportWriter = new EmployerReportWriter();
        reportWriter.setOutput(output);
        reportWriter.writeReport(schedule);
    }

    /**
     * Validates, that user's command line input makes sense.
     *
     * @param args user's command line input
     * @return is user input valid?
     */
    private static boolean isValidArguments(String[] args){
        CommandLine cmd;
        // parsing command line arguments
        try{
            cmd = createCommandLine(args);
        }catch(ParseException e){
            System.err.println("Error during parsing command line arguments:");
            System.err.println(e.getMessage());
            return false;
        }
        if(cmd.getArgs().length > 0){
            for(String arg:cmd.getArgs()){
                System.err.println("Argument '" + arg + "' was not recognized");
            }
            return false;
        }
        return true;
    }

    /**
     * Returns name of file if user inserted any.
     *
     * @param args user's command line input
     * @return null if user didn't insert file name
     * @return name of file which will be used as input
     */
    private static String getFileName(String[] args){
        try{
            CommandLine cmd = createCommandLine(args);
            if(cmd.hasOption(SOURCE_FILE.getOpt())){
                return cmd.getOptionValue(SOURCE_FILE.getOpt());
            }
        }catch(ParseException e){
            //already validated, can't happen
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * Creates application context with FileReader if given file exists.
     *
     * @param filename name of source file.
     */
    private static Input getInput(String filename){
        if(filename != null) {
            return new FileInput(new File(filename));
        }else{
            return new StandardInput();
        }
    }

    /**
     * Creates apache command line object and parses user's command line input
     * @param args user's command line input
     * @return CommandLine object
     * @throws ParseException if user inserted bad options
     */
    private static CommandLine createCommandLine(String[] args) throws  ParseException{
        Options options = new Options();
        options.addOption(SOURCE_FILE);
        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options,args);
        return cmd;
    }
}
