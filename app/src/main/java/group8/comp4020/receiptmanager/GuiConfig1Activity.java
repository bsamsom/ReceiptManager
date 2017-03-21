package group8.comp4020.receiptmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GuiConfig1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_config1);
        setContentView(R.layout.activity_gui_config2);

        Button home = (Button) findViewById(R.id.buttonHome1);
        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main_screen);
            }
        });
    }
}

