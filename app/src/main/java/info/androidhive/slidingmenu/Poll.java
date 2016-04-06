package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by Kapil Khatri on 05-Apr-16.
 */
public class Poll extends Fragment {

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

        rootView = inflater.inflate(R.layout.poll, container, false);




        add =(ImageButton)rootView.findViewById(R.id.add);
        mLayout = (LinearLayout) rootView.findViewById(R.id.ll);
        EditText textView = new EditText(mContext);
        textView.setText("New text");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mLayout.addView(createNewTextView());


            }
        });
        return rootView;
    }

    private EditText createNewTextView() {
        final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final EditText textView = new EditText(mContext);
        textView.setLayoutParams(lparams);
        textView.setHint("Option " + j);
        j=j+1;
        return textView;
    }
}
