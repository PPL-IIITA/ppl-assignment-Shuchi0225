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
 * run this file for question 7 as it contains main
 * @author Shuchi
 */
public class main7 {
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
         * ask user for method to find gf
         */
        System.out.println("choose method for finding gf:" + '\n' + "Enter 1 for array" + '\n' + "Enter 2 for hashtable");
        System.out.println("Enter 3 for binary search" + '\n' + "Enter any integer for default choice");
        Scanner scan=new Scanner(System.in);
        int input=scan.nextInt();
        findgf f;
        
        /**
         * choose method according to input
         */
        switch(input) {
            case 1: f=new arraygf();
                break;
            case 2: f=new hashgf();
                break;
            case 3: f=new binarygf();
                break;
            default: f=new arraygf();
        }
        
        /**
         * print gf of boy
         */
        for(int i=0; i<boy.length; i++) {
            int id=f.girlfriend(boy[i].name, boy);
            if(id==-1)
                System.out.println(boy[i].name + " is single");
            else System.out.println(boy[i].name + " is committed to girl_" + id);
        }
    }
}
