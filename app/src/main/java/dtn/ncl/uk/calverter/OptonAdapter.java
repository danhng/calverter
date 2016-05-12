package dtn.ncl.uk.calverter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by dtn on 11/05/2016.
 */
public class OptonAdapter extends BaseAdapter {
    private Opton[] optons;
    private Context mContext;
    private LayoutInflater inflater;

    public OptonAdapter(Context mContext, Opton[] optons){
        this.mContext = mContext;
        this.optons = optons;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return optons.length;
    }

    @Override
    public Object getItem(int position) {
        return optons[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Button button;
        if (convertView == null) {
            button = (Button) inflater.inflate(R.layout.opton_button, null);
         } else {
            button = (Button) convertView;
        }
        button.setText(optons[position].getRep());
        if (optons[position].getRep().equalsIgnoreCase("_")) {
            button.setClickable(false);
            button.setTextColor(ContextCompat.getColor(mContext, R.color.colorButtonDisabled));
        }
        return button;
    }
}
