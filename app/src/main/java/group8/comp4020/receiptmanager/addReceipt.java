package group8.comp4020.receiptmanager;

import java.util.ArrayList;

/**
 * Created by ben on 20-Mar-17.
 */

public interface addReceipt {
    public String insertReceipt(Receipt receipt);
    public String deleteReceipt(int rid);
    public String updateReceipt(int rid);
    public String getReceipt(int rid);
    public ArrayList<Receipt> getAllReceipts();
}
