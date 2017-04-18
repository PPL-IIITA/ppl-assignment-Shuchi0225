/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * This class defines exception
 * which is thrown if someone trying to access couple index
 * at which no valid couple exist.
 * 
 */
public class NoCoupleFoundAtIndex extends Exception{
    /**
     * This function prints the NoCoupleFoundAtIndex exception 
     * @param i tells the index at which no couple was found
     */
    public void printfirst(int i) {
        System.out.println("Exception found : NoCoupleFoundAtIndex" + i + "Can't assign happiness");
    }
}
