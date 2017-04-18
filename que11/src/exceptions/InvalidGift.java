/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * This class defines exception which is thrown if
 * trying to access gift which is yet not been defined
 * @author Shuchi
 */
public class InvalidGift extends Exception{
    int i;
    public InvalidGift(int j) {
        i=j;
    }
    public void printfirst() {
        System.out.println("gift does not exist at index " + i);
    }
}
