package group8.comp4020.receiptmanager;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by ben on 20-Mar-17.
 */

public class StubDatabase implements AddReceipt {


    ArrayList<Receipt> data = new ArrayList();


    public StubDatabase(){


        data.add(0 , new Receipt(0 , "Food"             , "Super Store"      , 50.00 , null, "1980-12-31", "30 Days"   , null));
        data.add(1 , new Receipt(1 , "Kitchen Stuff"    , "Walmart"          , 15.98 , null, "2016-05-15", "60 Days"   , "1 Year"));
        data.add(2 , new Receipt(2 , "Computer Parts"   , "Memory Express"   , 150.24, null, "2010-04-23", "180 Days"  , "3 Years"));
        data.add(3 , new Receipt(3 , "Tires"            , "Canadian Tire"    , 49.83 , null, "2012-10-26", "60 Days"   , "5 Years"));
        data.add(4 , new Receipt(4 , "Textbook"         , "U of M Book Store", 365.87, null, "2013-01-24", "90 Days"   , "1 Year"));
        data.add(5 , new Receipt(5 , "Gass"             , "Shell"            , 56.65 , null, "2014-10-16", "30 Days"   , null));
        data.add(6 , new Receipt(6 , "Book"             , "Chapters"         , 12.43 , null, "2003-07-06", "60 Days"   , "5 Years"));
        data.add(7 , new Receipt(7 , "Lumber"           , "Rona"             , 78.89 , null, "2009-12-09", "60 Days"   , "2 Years"));
        data.add(8 , new Receipt(8 , "Drywall"          , "Home Depot"       , 43.32 , null, "2016-04-12", "90 Days"   , "1 Year"));
        data.add(9 , new Receipt(9 , "Paint"            , "Benjamin More"    , 89.43 , null, "1994-09-04", "30 Days"   , null));
        data.add(10, new Receipt(10, "Food"             , "Foodfare"         , 12.95 , null, "1990-03-01", "180 Days"  , "3 Years"));

        data.get(5).setTags("waffle");
        data.get(2).setTags("waffle");
        data.get(1).setTags("waffle");

        data.get(1).setTags("cheese");
        data.get(5).setTags("cheese");
        data.get(6).setTags("cheese");
        data.get(9).setTags("cheese");
        data.get(8).setTags("cheese");


    }



    public String insertReceipt(Receipt receipt){
        String results = null;
        data.add(receipt);
        return results;
    }
    public String deleteReceipt(Receipt receipt){
        String results = null;
        Receipt r = receiptWithRid(receipt.getRid());

        if(r == null || receipt == null){
            results = "Item not in stub database";
        }
        else{
            data.remove(r);
        }
        return results;
    }
    private Receipt receiptWithRid(int id){
        Receipt result = null;
        boolean end = false;
        for(int i = 0; i < data.size() && !end;i++){
            Receipt temp = data.get(i);
            if (temp.getRid() == id){
                end = true;
                result = temp;
            }
        }
        return result;
    }
    public String updateReceipt(Receipt receipt){
        String results = null;
        Receipt temp = receiptWithRid(receipt.getRid());
        if(temp == null){
            results = "Item not in stub database";
        }
        else{
            //Log.w("tag",temp.toString() + "\n" + receipt.toString());
            data.remove(temp);
            data.add(receipt);
        }
        return results;
    }
    public Receipt getReceipt(int pos){
        Receipt results = null;
        if(pos > 0 && pos <= data.size()){
            results = data.get(pos);
        }

        return results;
    }
    public ArrayList<Receipt> getAllReceipts(){
        if (data.size() > 0){
            return data;
        }
        else{
            return null;
        }
    }
    public ArrayList<Receipt> getReceiptsByPurchaseDate(String startDate, String endDate){
        return dateRangeHelper(startDate, endDate, true);
    }
    public ArrayList<Receipt> getReceiptsByWarrantyDate(String startDate, String endDate){
        return dateRangeHelper(startDate, endDate, false);
    }
    private ArrayList<Receipt> dateRangeHelper(String startDate, String endDate, boolean purchase) {
        ArrayList<Receipt> results = new ArrayList<>();
        String print = "";
        String dateFormat = "yyyy-mm-dd hh:mm:ss";
        if(startDate == null){
            print = "Start date cannot be null";
            Log.w("tag", print);
        }
        else if(endDate == null){
            print = "End date cannot be null";
            Log.w("tag", print);
        }
        else {
            try {
                SimpleDateFormat format = new SimpleDateFormat(dateFormat);
                Date start = format.parse(startDate);
                Date end = format.parse(endDate);
                for(int i = 0; i < data.size();i++){
                    Receipt temp = data.get(i);
                    if(purchase) {
                        String[] split = temp.getPurchaseDate().split("\\s+");
                        Date d = format.parse(split[3] + "-" + split[2] + "-" + split[0]);
                        if (start.before(d) && end.after(d)) {
                            results.add(temp);
                        }
                    }
                    else {
                        String[] split = temp.getPurchaseDate().split("\\s+");
                        Date d = format.parse(split[3] + "-" + split[2] + "-" + split[0]);
                        d.setYear(temp.getWarrantyDate());

                        if (start.before(d) && end.after(d)) {
                            results.add(temp);
                        }
                    }
                }
            } catch (ParseException e) {
                print = "Return date does not have the format: " + dateFormat;
                Log.w("tag", print);
            }
        }
        return results;
    }
    public ArrayList<Receipt> getReceiptsWithTag(){
        ArrayList<Receipt> result = new ArrayList();
        for (int i = 0; i < data.size();i++){
            Receipt temp = data.get(i);
            if(temp.hasATag()){
                result.add(temp);
            }
        }
        return result;
    }
    public ArrayList<Receipt> getReceiptsWithSpecificTag(String tag){
        ArrayList<Receipt> result = new ArrayList();
        String print = "";
        if(tag.length() > 0 && tag != null){
            for (int i = 0; i < data.size();i++){
                Receipt temp = data.get(i);
                if(temp.hasTag(tag)){
                    result.add(temp);
                }
            }
        }
        else{
            print = "looking for empty and null tags";
            Log.w("tag", print);
        }
        return result;
    }
    public ArrayList<Receipt> getReceiptsWithWarranty(){
        ArrayList<Receipt> result = new ArrayList();
        for (int i = 0; i < data.size();i++){
            Receipt temp = data.get(i);
            if(temp.hasWarranty()){
                result.add(temp);
            }
        }
        return result;
    }
    public ArrayList<Receipt> getReceiptsWithOutWarranty(){
        ArrayList<Receipt> result = new ArrayList();
        for (int i = 0; i < data.size();i++){
            Receipt temp = data.get(i);
            if(!temp.hasWarranty()){
                result.add(temp);
            }
        }
        return result;
    }
}
