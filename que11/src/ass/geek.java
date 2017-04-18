/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import exceptions.GirlBudgetFulfilled;
import exceptions.InvalidGift;
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
 *geek class extending boys
 * @see boys
 */

public class geek extends boys{
    Random rand = new Random();
    
    //generates random values for geek boys attributes
    /**
     * constructor geek()
     * @param i id of boy
     * generates random values for geek boys attributes
     */
    public geek(int i) {
        this.attractiveness = rand.nextInt(50) + 45;
        this.gf_budget = rand.nextInt(3000) + 2000;
        this.intelligence = rand.nextInt(30) +65;
        this.attration_req = rand.nextInt(50) + 45;
        this.relation_status = "single";
        this.id=i;
        name="boy_" + id;
    }
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
                         * add the first gift
                         */
                        if(gift[0]!=null && gift[0].price<budget ) {
                            /**
                             * add gift to basket and print it in file
                             */
                            budget=(budget)-(gift[0].price);
                            total+=gift[0].price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple.boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                            System.out.println("boy_" + couple.boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                            couple.gifts.add(gift[0]);
                            giftgiven=true;
                            
                        }
                    

                        /**
                         * add one luxury gift if possible
                         */
                        try {
                            /**
                             * throw exception if girl's budget is fulfilled but
                             * gift basket continue to add gift
                             */
                            
                            for(int j=1; j<gift.length; j++) {
                                if(total>budget)
                                throw new GirlBudgetFulfilled();
                                    if(gift[j]==null)
                                        continue;
                                    
                                if(gift[j].price<budget && gift[j].getClass().getSimpleName().equals("luxury")) {
                                    /**
                                    * add gift to basket and print it in file
                                    */
                                    budget=(budget)-(gift[j].price);
                                    total+=gift[j].price;
                                    given=j;
                                    t=new Timestamp(System.currentTimeMillis());
                                    out.println("boy_" + couple.boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[j].price + " and value" + gift[j].value+ " at " + t);
                                    System.out.println("boy_" + couple.boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[j].price + " and value" + gift[j].value+ " at " + t);
                                    couple.gifts.add(gift[j]);
                                    break;
                                }
                                
                            }
                        } catch(GirlBudgetFulfilled e) {
                            e.printfirst();
                                                    Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, e);

                            //e.printStackTrace();
                        }

                        /**
                         * add more gifts if girl's mainteneance budget is greater than total cost of gifts
                         */
                        try {
                            /**
                             * throw exception if girl's budget is fulfilled but
                             * gift basket continue to add gift
                             */
                            
                            for(int j=1; j<gift.length; j++) {
                                if(gift[j]==null)
                                continue;
                                if(total>couple.girl.main_budget ||gift[j].price>budget )
                                    throw new GirlBudgetFulfilled();
                                
                                if(gift[j].price<budget && j!=given) {
                                    /**
                                    * add gift to basket and print it in file
                                    */
                                    budget=(budget)-(gift[j].price);
                                    total+=gift[j].price;
                                    t=new Timestamp(System.currentTimeMillis());
                                    out.println("boy_" + couple.boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value+ " at " + t);
                                    System.out.println("boy_" + couple.boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value+ " at " + t);
                                    couple.gifts.add(gift[j]);
                                }
                        
                            
                            }
                        } catch(GirlBudgetFulfilled e) {
                            e.printfirst();
                                                    Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, e);

                        }
                       // System.out.println("Now total is :" + total + "and gfbudget is:" + couple[i].girl.main_budget);
                        /**
                         * increase budget if no gifts have been given
                         */
                        if(giftgiven==false) {
                            try {
                                /**
                                 * throw InvalidGift exception
                                 * trying to give invalid gift
                                 */
                                if(gift[0]==null)
                                throw new InvalidGift(0);
                            couple.boy.gf_budget=gift[0].price;
                            couple.gifts.add(gift[0]);
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple.boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                            System.out.println("boy_" + couple.boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple.girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                        
                            } catch(InvalidGift e) {
                                
                            }
                            } 
    
        out.close();
        }
    
        catch(Exception e) {
        }
    }
}
