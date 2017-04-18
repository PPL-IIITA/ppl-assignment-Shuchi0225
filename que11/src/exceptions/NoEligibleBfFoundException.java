/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * This class defines exception which is thrown if
 * no boy fulfills all requirements to be boyfriend of girl
 * and thus no bf could be found for her
 * 
 */
public class NoEligibleBfFoundException extends Exception{
    int i;
    /**
     * constructor to create instance of exception
     * @param j index of girl
     */
    public NoEligibleBfFoundException(int j) {
        i=j;
    }
    /**
     * This method prints the details of exception
     */
    public void printfirst() {
        System.out.println("Exception : No boyfriend found for girl_" + i);
    }
}
