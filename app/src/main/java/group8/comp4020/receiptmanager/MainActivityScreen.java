package group8.comp4020.receiptmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivityScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Button config1 = (Button) findViewById(R.id.buttonConfig1) ;
        Button config2 = (Button) findViewById(R.id.buttonConfig2) ;
        config1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // write code for experiment gui 1 here
                setContentView(R.layout.activity_gui_config1);
            }
        });
        config2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // write code for experiment gui 2 here
                setContentView(R.layout.activity_gui_config2);
            }
        });


    }
}
