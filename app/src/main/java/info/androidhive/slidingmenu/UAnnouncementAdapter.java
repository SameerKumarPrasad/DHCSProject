package info.androidhive.slidingmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ubuntu on 10/4/16.
 */
public class UAnnouncementAdapter extends ArrayAdapter<UAnnouncement> {


    Context context;
    private ArrayList<UAnnouncement> uAnnouncements;

    public UAnnouncementAdapter(Context context, int textViewResourceId, ArrayList<UAnnouncement> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.uAnnouncements = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.team1, null);
        }
        UAnnouncement o = uAnnouncements.get(position);
        if (o != null) {
            TextView UAtimestamp = (TextView) v.findViewById(R.id.ts);
            TextView UA = (TextView) v.findViewById(R.id.bf);


            UAtimestamp.setText(String.valueOf(o.getUATimeStamp()));
            UA.setText(String.valueOf(o.getUA()));

        }
        return v;
    }

}
