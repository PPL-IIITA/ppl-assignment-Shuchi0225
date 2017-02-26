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
public class girls {
    int id=0;
    int attractiveness;
    int main_budget;
    int intelligence;
    String type;
    String relation_status;
    int happiness;
    int com_to_bid;
    
    Random rand = new Random();
    public girls(int i) {
        this.attractiveness = rand.nextInt(50) + 45;
        this.main_budget = rand.nextInt(2000) + 2000;
        this.intelligence = rand.nextInt(30) +65;
        this.type = girltype();
        this.relation_status = "single";
        this.id=i;
    }
    
    String girltype() {
        int i=rand.nextInt(3);
        switch(i) {
            case 0: return "choosy";
            case 1: return "normal";
            case 2: return "desperate";
        } 
        return null;
    }
}
