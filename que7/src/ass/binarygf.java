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

/**binarygf class extending findgf
 * @see findgf
 * implements binary search method for finding girlfriend of a given boy
 */
public class binarygf extends findgf{
    
    /**
     * sortbyid method 
     * sorts array of boys in ascending order by their names
     * arguments
     * @param boy array of boys
     * 
     */
    public void sortbyid(boys boy[]) {
        for(int i=0; i<boy.length-1; i++) {
            for(int j=i+1; j<boy.length; j++) {
                if(boy[i].id>boy[j].id) {
                    boys temp=boy[i];
                    boy[i]=boy[j];
                    boy[j]=temp;
                }
            }
        }
    }

    @Override
    /**
     * girlfriend()
     * method to find girlfriend of a given boy
     * searches in the array of boy for finding his corresponding gf using binary search
     * @param name name of boys whose gf is to be searched
     * @param boy array of boys
     * @return id of girlfriend
     */
    public int girlfriend(String name, boys boy[]) {
        sortbyid(boy);
        
        int first = 0;
        int last = boy.length - 1;
        int middle = (first+last)/2;
 
        while (first <= last) {
            if (boy[middle].name.compareTo(name) < 0)
                first = middle + 1;    
            else if (boy[middle].name.equals(name)) {
                if(boy[middle].relation_status.equals("committed"))
                    return boy[middle].com_to_gid;
                else return -1;
            }
            else
                last = middle - 1;
            middle = (first + last)/2;
        }
        return -1;
    }
    
}
