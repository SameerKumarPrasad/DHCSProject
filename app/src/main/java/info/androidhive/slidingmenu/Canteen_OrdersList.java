package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kapil Khatri on 05-Apr-16.
 */
public class Canteen_OrdersList extends Fragment {

    Button confirm1,done1;
    private Context mContext;
    RadioGroup rg;
    LinearLayout ly1;

    private static final String DEBUG_TAG = "HttpExample";
    ArrayList<CanOrder> canorders = new ArrayList<>();
    ListView listview;

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.canteen_orderslist, container, false);

        confirm1=(Button)rootView.findViewById(R.id.confirm1);



        done1=(Button)rootView.findViewById(R.id.done1);

        ly1=(LinearLayout)rootView.findViewById(R.id.ly1);

        done1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ly1.setVisibility(View.GONE);

            }

        });




        // Set up the input




                final RadioButton[] rb = new RadioButton[4];
                rg = new RadioGroup(mContext); //create the RadioGroup
                rg.setPadding(30, 50, 0, 0);
                rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL

                rb[0] = new RadioButton(mContext);
                rg.addView(rb[0]);

                rb[0].setTextSize(18);


                rb[0].setText("15 min");
                rb[0].setTextColor(Color.parseColor("#1b4a75"));



                rb[1] = new RadioButton(mContext);
                rg.addView(rb[1]);
                rb[1].setText("20 min");
                rb[1].setTextColor(Color.parseColor("#1b4a75"));
                rb[1].setTextSize(18);


                rb[2] = new RadioButton(mContext);
                rg.addView(rb[2]);
                rb[2].setText("30 min");
                rb[2].setTextColor(Color.parseColor("#1b4a75"));
                rb[2].setTextSize(18);
                rb[2].setChecked(true);
                //   rb[i].setId(i + 100);


                rb[3] = new RadioButton(mContext);
                rg.addView(rb[3]);
                rb[3].setText("40 min");
                rb[3].setTextColor(Color.parseColor("#1b4a75"));
                rb[3].setTextSize(18);
                rb[3].setChecked(true);





// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text





        confirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Delivery Time");

// Set up the input




                final RadioButton[] rb = new RadioButton[4];
                rg = new RadioGroup(mContext); //create the RadioGroup
                rg.setPadding(30, 50, 0, 0);
                rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL

                rb[0] = new RadioButton(mContext);
                rg.addView(rb[0]);

                rb[0].setTextSize(18);


                rb[0].setText("15 min");
                rb[0].setTextColor(Color.parseColor("#1b4a75"));



                rb[1] = new RadioButton(mContext);
                rg.addView(rb[1]);
                rb[1].setText("20 min");
                rb[1].setTextColor(Color.parseColor("#1b4a75"));
                rb[1].setTextSize(18);


                rb[2] = new RadioButton(mContext);
                rg.addView(rb[2]);
                rb[2].setText("30 min");
                rb[2].setTextColor(Color.parseColor("#1b4a75"));
                rb[2].setTextSize(18);
                rb[2].setChecked(true);
                //   rb[i].setId(i + 100);


                rb[3] = new RadioButton(mContext);
                rg.addView(rb[3]);
                rb[3].setText("40 min");
                rb[3].setTextColor(Color.parseColor("#1b4a75"));
                rb[3].setTextSize(18);
                rb[3].setChecked(true);





// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text



                builder.setView(rg);

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                    }
                });


                builder.show();



            }

        });


        return rootView;
    }

