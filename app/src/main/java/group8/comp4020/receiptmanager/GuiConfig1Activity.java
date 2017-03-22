package group8.comp4020.receiptmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class GuiConfig1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_config1);
        ListView list = (ListView) findViewById(R.id.ListView);
        RecyclerView list1 = (RecyclerView) findViewById(R.id.RecyclerView);
        String[] data = {"test","test1"};

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        //mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        list1.setLayoutManager(layoutManager);
      //  myRecyclerViewAdapter = new RecyclerViewAdapter(this);
       // list1.setAdapter(new RecyclerViewAdapter(data));
        /*
        need a RecyclerViewAdapter to add data but dont know how to build one.
         */




        ArrayAdapter itemsAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview,data);
        list.setAdapter(itemsAdapter);



        //testStub();




    }
    public void buttonHomeClick(View view) {
        Intent intent = new Intent(this, MainActivityScreen.class);
        //intent.putExtra("", "");
        startActivity(intent);
    }
    public void buttonEditClick(View view) {
        Intent intent = new Intent(this, MainActivityScreen.class);
        //intent.putExtra("", "");
        startActivity(intent);
    }
    public void buttonNewClick(View view) {
        Intent intent = new Intent(this, MainActivityScreen.class);
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

            ArrayList<Receipt> data6 = stub.getReceiptsWithTag("waffle");
            for(int i = 0; i < data6.size();i++){
                Receipt temp = data6.get(i);
                Log.w("tag",temp.getStore());
            }
            Log.w("tag",line  + " testing print with tag: cheese");

            ArrayList<Receipt> data7 = stub.getReceiptsWithTag("cheese");
            for(int i = 0; i < data7.size();i++){
                Receipt temp = data7.get(i);
                Log.w("tag",temp.getStore());
            }
            Log.w("tag",line);



        }
    }
}

