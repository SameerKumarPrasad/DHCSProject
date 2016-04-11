package info.androidhive.slidingmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ubuntu on 11/4/16.
 */
public class UPollAdapter  extends ArrayAdapter<UPoll>{

    Context context;
    private ArrayList<UPoll> uPolls;

    public UPollAdapter(Context context, int textViewResourceId, ArrayList<UPoll> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.uPolls = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.team1, null);
        }
        UPoll o = uPolls.get(position);
        if (o != null) {
            TextView UPtimestamp = (TextView) v.findViewById(R.id.ts);
            TextView UP = (TextView) v.findViewById(R.id.bf);


            UPtimestamp.setText(String.valueOf(o.getUPTimeStamp()));
            UP.setText(String.valueOf(o.getUP()));

        }
        return v;
    }

}
