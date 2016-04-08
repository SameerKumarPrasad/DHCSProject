package info.androidhive.slidingmenu;

/**
 * Created by Kapil Khatri on 30-Mar-16.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.plus.model.people.Person;
import java.io.InputStream;

public class sign_in extends AppCompatActivity implements OnConnectionFailedListener, View.OnClickListener, ConnectionCallbacks {
    GoogleApiClient google_api_client;
    GoogleApiAvailability google_api_availability;
    SignInButton signIn_btn;
    public String  personPhotoUrl,personName,personPhone;
    private static final int SIGN_IN_CODE = 0;
    public static final String PREFS_NAME ="MyPrefsFile";
    private static final int PROFILE_PIC_SIZE = 120;
    private ConnectionResult connection_result;
    private boolean is_intent_inprogress;
    private boolean is_signInBtn_clicked;
    private int request_code;
    ProgressDialog progress_dialog;
    public RadioGroup rg;
    public String inputx;
    public int selectedId;
    public String m_text;
    int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        selectedId = settings.getInt("Id", selectedId);

        AlertDialog.Builder builder = new AlertDialog.Builder(sign_in.this);
        builder.setTitle("Select User Type :");

// Set up the input
        final EditText input = new EditText(sign_in.this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        final RadioButton[] rb = new RadioButton[3];
        rg = new RadioGroup(this); //create the RadioGroup
        rg.setPadding(30, 50, 0, 0);
        rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL

        rb[0] = new RadioButton(this);
        rg.addView(rb[0]);

        rb[0].setTextSize(18);


        rb[0].setText("Canteen Vendor");
        rb[0].setTextColor(Color.parseColor("#1b4a75"));


        rb[1] = new RadioButton(this);
        rg.addView(rb[1]);
        rb[1].setText("Mess Vendor");
        rb[1].setTextColor(Color.parseColor("#1b4a75"));
        rb[1].setTextSize(18);


        rb[2] = new RadioButton(this);
        rg.addView(rb[2]);
        rb[2].setText("Student");
        rb[2].setTextColor(Color.parseColor("#1b4a75"));
        rb[2].setTextSize(18);
        rb[2].setChecked(true);
        //   rb[i].setId(i + 100);


        builder.setView(rg);

// Set up the buttons
        builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                selectedId = rg.getCheckedRadioButtonId();
                flag=1;


            }
        });


        builder.show();


        buidNewGoogleApiClient();
        setContentView(R.layout.sign_in);
        //Customize sign-in button.a red button may be displayed when Google+ scopes are requested
        custimizeSignBtn();
        setBtnClickListeners();
        progress_dialog = new ProgressDialog(this);
        progress_dialog.setMessage("Signing in....");

    }

    /*
    create and  initialize GoogleApiClient object to use Google Plus Api.
    While initializing the GoogleApiClient object, request the Plus.SCOPE_PLUS_LOGIN scope.
    */

    private void buidNewGoogleApiClient(){

        google_api_client =  new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API,Plus.PlusOptions.builder().build())
                .addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    /*
      Customize sign-in button. The sign-in button can be displayed in
      multiple sizes and color schemes. It can also be contextually
      rendered based on the requested scopes. For example. a red button may
      be displayed when Google+ scopes are requested, but a white button
      may be displayed when only basic profile is requested. Try adding the
      Plus.SCOPE_PLUS_LOGIN scope to see the  difference.
    */

    private void custimizeSignBtn(){

        signIn_btn = (SignInButton) findViewById(R.id.sign_in_button);
        signIn_btn.setSize(SignInButton.SIZE_STANDARD);
        signIn_btn.setScopes(new Scope[]{Plus.SCOPE_PLUS_LOGIN});

    }

    /*
      Set on click Listeners on the sign-in sign-out and disconnect buttons
     */

    private void setBtnClickListeners(){
        // Button listeners
        signIn_btn.setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.disconnect_button).setOnClickListener(this);
    }

    protected void onStart() {
        super.onStart();
        google_api_client.connect();
    }

    protected void onStop() {
        super.onStop();

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("Id",selectedId);


// Commit the edits!
        editor.commit();
        if (google_api_client.isConnected()) {
            google_api_client.disconnect();
        }
    }

    protected void onResume(){
        super.onResume();
        if (google_api_client.isConnected()) {
            google_api_client.connect();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (!result.hasResolution()) {
            google_api_availability.getErrorDialog(this, result.getErrorCode(),request_code).show();
            return;
        }

        if (!is_intent_inprogress) {

            connection_result = result;

            if (is_signInBtn_clicked) {

                resolveSignInError();
            }
        }

    }

    /*
      Will receive the activity result and check which request we are responding to

     */
    @Override
    protected void onActivityResult(int requestCode, int responseCode,
                                    Intent intent) {
        // Check which request we're responding to
        if (requestCode == SIGN_IN_CODE) {
            request_code = requestCode;
            if (responseCode != RESULT_OK) {
                is_signInBtn_clicked = false;
                progress_dialog.dismiss();

            }

            is_intent_inprogress = false;

            if (!google_api_client.isConnecting()) {
                google_api_client.connect();
            }
        }
    }

    @Override
    public void onConnected(Bundle arg0) {
        is_signInBtn_clicked = false;
        // Get user's information and set it into the layout
        getProfileInfo();
        // Update the UI after signin
       // changeUI(true);
        Intent i=new Intent(sign_in.this, MainActivity.class);;

if(selectedId==3) {
    i = new Intent(sign_in.this, MainActivity.class);
}
        if(selectedId==2) {
            i = new Intent(sign_in.this, MainActivity_MessVendor.class);
        }
        if(selectedId==1) {
            i = new Intent(sign_in.this, MainActivity_CanteenVendor.class);
        }



        startActivity(i);
        finish();


    }

    @Override
    public void onConnectionSuspended(int arg0) {
        google_api_client.connect();
        changeUI(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
             //Toast.makeText(this, "start sign process", Toast.LENGTH_SHORT).show();
             gPlusSignIn();
                break;
            case R.id.sign_out_button:
             //Toast.makeText(this, "Sign Out from G+", Toast.LENGTH_LONG).show();
              gPlusSignOut();

                break;
            case R.id.disconnect_button:
              //Toast.makeText(this, "Revoke Access from G+", Toast.LENGTH_LONG).show();
              gPlusRevokeAccess();

                break;
        }
    }

    /*
      Sign-in into the Google + account
     */

    private void gPlusSignIn() {
        if (!google_api_client.isConnecting()) {
            Log.d("user connected","connected");
            is_signInBtn_clicked = true;
            progress_dialog.show();
            resolveSignInError();

        }
    }

    /*
      Method to resolve any signin errors
     */

    private void resolveSignInError() {
        if (connection_result.hasResolution()) {
            try {
                is_intent_inprogress = true;
                connection_result.startResolutionForResult(this, SIGN_IN_CODE);
                Log.d("resolve error", "sign in error resolved");
            } catch (SendIntentException e) {
                is_intent_inprogress = false;
                google_api_client.connect();
            }
        }
    }

    /*
      Sign-out from Google+ account
     */

    private void gPlusSignOut() {
        if (google_api_client.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(google_api_client);
            google_api_client.disconnect();
            google_api_client.connect();
            changeUI(false);
        }
    }

    /*
     Revoking access from Google+ account
     */

    private void gPlusRevokeAccess() {
        if (google_api_client.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(google_api_client);
            Plus.AccountApi.revokeAccessAndDisconnect(google_api_client)
                    .setResultCallback(new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status arg0) {
                            Log.d("MainActivity", "User access revoked!");
                            buidNewGoogleApiClient();
                            google_api_client.connect();
                            changeUI(false);
                        }

                    });
        }
    }

    /*
     get user's information name, email, profile pic,Date of birth,tag line and about me
     */

    private void getProfileInfo() {

        try {

            if (Plus.PeopleApi.getCurrentPerson(google_api_client) != null) {
                Person currentPerson = Plus.PeopleApi.getCurrentPerson(google_api_client);
                setPersonalInfo(currentPerson);

            } else {
           //     Toast.makeText(getApplicationContext(),
             //         "No Personal info mention", Toast.LENGTH_LONG).show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /*
     set the User information into the views defined in the layout
     */

    private void setPersonalInfo(Person currentPerson){

        personName = currentPerson.getDisplayName();
        personPhotoUrl = currentPerson.getImage().getUrl();
        String email = Plus.AccountApi.getAccountName(google_api_client);
        TextView   user_name = (TextView) findViewById(R.id.userName);
        user_name.setText("Name: "+personName);
        TextView gemail_id = (TextView)findViewById(R.id.emailId);
        gemail_id.setText("Email Id: " +email);
        TextView dob = (TextView)findViewById(R.id.dob);
        dob.setText("DOB: "+currentPerson.getBirthday());
        TextView tag_line = (TextView)findViewById(R.id.tag_line);
        tag_line.setText("Tag Line: " +currentPerson.getTagline());
        TextView about_me = (TextView)findViewById(R.id.about_me);
        about_me.setText("About Me: "+currentPerson.getAboutMe());
        setProfilePic(personPhotoUrl);
        progress_dialog.dismiss();
        //Toast.makeText(this, "Person information is shown!", Toast.LENGTH_LONG).show();
    }

    /*
     By default the profile pic url gives 50x50 px image.
     If you need a bigger image we have to change the query parameter value from 50 to the size you want
    */

    public void setProfilePic(String profile_pic){
        profile_pic = profile_pic.substring(0,
                profile_pic.length() - 2)
                + PROFILE_PIC_SIZE;
        ImageView    user_picture = (ImageView)findViewById(R.id.profile_pic);
        new LoadProfilePic(user_picture).execute(profile_pic);
    }

    /*
     Show and hide of the Views according to the user login status
     */

    private void changeUI(boolean signedIn) {
        if (signedIn) {
            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);
        } else {

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }

   /*
    Perform background operation asynchronously, to load user profile picture with new dimensions from the modified url
    */

    private class LoadProfilePic extends AsyncTask<String, Void, Bitmap> {
        ImageView bitmap_img;

        public LoadProfilePic(ImageView bitmap_img) {
            this.bitmap_img = bitmap_img;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap new_icon = null;
            try {
                InputStream in_stream = new java.net.URL(url).openStream();
                new_icon = BitmapFactory.decodeStream(in_stream);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return new_icon;
        }

        protected void onPostExecute(Bitmap result_img) {

            bitmap_img.setImageBitmap(result_img);
        }
    }

}