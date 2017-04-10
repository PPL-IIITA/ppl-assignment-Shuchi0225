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
 *luxury class extending gifts
 * @see gifts
 */

public class luxury extends gifts{
    
    Random rand=new Random();
    /** constructor for luxury class
    *generates random values for luxury gifts attributes
    */
    public luxury() {
        value=(rand.nextInt(1000) + 1000);
        price=(rand.nextInt(2000) + 1500);
    }
}
