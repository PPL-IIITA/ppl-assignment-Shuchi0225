/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * This class defines exception which is thrown if
 * no gift type has yet been allocated to gift and thus has
 * no details
 * @author Shuchi
 */
public class NoGiftTypeFound extends Exception{
    int i;
    /**
     * constructor t create instance of exception
     * @param j index of gift
     */
    public NoGiftTypeFound(int j) {
        i=j;
    }
    /**
     * This method prints the details of exception
     */
    public void printfirst() {
        System.out.println("Exception : No gift type found for gift " + i);
    }
}
