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
    */
abstract public class girls {
    /**
     *   name unique name of each girl
    */
    public String name;                             //name
    public int id=0;
    /**
     *   attractiveness of girl
    */
    public int attractiveness;                      //attractiveness
    /**
     *   attraction_req minimum attractive_requirement of their girlfriends
    */
    public int main_budget;                         //maintenance budget
    /**
     *   intelligence intelligence level

    */
    public int intelligence;                        //intelligence
    /**
     *   relation_status current relationship status : single or committed

    */
    public String relation_status;                  //relationship status
    /**
     *   happiness happiness of each boy as per his type
    */
    public int happiness;
    public int com_to_bid;
    public int ex_bf;                               //ex boyfriend
    /**
     * This function calculates happiness of girl
     */
    public abstract void happ();
    public Timestamp t;
}
