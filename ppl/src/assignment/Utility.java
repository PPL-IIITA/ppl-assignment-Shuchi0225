package assignment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Shuchi
 */
public class Utility {
    /*gives initial value to the attributes of girls and boy*/
    int no_of_girls;
    int no_of_boys;
    int no_of_gifts;
    
    /*gives random values to attributes of boys and girls and store them in utility.csv file*/
    void init_b_and_g(girls girl[],boys boy[]) {
        int i=0;
        this.no_of_boys = boy.length;
        this.no_of_girls = girl.length;
        for( ; i<girl.length; i++) {
            girl[i]=new girls(i);
        }
    
        for( i=0 ; i< boy.length; i++) {
            boy[i]=new boys(i);
        }
        
        /*boy and girl attributes initialized. Now store in utility.csv*/
        store_b_and_g(boy,girl);
    }
    
    /*function to store boy and girl details in utility.csv*/
    void store_b_and_g(boys boy[], girls girl[]) {
        PrintWriter pw;
        try {
            /*creates new file*/
            pw = new PrintWriter(new File("boy.csv"));
        
        /*give column headings to store boys data*/
        StringBuilder sb = new StringBuilder();
        sb.append("boy_id");
        sb.append(',');
        sb.append("Atrractiveness");
        sb.append(',');
        sb.append("gf_budget");
        sb.append(',');
        sb.append("Intelligence");
        sb.append(',');
        sb.append("Required_attr");
        sb.append(',');
        sb.append("type");
        sb.append('\n');
        /*give boys values to each columns*/
        for(int i=0; i<no_of_boys; i++) {
            String s="boy_" +i;
            sb.append(s);
            sb.append(',');
            sb.append(boy[i].attractiveness);
            sb.append(',');
            sb.append(boy[i].gf_budget);
            sb.append(',');
            sb.append(boy[i].intelligence);
            sb.append(',');
            sb.append(boy[i].attration_req);
            sb.append(',');
            sb.append(boy[i].type);
            sb.append('\n');
        }
        /*write boy's details to file*/
        pw.write(sb.toString());
        pw.close();
        
        pw = new PrintWriter(new File("girl.csv"));
        StringBuilder sb1 = new StringBuilder();
        /*give column headings to store girls data*/
        sb1.append('\n');
        sb1.append("girl_id");
        sb1.append(',');
        sb1.append("Atrractiveness");
        sb1.append(',');
        sb1.append("Maintenance_budget");
        sb1.append(',');
        sb1.append("Intelligence");
        sb1.append(',');
        sb1.append("type");
        sb1.append('\n');
        /*give girls values to each columns*/
        for(int i=0; i<no_of_girls; i++) {
            String s="girl_" +i;
            sb1.append(s);
            sb1.append(',');
            sb1.append(girl[i].attractiveness);
            sb1.append(',');
            sb1.append(girl[i].main_budget);
            sb1.append(',');
            sb1.append(girl[i].intelligence);
            sb1.append(',');
            sb1.append(girl[i].type);
            sb1.append('\n');
        }
        /*write everything to file*/
        pw.write(sb1.toString());
        pw.close();
    }
     catch (FileNotFoundException ex) {
            //Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*allocate bf to all girls who can have eligible boyfriends and store them in log.txt file*/
    void allocate_bf(girls girl[],boys boy[]) {
        int j=0;
        for(int i=0; i<no_of_girls; i++) {
            int flag=0;
            for(; ; ) {
                /*check if all constraints are met*/
                if(isBudgetReq(girl[i],boy[j]) && isAttrReq(girl[i],boy[j]) && boy[j].relation_status.equals("single")) {
                       girl[i].relation_status="committed";
                       boy[j].relation_status="committed";
                       girl[i].com_to_bid=j;
                       boy[j].com_to_gid=i;
                       break;
                }
                else j++;
                if(j<no_of_boys) continue;
                if(j>=no_of_boys && flag==0) {
                    j=0;
                    flag=1;
                }
                else break;
            }
            if(j>=no_of_boys ) {
                    j=0;
            }
        }
        
    }
    
    /*check whether girl's maintenance cost is more than boy's budget.*/
    boolean isBudgetReq(girls girl, boys boy) {
        if(girl.main_budget > boy.gf_budget)
            return false;
        return true;
    }
    
    /*check whether boy's attraction requirement meets girl's attraction*/
    boolean isAttrReq(girls girl, boys boy) {
        if(girl.attractiveness < boy.attration_req)
            return false;
        return true;
    }
    
    /*store and print committments in log.txt file*/
    void print_bf(girls girl[]) {
        try{
            PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
            for(int i=0; i<girl.length; i++) {
                    if(girl[i].relation_status.equals("committed")) {
                        writer.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid);
                        System.out.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid);
                    }
            }
            writer.close();
        }
        catch(Exception e) {
            
        }
    }
    
