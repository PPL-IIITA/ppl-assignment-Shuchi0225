/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.sql.Timestamp;
import java.util.Random;

/**
 * abstract class containing general attributes of girls
 * @author Shuchi
 */

//general attributes of all girls types

/**
     *girls class containing :
     * @see girls
     *   name unique name of each girl
     *   attractiveness
     *   attraction_req minimum attractive_requirement of their girlfriends  
     *   main_budget minimum amount needed for her maintenanace
     *   relation_status current relationship status : single or committed
     *   happiness happiness of each boy as per his type
     *   intelligence intelligence level

    */
abstract public class girls {
    public String name;                             //name
    public int id=0;
    public int attractiveness;                      //attractiveness
    public int main_budget;                         //maintenance budget
    public int intelligence;                        //intelligence
    public String relation_status;                  //relationship status
    public int happiness;
    public int com_to_bid;
    public int ex_bf;                               //ex boyfriend
    public abstract void happ(couples couple);
    public Timestamp t;
}
