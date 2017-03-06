/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dolphins;

/**
 *
 * @author acm52
 */
public class Results
{
    private String environmentOne;
    private String environmentTwo;
    private double jaccardIndex;
    
    public Results(){
        environmentOne = "";
        environmentTwo = "";
        jaccardIndex = 0;
    }
    
    public Results(String envOne, String envTwo, double jacInd){
        environmentOne = envOne;
        environmentTwo = envTwo;
        jaccardIndex = jacInd;
    }
    
    public String getEnvironmentOne() {
        return environmentOne;
    }

    public void setEnvironmentOne(String environmentOne) {
        this.environmentOne = environmentOne;
    }

    public String getEnvironmentTwo() {
        return environmentTwo;
    }

    public void setEnvironmentTwo(String environmentTwo) {
        this.environmentTwo = environmentTwo;
    }

    public double getJaccardIndex() {
        return jaccardIndex;
    }

    public void setJaccardIndex(double jaccardIndex) {
        this.jaccardIndex = jaccardIndex;
    }  
}
