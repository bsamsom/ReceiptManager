package group8.comp4020.receiptmanager;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class AddReceipt_MultiScreen_Expiry extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    // fragment fields
    private EditText editText_Amount;
    private Spinner spinner_Units, spinner_Expiry;

    private OnFragmentInteractionListener mListener;

    public AddReceipt_MultiScreen_Expiry() {
        // Required empty public constructor
    }

//    public static AddReceipt_MultiScreen_Expiry newInstance(String param1, String param2) {
//        AddReceipt_MultiScreen_Expiry fragment = new AddReceipt_MultiScreen_Expiry();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static AddReceipt_MultiScreen_Expiry newInstance() {
        AddReceipt_MultiScreen_Expiry fragment = new AddReceipt_MultiScreen_Expiry();
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
        View v = inflater.inflate(R.layout.fragment_add_receipt__multi_screen__expiry, container, false);


        // gets all page controls that will be used to fill in data
        editText_Amount = (EditText) v.findViewById(R.id.editText_Amount);
        spinner_Expiry = (Spinner) v.findViewById(R.id.spinner_Expiry);
        spinner_Units = (Spinner) v.findViewById(R.id.spinner_Units);

        // loads the pre-formed expiry date spinner with choices
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(), R.array.expiry_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Expiry.setAdapter(adapter);
        spinner_Expiry.setOnItemSelectedListener(this);
        spinner_Expiry.setOnCreateContextMenuListener(this);
        spinner_Expiry.setSelection(0);

        // loads the units spinner with choices
        ArrayAdapter<CharSequence> adapterUnits = ArrayAdapter.createFromResource(v.getContext(), R.array.expiry_units, android.R.layout.simple_spinner_item);
        adapterUnits.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_Units.setAdapter(adapterUnits);
        spinner_Units.setOnItemSelectedListener(this);
        spinner_Units.setOnCreateContextMenuListener(this);
        spinner_Units.setSelection(0);



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
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        Log.d("ITEM SELECTED", adapterView.getItemAtPosition(pos).toString());
        switch (adapterView.getId()) {
            case R.id.spinner_Expiry:
                if (adapterView.getItemAtPosition(pos).toString().equals("Specify...")) {
                    editText_Amount.setVisibility(View.VISIBLE);
                    spinner_Units.setVisibility(View.VISIBLE);
                }
                else {
                    editText_Amount.setVisibility(View.INVISIBLE);
                    spinner_Units.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
//        if (adapterView.getSelectedItem().toString().equals("[Select expiry]"))
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
