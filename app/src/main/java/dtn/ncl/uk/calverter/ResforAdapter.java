package dtn.ncl.uk.calverter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by dtn on 11/05/2016.
 */
public class ResforAdapter extends ArrayAdapter<String> {

    // positions of result items in the list view
    private static final int RESFOR_BIN = 0;
    private static final int RESFOR_OCT = 1;
    private static final int RESFOR_DEC = 2;
    private static final int RESFOR_HEX = 3;

    private String[] resforUnitStrings = {"BIN", "OCT", "DEC", "HEX"};

    private LayoutInflater inflaterService;

    public ResforAdapter(Context context, String[] values) {
        super(context, -1, values);
        inflaterService = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // todo restart app
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout resforItem;
        if (convertView == null) {
            resforItem = (LinearLayout) inflaterService.inflate(R.layout.resfor_item_view, parent, false);
        }
        else {
            resforItem = (LinearLayout) convertView;
        }
        // resfor unit name

        // resfor unit name
//        TextView t1 = ((TextView)resforItem.getChildAt(0));
//        TextView t2 = ((TextView)resforItem.getChildAt(1));
//
//        t1.setText(resforUnitStrings[position]);
//        t1.setTextColor(ContextCompat.getColor(getContext(), R.color.colorButtonPressed));
//
//        t2.setText("0");
//        t2.setTextColor(ContextCompat.getColor(getContext(), R.color.colorButtonPressed));

        ((TextView)resforItem.getChildAt(0)).setText(resforUnitStrings[position]);

        ((TextView)resforItem.getChildAt(1)).setText("0");

        //Log.d("resfor", resforItem.toString());
        return resforItem;
    }
}
