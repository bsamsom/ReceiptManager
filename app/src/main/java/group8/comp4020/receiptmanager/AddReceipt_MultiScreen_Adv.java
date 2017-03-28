package group8.comp4020.receiptmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;


public class AddReceipt_MultiScreen_Adv extends Fragment{

    private Button button_Adv_Next, button_Adv_Finish;

    private Adapter_Tag adapter;
    private ArrayList<String> tags;
    private Receipt newReceipt;
    public ListView listView_TagList;

    private OnFragmentInteractionListener mListener;

    public AddReceipt_MultiScreen_Adv() {
        // Required empty public constructor
    }


    public static AddReceipt_MultiScreen_Adv newInstance() {
        AddReceipt_MultiScreen_Adv fragment = new AddReceipt_MultiScreen_Adv();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tags = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_receipt__multi_screen__adv, container, false);

        // add all interaction controls that need to be accessed in the fragment
        button_Adv_Finish = (Button)v.findViewById(R.id.button_Adv_Finish);
        button_Adv_Finish.setOnClickListener(click_Finish);
//        cb_Prog1 = (CheckBox) v.findViewById(R.id.checkBox_Basic_Prog1);
//        cb_Prog2 = (CheckBox) v.findViewById(R.id.checkBox_Basic_Prog2);
//        cb_Prog3 = (CheckBox) v.findViewById(R.id.checkBox_Basic_Prog3);
//        cb_Prog4 = (CheckBox) v.findViewById(R.id.checkBox_Basic_Prog4);

        // sets up the adapter area

        tags.add("");

        listView_TagList = (ListView) v.findViewById(R.id.listView_TagList);
        adapter = new Adapter_Tag(getActivity(), tags, this);
        listView_TagList.setAdapter(adapter);
        listView_TagList.setItemsCanFocus(true);
        listView_TagList.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS); // go along with android:windowSoftInputMode="AdjustPan" in android manifest for activity


        newReceipt = ((AddReceipt_MultiScreenActivity)getActivity()).getReceipt();

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void refreshList() {
        adapter.notifyDataSetChanged();
    }

    public void saveTags() {
        for (int i = 0; i < tags.size(); i++)
            newReceipt.setTags(tags.get(i));
    }

    View.OnClickListener click_Finish = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ((AddReceipt_MultiScreenActivity)getActivity()).submitReceipt();
            getActivity().finish();
        }
    };

}
