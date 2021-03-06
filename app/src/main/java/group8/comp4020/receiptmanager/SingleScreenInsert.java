package group8.comp4020.receiptmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SingleScreenInsert extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public boolean edit = false;
    public String returnSelection = "30 Days";
    public String warrantySelection = "1 Year";
    public ArrayAdapter<String> warrantyAdapter;
    public ArrayAdapter<String> dateAdapter;
    public Bitmap image = null;

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
       // TextView t2 = (TextView)findViewById(R.id.editTextPurchaseAmount);
        TextView t3_day = (TextView) findViewById(R.id.editText_Single_Date);
        TextView t3_month = (TextView) findViewById(R.id.editText_Single_Month);
        TextView t3_year = (TextView) findViewById(R.id.editText_Single_Year);
        Spinner ret = (Spinner)findViewById(R.id.spinnerReturnDate);
        Spinner war = (Spinner)findViewById(R.id.spinnerWarrantyDate);
        TextView t6 = (TextView)findViewById(R.id.editTextTags);
        TextView t7 = (TextView)findViewById(R.id.editTextName);

        String[] dateFields = stringList.get(3).split("-");

        t7.setText(stringList.get(0));
        t1.setText(stringList.get(1));
        t3_day.setText(dateFields[2]);
        t3_month.setText(dateFields[1]);
        t3_year.setText(dateFields[0]);
        //t2.setText(stringList.get(2));
        ret.setSelection(retPos);
        war.setSelection(warPos);
        t6.setText(stringList.get(4));

    }
    public void buttonSaveClick(View view) {
        //Helper.stub;
        TextView t1 = (TextView)findViewById(R.id.editTextStoreName);
//        TextView t3 = (TextView)findViewById(R.id.editTextPurchaseDate);
        TextView t3_day = (TextView) findViewById(R.id.editText_Single_Date);
        TextView t3_month = (TextView) findViewById(R.id.editText_Single_Month);
        TextView t3_year = (TextView) findViewById(R.id.editText_Single_Year);
        Spinner ret = (Spinner)findViewById(R.id.spinnerReturnDate);
        Spinner war = (Spinner)findViewById(R.id.spinnerWarrantyDate);
        TextView t6 = (TextView)findViewById(R.id.editTextTags);
        TextView t7 = (TextView)findViewById(R.id.editTextName);
        String store            = "" + t1.getText();
        String purchaseAmount   = "" + 0;
        /*
        if (purchaseAmount.length() - purchaseAmount.indexOf(".") > 2){
            purchaseAmount = purchaseAmount.substring(0,purchaseAmount.indexOf(".")+ 3);
        }
        */

        String purchaseDate = "";
        int returnDate          = ret.getSelectedItemPosition();
        int warrantyDate        = war.getSelectedItemPosition();
        String tags             = "" + t6.getText();
        String name             = "" + t7.getText();
        //Log.w("tag",store + "\n"+ purchaseAmount + "\n"+ purchaseDate + "\n"+ returnDate + "\n"+ warrantyDate + "\n"+ tags);

        String day = "00";
        String month = "00";
        String year = "0000";
        // purchase date
        if (!t3_day.getText().toString().trim().equals(""))
            day = new DecimalFormat("00").format(Integer.parseInt(t3_day.getText().toString().trim()));
        if (!t3_month.getText().toString().trim().equals(""))
            month = new DecimalFormat("00").format(Integer.parseInt(t3_month.getText().toString().trim()));
        if (!t3_year.getText().toString().trim().equals(""))
            year = new DecimalFormat("0000").format(Integer.parseInt(t3_year.getText().toString().trim()));

        purchaseDate = year + "-" + month + "-" + day;

        if(edit){
           // Log.w("tag","" + purchaseDate + "\n" + returnDate + "\n" + warrantyDate);
            //image = Helper.receipt.getImage();
           image = Helper.img;
            Receipt r = new Receipt(Helper.receipt.getRid(),name,store,Double.parseDouble(purchaseAmount),image,purchaseDate,returnDate,warrantyDate);
            if(!tags.equals("")){
                String[] temp = tags.split(",-/ ");
                for(int i = 0; i < temp.length;i++){
                    r.setTags(temp[i]);
                }
            }
            Helper.stub.updateReceipt(r);
        }
        else {
            Receipt r = new Receipt(Helper.receipt.getRid(),name,store,Double.parseDouble(purchaseAmount),Helper.img,purchaseDate,returnDate,warrantyDate);
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

        finish();
    }
    public void buttonPictureSelect(View view) {
        Intent intent;
        intent = new Intent(this,PickImageActivity.class);
        startActivity(intent);
    }
}
