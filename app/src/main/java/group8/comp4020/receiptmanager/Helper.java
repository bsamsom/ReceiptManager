package group8.comp4020.receiptmanager;

import java.util.ArrayList;
import  java.util.Collections;
/**
 * Created by ben on 21-Mar-17.
 */

public class Helper {

    public ArrayList<Receipt> sortByDate(ArrayList<Receipt> input){
        Collections.sort(input);
        return input;
    }


}
