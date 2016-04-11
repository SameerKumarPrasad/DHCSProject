package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Poll_user extends Fragment {


    TabHost th1,th2;
    TextView mess_poll,mess_poll_option1,mess_poll_option2,mess_poll_option3,mess_poll_option4;
    TextView canteen_poll,canteen_poll_option1,canteen_poll_option2,canteen_poll_option3,canteen_poll_option4;

    private Context mContext;
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    private static final String DEBUG_TAG = "HttpExample";
    ArrayList<UPoll> uPolls = new ArrayList<UPoll>();
    ListView listview;
    Button btnDownload;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.mess_review, container, false);


       /* mess_poll=(TextView)rootView.findViewById(R.id.mess_poll);
        mess_poll_option1=(TextView)rootView.findViewById(R.id.mess_poll_option1);
        mess_poll_option2=(TextView)rootView.findViewById(R.id.mess_poll_option2);
        mess_poll_option3=(TextView)rootView.findViewById(R.id.mess_poll_option3);
        mess_poll_option4=(TextView)rootView.findViewById(R.id.mess_poll_option4);*/




        //----------------poll mess-------------------------

        listview = (ListView)rootView.findViewById(R.id.listview);
        btnDownload = (Button) rootView.findViewById(R.id.btnDownload);
        ConnectivityManager connMgr = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            btnDownload.setEnabled(true);
        } else {
            btnDownload.setEnabled(false);
        }


        btnDownload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DownloadWebpageTask(new AsyncResult() {
                    @Override
                    public void onResult(JSONObject object) {
                        processJson(object);
                    }
                }).execute("https://spreadsheets.google.com/tq?key=1r9dCehR0xvF2ujiUvGQU8uUSpVDQ8FzbGjmvogh6BcY");
            }
        });


       /* canteen_poll=(TextView)rootView.findViewById(R.id.canteen_poll);
        canteen_poll_option1=(TextView)rootView.findViewById(R.id.canteen_poll_option1);
        canteen_poll_option2=(TextView)rootView.findViewById(R.id.canteen_poll_option2);
        canteen_poll_option3=(TextView)rootView.findViewById(R.id.canteen_poll_option3);
        canteen_poll_option4=(TextView)rootView.findViewById(R.id.canteen_poll_option4);*/







        /*th1=(TabHost)rootView.findViewById(R.id.tabHost);

        th1.setup();
        TabHost.TabSpec specs=th1.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("Mess Vendor");
        th1.addTab(specs);
        specs=th1.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Canteen");
        th1.addTab(specs);


        th1.getTabWidget().getChildAt(1)
                .setBackgroundColor(Color.parseColor("#7dfed0"));


        th1.getTabWidget().getChildAt(0)
                .setBackgroundColor(Color.TRANSPARENT);

        th1.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            public void onTabChanged(String arg0) {
                for (int i = 0; i < th1.getTabWidget().getChildCount(); i++) {
                    th1.getTabWidget().getChildAt(i)
                            .setBackgroundColor(Color.parseColor("#7dfed0"));
                    ; // unselected
                }
                th1.getTabWidget().getChildAt(th1.getCurrentTab())
                        .setBackgroundColor(Color.TRANSPARENT); // selected

            }
        });*/


        return rootView;
    }




    private void processJson(JSONObject object) {

        try {
            JSONArray rows = object.getJSONArray("rows");

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                String UPTimeStamp = columns.getJSONObject(0).getString("v");
                String UP = columns.getJSONObject(1).getString("v");
                String op1 = columns.getJSONObject(2).getString("v");
                String op2 = columns.getJSONObject(3).getString("v");
                String op3 = columns.getJSONObject(4).getString("v");
                String op4 = columns.getJSONObject(5).getString("v");
                UPoll Team = new UPoll(UPTimeStamp, UP,op1,op2,op3,op4);

                uPolls.add(Team);
            }

            final UPollAdapter adapter = new UPollAdapter(mContext,R.layout.upolllayout,uPolls);
            listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




}
