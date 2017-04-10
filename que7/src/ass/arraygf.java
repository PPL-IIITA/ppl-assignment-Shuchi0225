/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass;



/**
 *
 * @author Shuchi
 */

/**arraygf class extending findgf
 * @see findgf
 * implements array method for finding girlfriend of a given boy
 */
public class arraygf extends findgf{

    @Override
    /**
     * method to find girlfriend of a given boy
     * searches in the array of boy for finding his corresponding gf
     * @param name name of boys whose gf is to be searched
     * @param boy array of boys
     * @return id of girlfriend
     */
    public int girlfriend(String name, boys[] boy) {
        for(int i=0; i<boy.length; i++) {
            if(name.equals(boy[i].name)) {
                if(boy[i].relation_status.equals("committed"))
                    return boy[i].com_to_gid;
                return -1;
            }
        }
        return -1;
    }
}
