/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.util.Random;

/**
 *
 * @author Shuchi
 */

/**
 *essential class extending gifts
 * @see gifts
 */

public class essential extends gifts{
    Random rand=new Random();
    /** 
     * constructor for essential class
    *generates random values for essential gifts attributes
    */
    public essential() {
        value=(rand.nextInt(400) + 200);
        price=(rand.nextInt(600) + 500);
    }
}
