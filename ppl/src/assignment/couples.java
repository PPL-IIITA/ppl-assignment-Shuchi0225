/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import java.util.ArrayList;

/**
 *
 * @author Shuchi
 */
public class couples {
    girls girl;
    boys boy;
    ArrayList<gifts> gifts=new ArrayList<gifts>();;
    int happiness;
    int compatibility;
    int total_price;
    int total_value;
    
    public couples(girls girl, boys boy[]) {
        this.girl=girl;
        if(girl.relation_status=="committed") {
            this.boy=boy[girl.com_to_bid];
            //System.out.println("girl " + girl.id + "coupled to " + this.boy.id);
        }
    }
    
    public couples() {
        
    }
}
