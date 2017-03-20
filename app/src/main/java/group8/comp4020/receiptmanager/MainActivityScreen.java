package group8.comp4020.receiptmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivityScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });




        Button config1 = (Button) findViewById(R.id.buttonConfig1) ;
        Button config2 = (Button) findViewById(R.id.buttonConfig2) ;
        //config1.setOnClickListener(this);
        config1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // write code for experiment gui 1 here
            }
        });
        config2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // write code for experiment gui 2 here
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_screen, menu);
        return true;
    }

}
