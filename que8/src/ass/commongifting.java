/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 *
 * @author Shuchi
 */

/**
 * commongifting class extending GiftSelector
 * @see GiftSelector
 */

public class commongifting extends GiftSelector{

    @Override
    /**method makegiftbasket()
     * arguments
     * @param couple array of couples
     * @param gift array of gifts
     * 
     *
     * allocate gifts from each boy for their girl according to their type
     */
    public void makegiftbasket(couples couple[], gifts gift[]) {
        util ob=new util();
        ob.sort(gift);                                         
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
            for(int i=0; i<couple.length; i++) {
                /**
                 * if girl is single then don't make basket for her
                 */
                if(couple[i].girl.relation_status.equals("single"))
                continue;
                
                /**
                 * initialize all useful variables
                 * */
                
                boolean giftgiven=false;
                int total=0;
                int given=0;
                int budget=couple[i].boy.gf_budget;
                
                /**
                 * clear couple's gift basket
                */
                
                couple[i].gifts.clear();
                /**
                 * make basket if boy type is miser
                 */
                if(couple[i].boy.getClass().equals("miser")) {
                    /**
                     * add gifts one by one starting from lowest price
                     */
                    for(int j=0; j<gift.length; j++) {
                        /**
                         * check whether boy's budget allows giving gift
                         */
                        if(gift[j].price<budget) {
                            /**
                             * add gift to basket and print it in file
                             */
                            budget=(budget)-(gift[j].price);
                            total+=gift[j].price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value + " at " + t);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value + " at " + t);
                            couple[i].gifts.add(gift[j]);
                            giftgiven=true;
                        }
                        /**
                         * stop adding gifts if total price of gifts is greater than or equal to girl's maintenance cost
                         */
                        if(total>=couple[i].girl.main_budget)
                            break;
                    }
                    /**
                     * increase budget if no gifts have been given
                     */
                    if(giftgiven==false) {
                        /**
                             * add gift to basket and print it in file
                             */
                        couple[i].boy.gf_budget=gift[0].price;
                        couple[i].gifts.add(gift[0]);
                        t=new Timestamp(System.currentTimeMillis());
                        out.println("boy_" + couple[i].boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                        System.out.println("boy_" + couple[i].boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);    
                    }    
                }

                /**
                 * make basket if boy type is generous
                 */
                else if(couple[i].boy.getClass().equals("generous")) {
                    /**
                    * add gifts one by one starting from lowest price
                    *stop adding gifts when total price of gifts is just less than or equal to boy's gf_budget
                    */
                    for(int j=0; j<gift.length; j++) {
                        if(gift[j].price<budget) {
                            budget=(budget)-(gift[j].price);
                            total+=gift[j].price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value+ " at " + t);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value+ " at " + t);
                            couple[i].gifts.add(gift[j]);
                            giftgiven=true;
                        }
                    }
                    /**
                    * increase budget if no gifts have been given
                    */
                    if(giftgiven==false) {
                        /**
                             * add gift to basket and print it in file
                             */
                        couple[i].boy.gf_budget=gift[0].price;
                        couple[i].gifts.add(gift[0]);
                        t=new Timestamp(System.currentTimeMillis());
                        out.println("boy_" + couple[i].boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                        System.out.println("boy_" + couple[i].boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                    }   
                }

                /**
                 * make basket if boy type is geek
                 */
                else {
                        /**
                         * add the first gift
                         */
                        if(gift[0].price<budget) {
                            /**
                             * add gift to basket and print it in file
                             */
                            budget=(budget)-(gift[0].price);
                            total+=gift[0].price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple[i].boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                            couple[i].gifts.add(gift[0]);
                            giftgiven=true;
                        }

                        /**
                         * add one luxury gift if possible
                         */
                        if(total<=budget) {
                            for(int j=0; j<gift.length; j++) {
                                if(gift[j].price<budget && gift[j].getClass().equals("luxury")) {
                                    /**
                                    * add gift to basket and print it in file
                                    */
                                    budget=(budget)-(gift[j].price);
                                    total+=gift[j].price;
                                    given=j;
                                    t=new Timestamp(System.currentTimeMillis());
                                    out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value" + gift[j].value+ " at " + t);
                                    System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value" + gift[j].value+ " at " + t);
                                    couple[i].gifts.add(gift[j]);
                                    break;
                                }
                            }
                        }

                        /**
                         * add more gifts if girl's mainteneance budget is greater than total cost of gifts
                         */
                        if(total<=couple[i].girl.main_budget) {
                            for(int j=1; j<gift.length; j++) {
                                if(gift[j].price<budget && j!=given) {
                                    /**
                                    * add gift to basket and print it in file
                                    */
                                    budget=(budget)-(gift[j].price);
                                    total+=gift[j].price;
                                    t=new Timestamp(System.currentTimeMillis());
                                    out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value+ " at " + t);
                                    System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value+ " at " + t);
                                    couple[i].gifts.add(gift[j]);
                                }
                        /**
                         * stop adding gifts if total price of gifts is greater than or equal to girl's maintenance cost
                         */
                                if(total>=couple[i].girl.main_budget)
                                    break;
                            }
                        }
                        //System.out.println("Now total is :" + total + "and gfbudget is:" + couple[i].girl.main_budget);
                        /**
                         * increase budget if no gifts have been given
                         */
                        if(giftgiven==false) {
                            couple[i].boy.gf_budget=gift[0].price;
                            couple[i].gifts.add(gift[0]);
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple[i].boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + gift[0].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value+ " at " + t);
                        
                        }   
                }
            }
            out.close();
        }
        catch(Exception e) {
        }
    }
    
    
}
