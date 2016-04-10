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

public class Announcement_user extends Fragment {



    private Context mContext;
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    private static final String DEBUG_TAG = "HttpExample";
    ArrayList<UAnnouncement> uAnnouncements = new ArrayList<UAnnouncement>();
    ListView listview;
    Button btnDownload;
    TabHost th1,th2;
    TextView tv_mess_announce,tv_canteen_announce;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.announcement_user, container, false);


       /* tv_mess_announce=(TextView)rootView.findViewById(R.id.mess_annouce);
        tv_canteen_announce=(TextView)rootView.findViewById(R.id.canteen_announce);*/


        //----------------announcement canteen-------------------------

        listview = (ListView)rootView.findViewById(R.id.lv);
        btnDownload = (Button) rootView.findViewById(R.id.bd);
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
                }).execute("https://spreadsheets.google.com/tq?key=1S1DEwtnQ5YoFtvM442VpN2Z2SwvdnfLxp2rysSMIoOQ");
            }
        });





       /* th1=(TabHost)rootView.findViewById(R.id.tabHost);

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

                String UATimeStamp = columns.getJSONObject(0).getString("v");
                String UA = columns.getJSONObject(1).getString("v");
                UAnnouncement Team = new UAnnouncement(UATimeStamp, UA);
                uAnnouncements.add(Team);
            }

            final UAnnouncementAdapter adapter = new UAnnouncementAdapter(mContext,R.layout.team1,uAnnouncements);
            listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }








}
