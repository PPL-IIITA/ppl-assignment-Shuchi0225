/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.io.IOException;
import java.util.Random;

/**
 * run this file for question 3 as it contains main
 * @author Shuchi
 */
public class main3 {
    public static void main(String[] args) throws IOException {
         /**
          * Generates Random no of boys and girls
          */
        Random rand = new Random();
        int  no_of_girls = rand.nextInt(15) + 10;
        int no_of_boys = rand.nextInt(20) + 70;
        
        /**
         * creates array of boys and girls
         */
        boys boy[] = new boys[no_of_boys];
        girls girl[] = new girls[no_of_girls];
        
        /**
         * allocates types of boys and girls
        *give random values to attributes of boys and girls and store them in boy.csv and girl.csv file
        */
        util obj = new util();
        obj.type(girl, boy);
        
        /**
         * allocate boyfriends to all girls when everyone is initially single and store the information in log.txt
         */
        obj.allocate_bf(girl,boy);
        obj.print_bf(girl);
        
         /**
          * make an array of gifts
          */
        int no_of_gifts=rand.nextInt(10) + 10;
        gifts gift[]=new gifts[no_of_gifts];
        
        /**
         * give random values to attributes of gifts
         */
        obj.initializegifts(gift);
        
        /**
         * make an array of couples
         */
        couples couple[]=new couples[no_of_girls];
        
        /**
         * store current couples in the array and sore them in log.txt file
         */
        obj.init_couples(girl, boy, couple);
        obj.store_bf(girl);
        
        
        /**
         * make gift basket from each boy who is committed for her girl
         */
        obj.makegiftbasket(couple, gift);
        
        /**
         * generate a random no k and print k happiest and k compatible couples
         */
        int k=rand.nextInt(5) + 3;
        obj.gethappiness(couple);
        obj.khappiest(couple, k);
        obj.kcompatible(couple, k);
    }
}
