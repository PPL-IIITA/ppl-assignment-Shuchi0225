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
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Shuchi
 */
public class randomk {
     /**
     * allocate() function to
     * allocate bf to all girls who can have eligible boyfriends and store them in log.txt file
     * arguments
     * @param girl array of girls
     * @param boy array of boys
     *
     */
    public void allocate(girls girl[], boys boy[]) {
        try{
            PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
            Random rand = new Random();
            int m= rand.nextInt(girl.length);
            int k = 0;
            generic<boys> g_boys = new generic<>();
            boys tempboy[] = g_boys.genericMethod(boy, m);

            for(int i=0; i<girl.length; i++) {
                /**
         * allocate bf to all girls one by one
         */
                /**
                 * sort boys according to girl's primary criterion
                 */
                if(girl.getClass().getSimpleName().equals("choosy"))
                    Arrays.sort(boy,new util_k.BoyIntell());
                else if(girl.getClass().getSimpleName().equals("normal"))
                    Arrays.sort(boy,new util_k.BoyAttr());
                if(girl.getClass().getSimpleName().equals("desperate"))
                    Arrays.sort(boy,new util_k.BoyRich());
                int flag=0;
                m= rand.nextInt(10) + 10;
                /**
                 * store best m choices in another array
                 */
                tempboy = g_boys.genericMethod(boy, m);
                Arrays.sort(tempboy,new util_k.BoyRich());
                /**
                 * choose random boy out of best m choices
                 */
                boys b=g_boys.one(tempboy);
                util obj = new util();
                
                    /**
                 * check if all constraints are met
                 */
                if(obj.isBudgetReq(girl[i],b) && obj.isAttrReq(girl[i],b) && b.relation_status.equals("single")) {
                    /**
                     * allocate bf and update their attributes
                     */       
                    girl[i].relation_status="committed";
                           boy[b.id].relation_status="committed";
                           girl[i].com_to_bid=b.id;
                           boy[b.id].com_to_gid=i;
                           girl[i].t=new Timestamp(System.currentTimeMillis());
                           /**
                            * output to file and screen
                            */
                           writer.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid + " at " + girl[i].t);
                           System.out.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid + " at " + girl[i].t);
                         
                }
            }
            writer.close();
        }
        catch(Exception e) {
            
        }
    }
    
    /**method makegiftbasket()
     * arguments
     * @param couple array of couples
     * @param gift array of gifts
     * 
     */
    /**
     * allocate gifts from each boy for their girl according to their type
     */
    public void makegiftbasket(couples couple[], gifts gift[]) {
        Timestamp t;
        Random rand = new Random();
        int m= rand.nextInt(gift.length);
        int k = 0;
        generic <gifts> generic_gifts = new generic<>();
        /**
         * sort gifts in ascending order by their value
        */
        Arrays.sort(gift,new util_k.GiftComparator());

        gifts tempgift []= generic_gifts.genericMethod(gift, m);
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
                  */
                boolean giftgiven=false;
                int total=0;
                int given=0;
                int budget=couple[i].boy.gf_budget;
                /**
                 * clear couple's gift basket
                */
                couple[i].gifts.clear();
                gifts giftt;
                /**
                 * make basket if boy type is miser
                 */
                if(couple[i].boy.getClass().equals("miser")) {
                    /**
                     * add gifts one by one starting from lowest price
                     */
                    for(int j=0; j<gift.length/2; j++) {
                        giftt=generic_gifts.one(tempgift);
                        /**
                         * check whether boy's budget allows giving gift
                         */
                        if(giftt.price<budget) {
                            /**
                             * add gift to basket and print it in file
                             */
                            budget=(budget)-(giftt.price);
                            total+=giftt.price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value + " at " + t);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value + " at " + t);
                            couple[i].gifts.add(giftt);
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
                        giftt=generic_gifts.one(tempgift);
                        couple[i].boy.gf_budget=giftt.price;
                        couple[i].gifts.add(giftt);
                        t=new Timestamp(System.currentTimeMillis());
                        out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value+ " at " + t);
                        System.out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value+ " at " + t);    
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
                    for(int j=0; j<gift.length/2; j++) {
                        giftt=generic_gifts.one(tempgift);
                        if(giftt.price<budget) {
                            budget=(budget)-(giftt.price);
                            total+=giftt.price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value+ " at " + t);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value+ " at " + t);
                            couple[i].gifts.add(giftt);
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
                        giftt=generic_gifts.one(tempgift);
                        couple[i].boy.gf_budget=giftt.price;
                        couple[i].gifts.add(giftt);
                        t=new Timestamp(System.currentTimeMillis());
                        out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value+ " at " + t);
                        System.out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value+ " at " + t);
                    }   
                }

                /**
                 * make basket if boy type is geek
                 */
                else {
                        /**
                         * add the first gift
                         */
                        giftt=generic_gifts.one(tempgift);
                        if(giftt.price<budget) {
                            /**
                             * add gift to basket and print it in file
                             */
                            budget=(budget)-(giftt.price);
                            total+=giftt.price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value+ " at " + t);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value+ " at " + t);
                            couple[i].gifts.add(giftt);
                            giftgiven=true;
                        }

                        /**
                         * add one luxury gift if possible
                         */
                        if(total<=budget) {
                            for(int j=0; j<gift.length/2; j++) {
                                giftt=generic_gifts.one(tempgift);
                                if(giftt.price<budget && giftt.getClass().equals("luxury")) {
                                    /**
                                    * add gift to basket and print it in file
                                    */
                                    budget=(budget)-(giftt.price);
                                    total+=giftt.price;
                                    given=j;
                                    t=new Timestamp(System.currentTimeMillis());
                                    out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value" + giftt.value+ " at " + t);
                                    System.out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value" + giftt.value+ " at " + t);
                                    couple[i].gifts.add(giftt);
                                    break;
                                }
                            }
                        }

                        /**
                         * add more gifts if girl's mainteneance budget is greater than total cost of gifts
                         */
                        if(total<=couple[i].girl.main_budget) {
                            for(int j=1; j<gift.length/2; j++) {
                                giftt=generic_gifts.one(tempgift);
                                if(giftt.price<budget ) {
                                    /**
                                    * add gift to basket and print it in file
                                    */
                                    budget=(budget)-(giftt.price);
                                    total+=giftt.price;
                                    t=new Timestamp(System.currentTimeMillis());
                                    out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value+ " at " + t);
                                    System.out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value+ " at " + t);
                                    couple[i].gifts.add(giftt);
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
                            couple[i].boy.gf_budget=giftt.price;
                            couple[i].gifts.add(giftt);
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value+ " at " + t);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + giftt.getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + giftt.price + " and value=" + giftt.value+ " at " + t);
                        
                        }   
                }
            }
            out.close();
        }
        catch(Exception e) {
        }
    }
}
