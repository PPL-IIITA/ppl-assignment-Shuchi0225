/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * This class defines error which is thrown if
 * no girl type has not been assigned yet
 * @author Shuchi
 */
public class InvalidGirlType extends Exception{
    int i;
    /**
     * constructor of exception
     * @param j id of boy
     */
    public InvalidGirlType(int j) {
        i=j;
    }
    /**
     * This method prints the exception
     */
    public void printfirst() {
        System.out.println("Exception : No details found for boy at index " + i);
    }
}
