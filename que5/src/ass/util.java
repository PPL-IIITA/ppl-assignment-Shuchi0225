/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import ass.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Random;

/**
 *
 * @author Shuchi
 */

/**
 * util class 
 * contains all useful method
 * 
 */

public class util {
    /**
     * type() method
     * randomly creates different types of boys and girls
     * @param girl girl array
     * @param boy boy array
     * 
     */
    public void type(girls girl[], boys boy[]) {
        Random rand = new Random();
        for(int i=0; i<girl.length; i++) {
            /**
             * generate random number from 0 to 2
             * create choosy if 0
             * normal if 1
             * desperate if 2
             * type of girl
             */
            int a=rand.nextInt(3);
            switch(a) {
                case 0: girl[i]=new choosy(i);
                        break;
                case 1: girl[i]=new normal(i);
                        break;
                case 2: girl[i]=new desperate(i);
                        break;
            } 
        }
        for(int i=0; i<boy.length; i++) {
            /**
             * generate random number from 0 to 2
             * create miser if 0
             * generous if 1
             * geek if 2
             * type of boy
             */
            int a=rand.nextInt(3);
            switch(a) {
                case 0: boy[i]=new miser(i);
                    break;
                case 1: boy[i]=new generous(i);
                    break;
                case 2: boy[i]=new geek(i);
                    break;
            } 
        }
        /**
         * store the details of boys and girls created in file
         */
        store_b_and_g(boy,girl);
    }
    
