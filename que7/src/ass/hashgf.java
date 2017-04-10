/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;

import java.util.Hashtable;

/**
 *
 * @author Shuchi
 */

/**hashgf class extending findgf
 * @see findgf
 * implements hashing method for finding girlfriend of a given boy
 */

public class hashgf extends findgf{
    
    @Override
    /**
     * girlfriend()
     * method to find girlfriend of a given boy
     * uses hashing to find boy if boy name is given
     * and then find her girlfriend
     * arguments
     * @param name name of boy
     * @param boy array of boys
     * @return id of girlfriend
     */
    public int girlfriend(String name, boys boy[]) {
        Hashtable<String,boys> hash=new Hashtable();
        for(int i=0; i<boy.length; i++) {
            hash.put(boy[i].name, boy[i]);
        }
        boys b1=hash.get(name);
        if(b1.relation_status.equals("committed"))
            return b1.com_to_gid;
        return -1;
    }
}