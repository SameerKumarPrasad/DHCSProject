package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Kapil Khatri on 05-Apr-16.
 */
public class Announcement   extends Fragment {
    //variables to post review google form
    public static final MediaType FORM_DATA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //URL derived from form URL
    //---------------URL for response of userinput for attending meals--------------------
    public static final String URL="https://docs.google.com/a/iiitd.ac.in/forms/d/18eiHwsitCHfwLXWeLguag7yK13oNxid2lM9OTVk7_MQ/formResponse";

    public static final String ANNOUNCEMENT_KEY="entry_1432729733";

    private Context mContext;
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }
    Button Announce;
    EditText aData;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.announcement, container, false);

        aData = (EditText)rootView.findViewById(R.id.announcement);

        Announce = (Button)rootView.findViewById(R.id.ab);

        Announce.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                PostDataTask postDataTask = new PostDataTask();

                //execute asynctask
                postDataTask.execute(URL,aData.getText().toString());

            }
        });

        return rootView;
    }

    //-----------------------AsyncTask to send Canteen review data as a http POST request-------------------
    private class PostDataTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... inputData) {
            Boolean result = true;
            String url = inputData[0];
            String announcement = inputData[1];
            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = ANNOUNCEMENT_KEY+"=" + URLEncoder.encode(announcement, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                result=false;
            }


            try{
                //Create OkHttpClient for sending request
                OkHttpClient client = new OkHttpClient();
                //Create the request body with the help of Media Type
                RequestBody body = RequestBody.create(FORM_DATA_TYPE, postBody);
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                //Send the request
                Response response = client.newCall(request).execute();
            }catch (IOException exception){
                result=false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result){
            //Print Success or failure message accordingly
            Toast.makeText(mContext, result ? "Message successfully sent!" : "There was some error in sending message. Please try again after some time.", Toast.LENGTH_LONG).show();
        }

    }


}
