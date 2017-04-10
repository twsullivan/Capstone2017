/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainscraper;

/**
 *
 * @author Cody Grogan
 */
public class DomainScraper {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final long startTime = System.currentTimeMillis();

        Driver scrapper = new Driver();

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
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error: No and/or malformed arguments.");
            scrapper.usageDump();
        } 
        catch (NumberFormatException e) {
            System.err.println("Error: Be sure that levels is a integer value.");
            scrapper.usageDump();

        }
        catch(Exception e){
            System.err.println(e.getMessage());
        }
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
