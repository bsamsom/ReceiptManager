package group8.comp4020.receiptmanager;

import java.util.ArrayList;

/**
 * Created by ben on 20-Mar-17.
 */

public interface addReceipt {
    String insertReceipt(Receipt receipt);
    String deleteReceipt(Receipt receipt);
    String updateReceipt(Receipt receipt);
    Receipt getReceipt(int rid);
    ArrayList<Receipt> getAllReceipts();
}
