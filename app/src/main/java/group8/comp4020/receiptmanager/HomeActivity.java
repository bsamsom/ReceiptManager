package group8.comp4020.receiptmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {
    private RunSettings runSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // get intent (only if it is coming from method choice screen)
        int methodType = getIntent().getExtras().getInt("method_type", -1);
        if (methodType != -1)
            RunSettings.getInstance().setMethod(methodType);
    }
}
