package group8.comp4020.receiptmanager;

import android.media.Image;
import java.util.ArrayList;
import java.util.Date;

import android.util.Log;
/**
 * Created by ben on 20-Mar-17.
 */

public class Receipt implements Comparable <Receipt> {
    private int rid;
    private String name;
    private String store;
    private double purchaseAmount;
    private Image image;
    private int purchaseDay;
    private int purchaseMonth;
    private int purchaseYear;
    private int returnDate;
    private int warrantyDate;
    private boolean warranty;
    private boolean returnable;
    private ArrayList<String> tags;
    private Boolean[] hasBeenModified;

    public Receipt(int rid, String name, String store, double purchaseAmount, Image image, String purchaseDate, int returnDate, int warentyDate) {
        hasBeenModified = new Boolean[8];
        for (int i = 0; i < 8;i++){
            hasBeenModified[i] = false;
        }
        SetRid(rid);
        SetName(name);
        SetStore(store);
        SetPurchaseAmount(purchaseAmount);
        SetImage(image);
        SetPurchaseDate(purchaseDate);
        SetReturnDate_Simple(returnDate);
        SetWarrantyDate_Simple(warentyDate);
        tags = new ArrayList<>();
    }

    public Receipt() {
        hasBeenModified = new Boolean[8];
        for (int i = 0; i < 8;i++){
            hasBeenModified[i] = false;
        }
        rid = -1;
        purchaseAmount = -1;
        tags = new ArrayList<>();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Getters
    public int getRid() {
        return rid;
    }

    public String getName() {
        return name;
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

    public String getPurchaseDate() {
        return purchaseDay + " " + purchaseMonth + " " + purchaseYear;
    }

    public int getReturnDate() {
        return returnDate;
    }

    public int getWarrantyDate() {
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

    public String SetName(String newname) {
        String results = null;
        if (newname.length() > 0 && newname != null) {
            name = newname;
        } else {
            results = "Empty store or null store not allowed";
        }
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
        String[] dates = date.split("-");
        try {
            purchaseDay = Integer.parseInt(dates[0]);
            purchaseMonth = Integer.parseInt(dates[1]);
            purchaseYear = Integer.parseInt(dates[2]);
            results = purchaseDay + " " + purchaseMonth + " " + purchaseYear;

        } catch (Exception e) {
            Log.w("tag", "purchase date: one of " + date + " not an integer");
        }

        hasBeenModified[4] = true;
        return results;
    }

    public void SetWarrantyDate_Simple(int index) {
        if (index == 0) {
            warranty = false;
            warrantyDate = 0;
        }
        else {
            warranty = true;
            warrantyDate = index;
        }

        hasBeenModified[6] = true;
    }

    public void SetReturnDate_Simple (int index) {
        if (index == 0) {
            returnable = false;
            returnDate = 0;
        }
        else {
            returnable = true;
            returnDate = index;
        }

        hasBeenModified[5] = true;
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

    public String setPurchasedAt (String location)
    {
        return "Not implemented";
    }

    public String setPurchaseCategory (String category)
    {
        return "Not implemented";
    }


    @Override
    public String toString() {
        String tags = "";
        ArrayList<String> tagList = getTags();
        for (int i = 0; i < tagList.size(); i++) {
            tags += tagList.get(i) + ", ";
        }
        if (tags.length() > 2) {
            tags = tags.substring(0, tags.length() - 2);
        }
        String pDate = getPurchaseDate().replaceAll(" ", "-");
        if(pDate.equalsIgnoreCase("0-0-0")){
            pDate = "0000-00-00";
        }

        return getName() + "--" + getStore() + "--" + getPurchaseAmount() + "--" + pDate + "--" + returnDate + "--" + warrantyDate + "--" + tags + "--";
    }

    @Override
    public int compareTo(Receipt o) {
        return this.getPurchaseDate().compareTo(o.getPurchaseDate());
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public boolean hasATag() {
        return tags.size() > 0;
    }

    public boolean hasWarranty() {
        return warranty;
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

    public boolean setPurchaseDate_Date (int date)
    {
        if (checkValid_Date(date)) {
            purchaseDay = date;
            return true;
        }
        else
            return false;
    }

    public boolean setPurchaseDate_Month (int month)
    {
        if (checkValid_Month(month)) {
            purchaseMonth = month;

        return true;
        }

        else
            return false;


    }

    public boolean setPurchaseDate_Year (int year)
    {
        if (checkValid_Year(year, true)) {
            purchaseYear = year;
            return true;
        }

        else
            return false;


    }

    private boolean checkValid_Date (int date)
    {
        if (date > 0)
            return true;
        else
            return false;
    }

    private boolean checkValid_Month (int month)
    {
        if (month < 1 || month > 12)
            return false;
        else
            return true;
    }

    private boolean checkValid_Year (int year, boolean mustBeLessThanCurrentYear) {
        // if the year is further in the future than this date
        if (mustBeLessThanCurrentYear){
            if (year > new Date().getYear() + 1900 || year < 0)
                return false;
            else
                return true;
        }

        // year can be any value greater than 0
        else {
            if (year < 0)
                return false;
            else
                return true;
        }
    }
}