
package ass;

import java.util.Random;

/**
 * abstract class containing general attributes of boys
 * @author Shuchi
 */

    
abstract public class boys {
    /**
     * name {String} unique name of each boy
    */
    public String name;   
    
    public int id=0;
    /**
     * attractiveness of boy
    */
    public int attractiveness;
    /** 
     * gf_budget his budget for giving gifts to his gf
    */
    public int gf_budget;
    /**
     *   intelligence level
    */
    public int intelligence; 
    /** 
     * his budget for giving gifts to his gf
    */
    public int attration_req;
    /**
     * current relationship status : single or committed
    */
    public String relation_status; 
    /**
     *  happiness of each boy as per his type
     * */
    public int happiness;
    public int com_to_gid;
    /**
     * function to calculate boy's happiness
     */
    public abstract void happ();
    /**method makegiftbasket()
     * allocate gifts from each boy for their girl according to their type
     * arguments
     * @param couple array of couples
     * @param gift array of gifts
     * 
     */
    public abstract void makegiftbasket(couples couple, gifts gift[]);
}
