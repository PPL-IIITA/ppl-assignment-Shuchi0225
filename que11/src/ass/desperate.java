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
 *desperate class extending girls
 * @see girls
 */
public class desperate extends girls{
    Random rand = new Random();
    /**
     * 
     * constructor desperate()
     * @param i id of girl
     * generates random values for desperate girls attributes
     */
    //generates random values for desparate girls attributes
    public desperate(int i) {
        attractiveness = rand.nextInt(50) + 45;
        main_budget = rand.nextInt(2000) + 3000;
        intelligence = rand.nextInt(30) +65;
        relation_status = "single";
        id=i;
        name="girl_" + id;
    }
    
    /**
     * This function calculates happiness of girl
     */
    public void happ() {
    }
}
