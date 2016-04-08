package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Kapil Khatri on 05-Apr-16.
 */
public class CanteenReview extends Fragment {



    private Context mContext;
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    private static final String DEBUG_TAG = "HttpExample";
    ArrayList<CReview> creviews = new ArrayList<CReview>();
    ListView listview;
    Button btnDownload;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView)rootview.findViewById(R.id.listview);
        btnDownload = (Button) findViewById(R.id.btnDownload);
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            btnDownload.setEnabled(true);
        } else {
            btnDownload.setEnabled(false);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.canteen_reviews, container, false);

        //rootView.setContentView(R.layout.activity_main);
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
                }).execute("https://spreadsheets.google.com/tq?key=1zcvI8cCFqiqPtfJ3zZ1TtjwPB6XxQvewEmX_TwqHVeI");
            }
        });

        return rootView;
    }


    /*public void buttonClickHandler(View view) {
        new DownloadWebpageTask(new AsyncResult() {
            @Override
            public void onResult(JSONObject object) {
                processJson(object);
            }
        }).execute("https://spreadsheets.google.com/tq?key=1j_XcsfyjmcTfJvj7tm_iOHK6x2t8cEefrX568pb8hjA");

    }*/

    private void processJson(JSONObject object) {

        try {
            JSONArray rows = object.getJSONArray("rows");

            for (int r = 0; r < rows.length(); ++r) {
                JSONObject row = rows.getJSONObject(r);
                JSONArray columns = row.getJSONArray("c");

                String ctimeStamp = columns.getJSONObject(0).getString("v");
                String cr = columns.getJSONObject(1).getString("v");
                CReview Team = new CReview(ctimeStamp, cr);
                creviews.add(Team);
            }

            final CReviewadapter adapter = new CReviewadapter(mContext,R.layout.team1, creviews);
            listview.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
