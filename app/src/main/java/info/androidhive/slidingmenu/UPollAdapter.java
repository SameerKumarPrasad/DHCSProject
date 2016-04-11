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
            v = vi.inflate(R.layout.upolllayout, null);
        }
        UPoll o = uPolls.get(position);
        if (o != null) {
            TextView UPtimestamp = (TextView) v.findViewById(R.id.ts1);
            TextView UP = (TextView) v.findViewById(R.id.pollq);
            TextView op1 = (TextView) v.findViewById(R.id.op1);
            TextView op2 = (TextView) v.findViewById(R.id.op2);
            TextView op3 = (TextView) v.findViewById(R.id.op3);
            TextView op4 = (TextView) v.findViewById(R.id.op4);
            UPtimestamp.setText(String.valueOf(o.getUPTimeStamp()));
            UP.setText(String.valueOf(o.getUP()));
            op1.setText(String.valueOf(o.getOP1()));
            op2.setText(String.valueOf(o.getOP2()));
            op3.setText(String.valueOf(o.getOP3()));
            op4.setText(String.valueOf(o.getOP4()));
        }
        return v;
    }

}
