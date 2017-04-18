/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * This class defines exception which is thrown if 
 * Girl is not committed
 */
public class GirlNotCommitted extends Exception{
    int i;
    /**
     * constructor of exception 
     * @param j id of girl
     */
    public GirlNotCommitted(int j) {
        i=j;
    }
    /**
     * This method prints the exception with girl's id who is not committed
     */
    public void printfirst() {
        System.out.println("Exception : girl_" + i + "not committed");
    }
}
