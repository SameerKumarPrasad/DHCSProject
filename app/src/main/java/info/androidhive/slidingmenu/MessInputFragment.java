package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
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

public class MessInputFragment extends Fragment implements MediaStore.MediaColumns{

    //variables to post review google form
    public static final MediaType FORM_DATA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //URL derived from form URL
    //---------------URL for response of userinput for attending meals--------------------
    public static final String URL="https://docs.google.com/a/iiitd.ac.in/forms/d/1ThJl-3_5YqaBvjzDMOHiDZrt1iHeBrkpB2of4hM-KWE/formResponse";
    //---------------URL for response of user review--------------------------------------
    public static final String URL1="https://docs.google.com/a/iiitd.ac.in/forms/d/1mK48SW2Ykkgw5Tx7bM_1Uva6LFREIV3E7Zfu9VqxPrE/formResponse";

    //-------------input element ids found from the live mess form page--------------------------
    public static final String BREAKFAST_KEY="entry_69826248";
    public static final String LUNCH_KEY="entry_1581935754";
    public static final String SNACKS_KEY="entry_2130515782";
    public static final String DINNER_KEY="entry_1615627686";
    //-------------input element ids found from the live mess review form page-------------------
    public static final String BREAKFAST_REVIEW_KEY="entry_1581935754";
    public static final String LUNCH_REVIEW_KEY="entry_69826248";
    public static final String SNACKS_REVIEW_KEY="entry_2130515782";
    public static final String DINNER_REVIEW_KEY="entry_1615627686";

    private RadioGroup radioGroup1;
    private RadioGroup radioGroup2;
    private RadioGroup radioGroup3;
    private RadioGroup radioGroup4;

    String rb;
    String rl;
    String re;
    String rd;
    TabHost th1,th2;

    ImageButton imgButton1,imgButton2,imgButton3,imgButton4;
    private String m_Text1 = "",m_Text2 = "",m_Text3 = "",m_Text4 = "",pname="";
    public MessInputFragment(){}

    TextView nasta_tv,lunch_tv,snacks_tv,dinner_tv;
    Button update;
    private Context mContext;
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View rootView = inflater.inflate(R.layout.fragment_mess, container, false);
        sign_in obj=new sign_in();
        pname= obj.personName;

