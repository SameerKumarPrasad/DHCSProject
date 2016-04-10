package info.androidhive.slidingmenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rohan on 4/10/2016.
 */
public class CanOrderAdapter extends ArrayAdapter<CanOrder> {

        Context context;
private ArrayList<CanOrder> canorders;

public CanOrderAdapter(Context context, int textViewResourceId, ArrayList<CanOrder> items) {
        super(context, textViewResourceId, items);
        this.context = context;
        this.canorders = items;
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = vi.inflate(R.layout.team2, null);
        }
        CanOrder o = canorders.get(position);
        if (o != null) {
        TextView timestamp = (TextView) v.findViewById(R.id.ts);
        TextView name = (TextView) v.findViewById(R.id.name);
        TextView place = (TextView) v.findViewById(R.id.place);
        TextView idli = (TextView) v.findViewById(R.id.idli);
        TextView dosa = (TextView) v.findViewById(R.id.dosa);
            TextView vada = (TextView) v.findViewById(R.id.vada);
            TextView roll = (TextView) v.findViewById(R.id.roll);
            TextView hakka = (TextView) v.findViewById(R.id.hakka);
            TextView samosa = (TextView) v.findViewById(R.id.samosa);
            TextView momos = (TextView) v.findViewById(R.id.momos);
            TextView burger = (TextView) v.findViewById(R.id.burger);
            TextView mobileno = (TextView) v.findViewById(R.id.mobileno);
            TextView chowmein = (TextView) v.findViewById(R.id.chowmein);



        timestamp.setText(String.valueOf(o.gettimestamp()));
        name.setText(String.valueOf(o.getname()));
        place.setText(String.valueOf(o.getplace()));
        idli.setText(String.valueOf(o.getidli()));
        dosa.setText(String.valueOf(o.getdosa()));
            vada.setText(String.valueOf(o.getvada()));
            roll.setText(String.valueOf(o.getroll()));
            hakka.setText(String.valueOf(o.gethakka()));
            samosa.setText(String.valueOf(o.getsamosa()));
            momos.setText(String.valueOf(o.getmomos()));
            burger.setText(String.valueOf(o.getburger()));
            mobileno.setText(String.valueOf(o.getmobileno()));
            chowmein.setText(String.valueOf(o.getchowmein()));
        }
        return v;
        }
        }



