/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domainfileparser;

/**
 *
 * @author pgabriel
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String[] args2 = new String[] {
            
            "D:/Zack/Desktop/test/AdblockDNSList.txt",
            "D:/Zack/Desktop/test",
            "test",
            "test",
            "adblock",
            "0"
        
        };
        
        Console console = new Console(args2);
    }
    
}
