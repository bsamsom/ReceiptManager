package group8.comp4020.receiptmanager;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class Search_SingleScreen extends AppCompatActivity {
    private int i;
   // public int position = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__single_screen);
        Helper.receipt = Helper.stub.getAllReceipts().get(0);
        fillData();
    }
    private void fillData(){
        //final ArrayList<String> stringList = new ArrayList<String>();
        ArrayList<Receipt> receipts = Helper.stub.getAllReceipts();
        Collections.sort(receipts);

        final ListView[] list = new ListView[receipts.size()];
        LinearLayout layout = (LinearLayout) findViewById(R.id.LinearSingleSearch);
        layout.setShowDividers(LinearLayout.SHOW_DIVIDER_END);

        for (i = 0; i < receipts.size();i++){
            list[i] = new ListView(this);
            Receipt r = receipts.get(i);
            //stringList.add(r.getStore() + " " + r.getName());

            addReceiptToListView(list[i], receipts.get(i));
            //CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.activity_listview, stringList);
            //list[i].setAdapter(adapter);
            list[i].setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                    ArrayList<Receipt> rec = Helper.stub.getAllReceipts();
                    ListView l = ((ListView)arg0);
                    for(int j = 0; j < rec.size();j++){
                        if(list[j].equals(l)){
                            //Log.w("tag"," position = " + j);
                            Helper.receipt = rec.get(j);
                        }
                    }
                }
            });
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setStroke(4, Color.BLACK);
            list[i].setBackground(gradientDrawable);


            layout.addView(list[i]);
        }


        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setStroke(4, Color.BLACK);
        layout.setBackground(gradientDrawable);

    }
    public void addReceiptToListView(ListView list, Receipt receipt){
        String dataString = receipt.toString().trim();
        String[] data = dataString.split("--");
        final ArrayList<String> stringList = new ArrayList<String>();
        stringList.add("Store: " + data[1] + "\n" + "Name: " + data[0] + "\n" + "Purchase Date: " + data[3]);

        CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.activity_listview, stringList);
        list.setAdapter(adapter);
    }

    public void buttonEditReceipt(View view) {
       // Helper.receipt = Helper.stub.getAllReceipts().get(position);
        Intent intent = new Intent(this, SingleScreenInsert.class);
        intent.putExtra("edit", "extraData");

        startActivity(intent);
    }

}
