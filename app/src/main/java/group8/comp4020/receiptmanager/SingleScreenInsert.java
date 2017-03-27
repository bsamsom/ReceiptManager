package group8.comp4020.receiptmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ForwardingListener;
import android.support.v7.widget.ThemedSpinnerAdapter;
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
    public ArrayAdapter<String> warrantyAdapter;
    public ArrayAdapter<String> dateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_screen_insert);
        Bundle b = getIntent().getExtras();
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Return Date Spinner
        Spinner returnDate = (Spinner) findViewById(R.id.spinnerReturnDate);

        dateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Helper.returnDates);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        returnDate.setAdapter(dateAdapter);
        returnDate.setOnItemSelectedListener(this);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~End Return Date Spinner
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Return Date Spinner
        Spinner warrantyDate = (Spinner) findViewById(R.id.spinnerWarrantyDate);

        warrantyAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Helper.warrantyDates);
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
            //Log.w("tag", "Return Selection: " + dateAdapter.getItem(position));
        }
        else{
            warrantySelection = warrantyAdapter.getItem(position);
            //Log.w("tag", "Warranty Selection: " + warrantyAdapter.getItem(position));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // default selection values
        returnSelection = "None";
        warrantySelection = "None";
    }


    private void fillData(){
        String dataString = Helper.receipt.toString();
        String[] data = dataString.split("--");

        final ArrayList<String> stringList = new ArrayList<String>();
        stringList.add(data[0]);
        stringList.add(data[1]);
        stringList.add(data[2]);
        stringList.add(data[3]);

        int retPos = 0;
        int warPos = 0;

        for (int i = 0; i < Helper.returnDates.length;i++){
            String[] spinList = Helper.returnDates[i].split("\\s+");
            if(spinList[0].equals(data[4])){
                retPos = i;
            }
        }

        if(Integer.parseInt(data[5]) > 0) {
            for (int i = 0; i < Helper.warrantyDates.length; i++) {
                String[] spinList = Helper.warrantyDates[i].split("\\s+");
                if (spinList[0].equals(data[5])) {
                    warPos = i;
                }
            }
        }


        if(data.length > 6) {
            stringList.add(data[6]);
        }
        else{
            // no tags to add
            stringList.add("");
        }

        TextView t1 = (TextView)findViewById(R.id.editTextStoreName);
        TextView t2 = (TextView)findViewById(R.id.editTextPurchaseAmount);
        TextView t3 = (TextView)findViewById(R.id.editTextPurchaseDate);
        Spinner ret = (Spinner)findViewById(R.id.spinnerReturnDate);
        Spinner war = (Spinner)findViewById(R.id.spinnerWarrantyDate);
        TextView t6 = (TextView)findViewById(R.id.editTextTags);
        TextView t7 = (TextView)findViewById(R.id.editTextName);

        t7.setText(stringList.get(0));
        t1.setText(stringList.get(1));
        t2.setText(stringList.get(2));
        t3.setText(stringList.get(3));
        ret.setSelection(retPos);
        war.setSelection(warPos);
        t6.setText(stringList.get(4));

    }
    public void buttonSaveClick(View view) {
        //Helper.stub;
        TextView t1 = (TextView)findViewById(R.id.editTextStoreName);
        TextView t2 = (TextView)findViewById(R.id.editTextPurchaseAmount);
        TextView t3 = (TextView)findViewById(R.id.editTextPurchaseDate);
        Spinner ret = (Spinner)findViewById(R.id.spinnerReturnDate);
        Spinner war = (Spinner)findViewById(R.id.spinnerWarrantyDate);
        TextView t6 = (TextView)findViewById(R.id.editTextTags);
        TextView t7 = (TextView)findViewById(R.id.editTextName);
        String store            = "" + t1.getText();
        String purchaseAmount   = "" + t2.getText();
        if (purchaseAmount.length() - purchaseAmount.indexOf(".") > 2){
            purchaseAmount = purchaseAmount.substring(0,purchaseAmount.indexOf(".")+ 3);
        }
        String purchaseDate     = "" + t3.getText();
        String returnDate       = "" + ret.getSelectedItem();
        String warrantyDate     = "" + war.getSelectedItem();
        String tags             = "" + t6.getText();
        String name             = "" + t7.getText();
        //Log.w("tag",store + "\n"+ purchaseAmount + "\n"+ purchaseDate + "\n"+ returnDate + "\n"+ warrantyDate + "\n"+ tags);

        if(purchaseDate.length() >= 11){
            purchaseDate = purchaseDate.substring(0,11);
        }
        // incase user changes the - to a /
        purchaseDate = purchaseDate.replaceAll("/","-");

        if(edit){
           // Log.w("tag","" + purchaseDate + "\n" + returnDate + "\n" + warrantyDate);
            Receipt r = new Receipt(Helper.receipt.getRid(),name,store,Double.parseDouble(purchaseAmount),null,purchaseDate,returnDate,warrantyDate);
            if(!tags.equals("")){
                String[] temp = tags.split(",-/ ");
                for(int i = 0; i < temp.length;i++){
                    r.setTags(temp[i]);
                }
            }
            Helper.stub.updateReceipt(r);
        }
        else {
            if(returnDate.equalsIgnoreCase("None")){
                returnDate = "" + -1;
            }
            if(warrantyDate.equalsIgnoreCase("None")){
                warrantyDate = "" + -1;
            }

            Receipt r = new Receipt(Helper.rid,name,store,Double.parseDouble(purchaseAmount),null,purchaseDate,returnDate,warrantyDate);
            if(!tags.equals("")){
                String[] temp = tags.split(",-/ ");
                for(int i = 0; i < temp.length;i++){
                    r.setTags(temp[i]);
                }
            }

            Helper.rid += 1;
            Helper.stub.insertReceipt(r);
        }
       // Log.w("tag",r.toString());


        Intent intent = new Intent(this, GuiConfig1Activity.class);
        //intent.putExtra("", "");
        startActivity(intent);
    }
}
