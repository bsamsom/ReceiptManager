package group8.comp4020.receiptmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

    public void click_add(View view) {
        Intent intent;
        if (RunSettings.getInstance().getMethod() == 2)
            intent = new Intent(this, AddReceipt_MultiScreenActivity.class);
        else if (RunSettings.getInstance().getMethod() == 1)
            intent = new Intent(this, SingleScreenInsert.class);
        else {
            intent = null;
            Log.d("HomeActivity", "UNABLE TO START NEW ACTIVITY: experiment method is " +
                    RunSettings.getInstance().getMethod());
            return;
        }

        startActivity(intent);
    }

    public void click_view(View view) {
        Intent intent;
        if (RunSettings.getInstance().getMethod() == 2) {
            intent = new Intent(this, Search_MultiScreen.class);
        }
        else if (RunSettings.getInstance().getMethod() == 1)
            intent = new Intent(this, Search_SingleScreen.class);
        else {
            intent = null;
            Log.d("HomeActivity", "MADE IT!!, UNABLE TO START NEW ACTIVITY: experiment method is " +
                    RunSettings.getInstance().getMethod());
            return;
        }
        startActivity(intent);
    }
}
