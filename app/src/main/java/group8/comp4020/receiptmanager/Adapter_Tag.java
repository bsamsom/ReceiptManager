package group8.comp4020.receiptmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by Mark on 3/27/2017.
 */

public class Adapter_Tag extends ArrayAdapter<String> {

    public ArrayList<String> tags;
    public Adapter_Tag(Context context, ArrayList<String> tags)
    {
        super(context, 0, tags);
        this.tags = tags;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        String tag = getItem(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_tag, parent, false);
        }

        EditText editText_Input = (EditText) convertView.findViewById(R.id.editText_Adapter_TagName);
        Button button_Add = (Button) convertView.findViewById(R.id.button_Adapter_Add);
        Button button_Remove = (Button) convertView.findViewById(R.id.button_Adapter_Remove);

        return convertView;

    }
}
