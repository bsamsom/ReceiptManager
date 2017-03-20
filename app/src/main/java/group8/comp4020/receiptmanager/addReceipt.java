package group8.comp4020.receiptmanager;

/**
 * Created by ben on 20-Mar-17.
 */

public interface addReceipt {
    public String insertReceipt(int rid);
    public String deleteReceipt(int rid);
    public String updateReceipt(int rid);
    public String getReceipt(int rid);
    public Receipt[] getAllReceipts();
}
