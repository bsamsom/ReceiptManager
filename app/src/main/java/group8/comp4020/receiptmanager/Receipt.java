package group8.comp4020.receiptmanager;

import android.media.Image;

import java.util.Date;

/**
 * Created by ben on 20-Mar-17.
 */

public class Receipt {
    public int rid;
    public String store;
    public double purchaseAmount;
    public Image image;
    public Date purchaseDate;
    public Date returnDate;
    public Date warentyDate;
    public Receipt(int rid,String store, double purchaseAmount,Image image, Date purchaseDate, Date returnDate, Date warentyDate){
        this.rid = rid;
        this.store = store;
        this.purchaseAmount = purchaseAmount;
        this.image = image;
        this.purchaseDate = purchaseDate;
        this.returnDate = returnDate;
        this.warentyDate = warentyDate;

    }

}
