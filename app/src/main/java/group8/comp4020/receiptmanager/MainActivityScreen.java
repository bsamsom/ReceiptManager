package group8.comp4020.receiptmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.content.Intent;

public class MainActivityScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

    }
    public void buttonOneClick(View view) {
        setContentView(R.layout.activity_gui_config1);
        Intent intent = new Intent(this, GuiConfig1Activity.class);
        //intent.putExtra("", "");
        startActivity(intent);
    }
    public void buttonTwoClick(View view) {
        setContentView(R.layout.activity_gui_config1);
        Intent intent = new Intent(this, GuiConfig2Activity.class);
        //intent.putExtra("", "");
        startActivity(intent);
    }
    /*public void buttonHomeClick(View view) {
        Log.w("tag","Trying to find test data ");
        // setContentView(R.layout.activity_main_screen);
    }
    */
}