    /*store and print committments in log.txt file*/
    void store_bf(girls girl[]) {
        try{
            PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
            for(int i=0; i<girl.length; i++) {
                    if(girl[i].relation_status.equals("committed")) {
                        writer.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid);
                        //System.out.println("girl_" + i + " got committed to " + "boy_" + girl[i].com_to_bid);
                    }
            }
            writer.close();
        }
        catch(Exception e) {
            
        }
    }
    
    /*give random values to attributes of gifts and store them in gift.csv*/
    void initializegifts(gifts gift[]) {
        int i=0;
        this.no_of_gifts = gift.length;
        for( ; i<no_of_gifts; i++) {
            gift[i]=new gifts();
        }
        store_gifts(gift);
    }
    
    /*store gifts and their attribute in gift.csv file*/
    void store_gifts(gifts gift[]) {
        PrintWriter pw;
        try {
            /*creates new file*/
            pw = new PrintWriter(new File("gift.csv"));
        
            /*give column headings to store gifts*/
            StringBuilder sb = new StringBuilder();

            /*give column names*/
            sb.append("Type");
            sb.append(',');
            sb.append("Price");
            sb.append(',');
            sb.append("Value");
            sb.append('\n');
            
            /*append gift values*/
            for(int i=0; i<gift.length; i++) {
                sb.append(gift[i].type);
                sb.append(',');
                sb.append(gift[i].price);
                sb.append(',');
                sb.append(gift[i].value);
                sb.append('\n');
            }
            
            /*write the string to file*/
            pw.write(sb.toString());
            pw.close();
        }
        catch(Exception e) {

        }
    }
    
    /*store current couples in the array*/
    void init_couples(girls girl[], boys boy[], couples couple[]) {
        for(int i=0; i<no_of_girls; i++) {
            couple[i]=new couples(girl[i], boy);
        }
    }
    
    /*allocate gifts from each boy for their girl according to their type*/
    void makegiftbasket(couples couple[], gifts gift[]) {
        sort(gift);                                         //sort gifts in ascending order by their price
        try{
            /*open file log.txt to store gifting*/
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)));
            
            for(int i=0; i<no_of_girls; i++) {
                /*check if we have to make basket for corresponding girl or not*/
                if(couple[i].girl.relation_status.equals("single"))
                    continue;
                boolean giftgiven=false;
                int total=0;
                int given=0;
                int budget=couple[i].boy.gf_budget;

                /*make basket if boy type is miser*/
                if(couple[i].boy.type.equals("miser")) {
                    /*add gifts one by one starting from lowest price*/
                    for(int j=0; j<no_of_gifts; j++) {
                        if(gift[j].price<budget) {
                            budget=(budget)-(gift[j].price);
                            total+=gift[j].price;
                            out.println("boy_" + couple[i].boy.id + " gave " + gift[j].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value);
                            couple[i].gifts.add(gift[j]);
                            giftgiven=true;
                        }
                        /*stop adding gifts if total price of gifts is greater than or equal to girl's maintenance cost*/
                        if(total>=couple[i].girl.main_budget)
                            break;
                    }
                    /*increase budget if no gifts have been given*/
                    if(giftgiven==false) {
                        couple[i].boy.gf_budget=gift[0].price;
                        couple[i].gifts.add(gift[0]);
                        out.println("boy_" + couple[i].boy.id + " gave " + gift[0].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value);
                        System.out.println("boy_" + couple[i].boy.id + " gave " + gift[0].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value);    
                    }    
                }

                /*make basket if boy type is generous*/
                else if(couple[i].boy.type.equals("generous")) {
                    /*add gifts one by one starting from lowest price*/
                    /*stop adding gifts when total price of gifts is just less than or equal to boy's gf_budget*/
                    for(int j=0; j<no_of_gifts; j++) {
                        if(gift[j].price<budget) {
                            budget=(budget)-(gift[j].price);
                            total+=gift[j].price;
                            out.println("boy_" + couple[i].boy.id + " gave " + gift[j].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value);
                            couple[i].gifts.add(gift[j]);
                            giftgiven=true;
                        }
                    }
                    /*increase budget if no gifts have been given*/
                    if(giftgiven==false) {
                        couple[i].boy.gf_budget=gift[0].price;
                        couple[i].gifts.add(gift[0]);
                        out.println("boy_" + couple[i].boy.id + " gave " + gift[0].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value);
                        System.out.println("boy_" + couple[i].boy.id + " gave " + gift[0].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value);
                    }   
                }

                /*make basket if boy type is geek*/
                else {
                        /*add the first gift*/
                        if(gift[0].price<budget) {
                            budget=(budget)-(gift[0].price);
                            total+=gift[0].price;
                            out.println("boy_" + couple[i].boy.id + " gave " + gift[0].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + gift[0].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value);
                            couple[i].gifts.add(gift[0]);
                            giftgiven=true;
                        }

                        /*add one luxury gift if possible*/
                        if(total<=budget) {
                            for(int j=0; j<no_of_gifts; j++) {
                                if(gift[j].price<budget && gift[j].type.equals("luxury")) {
                                    budget=(budget)-(gift[j].price);
                                    total+=gift[j].price;
                                    given=j;
                                    out.println("boy_" + couple[i].boy.id + " gave " + gift[j].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value" + gift[j].value);
                                    System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value" + gift[j].value);
                                    couple[i].gifts.add(gift[j]);
                                    break;
                                }
                            }
                        }

                        /*add more gifts if girl's mainteneance budget is greater than total cost of gifts*/
                        if(total<=couple[i].girl.main_budget) {
                            for(int j=1; j<no_of_gifts; j++) {
                                if(gift[j].price<budget && j!=given) {
                                    budget=(budget)-(gift[j].price);
                                    total+=gift[j].price;
                                    out.println("boy_" + couple[i].boy.id + " gave " + gift[j].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value);
                                    System.out.println("boy_" + couple[i].boy.id + " gave " + gift[j].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[j].price + " and value=" + gift[j].value);
                                    couple[i].gifts.add(gift[j]);
                                }
                        /*stop adding gifts if total price of gifts is greater than or equal to girl's maintenance cost*/
                                if(total>=couple[i].girl.main_budget)
                                    break;
                            }
                        }
                        //System.out.println("Now total is :" + total + "and gfbudget is:" + couple[i].girl.main_budget);
                        /*increase budget if no gifts have been given*/
                        if(giftgiven==false) {
                            couple[i].boy.gf_budget=gift[0].price;
                            couple[i].gifts.add(gift[0]);
                            out.println("boy_" + couple[i].boy.id + " gave " + gift[0].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value);
                            System.out.println("boy_" + couple[i].boy.id + " gave " + gift[0].type + " gift to " + "girl_" + couple[i].girl.id + " with price=" + gift[0].price + " and value=" + gift[0].value);
                        
                        }   
                }
            }
            out.close();
        }
        catch(Exception e) {
        }
    }
    
    //sort gifts in ascending order by their price
    void sort(gifts gift[]) {
        int i=0;
        gifts tempg=new gifts();
        for(i=0; i<no_of_gifts-1; i++) {
            for(int j=i+1; j<no_of_gifts; j++ ) {
                if(gift[i].price > gift[j].price) {
                    tempg = gift[i];
                    gift[i] = gift[j];
                    gift[j] =tempg;
                }
            }
        }
    }
    
    /*allocate happinessness to committed boys and girls individually and as couple */ 
    void gethappiness(couples couple[]) {
        double girl_h=0, boy_h=0, happ=0;
        for(int i=0; i<no_of_girls; i++) {
            /*check whether girl is committed or not and assign happiness to girls*/
            if(couple[i].girl.relation_status.equals("single"))
                continue;
            if (couple[i].girl.type.equals("choosy")) {
                int value=0;
                for(int j=0; j<couple[i].gifts.size(); j++) {
                    if(couple[i].gifts.get(j).type.equals("luxury"))
                        value+=couple[i].gifts.get(j).value;
                }
                girl_h=Math.log(couple[i].girl.main_budget-couple[i].total_price) + value;
            }
            else if (couple[i].girl.type.equals("normal"))
                girl_h=(couple[i].girl.main_budget-couple[i].total_price) + couple[i].total_value;
            else if (couple[i].girl.type.equals("choosy"))
                girl_h=Math.getExponent(couple[i].girl.main_budget-couple[i].total_price) ;
            couple[i].girl.happiness=(int)girl_h;
            //System.out.println(couple[i].girl.happiness);
        }
        
        /*check whether boy is committed or not and assign happiness to boys*/
        for(int i=0; i<no_of_girls; i++) {
            if(couple[i].girl.relation_status.equals("single"))
                continue;
            if (couple[i].boy.type.equals("miser")) {
                boy_h=(couple[i].boy.gf_budget-couple[i].total_price);
            }
            else if (couple[i].boy.type.equals("geeks"))
                boy_h=(couple[i].girl.intelligence) ;
            else 
                boy_h=(couple[i].girl.happiness) ;
            couple[i].boy.happiness=(int)boy_h;
        }
        
        /* assign happiness to couples */
        for(int i=0; i<no_of_girls; i++) {
            if(couple[i].girl.relation_status.equals("single"))
                continue;
            couple[i].happiness=couple[i].boy.happiness + couple[i].girl.happiness;
            //System.out.println(couple[i].happiness);
        }
    }
    
    /*calculate compatibility of couples*/
    void getcompatibility(couples couple[]) {
        for(int i=0; i<no_of_girls; i++) {
            if(couple[i].girl.relation_status.equals("single"))
                continue;
            int attr=couple[i].girl.attractiveness - couple[i].boy.attractiveness;
            int intell=couple[i].girl.intelligence - couple[i].boy.intelligence;
            couple[i].compatibility=(couple[i].boy.gf_budget- couple[i].girl.main_budget)+Math.abs(attr)+Math.abs(intell);
        }
    }
    
    /*sort a duplicate array of couples by happiness and print happiest k couples*/
    void khappiest(couples couple[], int k) {
        if(k < 0 || k > couple.length){
            return;
        } 
        /*assign happiness to couples*/
        gethappiness(couple);
        couples[] temp = new couples[couple.length]; 
        for(int i = 0; i < couple.length; i++){
            temp[i] = couple[i];
        }  
        couples temp_c = new couples();
        for(int i = 0; i < k; i++){  
            for(int j = i; j < temp.length; j++){               
                if(temp[i].happiness < temp[j].happiness){
                    temp_c = temp[i];
                    temp[i] = temp[j];
                    temp[j] = temp_c;
                }
            } 
            System.out.println(i+1 + " happiest couple is girl_" + temp[i].girl.id + " and boy_" + temp[i].boy.id);
        }
    }
    
    /*sort a duplicate array of couples by happiness and print compatible k couples*/
    void kcompatible(couples couple[], int k) {
        if(k < 0 || k > couple.length){
            return;
        } 
        /*assign compatibility to couples*/
        getcompatibility(couple);
        couples[] temp = new couples[couple.length]; 
        for(int i = 0; i < couple.length; i++){
            temp[i] = couple[i];
        }  
        couples temp_c = new couples();
        for(int i = 0; i < k; i++){  
            for(int j = i; j < temp.length; j++){               
                if(temp[i].compatibility < temp[j].compatibility){
                    temp_c = temp[i];
                    temp[i] = temp[j];
                    temp[j] = temp_c;
                }
            } 
            System.out.println(i+1 + " compatible couple is girl_" + temp[i].girl.id + " and boy_" + temp[i].boy.id);
        }
    }
}
