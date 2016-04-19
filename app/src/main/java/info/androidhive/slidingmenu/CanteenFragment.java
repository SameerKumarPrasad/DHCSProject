package info.androidhive.slidingmenu;
import info.androidhive.slidingmenu.sign_in;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CanteenFragment extends Fragment {
	
	public CanteenFragment(){}

    //variables to post review google form
    public static final MediaType FORM_DATA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //URL derived from form URL
    //---------------URL for response of userinput for Canteen Review--------------------
    public static final String URL="https://docs.google.com/a/iiitd.ac.in/forms/d/1ATySjpO8SeQijhnjP0sP5-XaQlBuTtEguZbqh5SMSow/formResponse";
    //-----------------------URL for canteen orders--------------------------------------
    public static final String URL1="https://docs.google.com/a/iiitd.ac.in/forms/d/1haR-DZ1uk8UL7AFRONoMQgnpB8opFdrdtC7geJ4aAMY/formResponse";

    TextView roll_plus,roll_minus,roll,idli_plus,idli_minus,idli,dosa_plus,dosa_minus,dosa,vada_plus,vada_minus,vada;
    TextView chowmein_plus,chowmein_minus,chowmein,hakka_plus,hakka_minus,hakka,momos_plus,momos_minus,momos;
    TextView samosa_plus,samosa_minus,samosa,burger_plus,burger_minus,burger;
    int roll_count=0,idli_count=0,samosa_count=0,burger_count=0,momos_count=0,chowmein_count=0,hakka_count=0,dosa_count=0,vada_count=0;

    public static final String REVIEW_KEY="entry_294838471";

    public static final String UNAME_KEY="entry_1581935754";
    public static final String MOBILE_KEY="entry_1434612136";
    public static final String PLACE_KEY="entry_69826248";
    public static final String IDLI_KEY="entry_2130515782";
    public static final String DOSA_KEY="entry_1615627686";
    public static final String VADA_KEY="entry_1821514374";
    public static final String CHOWMEIN_KEY="entry_462306451";
    public static final String SPRINGROLL_KEY="entry_1341071666";
    public static final String HAKKA_KEY="entry_17557981";
    public static final String SAMOSA_KEY="entry_522077565";
    public static final String MOMOS_KEY="entry_396596316";
    public static final String BURGER_KEY="entry_1987358168";

    TabHost th1,th2;
    LinearLayout tab3ly;
    String m_text,pname="";
    Button proceed;
    ImageButton imgButton1;
    private Context mContext;
    private EditText DPlace;
    private EditText Mcontact;


    /*GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent();
    GoogleSignInAccount acct = result.getSignInAccount();
    String personName = acct.getDisplayName();
    String personEmail = acct.getEmail();*/
    //private EditText Mobile;
    private String Mobile = "9643730533";
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        sign_in obj=new sign_in();
        pname = sign_in.personName;

        View rootView = inflater.inflate(R.layout.frag_canteen, container, false);



        proceed=(Button)rootView.findViewById(R.id.proceed);
        tab3ly=(LinearLayout)rootView.findViewById(R.id.tab3ly);
        DPlace = (EditText)rootView.findViewById(R.id.etxtplace);
        Mcontact = (EditText)rootView.findViewById(R.id.etxtmobile);
        imgButton1 =(ImageButton)rootView.findViewById(R.id.imageButton1);
        imgButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);


                builder.setTitle("The food was ...");


                final TextView tvxx = new TextView(mContext);
                tvxx.setTextColor(Color.parseColor("#000000"));
                tvxx.setPadding(20, 20, 0, 0);
                tab3ly.addView(tvxx);

// Set up the input
                final EditText input = new EditText(mContext);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);

// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_text = input.getText().toString();

                        tvxx.setText(m_text);
                        PostDataTask postDataTask = new PostDataTask();

                        //execute asynctask
                        postDataTask.execute(URL, m_text);
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
        });

        th1=(TabHost)rootView.findViewById(R.id.tabHost);
        th2=(TabHost)rootView.findViewById(R.id.tabHost2);

        th1.setup();
        TabHost.TabSpec specs=th1.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("Order");
        th1.addTab(specs);
        specs=th1.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Review");

        th1.addTab(specs);

        th1.getTabWidget().getChildAt(1)
                .setBackgroundColor(Color.parseColor("#7dfed0"));
        th1.getTabWidget().getChildAt(0)
                .setBackgroundColor(Color.TRANSPARENT);
        TextView tv = (TextView) th1.getTabWidget().getChildAt(0).findViewById(android.R.id.title); //Unselected Tabs
        tv.setTextColor(Color.parseColor("#d51616"));
        tv.setTextSize(14);

        TextView tvx = (TextView) th1.getTabWidget().getChildAt(1).findViewById(android.R.id.title);

        tvx.setTextSize(14);
        th1.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            public void onTabChanged(String arg0) {
                for (int i = 0; i < th1.getTabWidget().getChildCount(); i++) {
                    th1.getTabWidget().getChildAt(i)
                            .setBackgroundColor(Color.parseColor("#7dfed0"));

                    TextView tv = (TextView) th1.getTabWidget().getChildAt(i).findViewById(android.R.id.title); //Unselected Tabs
                    tv.setTextColor(Color.parseColor("#000000"));
                    tv.setTextSize(14);

                    // ; // unselected
                }
                th1.getTabWidget().getChildAt(th1.getCurrentTab())
                        .setBackgroundColor(Color.TRANSPARENT); // selected
                TextView tv = (TextView) th1.getTabWidget().getChildAt(th1.getCurrentTab()).findViewById(android.R.id.title); //Unselected Tabs
                tv.setTextColor(Color.parseColor("#d51616"));
                tv.setTextSize(14);

            }
        });

        proceed.setBackgroundColor(Color.parseColor("#7dfed0"));
        roll_plus = (TextView) rootView.findViewById(R.id.roll_plus);
        roll = (TextView) rootView.findViewById(R.id.roll);
        roll_minus = (TextView) rootView.findViewById(R.id.roll_minus);

        proceed=(Button)rootView.findViewById(R.id.proceed);

        idli_plus = (TextView) rootView.findViewById(R.id.idli_plus);
        idli = (TextView) rootView.findViewById(R.id.idli);
        idli_minus = (TextView) rootView.findViewById(R.id.idli_minus);

        dosa_plus = (TextView) rootView.findViewById(R.id.dosa_plus);
        dosa = (TextView) rootView.findViewById(R.id.dosa);
        dosa_minus = (TextView) rootView.findViewById(R.id.dosa_minus);

        vada_plus = (TextView) rootView.findViewById(R.id.vada_plus);
        vada = (TextView) rootView.findViewById(R.id.vada);
        vada_minus = (TextView) rootView.findViewById(R.id.vada_minus);

        chowmein_plus = (TextView) rootView.findViewById(R.id.chowmein_plus);
        chowmein = (TextView) rootView.findViewById(R.id.chowmein);
        chowmein_minus = (TextView) rootView.findViewById(R.id.chowmein_minus);

        hakka_plus = (TextView) rootView.findViewById(R.id.hakka_plus);
        hakka = (TextView) rootView.findViewById(R.id.hakka);
        hakka_minus = (TextView) rootView.findViewById(R.id.hakka_minus);


        samosa_plus = (TextView) rootView.findViewById(R.id.samosa_plus);
        samosa = (TextView) rootView.findViewById(R.id.samosa);
        samosa_minus = (TextView) rootView.findViewById(R.id.samosa_minus);

        momos_plus = (TextView) rootView.findViewById(R.id.momos_plus);
        momos = (TextView) rootView.findViewById(R.id.momos);
        momos_minus = (TextView) rootView.findViewById(R.id.momos_minus);

        burger_plus = (TextView) rootView.findViewById(R.id.burger_plus);
        burger = (TextView) rootView.findViewById(R.id.burger);
        burger_minus = (TextView) rootView.findViewById(R.id.burger_minus);


        roll_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll_count++;
                roll.setText(Integer.toString(roll_count));
            }
        });

        roll_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll_count--;
                if(roll_count<0)
                    roll_count=0;
                roll.setText(Integer.toString(roll_count));
            }
        });
        idli_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idli_count++;
                idli.setText(Integer.toString(idli_count));
            }
        });

        idli_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idli_count--;
                if(idli_count<0)
                    idli_count=0;
                idli.setText(Integer.toString(idli_count));
            }
        });

        dosa_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dosa_count++;
                dosa.setText(Integer.toString(dosa_count));
            }
        });

        dosa_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dosa_count--;
                if(dosa_count<0)
                    dosa_count=0;
                dosa.setText(Integer.toString(dosa_count));
            }
        });

        vada_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vada_count++;
                vada.setText(Integer.toString(vada_count));
            }
        });

        vada_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vada_count--;
                if(vada_count<0)
                    vada_count=0;
                vada.setText(Integer.toString(vada_count));
            }
        });

        hakka_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hakka_count++;
                hakka.setText(Integer.toString(hakka_count));
            }
        });

        hakka_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hakka_count--;
                if(hakka_count<0)
                    hakka_count=0;
                hakka.setText(Integer.toString(hakka_count));
            }
        });

        chowmein_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chowmein_count++;
                chowmein.setText(Integer.toString(chowmein_count));
            }
        });

        chowmein_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chowmein_count--;
                if(chowmein_count<0)
                    chowmein_count=0;
                chowmein.setText(Integer.toString(chowmein_count));
            }
        });

        samosa_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                samosa_count++;
                samosa.setText(Integer.toString(samosa_count));
            }
        });

        samosa_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                samosa_count--;
                if(samosa_count<0)
                    samosa_count=0;
                samosa.setText(Integer.toString(samosa_count));
            }
        });

        burger_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                burger_count++;
                burger.setText(Integer.toString(burger_count));
            }
        });

        burger_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                burger_count--;
                if(burger_count<0)
                    burger_count=0;
                burger.setText(Integer.toString(burger_count));
            }
        });

        momos_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                momos_count++;

                momos.setText(Integer.toString(momos_count));
            }
        });

        momos_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                momos_count--;
                if(momos_count<0)
                   momos_count=0;
                momos.setText(Integer.toString(momos_count));
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PostDataTask1 postDataTask1 = new PostDataTask1();

                //execute asynctask
                postDataTask1.execute(URL1, pname,Mcontact.getText().toString(), DPlace.getText().toString(), idli.getText().toString(), dosa.getText().toString(), vada.getText().toString(), chowmein.getText().toString(), roll.getText().toString(), hakka.getText().toString(), samosa.getText().toString(), momos.getText().toString(),burger.getText().toString());

                Mcontact.setText("");
                DPlace.setText("");
                idli.setText("0");
                dosa.setText("0");
                vada.setText("0");
                chowmein.setText("0");
                roll.setText("0");
                hakka.setText("0");
                samosa.setText("0");
                momos.setText("0");
                burger.setText("0");

                /*Intent intent = new Intent(getActivity(), order.class);
                startActivity(intent);*/

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
            String reviewC = inputData[1];
            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = REVIEW_KEY+"=" + URLEncoder.encode(reviewC, "UTF-8");
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

    //------------------------AsyncTask to send data for meals as a http POST request--------------------------
    private class PostDataTask1 extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... inputData) {
            Boolean result = true;
            String url = inputData[0];
            String un = inputData[1];
            String mo = inputData[2];
            String pl = inputData[3];
            String idl = inputData[4];
            String dos = inputData[5];
            String vad = inputData[6];
            String chow = inputData[7];
            String sprg = inputData[8];
            String hak = inputData[9];
            String samo = inputData[10];
            String momo = inputData[11];
            String burg = inputData[12];
            String postBody="";

            try {
                //all values must be URL encoded to make sure that special characters like & | ",etc.
                //do not cause problems
                postBody = UNAME_KEY+"=" + URLEncoder.encode(un, "UTF-8") +
                        "&" +MOBILE_KEY  + "=" + URLEncoder.encode(mo,"UTF-8") +
                        "&" + PLACE_KEY + "=" + URLEncoder.encode(pl,"UTF-8")+
                        "&" + IDLI_KEY + "=" + URLEncoder.encode(idl,"UTF-8") +
                        "&" + DOSA_KEY + "=" + URLEncoder.encode(dos,"UTF-8")+
                        "&" + VADA_KEY + "=" + URLEncoder.encode(vad,"UTF-8") +
                        "&" + CHOWMEIN_KEY + "=" + URLEncoder.encode(chow,"UTF-8")+
                        "&" + SPRINGROLL_KEY + "=" + URLEncoder.encode(sprg,"UTF-8") +
                        "&" + HAKKA_KEY + "=" + URLEncoder.encode(hak,"UTF-8")+
                        "&" + SAMOSA_KEY + "=" + URLEncoder.encode(samo,"UTF-8") +
                        "&" + MOMOS_KEY + "=" + URLEncoder.encode(momo,"UTF-8")+
                        "&" + BURGER_KEY + "=" + URLEncoder.encode(burg,"UTF-8");
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

        protected void onPostExecute(Boolean result){
            //Print Success or failure message accordingly
            Toast.makeText(mContext, result ? "Message successfully sent!" : "There was some error in sending message. Please try again after some time.", Toast.LENGTH_LONG).show();
        }

    }
}
