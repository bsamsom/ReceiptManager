package group8.comp4020.receiptmanager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;

public class Search_MultiScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_multi_screen);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Sort Spinner
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        String[] items = new String[]{"All", "With Warranty", "Without Warranty", "With Tags"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~End Sort Spinner

        ArrayList<Receipt> receipts = Helper.stub.getAllReceipts();
        Collections.sort(receipts);
        addreceipts(receipts);
        Helper.receipt = receipts.get(0);

        //testStub();
    }
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //Log.w("tag", "Get item at: " + position);
        if(position == 0){
            ArrayList<Receipt> receipts = Helper.stub.getAllReceipts();
            addreceipts(receipts);
        }
        else if(position == 1){
            ArrayList<Receipt> receipts = Helper.stub.getReceiptsWithWarranty();
            addreceipts(receipts);
        }
        else if(position == 2){
            ArrayList<Receipt> receipts = Helper.stub.getReceiptsWithOutWarranty();
            addreceipts(receipts);
        }
        else{
            ArrayList<Receipt> receipts = Helper.stub.getReceiptsWithTag();
            addreceipts(receipts);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void addreceipts(final ArrayList<Receipt> receipts){
        final ListView[] list = new ListView[receipts.size()];
        LinearLayout layout = (LinearLayout) findViewById(R.id.LinearLayoutList);
        //layout.setLayoutParams(layout.getParent().getLayoutParams());
        int width = 700;
        int height = 400;

        layout.removeAllViews();
        ViewGroup.LayoutParams params = layout.getLayoutParams();
        int maxHeight =  params.height;
        for(i = 0; i < receipts.size();i++){
            LinearLayout layout1 = new LinearLayout(this);

            params = layout.getLayoutParams();
            params.height = params.height - height;
            params.width = width;

            layout1.setOrientation(LinearLayout.VERTICAL);
            layout1.setLayoutParams(params);

            list[i] = new ListView(this);
            addReceiptToListView(list[i], receipts.get(i));
            layout1.addView(list[i]);

            ImageView img = new ImageView(this);
            if (receipts.get(i).getImage() == null) {
                img.setImageResource(R.mipmap.ic_launcher);
            }
            else{
                Image image = receipts.get(i).getImage();
                ByteBuffer buffer = image.getPlanes()[0].getBuffer();
                byte[] bytes = new byte[buffer.capacity()];
                buffer.get(bytes);
                Bitmap bitmapImage = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, null);
                img.setImageBitmap(bitmapImage);
            }

            params = new ViewGroup.LayoutParams(width,height);
            img.setLayoutParams(params);
            layout1.addView(img);

            params = list[i].getLayoutParams();
            params.width = width;
            //params.height = maxHeight -height;
            //Log.w("tag","MAX HEIGHT: " + maxHeight);
            params.height = 750;


            list[i].setLayoutParams(params);
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setStroke(4,Color.BLACK);
            list[i].setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    ListView l = ((ListView)arg0);
                    for(int j = 0; j < receipts.size();j++){
                        if(list[j].equals(l)){
                            Helper.receipt = receipts.get(j);
                        }
                    }
                }
            });
            layout1.setBackground(gradientDrawable);
            layout1.requestLayout();
            layout.addView(layout1);
        }

    }
    public void addReceiptToListView(ListView list, Receipt receipt){
        String dataString = receipt.toString().trim();
        String[] data = dataString.split("--");
        final ArrayList<String> stringList = new ArrayList<String>();
        /*
        Log.w("tag",receipt.toString().trim());
        for(int i = 0; i < data.length;i++) {
            Log.w("tag", data[i]);
        }
        */
        stringList.add("Name: "             + data[0]);
        stringList.add("Store: "            + data[1]);
        //stringList.add("Purchase: "         + data[2]);
        stringList.add("Purchase Date: "    + data[3]);
        String addRet = "Return Date: "     + Helper.returnDates[Integer.parseInt(data[4])];
        String addWar = "Warranty Date: "   + Helper.warrantyDates[Integer.parseInt(data[5])];

        stringList.add(addRet);
        stringList.add(addWar);

        if(data.length > 6) {
            stringList.add("Tags: " + data[6]);
        }
        else{
            // no tags to add
            stringList.add("Tags: " + "");
        }

        CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.activity_listview, stringList);
        list.setAdapter(adapter);
    }
    public void buttonDeleteClick(View view) {
        Helper.stub.deleteReceipt(Helper.receipt);
        ArrayList<Receipt> list = Helper.stub.getAllReceipts();
        addreceipts(list);
        Helper.receipt = list.get(0);
    }
    public void buttonEditClick(View view) {
        //Helper.receipt = Helper.stub.getReceipt(1);


        Intent intent = new Intent(this, SingleScreenInsert.class);
        intent.putExtra("edit", "extraData");
        startActivity(intent);
    }
    public void buttonNewClick(View view) {
        Intent intent = new Intent(this, AddReceipt_MultiScreenActivity.class);
        //intent.putExtra("", "");
        startActivity(intent);
    }
    private void testStub(){
        StubDatabase stub = new StubDatabase();
        ArrayList<Receipt> data = stub.getAllReceipts();

        if (data != null){
            String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";

            Log.w("tag",line + " testing print all");
            for(int i = 0; i < data.size();i++){
                Receipt temp = data.get(i);
                Log.w("tag",temp.getStore());
            }
            Log.w("tag",line  + " testing sort");
            Helper help = new Helper();
            data = help.sortByDate(data);


            for(int i = 0; i < data.size();i++){
                Receipt temp = data.get(i);
                Log.w("tag",temp.getPurchaseDate() + " " + temp.getStore());
            }
            Log.w("tag",line  + " testing print with out warranty");

            ArrayList<Receipt> data2 = stub.getReceiptsWithOutWarranty();
            for(int i = 0; i < data2.size();i++){
                Receipt temp = data2.get(i);
                Log.w("tag",temp.getStore());
            }
            Log.w("tag",line  + " testing print purchase date range");

            String start = "1994-09-04 18:35:24";
            String end = "2016-14-05 18:35:24";

            Log.w("tag",start);
            ArrayList<Receipt> data3 = stub.getReceiptsByPurchaseDate(start,end);
            for(int i = 0; i < data3.size();i++){
                Receipt temp = data3.get(i);
                Log.w("tag",temp.getPurchaseDate() + " " + temp.getStore());
            }
            Log.w("tag",end);
            Log.w("tag",line);

            Log.w("tag",line  + " testing print warrenty date range");
            end = "2030-14-05 18:35:24";
            start = "1997-09-04 18:35:24";
            Log.w("tag",start);
            ArrayList<Receipt> data4 = stub.getReceiptsByWarrantyDate(start,end);
            for(int i = 0; i < data4.size();i++){
                Receipt temp = data4.get(i);
                Log.w("tag",temp.getWarrantyDate() + " " + temp.getStore());
            }
            Log.w("tag",end);
            Log.w("tag",line  + " testing print with warranty");

            ArrayList<Receipt> data5 = stub.getReceiptsWithWarranty();
            for(int i = 0; i < data5.size();i++){
                Receipt temp = data5.get(i);
                Log.w("tag",temp.getStore());
            }

            Log.w("tag",line  + " testing print with tag: waffle");

            ArrayList<Receipt> data6 = stub.getReceiptsWithSpecificTag("waffle");
            for(int i = 0; i < data6.size();i++){
                Receipt temp = data6.get(i);
                Log.w("tag",temp.getStore());
            }
            Log.w("tag",line  + " testing print with tag: cheese");

            ArrayList<Receipt> data7 = stub.getReceiptsWithSpecificTag("cheese");
            for(int i = 0; i < data7.size();i++){
                Receipt temp = data7.get(i);
                Log.w("tag",temp.getStore());
            }
            Log.w("tag",line);



        }
    }
}

