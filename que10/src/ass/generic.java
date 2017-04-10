/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Shuchi
 */

/**
 * generic class
 * 
 * @param <T> type of object
 */
public class generic <T> {
    Random rand = new Random();
    /**
     * genericMethod to
     * copy array of anytype
     * @param obj array 
     * @param k number of elements
     * @return array 
     */
    public  <T> T[] genericMethod(T[] obj,int k) {
        @SuppressWarnings("unchecked")
        T[] res = Arrays.copyOf((T[]) obj, k);
        return res;
    }
    
    /**
     * one to randomly select one from given array
     
     * @param obj array
     * @return object
     */
    public  <T> T one (T[] obj) {
        @SuppressWarnings("unchecked")
        int i = rand.nextInt(obj.length);
        T res = ((T) obj[i]);
        return res;
    }
}
