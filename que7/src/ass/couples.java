
package ass;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * containing present couples
 * @author Shuchi
 */



//class for girls and boys who are committed
public class couples {
    /**
     *couples class containing :
     
     *     girl girl who is in relationship, 
     *     boy boy who is in relationship, 
     *     gifts arraylist for couple's gifting history, 
     *     happiness couple's happiness, 
     *     compatibility couple's compatibility, 
     *     total_price total price of gifts given, 
     *     total_value total value of gifts given.

    */
    public girls girl;                                              //girl who is in relationship
    public boys boy;                                                //boy  who is in relationship
    public ArrayList<gifts> gifts=new ArrayList<gifts>();           //couple's gifting history
    public int happiness;                                           //happiness
    public int compatibility;                                       //compatibility
    public int total_price;                                         //total price of gifts given
    public int total_value;                                         //total value of gifts given
    
    /**
     * constructor couples
     * @param girl array of girl
     * @param boy array of boy
     * add boy and girl who gets committed
     */
    
    public couples(girls girl, boys boy[]) {
        this.girl=girl;
        if(girl.relation_status=="committed") {
            this.boy=boy[girl.com_to_bid];
        }
    }
    
    /**
     * 
     * constructor couples()
     * for initializing couples
     */
    
    public couples() {
        
    }
}
