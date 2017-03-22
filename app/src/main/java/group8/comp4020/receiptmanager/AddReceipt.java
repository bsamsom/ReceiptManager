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
    ArrayList<Receipt> getReceiptsByPurchaseDate(String startDate, String endDate);
    ArrayList<Receipt> getReceiptsByWarrantyDate(String startDate, String endDate);
    ArrayList<Receipt> getAllReceipts();
    ArrayList<Receipt> getReceiptsWithSpecificTag(String tag);
    ArrayList<Receipt> getReceiptsWithTag();
    ArrayList<Receipt> getReceiptsWithWarranty();
    ArrayList<Receipt> getReceiptsWithOutWarranty();
}
