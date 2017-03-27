package group8.comp4020.receiptmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class AddReceipt_MultiScreen_Adv extends Fragment implements View.OnClickListener{
    // private static final String ARG_PARAM1 = "param1";

    private Adapter_Tag adapter;
    private ArrayList<String> tags;

    private OnFragmentInteractionListener mListener;

    public AddReceipt_MultiScreen_Adv() {
        // Required empty public constructor
    }


//    public static AddReceipt_MultiScreen_Adv newInstance(String param1, String param2) {
//        AddReceipt_MultiScreen_Adv fragment = new AddReceipt_MultiScreen_Adv();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static AddReceipt_MultiScreen_Adv newInstance() {
        AddReceipt_MultiScreen_Adv fragment = new AddReceipt_MultiScreen_Adv();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tags = new ArrayList<String>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_receipt__multi_screen__adv, container, false);

        // sets up the adapter area
        // todo: figure out why this isn't working
        // adapter = new Adapter_Tag(getActivity().getApplicationContext(), tags);


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
}
