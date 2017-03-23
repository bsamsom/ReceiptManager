package group8.comp4020.receiptmanager;

import android.media.Image;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import android.util.Log;
/**
 * Created by ben on 20-Mar-17.
 */

public class Receipt implements Comparable <Receipt> {
    private int rid;
    private String store;
    private double purchaseAmount;
    private Image image;
    private Date purchaseDate;
    private Date returnDate;
    private Date warrantyDate;
    private ArrayList<String> tags;
    private Boolean[] hasBeenModified;

    public Receipt(int rid, String store, double purchaseAmount, Image image, String purchaseDate, String returnDate, String warentyDate) {
        this.rid = rid;
        SetRid(rid);
        SetStore(store);
        SetPurchaseAmount(purchaseAmount);
        SetImage(image);
        SetPurchaseDate(purchaseDate);
        SetReturnDate(returnDate);
        SetWarrantyDate(warentyDate);
        tags = new ArrayList<>();
        hasBeenModified = new Boolean[8];
    }

    public Receipt() {
        rid = -1;
        purchaseAmount = -1;
        tags = new ArrayList<>();
        hasBeenModified = new Boolean[8];
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Getters
    public int getRid() {
        return rid;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getStore() {
        return store;
    }

    public Double getPurchaseAmount() {
        return purchaseAmount;
    }

    public Image getImage() {
        return image;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public Date getWarrantyDate() {
        return warrantyDate;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Setters
    public String SetRid(int newRid) {
        String results = null;
        if (newRid < 0) {
            results = "Negative values are not allowed";
        } else {
            rid = newRid;
        }
        hasBeenModified[0] = true;
        return results;
    }

    public String SetStore(String newStore) {
        String results = null;
        if (newStore.length() > 0 && newStore != null) {
            store = newStore;
        } else {
            results = "Empty store or null store not allowed";
        }
        hasBeenModified[1] = true;
        return results;
    }

    public String SetPurchaseAmount(double cost) {
        String results = null;
        if (cost > 0) {
            purchaseAmount = cost;
        } else {
            results = "Cannot be a negative money amount";
        }
        hasBeenModified[2] = true;
        return results;
    }

    public String SetImage(Image img) {
        String results = null;
        image = img;
        hasBeenModified[3] = true;
        return results;
    }

    public String SetPurchaseDate(String date) {
        String results = null;
        String dateFormat = "yyyy-mm-dd hh:mm:ss";
        if (date == null) {
            results = "Purchase date cannot be null";
        } else {
            try {
                SimpleDateFormat format = new SimpleDateFormat(dateFormat);
                Date d = format.parse(date);
                purchaseDate = d;
            } catch (ParseException e) {
                results = "Purchase date does not have the format: " + dateFormat;
                Log.w("tag", results);
            }
        }
        hasBeenModified[4] = true;
        return results;
    }

    public String SetReturnDate(String date) {
        String results = null;
        String dateFormat = "yyyy-mm-dd hh:mm:ss";
        if (date == null) {
            results = "Return date cannot be null";
        } else {
            try {
                SimpleDateFormat format = new SimpleDateFormat(dateFormat);
                Date d = format.parse(date);
                returnDate = d;
            } catch (ParseException e) {
                results = "Return date does not have the format: " + dateFormat;
                Log.w("tag", results);
            }
        }
        hasBeenModified[5] = true;
        return results;
    }

    public String SetWarrantyDate(String date) {
        String results = null;
        String dateFormat = "yyyy-mm-dd hh:mm:ss";
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateFormat);
            Date d;
            if (date != null) {
                d = format.parse(date);
            } else {
                d = format.parse("0000-00-00 00:00:00");
            }
            warrantyDate = d;
        } catch (ParseException e) {
            results = "Return date does not have the format: " + dateFormat;
            Log.w("tag", results);
        }

        hasBeenModified[6] = true;
        return results;
    }

    public String setTags(String tag) {
        String result = null;
        if (tags.contains(tag)) {
            result = "Already has tag";
        } else {
            tags.add(tag);
        }
        hasBeenModified[7] = true;
        return result;
    }


    @Override
    public String toString() {
        return "";
    }

    @Override
    public int compareTo(Receipt o) {
        return this.getPurchaseDate().compareTo(o.getPurchaseDate());
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public boolean hasValue(String property) {
        switch (property) {
            case "rid":
                if (hasBeenModified[0])
                    return true;
                break;
            case "store":
                if (hasBeenModified[1])
                    return true;
                break;
            case "purchaseAmount":
                if (hasBeenModified[2])
                    return true;
                break;
            case "image":
                if (hasBeenModified[3])
                    return true;
                break;
            case "purchaseDate":
                if (hasBeenModified[4])
                    return true;
                break;
            case "returnDate":
                if (hasBeenModified[5])
                    return true;
                break;
            case "warrantyDate":
                if (hasBeenModified[6])
                    return true;
                break;
            case "tags":
                if (hasBeenModified[7])
                    return true;
                break;
        }

        return false;
    }
}


