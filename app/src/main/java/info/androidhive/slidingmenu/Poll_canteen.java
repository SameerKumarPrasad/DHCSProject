package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
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
public class Poll_canteen extends Fragment {

    //variables to post review google form
    public static final MediaType FORM_DATA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //URL derived from form URL
    //--------------------------URL for mess poll_mess------------------------------
    public static final String URL="https://docs.google.com/a/iiitd.ac.in/forms/d/1ibU1BqSDi1sOKmOZBlag3onIATD6sxu8NPCSd2iQo2c/formResponse";


    public static final String QUESTION_KEY="entry_1581935754";
    public static final String OPTION1_KEY="entry_69826248";
    public static final String OPTION2_KEY="entry_2130515782";
    public static final String OPTION3_KEY="entry_1615627686";
    public static final String OPTION4_KEY="entry_1821514374";

    Button submit;
    EditText question;
    EditText option1;
    EditText option2;
    EditText option3;
    EditText option4;

    ImageButton add;
    private LinearLayout mLayout;
    private EditText mEditText;
    int j=3;
    private Context mContext;
    View rootView;
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.poll_canteen, container, false);


        submit = (Button)rootView.findViewById(R.id.submit);
        question = (EditText)rootView.findViewById(R.id.question);
        option1 = (EditText)rootView.findViewById(R.id.opt1);
        option2 = (EditText)rootView.findViewById(R.id.opt2);

        option3 = (EditText)rootView.findViewById(R.id.opt3);
        option4 = (EditText)rootView.findViewById(R.id.opt4);

        //----------------onclick listner for sending data to sheet -------------------
        submit.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                PostDataTask postDataTask = new PostDataTask();

                //execute asynctask
                postDataTask.execute(URL, question.getText().toString(), option1.getText().toString(), option2.getText().toString(), option3.getText().toString(), option4.getText().toString());
                question.setText("");
                option1.setText("");
                option2.setText("");
                option3.setText("");
                option4.setText("");
            }
        });

       /* add =(ImageButton)rootView.findViewById(R.id.add);
        mLayout = (LinearLayout) rootView.findViewById(R.id.ll);
        EditText textView = new EditText(mContext);
        textView.setText("New text");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLayout.addView(createNewTextView());


            }
        });*/
        return rootView;
    }

    /*private EditText createNewTextView() {
        final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final EditText textView = new EditText(mContext);
        textView.setLayoutParams(lparams);
        textView.setHint("Option " + j);
        j=j+1;
        return textView;
    }
*/

    //------------------------AsyncTask to send data for meals as a http POST request--------------------------
    private class PostDataTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... inputData) {
            Boolean result = true;
            String url = inputData[0];
            String quest = inputData[1];
            String o1 = inputData[2];
            String o2 = inputData[3];
            String o3 = inputData[4];
            String o4 = inputData[5];
            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = QUESTION_KEY+"=" + URLEncoder.encode(quest, "UTF-8") +
                        "&" + OPTION1_KEY + "=" + URLEncoder.encode(o1,"UTF-8") +
                        "&" + OPTION2_KEY + "=" + URLEncoder.encode(o2,"UTF-8")+
                        "&" + OPTION3_KEY + "=" + URLEncoder.encode(o3,"UTF-8") +
                        "&" + OPTION4_KEY + "=" + URLEncoder.encode(o4,"UTF-8");
            } catch (UnsupportedEncodingException ex) {
                result=false;
            }

            /*
            //If you want to use HttpRequest class from http://stackoverflow.com/a/2253280/1261816
            try {
			HttpRequest httpRequest = new HttpRequest();
			httpRequest.sendPost(url, postBody);
		}catch (Exception exception){
			result = false;
		}
            */

            try{
                //Create OkHttp
                // Client for sending request
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
