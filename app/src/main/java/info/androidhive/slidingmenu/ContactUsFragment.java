package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class ContactUsFragment extends Fragment {


    private String m_Text = "";
    private Context mContext;
	public ContactUsFragment(){}
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_about, container, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Bonus Points !!");

        builder.setIcon(R.drawable.icon_messiah);
// Set up the input
        final TextView input = new TextView(mContext);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setText("Earn 20 Bonus Points by giving the FeedBack!!");

        input.setTextColor(Color.parseColor("#0b2a80"));

        input.setPadding(30, 30, 5, 0);
        input.setTextSize(15);


        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();

                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setType("plain/text");
                sendIntent.setData(Uri.parse("messiah@gmail.com"));
                sendIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"messiah@gmail.com"});
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Query");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "");
                startActivity(sendIntent);


            }
        });
        builder.show();




        return rootView;
    }
}
