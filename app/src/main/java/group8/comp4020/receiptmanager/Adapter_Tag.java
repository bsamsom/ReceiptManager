package group8.comp4020.receiptmanager;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mark on 3/27/2017.
 */

public class Adapter_Tag extends ArrayAdapter<String> {

    private ArrayList<String> tags;
    private final Context context;
    private AddReceipt_MultiScreen_Adv hook;
    private int index;
    private int removeID;

    public Adapter_Tag(Context context, ArrayList<String> tags, AddReceipt_MultiScreen_Adv hook)
    {
        super(context, 0, tags);
        this.tags = tags;
        this.context = context;
        this.hook = hook;
        // this.ids = new ArrayList<>();

    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        String tag = tags.get(position);
        this.index = position;

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_tag, parent, false);

        EditText editText_Input = (EditText) convertView.findViewById(R.id.editText_Adapter_TagName);
        Button button_Add = (Button) convertView.findViewById(R.id.button_Adapter_Add);
        Button button_Remove = (Button) convertView.findViewById(R.id.button_Adapter_Remove);
        TextView textView_AddText = (TextView) convertView.findViewById(R.id.textView_Adapter_AddText);

        editText_Input.setText(tag);

        // adds a unique ID to the view
//        removeID = convertView.generateViewId();
//        button_Remove.setId(removeID);

        final RowViewHolder staticElements = new RowViewHolder();
        staticElements.button_Remove = button_Remove;
        staticElements.button_Add = button_Add;
        staticElements.field = editText_Input;

        staticElements.button_Remove.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                if (tags.size() > 1) {
                    final int position = hook.listView_TagList.getPositionForView((View) v.getParent());
                    tags.remove(position);
                    hook.refreshList();
                    Log.d("MY MESSAGE", "CLICKED THE ID:" + removeID);
                    Log.d("MY MESSAGE", "Position: " + position);
                }
            }
        });

        staticElements.button_Add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick (View v)
            {
                tags.add("");
                hook.refreshList();
            }
        });

        staticElements.field.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!view.hasFocus())
                {
                    Log.d("error:", "field lost focus");
                    final int position = hook.listView_TagList.getPositionForView( (View) view.getParent());
                    tags.set(position, ((EditText)view).getText().toString());
                    hook.refreshList();
                    hook.saveTags();
                }
            }
        });


        if (position < tags.size() - 1) {
            button_Add.setVisibility(View.GONE);
            textView_AddText.setVisibility(View.GONE);
        }
        else {
            button_Add.setVisibility(View.VISIBLE);
            textView_AddText.setVisibility(View.VISIBLE);
        }

        return convertView;

    }


    protected static class RowViewHolder {
        public Button button_Remove;
        public Button button_Add;
        public EditText field;
    }
}
