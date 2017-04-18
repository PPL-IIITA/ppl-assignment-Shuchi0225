/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * This class defines exception which is thrown if
 * the boy has not been allocated any type and thus do not have any details
 * @author Shuchi
 */
public class NoBoyTypeFound extends Exception{
    int j;
    /**
     * constructor to create instance of exception
     * @param i index of boy
     */
    public NoBoyTypeFound(int i) {
        j=i;
    }
    /**
     * This functions print the details of exception
     */
    public void printfirst() {
        System.out.println("Exception: No type found for boy_" + j );
        super.printStackTrace();
    }
}
