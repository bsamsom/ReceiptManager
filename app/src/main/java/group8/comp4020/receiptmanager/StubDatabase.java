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


        data.add(0 , new Receipt(0 , "Super Store      ", 50.00 , null, "1980-12-31 18:35:24", "2017-01-30 18:35:24", null));
        data.add(1 , new Receipt(1 , "Walmart          ", 15.98 , null, "2016-05-15 18:35:24", "2017-01-30 18:35:24", "1993-10-05 18:35:24"));
        data.add(2 , new Receipt(2 , "Memory Express   ", 150.24, null, "2010-04-23 18:35:24", "2017-01-30 18:35:24", "1998-02-09 18:35:24"));
        data.add(3 , new Receipt(3 , "Canadian Tire    ", 49.83 , null, "2012-10-26 18:35:24", "2017-01-30 18:35:24", "2011-09-04 18:35:24"));
        data.add(4 , new Receipt(4 , "U of M Book Store", 365.87, null, "2013-01-24 18:35:24", "2017-01-30 18:35:24", "2001-05-01 18:35:24"));
        data.add(5 , new Receipt(5 , "Shell            ", 56.65 , null, "2014-10-16 18:35:24", "2017-01-30 18:35:24", null));
        data.add(6 , new Receipt(6 , "Chapters         ", 12.43 , null, "2003-07-06 18:35:24", "2017-01-30 18:35:24", "2020-04-15 18:35:24"));
        data.add(7 , new Receipt(7 , "Rona             ", 78/89 , null, "2009-12-09 18:35:24", "2017-01-30 18:35:24", "2002-12-19 18:35:24"));
        data.add(8 , new Receipt(8 , "Home Depot       ", 43.32 , null, "2016-04-12 18:35:24", "2017-01-30 18:35:24", "1960-02-12 18:35:24"));
        data.add(9 , new Receipt(9 , "Benjamin More    ", 89.43 , null, "1994-09-04 18:35:24", "2017-01-30 18:35:24", null));
        data.add(10, new Receipt(10, "Foodfare         ", 12.95 , null, "1990-03-01 18:35:24", "2017-01-30 18:35:24", "2021-06-23 18:35:24"));

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
        data.add(receipt.getRid(),receipt);
        return results;
    }
    public String deleteReceipt(Receipt receipt){
        String results = null;
        if(data.get(receipt.getRid()) != receipt){
            results = "Item not in stub database";
        }
        else{
            data.remove(receipt.getRid());
        }
        return results;
    }
    public String updateReceipt(Receipt receipt){
        String results = null;
        if(data.get(receipt.getRid()) != receipt){
            results = "Item not in stub database";
        }
        else{
            data.remove(receipt.getRid());
            data.add(receipt.getRid(),receipt);
        }
        return results;
    }
    public Receipt getReceipt(int rid){
        Receipt results = data.get(rid);
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
                        if (start.before(temp.getPurchaseDate()) && end.after(temp.getPurchaseDate())) {
                            results.add(temp);
                        }
                    }
                    else {
                        if (start.before(temp.getWarrantyDate()) && end.after(temp.getWarrantyDate())) {
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
    public ArrayList<Receipt> getReceiptsWithTag(String tag){
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
            String dateFormat = "yyyy-mm-dd hh:mm:ss";
            try {
                SimpleDateFormat format = new SimpleDateFormat(dateFormat);
                Date d = format.parse("0000-00-00 00:00:00");
                if(temp.getWarrantyDate().after(d)){
                    result.add(temp);
                }
            }catch (ParseException e) {}
        }
        return result;
    }
    public ArrayList<Receipt> getReceiptsWithOutWarranty(){
        ArrayList<Receipt> result = new ArrayList();
        for (int i = 0; i < data.size();i++){
            Receipt temp = data.get(i);
            String dateFormat = "yyyy-mm-dd hh:mm:ss";
            try {
                SimpleDateFormat format = new SimpleDateFormat(dateFormat);
                Date d = format.parse("0000-00-00 00:00:00");
                if(temp.getWarrantyDate().compareTo(d) == 0){
                    result.add(temp);
                }
            }catch (ParseException e) {}
        }
        return result;
    }
}
