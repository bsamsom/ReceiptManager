package group8.comp4020.receiptmanager;

import java.sql.Date;


import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by ben on 20-Mar-17.
 */

public class StubDatabase implements addReceipt {


    ArrayList<Receipt> data = new ArrayList();


    public StubDatabase(){

        String purchaseDate = "2016-12-31 18:35:24";
        String returnDate = "2017-01-30 18:35:24";
        String warentyDate = "2021-12-31 18:35:24";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        //java.sql.Date sqlDate = new Date(date.getTime());
        try {
            data.add(0 , new Receipt(0 , "Super Store      ", 50.00 , null, format.parse(purchaseDate), format.parse(returnDate), format.parse(warentyDate)));
            data.add(1 , new Receipt(1 , "Walmart          ", 15.98 , null, format.parse(purchaseDate), format.parse(returnDate), format.parse(warentyDate)));
            data.add(2 , new Receipt(2 , "Memory Express   ", 150.24, null, format.parse(purchaseDate), format.parse(returnDate), format.parse(warentyDate)));
            data.add(3 , new Receipt(3 , "Canadian Tire    ", 49.83 , null, format.parse(purchaseDate), format.parse(returnDate), format.parse(warentyDate)));
            data.add(4 , new Receipt(4 , "U of M Book Store", 365.87, null, format.parse(purchaseDate), format.parse(returnDate), format.parse(warentyDate)));
            data.add(5 , new Receipt(5 , "Shell            ", 56.65 , null, format.parse(purchaseDate), format.parse(returnDate), format.parse(warentyDate)));
            data.add(6 , new Receipt(6 , "Chapters         ", 12.43 , null, format.parse(purchaseDate), format.parse(returnDate), format.parse(warentyDate)));
            data.add(7 , new Receipt(7 , "Rona             ", 78/89 , null, format.parse(purchaseDate), format.parse(returnDate), format.parse(warentyDate)));
            data.add(8 , new Receipt(8 , "Home Depot       ", 43.32 , null, format.parse(purchaseDate), format.parse(returnDate), format.parse(warentyDate)));
            data.add(9 , new Receipt(9 , "Benjamin More    ", 89.43 , null, format.parse(purchaseDate), format.parse(returnDate), format.parse(warentyDate)));
            data.add(10, new Receipt(10, "Foodfare         ", 12.95 , null, format.parse(purchaseDate), format.parse(returnDate), format.parse(warentyDate)));
        }catch (ParseException e)
        {
            System.out.println("Error in stubDatabase with date formats");
        }
    }



    public String insertReceipt(Receipt receipt){
        String results = null;
        data.add(receipt.rid,receipt);
        return results;
    }
    public String deleteReceipt(Receipt receipt){
        String results = null;
        if(data.get(receipt.rid) != receipt){
            results = "Item not in stub database";
        }
        else{
            data.remove(receipt.rid);
        }
        return results;
    }
    public String updateReceipt(Receipt receipt){
        String results = null;
        if(data.get(receipt.rid) != receipt){
            results = "Item not in stub database";
        }
        else{
            data.remove(receipt.rid);
            data.add(receipt.rid,receipt);
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
}