*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.canteen_orderslist, container, false);

        //rootView.setContentView(R.layout.activity_main);
        listview = (ListView)rootView.findViewById(R.id.listview);
        confirm1 = (Button) rootView.findViewById(R.id.confirm1);
        ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            confirm1.setEnabled(true);
        } else {
            confirm1.setEnabled(false);
        }


        confirm1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DownloadWebpageTask(new AsyncResult() {
                    @Override
                    public void onResult(JSONObject object) {
                        processJson(object);
                    }
                }).execute("https://spreadsheets.google.com/tq?key=1XAEiDV1JQoZT6IksGgLJT8S4PdmudK7NMuEQ0Epsr-k");
            }
        });


        done1=(Button)rootView.findViewById(R.id.done1);

        ly1=(LinearLayout)rootView.findViewById(R.id.ly1);

        done1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ly1.setVisibility(View.GONE);

            }

        });




        // Set up the input




        final RadioButton[] rb = new RadioButton[4];
        rg = new RadioGroup(mContext); //create the RadioGroup
        rg.setPadding(30, 50, 0, 0);
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL

        rb[0] = new RadioButton(mContext);
        rg.addView(rb[0]);

        rb[0].setTextSize(18);


        rb[0].setText("15 min");
        rb[0].setTextColor(Color.parseColor("#1b4a75"));



        rb[1] = new RadioButton(mContext);
        rg.addView(rb[1]);
        rb[1].setText("20 min");
        rb[1].setTextColor(Color.parseColor("#1b4a75"));
        rb[1].setTextSize(18);


        rb[2] = new RadioButton(mContext);
        rg.addView(rb[2]);
        rb[2].setText("30 min");
        rb[2].setTextColor(Color.parseColor("#1b4a75"));
        rb[2].setTextSize(18);
        rb[2].setChecked(true);
        //   rb[i].setId(i + 100);


        rb[3] = new RadioButton(mContext);
        rg.addView(rb[3]);
        rb[3].setText("40 min");
        rb[3].setTextColor(Color.parseColor("#1b4a75"));
        rb[3].setTextSize(18);
        rb[3].setChecked(true);





// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text





        done1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Delivery Time");

// Set up the input




                final RadioButton[] rb = new RadioButton[4];
                rg = new RadioGroup(mContext); //create the RadioGroup
                rg.setPadding(30, 50, 0, 0);
                rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL

                rb[0] = new RadioButton(mContext);
                rg.addView(rb[0]);

                rb[0].setTextSize(18);


                rb[0].setText("15 min");
                rb[0].setTextColor(Color.parseColor("#1b4a75"));



                rb[1] = new RadioButton(mContext);
                rg.addView(rb[1]);
                rb[1].setText("20 min");
                rb[1].setTextColor(Color.parseColor("#1b4a75"));
                rb[1].setTextSize(18);


                rb[2] = new RadioButton(mContext);
                rg.addView(rb[2]);
                rb[2].setText("30 min");
                rb[2].setTextColor(Color.parseColor("#1b4a75"));
                rb[2].setTextSize(18);
                rb[2].setChecked(true);
                //   rb[i].setId(i + 100);


                rb[3] = new RadioButton(mContext);
                rg.addView(rb[3]);
                rb[3].setText("40 min");
                rb[3].setTextColor(Color.parseColor("#1b4a75"));
                rb[3].setTextSize(18);
                rb[3].setChecked(true);





// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text



                builder.setView(rg);

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                    }
                });


                builder.show();



            }

        });




        return rootView;
    }

    private void processJson(JSONObject object) {
        int r,j=2;

        try {
            JSONArray rows = object.getJSONArray("rows");

            for (r=0 ; r < rows.length() ; ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                String timestamp = columns.getJSONObject(0).getString("v");
                String  name= columns.getJSONObject(1).getString("v");
                String  place= columns.getJSONObject(2).getString("v");
                String  idli= columns.getJSONObject(3).getString("v");
                String dosa = columns.getJSONObject(4).getString("v");
                String vada = columns.getJSONObject(5).getString("v");
                String roll = columns.getJSONObject(6).getString("v");
                String  hakka= columns.getJSONObject(7).getString("v");
                String samosa = columns.getJSONObject(8).getString("v");
                String burger = columns.getJSONObject(10).getString("v");
                String mobileno = columns.getJSONObject(11).getString("v");
                String  momos= columns.getJSONObject(9).getString("v");
                String chowmein = columns.getJSONObject(12).getString("v");
                CanOrder Team = new CanOrder(timestamp,name,place,idli,dosa,vada,roll,hakka,samosa,burger,mobileno,momos,chowmein );
                canorders.add(Team);
            }j++;

            final CanOrderAdapter adapter = new CanOrderAdapter(mContext,R.layout.team2, canorders);
            listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}