package assignment;
import java.util.Random;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shuchi
 */
public class boys {
    int id=0;
    int attractiveness;
    int gf_budget;
    int intelligence;
    int attration_req;
    String type;
    String relation_status;
    int happiness;
    int com_to_gid;
    
    public boys(int i) {
        this.attractiveness = rand.nextInt(50) + 45;
        this.gf_budget = rand.nextInt(5000) + 2000;
        this.intelligence = rand.nextInt(30) +65;
        this.attration_req = rand.nextInt(50) + 45;
        this.type = boytype();
        this.relation_status = "single";
        this.id=i;
    }
    
    Random rand = new Random();
    String boytype() {
        int i=rand.nextInt(3);
        switch(i) {
            case 0: return "miser";
            case 1: return "generous";
            case 2: return "geeks";
        } 
        return null;
    }
}