        //--------------------------oncheckedchagelistner for radiogroup1----------------------
        radioGroup1 = (RadioGroup)rootView.findViewById(R.id.radioGroup1);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged (RadioGroup group,int checkedId){
                switch (checkedId) {
                    case R.id.rb11:
                        rb = "1";
                        break;

                    case R.id.rb12:
                        rb = "0";
                        break;

                }


            }
        });


        //-----------------------oncheckedchagelistner for radiogroup2-------------------------
        radioGroup2 = (RadioGroup)rootView.findViewById(R.id.radioGroup2);

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged (RadioGroup group,int checkedId){
                switch (checkedId) {
                    case R.id.rb21:
                        rl = "1";
                        break;

                    case R.id.rb22:
                        rl = "0";
                        break;

                }


            }
        });


        //--------------------oncheckedchagelistner for radiogroup3-------------------------
        radioGroup3 = (RadioGroup)rootView.findViewById(R.id.radioGroup3);

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged (RadioGroup group,int checkedId){
                switch (checkedId) {
                    case R.id.rb31:
                        re = "1";
                        break;

                    case R.id.rb32:
                        re = "0";
                        break;

                }


            }
        });


        //-------------------oncheckedchagelistner for radiogroup4------------------------
        radioGroup4 = (RadioGroup)rootView.findViewById(R.id.radioGroup4);

        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            public void onCheckedChanged (RadioGroup group,int checkedId){
                switch (checkedId) {
                    case R.id.rb41:
                        rd = "1";
                        break;

                    case R.id.rb42:
                        rd = "0";
                        break;

                }


            }
        });

        update=(Button)rootView.findViewById(R.id.update);
        update.setBackgroundColor(Color.parseColor("#7dfed0"));
        nasta_tv=(TextView)rootView.findViewById(R.id.nasta_tv);
        lunch_tv=(TextView)rootView.findViewById(R.id.lunch_tv);
        snacks_tv=(TextView)rootView.findViewById(R.id.snacks_tv);
        dinner_tv=(TextView)rootView.findViewById(R.id.dinner_tv);

        //------------------------update Button to send send data------------------------------

        update.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {

                PostDataTask postDataTask = new PostDataTask();

                //execute asynctask
                postDataTask.execute(URL,rb,rl,re,rd);

            }
        });




        imgButton1=(ImageButton)rootView.findViewById(R.id.imageButton1);
        imgButton1.setOnClickListener(new View.OnClickListener()

                                      {
                                          @Override
                                          public void onClick (View v){
                                              AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                              builder.setTitle("The food was ...");

// Set up the input
                                              final EditText input = new EditText(mContext);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                                              input.setInputType(InputType.TYPE_CLASS_TEXT);
                                              builder.setView(input);

// Set up the buttons
                                              builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      m_Text1 = input.getText().toString();
                                                      nasta_tv.setText(m_Text1);

                                                      PostDataTask1 postDataTask1 = new PostDataTask1();

                                                      //execute asynctask
                                                      postDataTask1.execute(URL1, m_Text1);
                                                  }
                                              });
                                              builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      dialog.cancel();
                                                  }
                                              });

                                              builder.show();
                                          }
                                      }

        );

        th1=(TabHost)rootView.findViewById(R.id.tabHost);
        th2=(TabHost)rootView.findViewById(R.id.tabHost2);

        th1.setup();
        TabHost.TabSpec specs = th1.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("Poll");
        th1.addTab(specs);
        specs=th1.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Review");

        th1.addTab(specs);

        th1.getTabWidget().

                getChildAt(1)

                .

                        setBackgroundColor(Color.parseColor("#7dfed0")

                        );
        th1.getTabWidget().

                getChildAt(0)

                .

                        setBackgroundColor(Color.TRANSPARENT);

        TextView tv = (TextView) th1.getTabWidget().getChildAt(0).findViewById(android.R.id.title); //Unselected Tabs
        tv.setTextColor(Color.parseColor("#d51616"));
        tv.setTextSize(14);

        TextView tvx = (TextView) th1.getTabWidget().getChildAt(1).findViewById(android.R.id.title);

        tvx.setTextSize(14);
        th1.setOnTabChangedListener(new TabHost.OnTabChangeListener()

                                    {

                                        public void onTabChanged (String arg0){
                                            for (int i = 0; i < th1.getTabWidget().getChildCount(); i++) {
                                                th1.getTabWidget().getChildAt(i)
                                                        .setBackgroundColor(Color.parseColor("#7dfed0"));

                                                TextView tv = (TextView) th1.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                                                tv.setTextColor(Color.parseColor("#000000"));
                                                tv.setTextSize(14);

                                                ; // unselected
                                            }
                                            th1.getTabWidget().getChildAt(th1.getCurrentTab())
                                                    .setBackgroundColor(Color.TRANSPARENT); // selected
                                            TextView tv = (TextView) th1.getTabWidget().getChildAt(th1.getCurrentTab()).findViewById(android.R.id.title); //Unselected Tabs
                                            tv.setTextColor(Color.parseColor("#d51616"));
                                            tv.setTextSize(14);

                                        }
                                    }

        );

        th2.setup();
        TabHost.TabSpec specs2 = th2.newTabSpec("tag1");
        specs2.setContent(R.id.tab3);
        specs2.setIndicator("B.Fast");
        th2.addTab(specs2);
        specs2=th2.newTabSpec("tag2");
        specs2.setContent(R.id.tab4);
        specs2.setIndicator("Lunch");
        th2.addTab(specs2);
        specs2=th2.newTabSpec("tag3");
        specs2.setContent(R.id.tab5);
        specs2.setIndicator("Snacks");
        th2.addTab(specs2);
        specs2=th2.newTabSpec("tag4");
        specs2.setContent(R.id.tab6);
        specs2.setIndicator("Dinner");
        th2.addTab(specs2);
        ;

        TextView tv1 = (TextView) th2.getTabWidget().getChildAt(0).findViewById(android.R.id.title); //Unselected Tabs
        tv1.setTextColor(Color.parseColor("#d51616"));


        th2.setOnTabChangedListener(new TabHost.OnTabChangeListener()

                                    {

                                        public void onTabChanged (String arg0){
                                            for (int i = 0; i < th2.getTabWidget().getChildCount(); i++) {

                                                ; // unselected

                                                TextView tv = (TextView) th2.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                                                tv.setTextColor(Color.parseColor("#000000"));
                                            }
                                            // selected

                                            TextView tv = (TextView) th2.getTabWidget().getChildAt(th2.getCurrentTab()).findViewById(android.R.id.title);
                                            tv.setTextColor(Color.parseColor("#d51616"));

                                        }
                                    }

        );

        imgButton2=(ImageButton)rootView.findViewById(R.id.imageButton2);
        imgButton2.setOnClickListener(new View.OnClickListener()

                                      {
                                          @Override
                                          public void onClick (View v){
                                              AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                              builder.setTitle("The food was ...");

// Set up the input
                                              final EditText input = new EditText(mContext);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                                              input.setInputType(InputType.TYPE_CLASS_TEXT);
                                              builder.setView(input);

// Set up the buttons
                                              builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      m_Text2 = input.getText().toString();
                                                      lunch_tv.setText(m_Text2);

                                                      PostDataTask2 postDataTask2 = new PostDataTask2();

                                                      //execute asynctask
                                                      postDataTask2.execute(URL1, m_Text2);
                                                  }
                                              });
                                              builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      dialog.cancel();
                                                  }
                                              });

                                              builder.show();
                                          }
                                      }

        );

        imgButton3=(ImageButton)rootView.findViewById(R.id.imageButton3);
        imgButton3.setOnClickListener(new View.OnClickListener()

                                      {
                                          @Override
                                          public void onClick (View v){
                                              AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                              builder.setTitle("The food was ...");

// Set up the input
                                              final EditText input = new EditText(mContext);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                                              input.setInputType(InputType.TYPE_CLASS_TEXT);
                                              builder.setView(input);

// Set up the buttons
                                              builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      m_Text3 = input.getText().toString();
                                                      snacks_tv.setText(m_Text3);
                                                      PostDataTask3 postDataTask3 = new PostDataTask3();

                                                      //execute asynctask
                                                      postDataTask3.execute(URL1, m_Text3);
                                                  }
                                              });
                                              builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      dialog.cancel();
                                                  }
                                              });

                                              builder.show();
                                          }
                                      }

        );

        imgButton4=(ImageButton)rootView.findViewById(R.id.imageButton4);
        imgButton4.setOnClickListener(new View.OnClickListener()

                                      {
                                          @Override
                                          public void onClick (View v){
                                              AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                              builder.setTitle("The food was ...");

// Set up the input
                                              final EditText input = new EditText(mContext);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                                              input.setInputType(InputType.TYPE_CLASS_TEXT);
                                              builder.setView(input);

// Set up the buttons
                                              builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      m_Text4 = input.getText().toString();
                                                      dinner_tv.setText(m_Text4);

                                                      PostDataTask4 postDataTask4 = new PostDataTask4();

                                                      //execute asynctask
                                                      postDataTask4.execute(URL1, m_Text4);
                                                  }
                                              });
                                              builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      dialog.cancel();
                                                  }
                                              });

                                              builder.show();
                                          }
                                      }

        );

        return rootView;
    }




    //------------------------AsyncTask to send data for meals as a http POST request--------------------------
    private class PostDataTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... inputData) {
            Boolean result = true;
            String url = inputData[0];
            String rb = inputData[1];
            String rl = inputData[2];
            String re = inputData[3];
            String rd = inputData[4];
            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = BREAKFAST_KEY+"=" + URLEncoder.encode(rb, "UTF-8") +
                        "&" + LUNCH_KEY + "=" + URLEncoder.encode(rl,"UTF-8") +
                        "&" + SNACKS_KEY + "=" + URLEncoder.encode(re,"UTF-8")+
                        "&" + DINNER_KEY + "=" + URLEncoder.encode(rd,"UTF-8");
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
            Toast.makeText(mContext,result?"Message successfully sent!":"There was some error in sending message. Please try again after some time.",Toast.LENGTH_LONG).show();
        }

    }
    //-----------------------AsyncTask to send breakfast review data as a http POST request-------------------
    private class PostDataTask1 extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... inputData) {
            Boolean result = true;
            String url = inputData[0];
            String reviewB = inputData[1];
            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = BREAKFAST_REVIEW_KEY+"=" + URLEncoder.encode(reviewB, "UTF-8");
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
            Toast.makeText(mContext,result?"Message successfully sent!":"There was some error in sending message. Please try again after some time.",Toast.LENGTH_LONG).show();
        }

    }

    //------------------------AsyncTask to send Lunch review data as a http POST request------------------
    private class PostDataTask2 extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... inputData) {
            Boolean result = true;
            String url = inputData[0];
            String reviewL = inputData[1];
            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = LUNCH_REVIEW_KEY+"=" + URLEncoder.encode(reviewL, "UTF-8");
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
            Toast.makeText(mContext,result?"Message successfully sent!":"There was some error in sending message. Please try again after some time.",Toast.LENGTH_LONG).show();
        }

    }


    //-----------------------AsyncTask to send Snacks review data as a http POST request-------------------------
    private class PostDataTask3 extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... inputData) {
            Boolean result = true;
            String url = inputData[0];
            String reviewE = inputData[1];
            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = SNACKS_REVIEW_KEY+"=" + URLEncoder.encode(reviewE, "UTF-8");
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
            Toast.makeText(mContext,result?"Message successfully sent!":"There was some error in sending message. Please try again after some time.",Toast.LENGTH_LONG).show();
        }

    }


    //-------------------------AsyncTask to send Dinnner review data as a http POST request------------------------
    private class PostDataTask4 extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... inputData) {
            Boolean result = true;
            String url = inputData[0];
            String reviewD = inputData[1];
            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = DINNER_REVIEW_KEY+"=" + URLEncoder.encode(reviewD, "UTF-8");
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
            Toast.makeText(mContext,result?"Message successfully sent!":"There was some error in sending message. Please try again after some time.",Toast.LENGTH_LONG).show();
        }

    }

}

