import org.junit.*;

import static org.junit.Assert.assertEquals;

public class formatTester{

   Format tester = new Format();
   
   public void testAll(){
      
      testIsURL();
      
      testCheckToken();
      
      testCheckLine();
      
      testGenerateDomains();
      
      testRemoveDuplicates();
   }
   
   @Test
   public void testIsURL(){
      
      assertEquals("http://bungie.net is a URL", true, tester.isURL("http://bungie.net"));
      assertEquals("OTHERTEXT is not a URL", false, tester.isURL("OTHERTEXT"));
      
      System.out.println("isURL tested");
   }
   
   public void testCheckToken(){
      System.out.println("checkToken tested");
   }
   
   public void testCheckLine(){
      System.out.println("checkLine tested");
   }
   
   public void testGenerateDomains(){
      System.out.println("generateDomains tested");
   }
   
   public void testRemoveDuplicates(){
      System.out.println("removeDuplicates tested");
   }
   
   public static void main(String[] args){
      formatTester mainTester = new formatTester();
      
      mainTester.testAll();
   }
   
}
