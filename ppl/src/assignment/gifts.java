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
public class gifts {
    int value;
    int price;
    String type;
    //int luxury_rat;
    //int utility_value;
    //String utilty_class;
    
    Random rand = new Random();
    String gifttype() {
        int i=rand.nextInt(3);
        switch(i) {
            case 0: return "essential";
            case 1: return "luxury";
            case 2: return "utility";
        } 
        return null;
    }
    
    int value(String type) {
        
        if(type.equals("essential"))
            return (rand.nextInt(400) + 200);
        else if(type.equals("luxury"))
            return (rand.nextInt(1000) + 1000);
        else
            return (rand.nextInt(500) + 500);
    }
    
    int price(String type) {
        if(type.equals("essential"))
            return (rand.nextInt(600) + 500);
        else if(type.equals("luxury"))
            return (rand.nextInt(2000) + 1500);
        else
            return (rand.nextInt(600) + 800);
    }
    
    public gifts() {
        this.type=gifttype();
        this.value=value(type);
        this.price=price(type);
    }
}
