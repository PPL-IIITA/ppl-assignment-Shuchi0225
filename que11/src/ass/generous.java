/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import exceptions.NoMoneyLeft;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    /**method makegiftbasket()
     * allocate gifts from each boy for their girl according to their type
     * arguments
     * @param couple array of couples
     * @param gift array of gifts
     * 
     */
    public void makegiftbasket(couples couple, gifts gift[]) {
        util obj=new util();
    
         obj.sort(gift);                                         
        /**
         * sort gifts in ascending order by their price
        */
        Timestamp t;
        try{
            /**
             * open file log.txt to store gifting
             */
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)));
            
            /**
             * start making gifts for each couple one by one
             */
           
                
                /**
                 * initialize all useful variables
                 * */
                
                boolean giftgiven=false;
                int total=0;
                int given=0;
                int budget=couple.boy.gf_budget;
                
                /**
                 * clear couple's gift basket
                */
                
                couple.gifts.clear();
                /**
                 * make basket if boy type is miser
                 */
                   /** add gifts one by one starting from lowest price
                    *stop adding gifts when total price of gifts is just less than or equal to boy's gf_budget
                    */
                    try {
                    for(int j=0; j<gift.length; j++) {
                        if(gift[j]==null)
                               continue;
                        if(gift[j].price>budget)
                                throw new NoMoneyLeft();
                        
                            
                            
                        
                            
                            budget=(budget)-(gift[j].price);
                            total+=gift[j].price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple.boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value+ " at " + t);
                            System.out.println("boy_" + couple.boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value+ " at " + t);
                            couple.gifts.add(gift[j]);
                            giftgiven=true;
                        
                        
                    }
                    }
                        catch(NoMoneyLeft e) {
                            Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, e);

                         e.printfirst();   
                        }
                    /**
                    * increase budget if no gifts have been given
                    */
                    if(giftgiven==false) {
                        
                            if(gift[0]!=null)
                                /**
                                 * throw InvalidGift exception
                                 * trying to give invalid gift
                                 */
                               
                        /**
                             * add gift to basket and print it in file
                             */
                        couple.boy.gf_budget=gift[0].price;
                        couple.gifts.add(gift[0]);
                        t=new Timestamp(System.currentTimeMillis());
                        out.println("boy_" + couple.boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                        System.out.println("boy_" + couple.boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                        
                    }
    
        out.close();
        }
    
        catch(Exception e) {
        }
    }
}
