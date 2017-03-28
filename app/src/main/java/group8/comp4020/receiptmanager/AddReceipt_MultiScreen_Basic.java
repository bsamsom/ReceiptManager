package group8.comp4020.receiptmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class AddReceipt_MultiScreen_Basic extends Fragment implements View.OnClickListener, View.OnFocusChangeListener{
    private EditText editText_ReceiptName,  editText_PurchaseCategory, editText_PurchasedAt;
    private EditText editText_DateOfPurchase_Day, editText_DateOfPurchase_Month, editText_DateOfPurchase_Year;
    private Button button_Basic_Next, button_Basic_Finish;
    private CheckBox cb_Prog1, cb_Prog2, cb_Prog3, cb_Prog4;
    private Receipt newReceipt;

    private OnFragmentInteractionListener mListener;

    public AddReceipt_MultiScreen_Basic() {
        // Required empty public constructor
    }


//    public static AddReceipt_MultiScreen_Basic newInstance(String param1, String param2) {
//        AddReceipt_MultiScreen_Basic fragment = new AddReceipt_MultiScreen_Basic();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static AddReceipt_MultiScreen_Basic newInstance() {
        AddReceipt_MultiScreen_Basic fragment = new AddReceipt_MultiScreen_Basic();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_receipt__multi_screen__basic, container, false);

        // add all interaction controls that need to be accessed in the fragment
        button_Basic_Next = (Button)v.findViewById(R.id.button_Basic_Next);
        button_Basic_Finish = (Button)v.findViewById(R.id.button_Basic_Finish);
        cb_Prog1 = (CheckBox) v.findViewById(R.id.checkBox_Basic_Prog1);
        cb_Prog2 = (CheckBox) v.findViewById(R.id.checkBox_Basic_Prog2);
        cb_Prog3 = (CheckBox) v.findViewById(R.id.checkBox_Basic_Prog3);
        cb_Prog4 = (CheckBox) v.findViewById(R.id.checkBox_Basic_Prog4);

        editText_ReceiptName = (EditText) v.findViewById(R.id.editText_Basic_ReceiptName);
        editText_PurchaseCategory = (EditText) v.findViewById(R.id.editText_Basic_PurchaseCategory);
        editText_PurchasedAt = (EditText) v.findViewById(R.id.editText_Basic_PurchasedAt);
        editText_ReceiptName = (EditText) v.findViewById(R.id.editText_Basic_ReceiptName);
        editText_DateOfPurchase_Day = (EditText) v.findViewById(R.id.editText_Basic_DateOfPurchase_Day);
        editText_DateOfPurchase_Month = (EditText) v.findViewById(R.id.editText_Basic_DateOfPurchase_Month);
        editText_DateOfPurchase_Year = (EditText) v.findViewById(R.id.editText_Basic_DateOfPurchase_Year);

        newReceipt = ((AddReceipt_MultiScreenActivity)getActivity()).getReceipt();


        // sets up click listeners for the buttons (as they would otherwise have to be regsitered in the activity, not the fragment)
        setupBasicEvents(inflater, v);

        return v;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onFocusChange (View v, boolean hasFocus) {
        // records data only on loss of focus
        if (!hasFocus) {
            switch (v.getId()) {

                // Receipt name
                case R.id.editText_Basic_ReceiptName:
                    newReceipt.SetName(editText_ReceiptName.getText().toString().trim());
                    break;

                // Purchased at
                case R.id.editText_Basic_PurchasedAt:
                    newReceipt.setPurchasedAt(editText_PurchasedAt.getText().toString().trim());
                    break;

                // category
                case R.id.editText_Basic_PurchaseCategory:
                    newReceipt.setPurchaseCategory(editText_PurchaseCategory.getText().toString().trim());
                    break;

                // purchase date
                case R.id.editText_Basic_DateOfPurchase_Day:
                    if (!editText_DateOfPurchase_Day.getText().toString().trim().equals(""))
                        newReceipt.setPurchaseDate_Date (Integer.parseInt (editText_DateOfPurchase_Day.getText().toString().trim()));
                    break;
                case R.id.editText_Basic_DateOfPurchase_Month:
                    if (!editText_DateOfPurchase_Month.getText().toString().trim().equals(""))
                        newReceipt.setPurchaseDate_Month (Integer.parseInt (editText_DateOfPurchase_Month.getText().toString().trim()));
                    break;
                case R.id.editText_Basic_DateOfPurchase_Year:
                    if (!editText_DateOfPurchase_Year.getText().toString().trim().equals(""))
                        newReceipt.setPurchaseDate_Year (Integer.parseInt (editText_DateOfPurchase_Year.getText().toString().trim()));
                    break;

            }
        }
    }

    private void setupBasicEvents(LayoutInflater inflater, View v) {
        // button clicks
        button_Basic_Next.setOnClickListener(click_Next);
        button_Basic_Finish.setOnClickListener(click_Finish);

        // focus change
        editText_PurchasedAt.setOnFocusChangeListener(this);
        editText_PurchaseCategory.setOnFocusChangeListener(this);
        editText_ReceiptName.setOnFocusChangeListener(this);
        editText_DateOfPurchase_Day.setOnFocusChangeListener(this);
        editText_DateOfPurchase_Month.setOnFocusChangeListener(this);
        editText_DateOfPurchase_Year.setOnFocusChangeListener(this);
    }

    View.OnClickListener click_Next = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ViewPager tabs = ((AddReceipt_MultiScreenActivity)getActivity()).getViewPager();
            tabs.setCurrentItem(tabs.getCurrentItem() + 1);
        }
    };

    View.OnClickListener click_Finish = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
             ((AddReceipt_MultiScreenActivity)getActivity()).submitReceipt();
            getActivity().finish();
        }
    };


}
