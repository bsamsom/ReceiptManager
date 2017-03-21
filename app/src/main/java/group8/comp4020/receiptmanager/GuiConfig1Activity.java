package group8.comp4020.receiptmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GuiConfig1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_config1);
        StubDatabase stub = new StubDatabase();
        
        ArrayList<Receipt> data = stub.getAllReceipts();
        List<String> listDataHeader = new ArrayList<String>();
        HashMap<String, List<String>> listDataChild = new HashMap<String, List<String>>();

        listDataHeader.add("Current Receipts");
        if (data != null){
            List<String> storeNames = new ArrayList<String>();
            for(int i = 0; i < data.size();i++){
                Receipt temp = data.get(i);
                storeNames.add(temp.store);
            }
            listDataChild.put(listDataHeader.get(0),storeNames);
            ExpandableListView temp = (ExpandableListView) findViewById(R.id.ListReceipts);
            ExpandableListAdapter listAdapter = new CustomExpandableListAdapter(this, listDataHeader, listDataChild);
            temp.setAdapter(listAdapter);

        }




    }
    public void buttonHomeClick(View view) {
        setContentView(R.layout.activity_main_screen);
    }
    public void buttonEditClick(View view) {
        setContentView(R.layout.activity_main_screen);
    }
    public void buttonNewClick(View view) {
        setContentView(R.layout.activity_main_screen);
    }
}

