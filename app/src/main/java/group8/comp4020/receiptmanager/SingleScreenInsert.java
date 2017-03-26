package group8.comp4020.receiptmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ForwardingListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.data;

public class SingleScreenInsert extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public boolean edit = false;
    public String returnSelection = "30 Days";
    public String warrantySelection = "1 Year";
    private ArrayAdapter<String> warrantyAdapter;
    private ArrayAdapter<String> dateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_screen_insert);
        Bundle b = getIntent().getExtras();
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Return Date Spinner
        Spinner returnDate = (Spinner) findViewById(R.id.spinnerReturnDate);
        String[] dates = new String[]{"30 Days", "60 Days", "90 Days", "180 Days"};
        dateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dates);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        returnDate.setAdapter(dateAdapter);
        returnDate.setOnItemSelectedListener(this);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~End Return Date Spinner
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Return Date Spinner
        Spinner warrantyDate = (Spinner) findViewById(R.id.spinnerWarrantyDate);
        String[] warrantyDates = new String[]{"1 Year", "2 Year", "3 Year", "5 Year"};
        warrantyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, warrantyDates);
        warrantyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        warrantyDate.setAdapter(warrantyAdapter);
        warrantyDate.setOnItemSelectedListener(this);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~End Return Date Spinner

        if(b != null){
            fillData();
            edit = true;
        }
        else{
            edit = false;
        }
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getAdapter().equals(dateAdapter)){
            returnSelection = dateAdapter.getItem(position);
            Log.w("tag", "Return Selection: " + dateAdapter.getItem(position));
        }
        else{
            warrantySelection = warrantyAdapter.getItem(position);
            Log.w("tag", "Warranty Selection: " + warrantyAdapter.getItem(position));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // default selection values
        returnSelection = "30 Days";
        warrantySelection = "1 Year";
    }


    private void fillData(){
        String dataString = Helper.receipt.toString();
        String[] data = dataString.split("--");

        final ArrayList<String> stringList = new ArrayList<String>();
        for (int i = 0; i < data.length; ++i) {
            String[] temp = data[i].split("\\s+");
            if(temp.length == 6){
                data[i] = temp[1] + " " + temp[2] + " " + temp[5];
            }
        }
        stringList.add(data[0]);
        stringList.add(data[1]);
        stringList.add(data[2]);

        if(data.length > 3) {
            stringList.add(data[3]);
        }
        else{
            stringList.add("0000-00-00");
        }
        if(data.length > 4) {
            stringList.add(data[4]);
        }
        else{
            stringList.add("0000-00-00");
        }

        if(data.length > 5) {
            stringList.add(data[5]);
        }
        else{
            stringList.add("0000-00-00");
        }

        if(data.length > 6) {
            stringList.add(data[6]);
        }
        else {
            // no tags to add
            stringList.add("");
        }
        TextView t1 = (TextView)findViewById(R.id.editTextStoreName);
        TextView t2 = (TextView)findViewById(R.id.editTextPurchaseAmount);
        TextView t3 = (TextView)findViewById(R.id.editTextPurchaseDate);
        //TextView t4 = (TextView)findViewById(R.id.editTextReturnDate);
        //TextView t5 = (TextView)findViewById(R.id.editTextWarrantyDate);
        TextView t6 = (TextView)findViewById(R.id.editTextTags);
        TextView t7 = (TextView)findViewById(R.id.editTextName);

        t7.setText(stringList.get(0));
        t1.setText(stringList.get(1));
        t2.setText(stringList.get(2));
        t3.setText(stringList.get(3));
        //t4.setText(stringList.get(4));
        //t5.setText(stringList.get(5));
        t6.setText(stringList.get(6));

    }
    public void buttonSaveClick(View view) {
        //Helper.stub;
        TextView t1 = (TextView)findViewById(R.id.editTextStoreName);
        TextView t2 = (TextView)findViewById(R.id.editTextPurchaseAmount);
        TextView t3 = (TextView)findViewById(R.id.editTextPurchaseDate);
        //TextView t4 = (TextView)findViewById(R.id.editTextReturnDate);
        //TextView t5 = (TextView)findViewById(R.id.editTextWarrantyDate);
        TextView t6 = (TextView)findViewById(R.id.editTextTags);
        TextView t7 = (TextView)findViewById(R.id.editTextName);
        String store            = "" + t1.getText();
        String purchaseAmount   = "" + t2.getText();
        if (purchaseAmount.length() - purchaseAmount.indexOf(".") > 2){
            purchaseAmount = purchaseAmount.substring(0,purchaseAmount.indexOf(".")+ 3);
        }
        String purchaseDate     = "" + t3.getText();
        String returnDate       = "";// + t4.getText();
        String warrantyDate     = "";// + t5.getText();
        String tags             = "" + t6.getText();
        String name             = "" + t7.getText();
        //Log.w("tag",store + "\n"+ purchaseAmount + "\n"+ purchaseDate + "\n"+ returnDate + "\n"+ warrantyDate + "\n"+ tags);

        //"0000-00-00 00:00:00";
        String ending = " 00:00:00";
        if(purchaseDate.length() >= 11){
            purchaseDate = purchaseDate.substring(0,11);
            purchaseDate += ending;
        }
        else if(purchaseDate.length() < 11){
            purchaseDate += ending;
        }
/*
        if(returnDate.length() >= 11){
            returnDate = returnDate.substring(0,11);
            returnDate += ending;
        }
        else if(returnDate.length() < 11){
            returnDate += ending;
        }

        if(warrantyDate.length() >= 11){
            warrantyDate = warrantyDate.substring(0,11);
            warrantyDate += ending;
        }
        else if(warrantyDate.length() < 11){
            warrantyDate += ending;
        }
*/
        if(edit){
            Log.w("tag","" + purchaseDate + "\n" + returnDate + "\n" + warrantyDate);
            Receipt r = new Receipt(Helper.receipt.getRid(),name,store,Double.parseDouble(purchaseAmount),null,purchaseDate,returnDate,warrantyDate);

            Helper.stub.updateReceipt(r);
        }
        else {
            Receipt r = new Receipt(Helper.rid,name,store,Double.parseDouble(purchaseAmount),null,purchaseDate,returnDate,warrantyDate);
            Helper.rid += 1;
            Helper.stub.insertReceipt(r);
        }
       // Log.w("tag",r.toString());


        Intent intent = new Intent(this, GuiConfig1Activity.class);
        //intent.putExtra("", "");
        startActivity(intent);
    }
}
