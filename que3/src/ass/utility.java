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
 *utility class extending gifts
 * @see gifts
 */
public class utility extends gifts{
    Random rand=new Random();
    /** constructor for utility class
    *generates random values for utility gifts attributes
    */
    public utility() {
        value=(rand.nextInt(500) + 500);
        price=(rand.nextInt(600) + 800);
    }
}
