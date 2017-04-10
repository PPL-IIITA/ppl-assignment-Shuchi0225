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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * contains useful functions
 * @author Shuchi
 */
public class util_k {
    /**
     * create comparator for girl based on attractiveness descending order
     */
    public static class GirlComparator implements Comparator<girls>{
        @Override
        public int compare(girls o1, girls o2) {
            return o1.attractiveness < o2.attractiveness ? 1 :(o1.attractiveness > o2.attractiveness ? -1 : 0); //Descending
        }
    }
    /**
     * create comparator for boy based on attractiveness descending order
     */
    public static class BoyAttr implements Comparator<boys>{
        @Override
        public int compare(boys o1, boys o2) {
            return o1.attractiveness < o2.attractiveness ? 1 :(o1.attractiveness > o2.attractiveness ? -1 : 0); //Descending
        }
    }
    /**
     * create comparator for boy based on gf budget descending order
     */
    public static class BoyRich implements Comparator<boys>{
        @Override
        public int compare(boys o1, boys o2) {
            return o1.gf_budget < o2.gf_budget ? 1 :(o1.gf_budget > o2.gf_budget ? -1 : 0); //Descending
        }
    }
    /**
     * create comparator for boy based on intelligence descending order
     */
    public static class BoyIntell implements Comparator<boys>{
        @Override
        public int compare(boys o1, boys o2) {
            return o1.intelligence < o2.intelligence ? 1 :(o1.intelligence > o2.intelligence ? -1 : 0); //Descending
        }
    }
    
    /**
     * create comparator for gift based on value descending order
     */
    public static class GiftComparator implements Comparator<gifts>{
        @Override
        public int compare(gifts o1, gifts o2) {
            return o1.value < o2.value ? 1 :(o1.value > o2.value ? -1 : 0); //Descending
        }
    }
    
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
            generic<girls> g_girls = new generic<>();
            generic<boys> g_boys = new generic<>();

            girls tempgirl[] = g_girls.genericMethod(girl, m);
            boys tempboy[] = g_boys.genericMethod(boy, m);

            for(int i=0; i<girl.length; i++) {
                /**
                * allocate bf to all girls one by one
                */
                /**
                 * sort boys according to primary criterion
                 */
                if(girl.getClass().getSimpleName().equals("choosy"))
                    Arrays.sort(boy,new BoyIntell());
                else if(girl.getClass().getSimpleName().equals("normal"))
                    Arrays.sort(boy,new BoyAttr());
                if(girl.getClass().getSimpleName().equals("desperate"))
                    Arrays.sort(boy,new BoyRich());
                int flag=0;
                m= rand.nextInt(10) + 10;
                tempboy = g_boys.genericMethod(boy, m);
                /**
                 * select m best options and allocate best bf according to secondary criterion
                 */
                Arrays.sort(tempboy,new BoyRich());
                util obj = new util();
                for(int j=0; j<tempboy.length; j++) {
                    /**
                     * check if all constraints are met
                     */
                    if(obj.isBudgetReq(girl[i],tempboy[j]) && obj.isAttrReq(girl[i],tempboy[j]) && tempboy[j].relation_status.equals("single")) {
                           /**
                     * allocate bf and update their attributes
                     */
                            girl[i].relation_status="committed";
                           boy[tempboy[j].id].relation_status="committed";
                           girl[i].com_to_bid=tempboy[j].id;
                           boy[tempboy[j].id].com_to_gid=i;
                           girl[i].t=new Timestamp(System.currentTimeMillis());
                           /**
                         * print on output stream and in file
                         */
                           writer.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid + " at " + girl[i].t);
                           System.out.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid + " at " + girl[i].t);
                           break;
                    }

                }
            }
            writer.close();
        }
        catch(Exception e) {
            
        }
    }
    
    /**method makegiftbasket()
     * arguments
     * @param couple array of couple
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
        Arrays.sort(gift,new GiftComparator());

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
