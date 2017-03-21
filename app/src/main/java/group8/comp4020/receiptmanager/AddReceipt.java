package group8.comp4020.receiptmanager;

import java.util.ArrayList;

/**
 * Created by ben on 20-Mar-17.
 */

public interface AddReceipt {
    String insertReceipt(Receipt receipt);
    String deleteReceipt(Receipt receipt);
    String updateReceipt(Receipt receipt);
    Receipt getReceipt(int rid);
    ArrayList<Receipt> getReceiptsByPurchseDate(String startDate, String endDate);
    ArrayList<Receipt> getReceiptsByWarentyDate(String startDate, String endDate);
    ArrayList<Receipt> getAllReceipts();
    ArrayList<Receipt> getReceiptsWithTag(String tag);
}
