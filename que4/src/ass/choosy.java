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
 *choosy class extending girls
 * @see girls
 */
public class choosy extends girls {
    Random rand = new Random();
    /**
     * 
     * constructor choosy()
     * @param i id of girl
     * generates random values for choosy girls attributes
     */
    //generates random values for choosy girls attributes
    public choosy(int i) {
        attractiveness = rand.nextInt(50) + 45;
        main_budget = rand.nextInt(2000) + 2000;
        intelligence = rand.nextInt(30) +65;
        relation_status = "single";
        id=i;
        name="girl_" + id;
    }
    public void happ(couples couple) {
        
    }

}
