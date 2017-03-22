package group8.comp4020.receiptmanager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;

public class MethodChoiceScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_choice_screen);

    }
    public void buttonOneClick(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("method_type", 1);
        startActivity(intent);
    }
    public void buttonTwoClick(View view) {
//        setContentView(R.layout.activity_gui_config2);        // prevents jump to other activity
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("method_type", 2);
        startActivity(intent);
    }
}
