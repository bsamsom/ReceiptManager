package group8.comp4020.receiptmanager;
import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ben on 22-Mar-17.
 */

public class CustomArrayAdapter  extends ArrayAdapter<String> {
    HashMap<String, Integer> hashMap = new HashMap<String, Integer>();

    public CustomArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
        super(context, textViewResourceId, objects);
        for (int i = 0; i < objects.size(); ++i) {
            hashMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        String item = getItem(position);
        return hashMap.get(item);
    }
/*
    @Override
    public boolean isEnabled(int position) {
        return false;
    }
    */
}

