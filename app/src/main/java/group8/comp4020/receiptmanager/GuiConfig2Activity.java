package group8.comp4020.receiptmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class GuiConfig2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_config2);
    }
    public void buttonOneClick(View view) {
        Intent intent = new Intent(this, MainActivityScreen.class);
        //intent.putExtra("", "");
        startActivity(intent);
    }
}
