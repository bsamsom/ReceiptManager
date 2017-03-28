package group8.comp4020.receiptmanager;

import android.graphics.Bitmap;
import android.media.Image;

import java.util.ArrayList;
import  java.util.Collections;
import java.util.Comparator;

/**
 * Created by ben on 21-Mar-17.
 */

public class Helper {
    public static StubDatabase stub = new StubDatabase();
    public static Receipt receipt = null;
    public static Bitmap img = null;
    public static int rid = 13;
    public static String[] returnDates = new String[]{"None", "30 Days", "60 Days", "90 Days", "180 Days", "1 Year", "2 Years", "3 Years", "5 Years" };
    public static String[] warrantyDates = new String[]{"None", "30 Days", "60 Days", "90 Days", "180 Days", "1 Year", "2 Years", "3 Years", "5 Years" };

    public ArrayList<Receipt> sortByDate(ArrayList<Receipt> input){
        Collections.sort(input);
        return input;
    }
    public ArrayList<Receipt> sortByAmount(ArrayList<Receipt> input){
        Collections.sort(input,new Comparator<Receipt>(){
            public int compare(Receipt one,Receipt two){
                return (int)(one.getPurchaseAmount() - two.getPurchaseAmount());
            }});
        return input;
    }
}
