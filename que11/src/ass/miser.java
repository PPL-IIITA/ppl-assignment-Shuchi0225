/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import exceptions.GirlBudgetFulfilled;
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
 *miser class extending boys
 * @see boys
 */

public class miser extends boys{
    Random rand = new Random();
    /**
     * constructor miser()
     * @param i id of boy
     * generates random values for miser boys attributes
     */
    public miser(int i) {
        this.attractiveness = rand.nextInt(50) + 45;
        this.gf_budget = rand.nextInt(2000) + 2000;
        this.intelligence = rand.nextInt(30) +65;
        this.attration_req = rand.nextInt(50) + 45;
        this.relation_status = "single";
        this.id=i;
        name="boy_" + id;
    }
    @Override
    public void happ(){
        
    }

    @Override
    /**method makegiftbasket()
     * allocate gifts from each boy for their girl according to their type
     * arguments
     * @param couple array of couples
     * @param gift array of gifts
     * 
     */
    public void makegiftbasket(couples couple, gifts[] gift) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                   /**
                     * add gifts one by one starting from lowest price
                     */
                    //System.out.println("hiiiiiiiiiiii");
                    try {
                    for(int j=0; j<gift.length; j++) {
                        if(total>=couple.girl.main_budget)
                            throw new GirlBudgetFulfilled();
                        
                            /**
                             * throw exception if girl's budget is fulfilled but
                             * gift basket continue to add gift
                             */
                            
                        
                            if(gift[j]==null)
                                /**
                                 * throw InvalidGift exception
                                 * trying to give invalid gift
                                 */
                                continue;
                        /**
                         * check whether boy's budget allows giving gift
                         */
                            if(gift[j].price>budget)
                                throw new NoMoneyLeft();
                         {
                            /**
                             * add gift to basket and print it in file
                             */
                            budget=(budget)-(gift[j].price);
                            total+=gift[j].price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple.boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value + " at " + t);
                            System.out.println("boy_" + couple.boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value + " at " + t);
                            couple.gifts.add(gift[j]);
                            giftgiven=true;
                        }
                        
                        
                        
                        
                    }
                } catch(GirlBudgetFulfilled e) {
                    /**
                     * print exception
                     */
                    e.printfirst();
                                            Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, e);

                   // e.printStackTrace();
                }
                    catch(NoMoneyLeft e) {
                        e.printfirst();
                                                Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, e);

                    }
                        
                    /**
                     * increase budget if no gifts have been given
                     */
                    if(giftgiven==false && gift[0]!=null) {
                           
                                
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
