package group8.comp4020.receiptmanager;

import java.util.ArrayList;

/**
 * Created by ben on 20-Mar-17.
 */

public class SqlDatabase  implements AddReceipt {
    public SqlDatabase(){

    }
    public String insertReceipt(Receipt receipt){
        String results = null;

        return results;
    }
    public String deleteReceipt(Receipt receipt){
        String results = null;

        return results;
    }
    public String updateReceipt(Receipt receipt){
        String results = null;

        return results;
    }
    public Receipt getReceipt(int rid){
        Receipt results = null;

        return results;
    }
    public ArrayList<Receipt> getAllReceipts(){
        return null;
    }
    public ArrayList<Receipt> getReceiptsByPurchaseDate(String startDate, String endDate){return null;}
    public ArrayList<Receipt> getReceiptsByWarrantyDate(String startDate, String endDate){return null;}
    public ArrayList<Receipt> getReceiptsWithTag(String tag){return null;}
    public ArrayList<Receipt> getReceiptsWithWarranty(){return null;}
    public ArrayList<Receipt> getReceiptsWithOutWarranty(){return null;}
}