    /**
     * store_b_and_g
     * @param boy array of boys
     * @param girl array of girls
     * function to store boy and girl details in utility.csv
     */
    public void store_b_and_g(boys boy[], girls girl[]) {
        PrintWriter pw;
        try {
            /**
             * creates new file
             */
            pw = new PrintWriter(new File("boy.csv"));
        
        /**
         * give column headings to store boys data
         */
        StringBuilder sb = new StringBuilder();
        sb.append("boy_id" + ',' + "type" + "Atrractiveness" + ',' + "gf_budget" + ',' + "Intelligence" + ',' + "Required_attr" + '\n');
        
        /**
         * give boys values to each columns
         */
        for(int i=0; i<boy.length; i++) {
            String s="boy_" +i;
            sb.append(s + ',' + boy[i].getClass().getSimpleName() + ',' + boy[i].attractiveness + ',' + boy[i].gf_budget + ',' + boy[i].intelligence + ',' + boy[i].attration_req + '\n');
        }
        
        /**
         * write boy's details to file
         */
        pw.write(sb.toString());
        pw.close();
        
        pw = new PrintWriter(new File("girl.csv"));
        StringBuilder sb1 = new StringBuilder();
        
        /**
         * give column headings to store girls data
         */
        sb1.append('\n');
        sb1.append("girl_id");
        sb1.append(',');
        sb1.append("type");
        sb1.append(',');
        sb1.append("Atrractiveness");
        sb1.append(',');
        sb1.append("Maintenance_budget");
        sb1.append(',');
        sb1.append("Intelligence");
        sb1.append('\n');
        /**
        *give girls values to each columns
        */
        for(int i=0; i<girl.length; i++) {
            String s="girl_" +i;
            sb1.append(s);
            sb1.append(',');
            sb1.append(girl[i].getClass().getSimpleName());
            sb1.append(',');
            sb1.append(girl[i].attractiveness);
            sb1.append(',');
            sb1.append(girl[i].main_budget);
            sb1.append(',');
            sb1.append(girl[i].intelligence);
            sb1.append('\n');
        }
        /**
         * write everything to file
         */
        pw.write(sb1.toString());
        pw.close();
    }
     catch (FileNotFoundException ex) {
            //Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * allocate_bf() function to
     * allocate bf to all girls who can have eligible boyfriends and store them in log.txt file
     * arguments
     * @param girl array of girls
     * @param boy array of boys
     * 
     */
    public void allocate_bf(girls girl[],boys boy[]) {
        int j=0;
        /**
         * allocate bf to all girls one by one
         */
        for(int i=0; i<girl.length; i++) {
            int flag=0;
            for(; ; ) {
                /**
                 * check if all constraints are met
                 */
                if(isBudgetReq(girl[i],boy[j]) && isAttrReq(girl[i],boy[j]) && boy[j].relation_status.equals("single")) {
                    /**
                     * allocate bf and update their attributes
                     */
                       girl[i].relation_status="committed";
                       boy[j].relation_status="committed";
                       girl[i].com_to_bid=j;
                       boy[j].com_to_gid=i;
                       girl[i].t=new Timestamp(System.currentTimeMillis());
                       break;
                }
                else j++;
                if(j<boy.length) continue;
                if(j>=boy.length && flag==0) {
                    j=0;
                    flag=1;
                }
                else break;
            }
            if(j>=boy.length) {
                    j=0;
            }
        }
    }
    
    /*check whether girl's maintenance cost is more than boy's budget.*/
    /**
     * isBudgetReq function
     * check whether girl's maintenance cost is more than boy's budget
     * @param girl array of girls
     * @param boy array of boys
     * @return true if girl's maintenance budget is less than boy's gf budget otherwise false
     */
    public boolean isBudgetReq(girls girl, boys boy) {
        if(girl.main_budget > boy.gf_budget)
            return false;
        return true;
    }
    
    /*check whether boy's attraction requirement meets girl's attraction*/
    /**
     * isAttrReq
     * check whether boy's attraction requirement meets girl's attraction
     * @param girl array of girls
     * @param boy array of boys
     * @return true if girl's attractiveness is more than boy's required attractiveness otherwise false
     */
    public boolean isAttrReq(girls girl, boys boy) {
        if(girl.attractiveness < boy.attration_req)
            return false;
        return true;
    }
    
    
    /**
     * print_bf function to 
     * store and print commitments in log.txt file
     * @param girl array of girls
     * 
     */
    public void print_bf(girls girl[]) {
        try{
            PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
            for(int i=0; i<girl.length; i++) {
                /**
                 * check whether girl is committed
                 */
                    if(girl[i].relation_status.equals("committed")) {
                        /**
                         * print on output stream and in file
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
    
    /*store and print committments in log.txt file*/
    public void store_bf(girls girl[]) {
        try{
            PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
            for(int i=0; i<girl.length; i++) {
                    if(girl[i].relation_status.equals("committed")) {
                        writer.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid + " at " + girl[i].t);
                        //System.out.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid);
                    }
            }
            writer.close();
        }
        catch(Exception e) {
            
        }
    }
    
    
    /**
     * initializegifts function
     * give random values to attributes of gifts 
     * @param gift array of gifts
     * 
     */
    public void initializegifts(gifts gift[]) {
        int i=0;
        Random rand = new Random();
        for( ; i<gift.length; i++) {
            /**
             * generate random number from 0 to 2
             * create essential if 0
             * luxury if 1
             * utility if 2
             * type of gift
             */
            int a=rand.nextInt(3);
            switch(a) {
                case 0: gift[i]=new essential();
                        gift[i].id=i;
                        break;
                case 1: gift[i]=new luxury();
                        gift[i].id=i;
                        break;
                case 2: gift[i]=new utility();
                        gift[i].id=i;
                        break;
            } 
        }
        store_gifts(gift);
    }
    
    
    /**
     * store_gifts function
     * store gifts and their attribute in gift.csv file
     * @param gift array of gifts
     */
    public void store_gifts(gifts gift[]) {
        PrintWriter pw;
        try {
            /**
             * creates new file gift.csv
             */
            pw = new PrintWriter(new File("gift.csv"));
        
            /**
             * give column headings to store gifts
             */
            StringBuilder sb = new StringBuilder();

            /**
             * give column names
             */
            sb.append("Type");
            sb.append(',');
            sb.append("Price");
            sb.append(',');
            sb.append("Value");
            sb.append('\n');
            
            /**
             * append gift values
             */
            for(int i=0; i<gift.length; i++) {
                sb.append(gift[i].getClass().getSimpleName());
                sb.append(',');
                sb.append(gift[i].price);
                sb.append(',');
                sb.append(gift[i].value);
                sb.append('\n');
            }
            
            /**
             * write the string to file and close it
             */
            pw.write(sb.toString());
            pw.close();
        }
        catch(Exception e) {

        }
    }
    
    /**
     * init_couples function to
     * store current couples in the array
     * @param girl array of girls
     * @param boy array of boys
     * @param couple array of couples
     */
    public void init_couples(girls girl[], boys boy[], couples couple[]) {
        for(int i=0; i<girl.length; i++) {
            couple[i]=new couples(girl[i], boy);
        }
    }
    
    /**method makegiftbasket()
     * arguments
     * @param couple array of couples
     * @param gift array of gifts
     * @return nothing
     */
    /**
     * allocate gifts from each boy for their girl according to their type
     */
    public void makegiftbasket(couples couple[], gifts gift[]) {
        sort(gift);                                         
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
    
    /**
     * sort function to sort gifts in ascending order by their price
     * @param gift array of gifts
     * 
     */
    public void sort(gifts gift[]) {
        int i=0;
        for(i=0; i<gift.length-1; i++) {
            for(int j=i+1; j<gift.length; j++ ) {
                if(gift[i].price > gift[j].price) {
                    gifts tempg;
                    tempg = gift[i];
                    gift[i] = gift[j];
                    gift[j] =tempg;
                }
            }
        }
    }
    
    /**
     * gethappiness function to allocate happinessness to committed boys and girls individually and as couple 
     * @param couple array of couples
     * 
     */
    public void gethappiness(couples couple[]) {
        double girl_h=0, boy_h=0, happ=0;
        /**
         * allocate happiness to girls
         */
        for(int i=0; i<couple.length; i++) {
            /**
             * if girl's @param relation_status is single then don't allocate happiness
             */
            if(couple[i].girl.relation_status.equals("single")) {
                //System.out.println("girl_" + couple[i].girl.id + "is single");
                continue;
            }
            //System.out.println(couple[i].girl.getClass().getSimpleName());
            /**
             * allocate happiness to different types of girls according to their criterion
             */
            if (couple[i].girl.getClass().getSimpleName().equals("choosy")) {
                int value=0;
                for(int j=0; j<couple[i].gifts.size(); j++) {
                    if(couple[i].gifts.get(j).getClass().getSimpleName().equals("luxury"))
                        value+=couple[i].gifts.get(j).value;
                }
                girl_h=Math.log(couple[i].girl.main_budget-couple[i].total_price) + value;
            }
            else if (couple[i].girl.getClass().getSimpleName().equals("normal"))
                girl_h=(couple[i].girl.main_budget-couple[i].total_price) + couple[i].total_value;
            else if (couple[i].girl.getClass().getSimpleName().equals("desperate"))
                girl_h=Math.getExponent(couple[i].girl.main_budget-couple[i].total_price) ;
            couple[i].girl.happiness=(int)girl_h;
            //System.out.println(couple[i].girl.happiness);
        }
        
        /**
         * allocate happiness to boys
         */
        
        /**
         * check whether boy is committed or not and assign happiness to boys according to their types
         */
        for(int i=0; i<couple.length; i++) {
            if(couple[i].girl.relation_status.equals("single"))
                continue;
            if (couple[i].boy.getClass().equals("miser")) {
                boy_h=(couple[i].boy.gf_budget-couple[i].total_price);
            }
            else if (couple[i].boy.getClass().equals("geeks"))
                boy_h=(couple[i].girl.intelligence) ;
            else 
                boy_h=(couple[i].girl.happiness) ;
            couple[i].boy.happiness=(int)boy_h;
        }
        
        /**
         * assign happiness to couples as sum of girl's and boy's happiness
         */
        for(int i=0; i<couple.length; i++) {
            if(couple[i].girl.relation_status.equals("single"))
                continue;
            couple[i].happiness=couple[i].boy.happiness + couple[i].girl.happiness;
            //System.out.println(couple[i].happiness);
        }
    }
    
    
    /**
     * getcompatibility function to
     * calculate compatibility of couples
     * @param couple array of couples
     * 
     */
    public void getcompatibility(couples couple[]) {
        for(int i=0; i<couple.length; i++) {
            if(couple[i].girl.relation_status.equals("single"))
                continue;
            int attr=couple[i].girl.attractiveness - couple[i].boy.attractiveness;
            int intell=couple[i].girl.intelligence - couple[i].boy.intelligence;
            couple[i].compatibility=(couple[i].boy.gf_budget- couple[i].girl.main_budget)+Math.abs(attr)+Math.abs(intell);
        }
    }
    
   /**
     * khappiest function to print happiest k couples
     * @param couple array of couples
     * @param k number of happiest couples to print
     */
    public void khappiest(couples couple[], int k) {
        if(k < 0 || k > couple.length){
            return;
        } 
        /**
         * sort a duplicate array of couples by happiness
         */
        couples[] temp = new couples[couple.length]; 
        for(int i = 0; i < couple.length; i++){
            
            temp[i] = couple[i];
        }  
        int f=1;
        couples temp_c = new couples();
        for(int i = 0; i < k && i <temp.length; i++){ 
            if(temp[i].girl.relation_status.equals("single"))
                continue;
            for(int j = i+1; j < temp.length; j++){  
                if(temp[j].girl.relation_status.equals("single"))
                    continue;
                if(temp[i].happiness < temp[j].happiness){
                    temp_c = temp[i];
                    temp[i] = temp[j];
                    temp[j] = temp_c;
                }
            } 
            /**
             * print ith happiest couple
             */
            System.out.println(f++ + " happiest couple is girl_" + temp[i].girl.id + " and boy_" + temp[i].boy.id);
        }
    }
    
    
    /**
     * kcompatible function to print compatible k couples
     * @param couple array of couples
     * @param k number of compatible couples to print
     */
    public void kcompatible(couples couple[], int k) {
        if(k < 0 || k > couple.length){
            return;
        } 
        /**
         * assign compatibility to couples
         */
        getcompatibility(couple);
        /**
         * sort a duplicate array of couples by compatibility
         */
        couples[] temp = new couples[couple.length]; 
        for(int i = 0; i < couple.length; i++){
           
            temp[i] = couple[i];
        }  
        couples temp_c = new couples();
        int f=1;
        for(int i = 0; i < k  && i <temp.length; i++){  
            if(temp[i].girl.relation_status.equals("single"))
                continue;
            for(int j = i; j < temp.length; j++){   
                if(temp[j].girl.relation_status.equals("single"))
                continue;
                if(temp[i].compatibility < temp[j].compatibility){
                    temp_c = temp[i];
                    temp[i] = temp[j];
                    temp[j] = temp_c;
                }
            } 
            /**
             * print ith compatible couple
             */
            System.out.println(f++ + " compatible couple is girl_" + temp[i].girl.id + " and boy_" + temp[i].boy.id);
        }
    }


    /**
     * lkhappiest function to print least happiest k couples
     * @param couple array of couples
     * @param k number of least happiest couples to print
     */
    public void lkhappiest(couples couple[], int k) {
        if(k < 0 || k > couple.length){
            return;
        } 
        Timestamp time;
        /**
         * assign happiness to couples
         */
        gethappiness(couple); 
        couples[] temp = new couples[couple.length];
        /**
         * sort a duplicate array of couples by happiness in ascending order
         */
        for(int i = 0; i < couple.length; i++){
            
            temp[i] = couple[i];
        }
        int ind=0;
        try {
            /**
             * open file log.txt to store break-ups
             */
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)));
            couples temp_c;
            for(int i = 0; i<k; i++){
                if(temp[i].girl.relation_status.equals("single"))
                continue;
                ind=i;
                /**
                 * find least ith happiest couple
                 */
                for(int j = i+1; j < temp.length; j++){ 
                    if(temp[j].girl.relation_status.equals("single"))
                        continue;
                    if(temp[i].happiness > temp[j].happiness && temp[j].girl.relation_status.equals("committed")){
                        temp_c = temp[i];
                        temp[i] = temp[j];
                        temp[j] = temp_c;
                        ind=j;
                    }
                }
                //System.out.println("happ " + temp[i].happiness + " " + i);
                /**
                 * perform their break-up and change attributes accordingly
                 */
                time=new Timestamp(System.currentTimeMillis());
                couple[ind].girl.relation_status="single";
                couple[temp[i].girl.id].boy.relation_status="single";
                //couple[ind].girl.happiness=0;
                couple[ind].girl.ex_bf=couple[ind].girl.com_to_bid;
                couple[temp[i].girl.id].boy.happiness=0;
                couple[temp[i].girl.id].gifts.clear();
                //couple[temp[i].girl.id].boy=null;
                //couple[ind].happiness=0;
                /**
                 * output to screen and store the break-up in log file
                 */
                System.out.println("girl_" + couple[temp[i].girl.id].girl.id + " and boy_" + couple[temp[i].girl.id].boy.id + " broke up at " + time);
                out.println("girl_" + couple[temp[i].girl.id].girl.id + " and boy_" + couple[temp[i].girl.id].boy.id + " broke up at " + time);
                //System.out.println(i+1 + " happiest couple is girl_" + temp[i].girl.id + " and boy_" + temp[i].boy.id);
                //System.out.println("k=" + k + "i=" + i);
            }
            out.close();
        } catch(Exception e) {
        }
    }
    
  
    /**
     * allocate_new_bf function to allocate bf to all girls who can have eligible boyfriends
     * @param girl array of girls
     * @param boy array of boys
     *
     */
    public void allocate_new_bf(girls girl[],boys boy[]) {
        int j=0;
        try{
            /**
             * open file log.txt to store commitments
             */
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)));
            for(int i=0; i<girl.length; i++) {
                /**
                 * if girl is committed then don't allocate bf
                 */
                if(girl[i].relation_status=="committed")
                    continue;
                int flag=0;
                for(; ; ) {
                    /**
                     * check if all constraints are met
                     */
                    if(isBudgetReq(girl[i],boy[j]) && isAttrReq(girl[i],boy[j]) && boy[j].relation_status.equals("single") && girl[i].ex_bf!=boy[j].id) {
                           /**
                            * allocate bf and change attributes accordingly
                            */
                           girl[i].relation_status="committed";
                           boy[j].relation_status="committed";
                           girl[i].com_to_bid=j;
                           boy[j].com_to_gid=i;
                           girl[i].t=new Timestamp(System.currentTimeMillis());
                           /**
                            * output to screen and store the break-up in log file
                            */
                           out.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid + " at " + girl[i].t);
                           System.out.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid + " at " + girl[i].t);
                           break;
                    }
                    else j++;
                    if(j<boy.length) continue;
                    if(j>=boy.length && flag==0) {
                        j=0;
                        flag=1;
                    }
                    else break;
                }
                if(j>=boy.length) {
                        j=0;
                }
            }
            out.close();
        } catch(Exception e) {

        }
    }
    
    /**
     * sortgirls function to sort girls by their maintenance budget in ascending order
     
     */
    public void sortgirls(girls girl[]) {
        girls temp;
        for(int i=0; i<girl.length-1; i++) {
            for(int j=i+1; j<girl.length; j++) {
                if(girl[i].main_budget>girl[j].main_budget) {
                    temp=girl[i];
                    girl[i]=girl[j];
                    girl[j]=temp;
                }
            }
        }
    }
    
    /**
     * sortboys function to sort girls by their attractiveness in descending order
     * @param boy array of boys
     
     */
    public void sortboys(boys boy[]) {
        boys temp;
        for(int i=0; i<boy.length-1; i++) {
            for(int j=i+1; j<boy.length; j++) {
                if(boy[i].attractiveness<boy[j].attractiveness) {
                    temp=boy[i];
                    boy[i]=boy[j];
                    boy[j]=temp;
                }
            }
        }
    }
    
    /**
     * all function to allocate bf where girl and boy chooses one by one
     * @param boy array of boys
     * @param girl array of girls
     */
    public void all(boys boy[], girls girl[]) {
        int j=0, i=0, a=0, flag=0;
        try{
            /**
             * open file log.txt to store commitments
             */
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)));
            for(int k=0; k<girl.length; ) {
                    /**
                     * if girl is committed then don't allocate bf
                     */
                    for(i=0; i<girl.length ; ) {
                        if(girl[i].relation_status.equals("committed")) {
                            i++;
                            continue;
                        }
                        /**
                         * first girl chooses a boy
                         */
                        flag=0;
                        int found=0;
                        for(j=0;j<boy.length;j++) {
                        /**
                         * check if all constraints are met
                         */
                            if(isBudgetReq(girl[i],boy[j]) && isAttrReq(girl[i],boy[j]) && boy[j].relation_status.equals("single")) {
                                   /**
                                    * allocate bf and change attributes accordingly
                                    */
                                   girl[i].relation_status="committed";
                                   boy[j].relation_status="committed";
                                   girl[i].com_to_bid=j;
                                   boy[j].com_to_gid=i;
                                   girl[i].t=new Timestamp(System.currentTimeMillis());
                                   /**
                                    * output to screen and store the break-up in log file
                                    */
                                   out.println("girl_" + girl[i].id + " got committed to " + "boy_" + girl[i].com_to_bid + " at " + girl[i].t);
                                   System.out.println(k+" girl_" + girl[i].id + " got committed to " + "boy_" + girl[i].com_to_bid + " at " + girl[i].t);
                                   i++;
                                   found=1;
                                   break;
                            }
                        }
                        if(found==1)
                            break;
                        i++;
                    }
                    k++;
                    //System.out.println(k + " " + girl.length);
                    if(k==girl.length) {
                        //System.out.println("bye");
                        break;
                    }
                    /**
                     * one boy chooses girl for him
                     */
                    for(a=0; a<boy.length; a++) {
                        //System.out.println("Inside boys");
                        flag=0;
                        if(boy[a].relation_status.equals("committed"))
                            continue;
                        /**
                         * if boy is committed then give chance to another boy
                         */
                        //System.out.println("Inside boys");
                        j=0;
                        int index=0;
                        girls g1=null;
                        /**
                         * check if all constraints are met
                         */
                        while(j<girl.length && girl[j].main_budget<=boy[a].gf_budget ) {
                            if(!isAttrReq(girl[j],boy[a])) {
                                j++;
                                continue;
                            }
                            if(girl[j].relation_status.equals("single") ) {
                                if(g1==null) {
                                    index=j;
                                    g1=girl[j];
                                }
                                else if(g1.attractiveness<girl[j].attractiveness) {
                                    index=j;
                                    g1=girl[j];
                                    //System.out.println("found again ");
                                }
                                //System.out.println("found and id="+ girl[j].id + "and flag=" + flag);
                                flag=1;
                                //System.out.println("finally found annd flag=" + flag);
                            }
                            j++;
                            //System.out.println("j=" + j + "and flag=" + flag);
                        }
                        //System.out.println("hellloooo");
                        if(flag==1) {
                            //System.out.println("Committed");
                            /**
                             * allocate gf and change attributes accordingly
                            */
                            girl[index].relation_status="committed";
                            boy[a].relation_status="committed";
                            girl[index].t=new Timestamp(System.currentTimeMillis());
                            /*girl[index].com_to_bid=j;
                            boy[a].com_to_gid=i;*/
                            girl[index].t=new Timestamp(System.currentTimeMillis());
                            out.println("girl_" + girl[index].id + " got committed to " + "boy_" + a + " at " + girl[index].t);
                            /**
                            * output to screen and store the break-up in log file
                            */
                            System.out.println("girl_" + girl[index].id + " got committed to " + "boy_" + a + " at " + girl[index].t);
                            break;
                        }
                        
                    }
                    k++;
                }
            out.close();
        } catch(Exception e) {

        }
    }
    
    
    
}
