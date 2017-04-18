/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * This class defines exception which is thrown if
 * @author Shuchi
 */
public class NoMoneyLeft extends Exception{
    /**
     * This method prints the details of exception
     */
    public void printfirst() {
        System.out.println("Exception : No more money left with boy for gifting");
    }
}
