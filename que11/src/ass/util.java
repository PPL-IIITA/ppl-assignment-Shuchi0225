/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import ass.*;
import exceptions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
        for(int i=0; i<=girl.length; i++) {
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
        }
        /** catch exception ArrayIndexOutOfBoundsException if trying to create object of a girl type
         * when no girl exists at that index
         */
        catch(ArrayIndexOutOfBoundsException e) {
            
            System.out.println("Exception found : " + e);
            //System.out.println("Reason : No more girls found");
            //e.printStackTrace();
        }
        try {
        for(int i=0; i<=boy.length; i++) {
            /**
             * generate random number from 0 to 2
             * create miser if 0
             * generous if 1
             * geek if 2
             * type of boy
             */
            try {
            int a=rand.nextInt(3);
            switch(a) {
                case 0: boy[i]=new miser(i);
                    break;
                case 1: boy[i]=new generous(i);
                    break;
                case 2: boy[i]=new geek(i);
                    break;
                /**
                 * throw NoBoyTypeFound exception if generated random no
                 * is greater than 2 and thus we have no boy type
                 */
                default : throw new NoBoyTypeFound(i);
            } 
            } catch(NoBoyTypeFound e) {
                e.printfirst();
                //System.out.println( e);
                //e.printStackTrace();
            }
        }
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Exception found : " + e);
            //System.out.println("Reason : No more boys found");
        }
        /**
         * store the details of boys and girls created in file
         */
        store_b_and_g(boy,girl);
    }
    
    /**
     * store_b_and_g
     * function to store boy and girl details in utility.csv
     * @param boy array of boys
     * @param girl array of girls
     * 
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
            try {
            if(boy[i]==null) {
                /**throw exception if trying to store details
                 * of boys when it does not exist
                 */
                throw new InvalidBoyDetail(i);
                //continue;
            }
            String s="boy_" +i;
            sb.append(s + ',' + boy[i].getClass().getSimpleName() + ',' + boy[i].attractiveness + ',' + boy[i].gf_budget + ',' + boy[i].intelligence + ',' + boy[i].attration_req + '\n');
            } catch (InvalidBoyDetail e) {
                /**
                 * print InvalidBoyDetail exception
                 */
            e.printfirst();
            //e.printStackTrace();
        }
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
        /**
         * catch exception if file is not found
         */
     catch (FileNotFoundException ex) {
            Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, ex);
            //Logger.getLogger(q11_main.class.getName()).log(Level.SEVERE, null, ee);
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
            int found=0;
            try {
            for(; ; ) {
                /**
                 * check if all constraints are met
                 */
                try {
                    if(boy[j]==null)
                        throw new InvalidBoyDetail(j);
                    /**
                     * throw exception if girl trying to commit to a boy
                     * whose details does not exist
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
                       System.out.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid + " at " + girl[i].t);
                    
                       found=1;
                       break;
                }
                else j++;
                
                
                if(j<boy.length) continue;
                if(j>=boy.length && flag==0) {
                    j=0;
                    flag=1;
                }
                else break;
            
            if(j>=boy.length) {
                    j=0;
            }
            }
                catch(InvalidBoyDetail e) {
                    
                }
            if(found==0) {
                /**
                 * if no eligible boyfriend is available but girl is still trying to find one
                 * then throw NoEligibleBfFoundException exception
                 */
                throw new NoEligibleBfFoundException(i);
                
            }
        } 
        
    }
            catch(NoEligibleBfFoundException e) {
            e.printfirst();
            Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, e);
            //e.printStackTrace();
            
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
    public void print_bf(girls girl[])  {
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
            e.printStackTrace();
        }
    }
    
    /*store and print committments in log.txt file*/
    public void store_bf(girls girl[]) {
        try{
            PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
            for(int i=0; i<girl.length; i++) {
                
                    try {
                        if(girl[i].relation_status.equals("single"))
                            /**
                             * if girl is not committed but still trying to store her
                             * commitment details in file then throw GirlNotCommitted exception
                             */
                            throw new GirlNotCommitted(i);
                        writer.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid + " at " + girl[i].t);
                        //System.out.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid);
                    } catch(GirlNotCommitted e) {
                        /**
                         * print GirlNotCommitted exception
                         */
                        //e.printfirst();
                        //Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, e);

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
            try {
            int a=rand.nextInt(4);
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
                /**
                 * if generated no is greater than no of gift type
                 * then generate NoGiftTypeFound exception
                 */
                default : throw new NoGiftTypeFound(i);
            } 
        } catch (NoGiftTypeFound e) {
            /**
             * print generated NoGiftTypeFound exception
             */
            e.printfirst();
            Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, e);

        }
            /**
             * store generated gifts
             */
        store_gifts(gift);
    }
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
                try {
                    if(gift[i]==null)
                        /**
                         * throw exception if generated gift was invalid
                         * and thus can't be stored in file
                         */
                        throw new InvalidGift(i);
                sb.append(gift[i].getClass().getSimpleName());
                sb.append(',');
                sb.append(gift[i].price);
                sb.append(',');
                sb.append(gift[i].value);
                sb.append('\n');
                } catch(InvalidGift e) {
                    //e.printfirst();
                    //e.printStackTrace();
                }
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
     * allocate gifts from each boy for their girl according to their type
     * arguments
     * @param couple array of couples
     * @param gift array of gifts
     * 
     */
    
    public void makegiftbasket(couples couple[], gifts gift[]) {
        for(int i=0; i<couple.length; i++) {
            if(couple[i].girl.relation_status.equals("single"))
                continue;
        
        if(couple[i].boy.getClass().getSimpleName().equals("generous"))
           couple[i].boy.makegiftbasket(couple[i], gift);
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
            if(gift[i]==null)
                                continue;
            for(int j=i+1; j<gift.length; j++ ) {
                
                    if(gift[j]==null)
                                continue;
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
            try {
            /**
             * if girl's @param relation_status is single then don't allocate happiness
             */
            if(couple[i].girl.relation_status.equals("single")) {
                //System.out.println("girl_" + couple[i].girl.id + "is single");
                throw new NoCoupleFoundAtIndex();
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
        } catch(NoCoupleFoundAtIndex e) {
           // e.printfirst();
            //e.printStackTrace();
                                    Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, e);

        }
        }
        
        /**
         * allocate happiness to boys
         */
        
        /**
         * check whether boy is committed or not and assign happiness to boys according to their types
         */
        for(int i=0; i<couple.length; i++) {
            try {
            if(couple[i].girl.relation_status.equals("single"))
                throw new NoCoupleFoundAtIndex();
            if (couple[i].boy.getClass().equals("miser")) {
                boy_h=(couple[i].boy.gf_budget-couple[i].total_price);
            }
            else if (couple[i].boy.getClass().equals("geeks"))
                boy_h=(couple[i].girl.intelligence) ;
            else 
                boy_h=(couple[i].girl.happiness) ;
            couple[i].boy.happiness=(int)boy_h;
            } catch(NoCoupleFoundAtIndex e) {
             //e.printfirst();   
            }
        }
        
        /**
         * assign happiness to couples as sum of girl's and boy's happiness
         */
        for(int i=0; i<couple.length; i++) {
            try {
            if(couple[i].girl.relation_status.equals("single"))
                throw new NoCoupleFoundAtIndex();
            couple[i].happiness=couple[i].boy.happiness + couple[i].girl.happiness;
            //System.out.println(couple[i].happiness);
            } catch(NoCoupleFoundAtIndex e) {
                                        Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, e);

                e.printfirst(i);
            }
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
            try {
            if(couple[i].girl.relation_status.equals("single"))
                throw new NoCoupleFoundAtIndex();
            int attr=couple[i].girl.attractiveness - couple[i].boy.attractiveness;
            int intell=couple[i].girl.intelligence - couple[i].boy.intelligence;
            couple[i].compatibility=(couple[i].boy.gf_budget- couple[i].girl.main_budget)+Math.abs(attr)+Math.abs(intell);
            } catch(NoCoupleFoundAtIndex e) {
                
            }
        }
    }
    
    /**
     * khappiest function to print happiest k couples
     * @param couple array of couples
     * @param k number of happiest couples to print
     */
    public void khappiest(couples couple[], int k) {
        try {
        if(k < 0 && k>couple.length){
            throw new InvalidCoupleNumber();
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
        for(int i = 0; i < k ; i++){ 
            try {
            if(temp[i].girl.relation_status.equals("single"))
                throw new NoCoupleFoundAtIndex();
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
            } catch(NoCoupleFoundAtIndex e) {
                
            }
        }
    } catch(InvalidCoupleNumber e) {
        
    }
    }
    
    
    /**
     * kcompatible function to print compatible k couples
     * @param couple array of couples
     * @param k number of compatible couples to print
     */
    public void kcompatible(couples couple[], int k) {
        try {
        if(k < 0 || k > couple.length){
            throw new InvalidCoupleNumber();
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
            try{
            if(temp[i].girl.relation_status.equals("single"))
                throw new NoCoupleFoundAtIndex();
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
            } catch(NoCoupleFoundAtIndex e) {
                
            }
        }
    } catch(InvalidCoupleNumber e) {
        
    }
    }


    /**
     * lkhappiest function to print least happiest k couples
     * @param couple array of couples
     * @param k number of least happiest couples to print
     */
    public void lkhappiest(couples couple[], int k) {
        try {
        if(k < 0 || k > couple.length){
            throw new NoCoupleFoundAtIndex();
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
                try {
                if(temp[i].girl.relation_status.equals("single"))
                throw new GirlNotCommitted(i);
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
            } catch(GirlNotCommitted e) {
                
            } }
            out.close();
        } catch(Exception e) {
        }
    } catch(NoCoupleFoundAtIndex e){
        
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
                if(girl[i].ex_bf==0)
                    continue;
                int flag=0;
                int found=0;
                try {
            for(; ; ) {
                /**
                 * check if all constraints are met
                 */
                
                    if(boy[j]==null)
                        continue;
                if(isBudgetReq(girl[i],boy[j]) && isAttrReq(girl[i],boy[j]) && boy[j].relation_status.equals("single") && girl[i].ex_bf!=boy[j].id) {
                    /**
                     * allocate bf and update their attributes
                     */
                       girl[i].relation_status="committed";
                       boy[j].relation_status="committed";
                       girl[i].com_to_bid=j;
                       boy[j].com_to_gid=i;
                       girl[i].t=new Timestamp(System.currentTimeMillis());
                       System.out.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid + " at " + girl[i].t);
                    
                       found=1;
                       break;
                }
                else j++;
                
                
                if(j<boy.length) continue;
                if(j>=boy.length && flag==0) {
                    j=0;
                    flag=1;
                }
                else break;
            
            if(j>=boy.length) {
                    j=0;
            }
            
            if(found==0) {
                
                throw new NoEligibleBfFoundException(i);
                
            }
        } 
        
    }
            catch(NoEligibleBfFoundException e) {
            e.printfirst();
            //e.printStackTrace();
            Logger.getLogger(util.class.getName()).log(Level.SEVERE, null, e);

            
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
}
