/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;



/**
 *
 * @author Shuchi
 */

/**GiftSelector class for selecting method of making gift basket
 * abstract class
 * extending class needs to implement makegiftbasket() method
 */
abstract public class GiftSelector {
    
    /**method makegiftbasket()
     * arguments
     * @param couple array of couples
     * @param gift array of gifts
     * 
     */
    abstract public void makegiftbasket(couples couple[], gifts gift[]);
}
