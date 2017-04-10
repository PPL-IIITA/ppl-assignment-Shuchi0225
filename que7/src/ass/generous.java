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
 *generous class extending boys
 * @see boys
 */

public class generous extends boys{
    Random rand = new Random();
    
    //generates random values for generous boys attributes
    /**
     * constructor generous()
     * @param i id of boy
     * generates random values for generous boys attributes
     */
    public generous(int i) {
        this.attractiveness = rand.nextInt(50) + 45;
        this.gf_budget = rand.nextInt(5000) + 2000;
        this.intelligence = rand.nextInt(30) +65;
        this.attration_req = rand.nextInt(50) + 45;
        this.relation_status = "single";
        this.id=i;
        name="boy_" + id;
    }
    public void happ(){
        
    }
}
