package group8.comp4020.receiptmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TabHost;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import static android.app.Activity.RESULT_OK;


public class AddReceipt_MultiScreen_Scan extends Fragment implements View.OnClickListener{
    // interaction controls for the fragment
    ImageView imageView_Scan_Camera, imageView_Scan_Gallery;
    Button button_Scan_Next, button_Scan_Finish;
    CheckBox cb_Prog1, cb_Prog2, cb_Prog3, cb_Prog4;
    Intent pickPhotoIntent;
    Bitmap photo, image;

    private OnFragmentInteractionListener mListener;

    public AddReceipt_MultiScreen_Scan() {
        // Required empty public constructor
    }


    public static AddReceipt_MultiScreen_Scan newInstance() {
        AddReceipt_MultiScreen_Scan fragment = new AddReceipt_MultiScreen_Scan();
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
        View v = inflater.inflate(R.layout.fragment_add_receipt__multi_screen__scan, container, false);

        // add all interaction controls that need to be accessed in the fragment
        imageView_Scan_Camera = (ImageView)v.findViewById(R.id.imageView_Scan_Camera);
        imageView_Scan_Gallery = (ImageView)v.findViewById(R.id.imageView_Scan_Gallery);
        button_Scan_Next = (Button)v.findViewById(R.id.button_Scan_Next);
        button_Scan_Finish = (Button)v.findViewById(R.id.button_Basic_Finish);
        cb_Prog1 = (CheckBox) v.findViewById(R.id.checkBox_Scan_Prog1);
        cb_Prog2 = (CheckBox) v.findViewById(R.id.checkBox_Scan_Prog2);
        cb_Prog3 = (CheckBox) v.findViewById(R.id.checkBox_Scan_Prog3);
        cb_Prog4 = (CheckBox) v.findViewById(R.id.checkBox_Scan_Prog4);


        // sets up click listeners for the buttons (as they would otherwise have to be regsitered in the activity, not the fragment)
        setupScanEvents(inflater, v);

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
        switch (v.getId()) {
            case R.id.button_Scan_Next:
                TabHost host = (TabHost) getView().findViewById(android.R.id.tabhost);
                host.setCurrentTab(host.getCurrentTab() + 1);
                break;
            default:
                Log.d("", "Something was clicked!");
        }
    }

    public void calculate(View v) {

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

    /////////////////////////////////////////////////////////////////////////////////////
    private void setupScanEvents(LayoutInflater inflater, View v) {
        // button clicks
        button_Scan_Next.setOnClickListener(click_Next);
        button_Scan_Finish.setOnClickListener(click_Finish);

        // image area clicks
        imageView_Scan_Camera.setOnClickListener(click_Camera);
        imageView_Scan_Gallery.setOnClickListener(click_Gallery);
    }

    View.OnClickListener click_Camera = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            pickPhotoIntent = new Intent(getActivity(), PickPhotoActivity.class);
            startActivityForResult(pickPhotoIntent, 1);
        }
    };

    View.OnClickListener click_Gallery = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent(getActivity(), PickImageActivity.class);
            startActivityForResult(intent, 2);
            return;
        }
    };

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
            Receipt receipt = ((AddReceipt_MultiScreenActivity)getActivity()).getReceipt();
            if (photo != null) { receipt.SetImage(photo); }
            else if (image != null) { receipt.SetImage(image); }

            ((AddReceipt_MultiScreenActivity)getActivity()).submitReceipt();
            getActivity().finish();
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Log.d("My message", "Picture taken");
            if(data.hasExtra("byteArray")) {
                photo = BitmapFactory.decodeByteArray(
                        data.getByteArrayExtra("byteArray"),0,data.getByteArrayExtra("byteArray").length);
                imageView_Scan_Camera.setImageBitmap(photo);
                imageView_Scan_Gallery.setImageResource(android.R.drawable.ic_menu_report_image);
                image = null;
            }
        }

        else if (requestCode == 2 && resultCode == RESULT_OK) {
            Log.d("My message", "Image from gallery chosen");
            if(data.hasExtra("byteArray")) {
                image = BitmapFactory.decodeByteArray(
                        data.getByteArrayExtra("byteArray"),0,data.getByteArrayExtra("byteArray").length);
                imageView_Scan_Gallery.setImageBitmap(image);
                imageView_Scan_Camera.setImageResource(android.R.drawable.ic_menu_camera);
                photo = null;
            }
        }
    }
}
