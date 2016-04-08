package info.androidhive.slidingmenu;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import java.util.ArrayList;


public class CReviewadapter extends ArrayAdapter<CReview> {

    Context context;
    private ArrayList<CReview> creviews;

    public CReviewadapter(Context context, int textViewResourceId, ArrayList<CReview> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.creviews = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.team1, null);
        }
        CReview o = creviews.get(position);
        if (o != null) {
            TextView ctimestamp = (TextView) v.findViewById(R.id.ts);
            TextView cr = (TextView) v.findViewById(R.id.bf);


            ctimestamp.setText(String.valueOf(o.getCtimeStamp()));
            cr.setText(String.valueOf(o.getCr()));

        }
        return v;
    }
}
