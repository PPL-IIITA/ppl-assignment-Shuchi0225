
package ass;

import java.util.Random;

/**
 * abstract class containing general attributes of boys
 * @author Shuchi
 */

    
abstract public class boys {
    /**
     *Boys class containing :
     * name {String} unique name of each boy, 
     * attractive, 
     * attraction_req minimum attractive_requirement of their girlfriends ,  
     * gf_budget his budget for giving gifts to his gf, 
     * relation_status current relationship status : single or committed, 
     *  happiness happiness of each boy as per his type, 
     *  intelligence intelligence level.
    */
    public String name;                 
    public int id=0;
    public int attractiveness;
    public int gf_budget;
    public int intelligence; 
    public int attration_req;
    public String relation_status; 
    public int happiness;
    public int com_to_gid;
    public abstract void happ();
}
