/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * This class defines exception which is thrown if 
 * girl's maintenance budget is fulfilled yet boy keeps
 * on adding gifts to basket
 * @author Shuchi
 */
public class GirlBudgetFulfilled extends Exception{
    /**
     * This function prints GirlBudgetFulfilled type of exception
     */
    public void printfirst(){
        System.out.println("Exception : No more gifting allowed, girl's budget fulfilled");
    }
}
