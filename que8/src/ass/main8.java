/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * run this file for question 8 as it contains main
 * @author Shuchi
 */
public class main8 {
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
        *allocates types of boys and girls
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
         * ask user for gifting mode
         */
        GiftSelector gifting;
        System.out.println("Enter 1 for common gifting" + '\n' + "Enter 2 for at least one gift of each type in each basket" + '\n' + "Enter any integer for default method");
        
        Scanner scan=new Scanner(System.in);
        int input=scan.nextInt();
        /**
         * select gifting mode according to input or choose default
         */
        switch(input) {
            case 1: gifting=new commongifting();
                break;
            case 2: gifting=new atleastone();
                break;
            default: gifting=new commongifting();
        }
        /**
         * make gift basket
         */
        gifting.makegiftbasket(couple, gift);
    }
}
