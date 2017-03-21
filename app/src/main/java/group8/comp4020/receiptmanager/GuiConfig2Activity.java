package group8.comp4020.receiptmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GuiConfig2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gui_config2);

        Button home = (Button) findViewById(R.id.buttonHome);
        home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main_screen);
                TextView temp = (TextView) findViewById(R.id.textView3);
                temp.setText("test1");

            }
        });
    }
}
