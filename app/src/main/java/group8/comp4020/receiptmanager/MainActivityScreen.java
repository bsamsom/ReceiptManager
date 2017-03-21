package group8.comp4020.receiptmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
public class MainActivityScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

    }
    public void buttonOneClick(View view) {
        setContentView(R.layout.activity_gui_config1);
    }
    public void buttonTwoClick(View view) {
        setContentView(R.layout.activity_gui_config2);
    }
}
