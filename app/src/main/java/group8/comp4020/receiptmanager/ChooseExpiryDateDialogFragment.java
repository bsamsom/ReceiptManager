package group8.comp4020.receiptmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.NotificationCompatExtras;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Mark on 3/24/2017.
 */

public class ChooseExpiryDateDialogFragment extends DialogFragment {

    // delivers action events
    ChooseExpiryDateDialogListener listener;
    View v;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // assign a custom view
        LayoutInflater inflater = this.getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.dialog_choose_expiry, null);
        builder.setView(v)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // gets amount and type
                        EditText amount = (EditText) v.findViewById(R.id.text_Amount);
                        RadioGroup testRadioGroup = (RadioGroup) v.findViewById(R.id.rgp_dateUnits);
                        Log.d("MY TEXT -------------", amount.getText().toString());
                        RadioButton selected = (RadioButton) v.findViewById( ((RadioGroup)v.findViewById(R.id.rgp_dateUnits)).getCheckedRadioButtonId());

                        listener.onDialogPositiveClick(ChooseExpiryDateDialogFragment.this);
//                        listener.onDialogPositiveClick(ChooseExpiryDateDialogFragment.this,
//                                Integer.parseInt(amount.getText().toString()),
//                                selected.getText().toString()
//                                );
                        // amount, type
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ChooseExpiryDateDialogFragment.this.getDialog().cancel();
                    }
                });

        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach (Activity activity)
    {
        super.onAttach(activity);
//        try {
//            listener = (ChooseExpiryDateDialogListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException((activity.toString()) + "must implement NoticeDialogListener");
//        }
    }


    // interface to listen to dialog events from main activity
    public interface ChooseExpiryDateDialogListener {
//        public void onDialogPositiveClick (DialogFragment dialog, int amount, String type);
        public void onDialogPositiveClick(DialogFragment dialog);
    }
}