package group8.comp4020.receiptmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.data;

public class SingleScreenInsert extends AppCompatActivity {
    public boolean edit = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_screen_insert);
        Bundle b = getIntent().getExtras();
        if(b != null){
            fillData();
            edit = true;
        }
        else{
            edit = false;
        }
    }
    private void fillData(){
        String dataString = Helper.receipt.toString();
        String[] data = dataString.split("-");

        final ArrayList<String> stringList = new ArrayList<String>();
        for (int i = 0; i < data.length; ++i) {
            String[] temp = data[i].split("\\s+");
            if(temp.length == 6){
                data[i] = temp[0] + " " + temp[1] + " " + temp[2] + " " + temp[5];
            }
        }
        stringList.add(data[0]);
        stringList.add(data[1]);

        if(data.length > 2) {
            stringList.add(data[2]);
        }
        else{
            // no Warranty Date to add
            stringList.add("");
        }
        if(data.length > 3) {
            stringList.add(data[3]);
        }
        else{
            // no Warranty Date to add
            stringList.add("");
        }


        if(data.length > 4) {
            stringList.add(data[4]);
        }
        else{
            // no Warranty Date to add
            stringList.add("");
        }

        if(data.length > 5) {
            stringList.add(data[5]);
        }
        else {
            // no tags to add
            stringList.add("");
        }
        TextView t1 = (TextView)findViewById(R.id.editTextStoreName);
        TextView t2 = (TextView)findViewById(R.id.editTextPurchaseAmount);
        TextView t3 = (TextView)findViewById(R.id.editTextPurchaseDate);
        TextView t4 = (TextView)findViewById(R.id.editTextReturnDate);
        TextView t5 = (TextView)findViewById(R.id.editTextWarrantyDate);
        TextView t6 = (TextView)findViewById(R.id.editTextTags);
        t1.setText(stringList.get(0));
        t2.setText(stringList.get(1));
        t3.setText(stringList.get(2));
        t4.setText(stringList.get(3));
        t5.setText(stringList.get(4));
        t6.setText(stringList.get(5));

    }
    public void buttonSaveClick(View view) {
        //Helper.stub;
        TextView t1 = (TextView)findViewById(R.id.editTextStoreName);
        TextView t2 = (TextView)findViewById(R.id.editTextPurchaseAmount);
        TextView t3 = (TextView)findViewById(R.id.editTextPurchaseDate);
        TextView t4 = (TextView)findViewById(R.id.editTextReturnDate);
        TextView t5 = (TextView)findViewById(R.id.editTextWarrantyDate);
        TextView t6 = (TextView)findViewById(R.id.editTextTags);
        String store            = "" + t1.getText();
        String purchaseAmount   = "" + t2.getText();
        if (purchaseAmount.length() - purchaseAmount.indexOf(".") > 2){
            purchaseAmount = purchaseAmount.substring(0,purchaseAmount.indexOf(".")+ 3);
        }
        String purchaseDate     = "" + t3.getText();
        String returnDate       = "" + t4.getText();
        String warrantyDate     = "" + t5.getText();
        String tags             = "" + t6.getText();
        //Log.w("tag",store + "\n"+ purchaseAmount + "\n"+ purchaseDate + "\n"+ returnDate + "\n"+ warrantyDate + "\n"+ tags);

        //"0000-00-00 00:00:00";
        String ending = " 00:00:00";
        if(purchaseDate.length() >= 10){
            purchaseDate = purchaseDate.substring(0,10);
            purchaseDate += ending;
        }
        if(returnDate.length() >= 10){
            returnDate = returnDate.substring(0,10);
            returnDate += ending;
        }
        if(warrantyDate.length() >= 10){
            warrantyDate = warrantyDate.substring(0,10);
            warrantyDate += ending;
        }

        if(edit){
            Receipt r = new Receipt(Helper.receipt.getRid(),store,Double.parseDouble(purchaseAmount),null,Helper.receipt.pDate(),Helper.receipt.rDate(),Helper.receipt.wDate());
            //Log.w("tag",Helper.receipt.pDate() + "\n" + Helper.receipt.rDate() + "\n" + Helper.receipt.wDate());
            Helper.stub.updateReceipt(r);
        }
        else {
            Receipt r = new Receipt(13,store,Double.parseDouble(purchaseAmount),null,purchaseDate,returnDate,warrantyDate);
            Helper.stub.insertReceipt(r);
        }
       // Log.w("tag",r.toString());


        Intent intent = new Intent(this, GuiConfig1Activity.class);
        //intent.putExtra("", "");
        startActivity(intent);
    }
}
