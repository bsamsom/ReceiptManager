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

import static android.R.attr.data;

public class SingleScreenInsert extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_screen_insert);

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



        Receipt r = new Receipt(13,store,Double.parseDouble(purchaseAmount),null,purchaseDate,returnDate,warrantyDate);
        Helper.stub.insertReceipt(r);

       // Log.w("tag",r.toString());


        Intent intent = new Intent(this, GuiConfig1Activity.class);
        //intent.putExtra("", "");
        startActivity(intent);
    }
}
