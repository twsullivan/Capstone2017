/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainscraper;
/**Domainscarper is the main class of the domain scrap tool.
 * This tool collects and sends command input to the Driver class.
 * Has a timer feature to see how long the scrap run took.
 * <p>
 * Initialize tool and provides starting information
 * <p>
 * Public methods:
 * <ol>
 * <li>main() - Starts timer, collects command information, constructs tool, sends information to Driver
  and begins run.</li>
 * </ol>
 * 
 * 
 * @author Cody Grogan
 * Capstone Systems Project - 10215 CIS4595C 201701
 * Team - Devel_Ravens
 * @catches ArrayIndexOutOfBoundsException If command line input is incorrect
 * @catches NumberFormatException If levels or max site is not an integer
 * @catches Exception For all other errors that can occur 
 * 
 */
public class DomainScraper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Starts Timers
        final long startTime = System.currentTimeMillis();
        
        //Constructs new instance of timer
        Driver scrapper = new Driver();
        
        //Collects arguements from command line and sets them to the apporiate
        //variables in the Driver class.
        //Runs driver
        try {
            
            if (args.length == 7) {
                scrapper.setUser(args[0]);
                scrapper.setInitialDNS(args[1]);
                scrapper.setFileName(args[2]);
                scrapper.setListId(args[3]);
                scrapper.setListDesc(args[4]);
                scrapper.setnLevels(Integer.parseInt(args[5]));
                scrapper.setMaxNum(Integer.parseInt(args[6]));

                scrapper.runDriver();
            }
            else {
                scrapper.usageDump();
            }

        } 
        //If input does not match proper command format
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: No and/or malformed arguments.");
            scrapper.usageDump();
        } 
        //If the user attempts to use a non-integer
        catch (NumberFormatException e) {
            System.err.println("Error: Be sure that levels or max number of visits is an integer value.");
            scrapper.usageDump();

        }
        //Any other non-mentioned exception
        catch(Exception e){
            System.err.println(e.getMessage());
        }
        //Stops timer and prints total elapsed time of run to screen
        finally{
            final long endTime = System.currentTimeMillis();
            long millis = endTime - startTime;
            long second = (millis / 1000) % 60;
            long minute = (millis / (1000 * 60)) % 60;
            long hour = (millis / (1000 * 60 * 60)) % 24;
            String time = String.format("%02d:%02d:%02d", hour, minute, second);
            
            System.out.println("Complete!! Total execution time: " + time );
            
        }
    }
}
