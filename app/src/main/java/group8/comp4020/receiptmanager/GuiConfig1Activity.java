package group8.comp4020.receiptmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;

public class GuiConfig1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_config1);
        StubDatabase stub = new StubDatabase();

        ArrayList<Receipt> data = stub.getAllReceipts();

        if (data != null){
            for(int i = 0; i < data.size();i++){
                Receipt temp = data.get(i);
                Log.w("tag",temp.getStore());
            }
        }




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
}

