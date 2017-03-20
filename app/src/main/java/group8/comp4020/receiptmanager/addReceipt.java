package group8.comp4020.receiptmanager;

import java.util.ArrayList;

/**
 * Created by ben on 20-Mar-17.
 */

public interface addReceipt {
    public String insertReceipt(Receipt receipt);
    public String deleteReceipt(Receipt receipt);
    public String updateReceipt(Receipt receipt);
    public Receipt getReceipt(int rid);
    public ArrayList<Receipt> getAllReceipts();
}
