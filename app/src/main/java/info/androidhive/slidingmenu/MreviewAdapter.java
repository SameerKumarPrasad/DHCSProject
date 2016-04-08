package info.androidhive.slidingmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class MreviewAdapter extends ArrayAdapter<Mreview> {

    Context context;
    private ArrayList<Mreview> mreviews;

    public MreviewAdapter(Context context, int textViewResourceId, ArrayList<Mreview> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.mreviews = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.team, null);
        }
        Mreview o = mreviews.get(position);
        if (o != null) {
            TextView mtimestamp = (TextView) v.findViewById(R.id.ts);
            TextView mbreakfat = (TextView) v.findViewById(R.id.bf);
            TextView mlunch = (TextView) v.findViewById(R.id.ln);
            TextView msnacks = (TextView) v.findViewById(R.id.sn);
            TextView mdinner = (TextView) v.findViewById(R.id.dn);


            mtimestamp.setText(String.valueOf(o.getMtimeStamp()));
            mbreakfat.setText(String.valueOf(o.getMbreakfast()));
            mlunch.setText(String.valueOf(o.getMlunch()));
            msnacks.setText(String.valueOf(o.getMsnacks()));
            mdinner.setText(String.valueOf(o.getMdinner()));

        }
        return v;
    }
}
