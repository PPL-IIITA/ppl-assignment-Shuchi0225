package assignment;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
/**
 *
 * @author Shuchi
 */

public class main1 {
    public static void main(String[] args) throws IOException {
         /*Generates Random no of boys and girls*/
        Random rand = new Random();
        int  no_of_girls = rand.nextInt(15) + 10;
        int no_of_boys = rand.nextInt(20) + 70;
        
        /*creates array of boys and girls*/
        girls girl[]=new girls[no_of_girls];
        boys boy[]=new boys[no_of_boys];
        
        /*create an object to use functions of utility*/
        Utility obj = new Utility();
        
        /*give random values to attributes of boys and girls and store them in boy.csv and girl.csv file*/
        obj.init_b_and_g(girl, boy);
        
        /*allocate boyfriends to all girls when everyone is initially single and store the information in log.txt*/
        obj.allocate_bf(girl,boy);
        obj.print_bf(girl);
    }
}
