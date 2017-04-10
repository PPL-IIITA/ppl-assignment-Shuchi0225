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
 * atleastone class extending GiftSelector
 * @see GiftSelector
 * this type of basket includes atleast one gift of each type
 */
public class atleastone extends GiftSelector{

    /**
     * given() : checks whether the current gift has already been given by the boy or not
     * @param arr array of id of gifts already given
     * @param n id of current gift
     * @return 1 if gift is given and 0 if not given
     */
    int given(int arr[], int n) {
        for(int i=0; i<arr.length; i++) {
            if(arr[i]==n)
                return 1;
        }
        return 0;
    }
    
    @Override
    /**method makegiftbasket()
     * arguments
     * @param couple array of couples
     * @param gift array of gifts
     * 
     */
    public void makegiftbasket(couples[] couple, gifts[] gift) {
        //util obj=new util();
        //obj.sort(gift);                                         //sort gifts in ascending order by their price
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
                 * if girl is single then dont make basket
                 * */
                if(couple[i].girl.relation_status.equals("single"))
                continue;
                
                /**
                 * initialize all useful variables
                 * */
                boolean giftgiven=false, essgift=false, luxgift=false, utilgift=false;
                int total=0;
                int given=0;
                int budget=couple[i].boy.gf_budget;
                
                couple[i].gifts.clear();                
                /**
                 * clear couple's gift basket
                 * */
                //System.out.println(budget);
                int arr[]=new int[3];
                /**
                 * make basket in which at least one gift of each type is present
                 * start taking gifts one by one
                 */
                for(int j=0; j<gift.length; j++) {
                    
                        /**
                         * if gift type is essential which is not given yet then give it
                         * */
                        if(gift[j].getClass().getSimpleName().equals("essential") && essgift==false) {
                            
                            /**
                             * if boy's budget is less then increase it
                             * */
                            if(budget < gift[j].price) {
                                couple[i].boy.gf_budget += (gift[j].price - budget);
                                budget+=gift[j].price - budget;
                            }
                            /**
                             * add gift to basket and print it in file
                             */
                            budget=(budget)-(gift[j].price);
                            total+=gift[j].price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value + " at " + t);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value + " at " + t);
                            couple[i].gifts.add(gift[j]);               //add gift to basket
                            arr[0]=j;
                            essgift=true;
                        }
                        
                        /**
                         * if gift type is luxury which is not given yet then give it
                         * */
                        if(gift[j].getClass().getSimpleName().equals("luxury") && luxgift==false) {
                            
                            /**
                             * if boy's budget is less then increase it
                             * 
                             */
                            if(budget < gift[j].price) {
                                couple[i].boy.gf_budget += (gift[j].price - budget);
                                budget+=gift[j].price - budget;
                            }
                            /**
                             * add gift to basket and print it in file
                             */
                            budget=(budget)-(gift[j].price);
                            total+=gift[j].price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value + " at " + t);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value + " at " + t);
                            couple[i].gifts.add(gift[j]);               //add gift to basket
                            arr[1]=j;
                            luxgift=true;
                        }
                        
                        /**
                         * if gift type is utility which is not given yet then give it
                         * */
                        if(gift[j].getClass().getSimpleName().equals("utility") && utilgift==false) {
                            
                            /**
                             * if boy's budget is less then increase it
                             * */
                            if(budget < gift[j].price) {
                                couple[i].boy.gf_budget += (gift[j].price - budget);
                                budget+=gift[j].price - budget;
                            }
                            /**
                             * add gift to basket and print it in file
                             */
                            budget=(budget)-(gift[j].price);
                            total+=gift[j].price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value + " at " + t);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value + " at " + t);
                            couple[i].gifts.add(gift[j]);                   //add gift to basket
                            arr[2]=j;
                            utilgift=true;
                        }
                    }
                
                /**
                 * continue adding gifts after adding one of each type
                 */
                
                /**
                 * make basket if boy type is miser
                 */
                if(couple[i].boy.getClass().equals("miser")) {
                        
                    /**
                     * add gifts one by one starting from lowest price
                     */
                    for(int j=0; j<gift.length; j++) {
                        /**
                         * stop adding gifts if total price of gifts is greater than or equal to girl's maintenance cost
                         */
                        if(total>=couple[i].girl.main_budget)
                            break;
                        if(given(arr, j)>0)
                            continue;
                        /**
                         * check if boys budget allows any more gifting
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
                            couple[i].gifts.add(gift[j]);                   //add gift to basket
                            giftgiven=true;
                        }
                        
                    }   
                }

                /**
                 * make basket if boy type is generous
                 */
                else if(couple[i].boy.getClass().equals("generous")) {
                    /**
                     * add gifts one by one starting from lowest price
                     */
                    /**
                     * stop adding gifts when total price of gifts is just less than or equal to boy's gf_budget
                     */
                    for(int j=0; j<gift.length; j++) {
                        if(given(arr, j)>0)
                            continue;
                        if(gift[j].price<budget) {
                            /**
                             * add gift to basket and print it in file
                             */
                            budget=(budget)-(gift[j].price);
                            total+=gift[j].price;
                            t=new Timestamp(System.currentTimeMillis());
                            out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value+ " at " + t);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value+ " at " + t);
                            couple[i].gifts.add(gift[j]);                   //add gift to basket
                            giftgiven=true;
                        }
                    }  
                }

                /**
                 * make basket if boy type is geek
                 */
                else {
                      
                        /**
                         * add more gifts if girl's mainteneance budget is greater than total cost of gifts
                         */
                        if(total<=couple[i].girl.main_budget) {
                            for(int j=1; j<gift.length; j++) {
                                /**
                                 * stop adding gifts if total price of gifts is greater than or equal to girl's maintenance cost
                                 */
                                if(total>=couple[i].girl.main_budget)
                                    break;
                                if(given(arr, j)>0)
                                    continue;
                                if(gift[j].price<budget && j!=given) {
                                    /**
                             * add gift to basket and print it in file
                             */
                                    budget=(budget)-(gift[j].price);
                                    total+=gift[j].price;
                                    t=new Timestamp(System.currentTimeMillis());
                                    out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value+ " at " + t);
                                    System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].getClass().getSimpleName() + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value+ " at " + t);
                                    couple[i].gifts.add(gift[j]);                   //add gift to basket
                                }
                            }
                        }
                        //System.out.println("Now total is :" + total + "and gfbudget is:" + couple[i].girl.main_budget);
                          
                }
            }
            out.close();
        }
        catch(Exception e) {
        }
    }
    
}
