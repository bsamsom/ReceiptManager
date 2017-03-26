package group8.comp4020.receiptmanager;

import android.media.Image;
import java.util.ArrayList;
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
    private ArrayList<String> tags;

    public Receipt(int rid, String name, String store, double purchaseAmount, Image image, String purchaseDate, String returnDate, String warentyDate) {
        this.rid = rid;
        SetRid(rid);
        SetName(name);
        SetStore(store);
        SetPurchaseAmount(purchaseAmount);
        SetImage(image);
        SetPurchaseDate(purchaseDate);
        SetReturnDate(returnDate);
        SetWarrantyDate(warentyDate);
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
        return results;
    }
    public String SetPurchaseAmount(double cost) {
        String results = null;
        if (cost > 0) {
            purchaseAmount = cost;
        } else {
            results = "Cannot be a negative money amount";
        }
        return results;
    }
    public String SetImage(Image img) {
        String results = null;
        image = img;
        return results;
    }
    public String SetPurchaseDate(String date) {
        String results = null;
        String[] dates = date.split("-");
        try{
            purchaseDay   = Integer.parseInt(dates[0]);
            purchaseMonth = Integer.parseInt(dates[1]);
            purchaseYear  = Integer.parseInt(dates[2]);
            results = purchaseDay + " " + purchaseMonth + " " + purchaseYear;

        }catch (Exception e){
            Log.w("tag", "purchase date: one of " + date + " not an integer");
        }

        return results;
    }
    public String SetReturnDate(String date) {
        String results = null;
        try {
            date = date.trim();
            String[] temp = date.split("\\s+");
            int rDate = Integer.parseInt(temp[0]);
            returnDate = rDate;
        } catch (Exception e) {
            results += "\nCurrently: " + date;
            if(date != null) {
                Log.w("tag", "return date: " + date + " not an integer");
            }
        }
        return results;
    }
    public String SetWarrantyDate(String date) {
        String results = null;
        try {
            date = date.trim();
            String[] temp = date.split("\\s+");
            int wDate = Integer.parseInt(temp[0]);
            warranty = true;
            warrantyDate = wDate;
        } catch (Exception e) {
            warranty = false;
            results += "\nCurrently: " + date;
            if(date != null) {
                Log.w("tag", "warranty date: " + date + " not an integer");
            }
        }
        return results;
    }
    public String setTags(String tag){
        String result = null;
        if (tags.contains(tag)){
            result = "Already has tag";
        }
        else{
            tags.add(tag);
        }
        return result;
    }


    @Override
    public String toString() {
        String tags = "";
        ArrayList<String> tagList = getTags();
        for(int i = 0; i < tagList.size();i++){
            tags += tagList.get(i) + ", ";
        }
        if(tags.length() > 2) {
            tags = tags.substring(0, tags.length() - 2);
        }
        return getName() + "--" + getStore() + "--"  + getPurchaseAmount() + "--" + getPurchaseDate() + "--" + returnDate + "--" + warrantyDate + "--" + tags + "--";
    }

    @Override
    public int compareTo(Receipt o) {
        return this.getPurchaseDate().compareTo(o.getPurchaseDate());
    }

    public boolean hasTag(String tag){
        return tags.contains(tag);
    }
    public boolean hasATag(){return tags.size() > 0;}
    public boolean hasWarranty(){return warranty;}


}