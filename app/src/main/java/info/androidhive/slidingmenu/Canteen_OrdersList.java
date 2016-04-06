package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by Kapil Khatri on 05-Apr-16.
 */
public class Canteen_OrdersList extends Fragment {

Button confirm1,done1,confirm2,confirm3,confirm4,done2,done3,done4;
    private Context mContext;
    RadioGroup rg;
    LinearLayout ly1,ly2,ly3,ly4;

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.canteen_orderslist, container, false);

        confirm1=(Button)rootView.findViewById(R.id.confirm1);

        confirm2=(Button)rootView.findViewById(R.id.confirm2);
        confirm3=(Button)rootView.findViewById(R.id.confirm3);
        confirm4=(Button)rootView.findViewById(R.id.confirm4);



        done1=(Button)rootView.findViewById(R.id.done1);
        done2=(Button)rootView.findViewById(R.id.done2);
        done3=(Button)rootView.findViewById(R.id.done3);
        done4=(Button)rootView.findViewById(R.id.done4);

        ly1=(LinearLayout)rootView.findViewById(R.id.ly1);
        ly2=(LinearLayout)rootView.findViewById(R.id.ly2);
        ly3=(LinearLayout)rootView.findViewById(R.id.ly3);
        ly4=(LinearLayout)rootView.findViewById(R.id.ly4);

        done1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ly1.setVisibility(View.GONE);

            }

        });

        done2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ly2.setVisibility(View.GONE);

            }

        });

        done3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ly3.setVisibility(View.GONE);

            }

        });


        done4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ly4.setVisibility(View.GONE);

            }

        });



        confirm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Delivery Time");

// Set up the input




                final RadioButton[] rb = new RadioButton[4];
                rg = new RadioGroup(mContext); //create the RadioGroup
                rg.setPadding(30, 50, 0, 0);
                rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL

                rb[0] = new RadioButton(mContext);
                rg.addView(rb[0]);

                rb[0].setTextSize(18);


                rb[0].setText("15 min");
                rb[0].setTextColor(Color.parseColor("#1b4a75"));



                rb[1] = new RadioButton(mContext);
                rg.addView(rb[1]);
                rb[1].setText("20 min");
                rb[1].setTextColor(Color.parseColor("#1b4a75"));
                rb[1].setTextSize(18);


                rb[2] = new RadioButton(mContext);
                rg.addView(rb[2]);
                rb[2].setText("30 min");
                rb[2].setTextColor(Color.parseColor("#1b4a75"));
                rb[2].setTextSize(18);
                rb[2].setChecked(true);
                //   rb[i].setId(i + 100);


                rb[3] = new RadioButton(mContext);
                rg.addView(rb[3]);
                rb[3].setText("40 min");
                rb[3].setTextColor(Color.parseColor("#1b4a75"));
                rb[3].setTextSize(18);
                rb[3].setChecked(true);





// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text



                builder.setView(rg);

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                    }
                });


                builder.show();



            }

        });




        confirm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Delivery Time");

// Set up the input




                final RadioButton[] rb = new RadioButton[4];
                rg = new RadioGroup(mContext); //create the RadioGroup
                rg.setPadding(30, 50, 0, 0);
                rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL

                rb[0] = new RadioButton(mContext);
                rg.addView(rb[0]);

                rb[0].setTextSize(18);


                rb[0].setText("15 min");
                rb[0].setTextColor(Color.parseColor("#1b4a75"));



                rb[1] = new RadioButton(mContext);
                rg.addView(rb[1]);
                rb[1].setText("20 min");
                rb[1].setTextColor(Color.parseColor("#1b4a75"));
                rb[1].setTextSize(18);


                rb[2] = new RadioButton(mContext);
                rg.addView(rb[2]);
                rb[2].setText("30 min");
                rb[2].setTextColor(Color.parseColor("#1b4a75"));
                rb[2].setTextSize(18);
                rb[2].setChecked(true);
                //   rb[i].setId(i + 100);


                rb[3] = new RadioButton(mContext);
                rg.addView(rb[3]);
                rb[3].setText("40 min");
                rb[3].setTextColor(Color.parseColor("#1b4a75"));
                rb[3].setTextSize(18);
                rb[3].setChecked(true);





// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text



                builder.setView(rg);

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                    }
                });


                builder.show();



            }

        });




        confirm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Delivery Time");

// Set up the input




                final RadioButton[] rb = new RadioButton[4];
                rg = new RadioGroup(mContext); //create the RadioGroup
                rg.setPadding(30, 50, 0, 0);
                rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL

                rb[0] = new RadioButton(mContext);
                rg.addView(rb[0]);

                rb[0].setTextSize(18);


                rb[0].setText("15 min");
                rb[0].setTextColor(Color.parseColor("#1b4a75"));



                rb[1] = new RadioButton(mContext);
                rg.addView(rb[1]);
                rb[1].setText("20 min");
                rb[1].setTextColor(Color.parseColor("#1b4a75"));
                rb[1].setTextSize(18);


                rb[2] = new RadioButton(mContext);
                rg.addView(rb[2]);
                rb[2].setText("30 min");
                rb[2].setTextColor(Color.parseColor("#1b4a75"));
                rb[2].setTextSize(18);
                rb[2].setChecked(true);
                //   rb[i].setId(i + 100);


                rb[3] = new RadioButton(mContext);
                rg.addView(rb[3]);
                rb[3].setText("40 min");
                rb[3].setTextColor(Color.parseColor("#1b4a75"));
                rb[3].setTextSize(18);
                rb[3].setChecked(true);





// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text



                builder.setView(rg);

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                    }
                });


                builder.show();



            }

        });


        confirm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Delivery Time");

// Set up the input




                final RadioButton[] rb = new RadioButton[4];
                rg = new RadioGroup(mContext); //create the RadioGroup
                rg.setPadding(30, 50, 0, 0);
                rg.setOrientation(RadioGroup.VERTICAL);//or RadioGroup.VERTICAL

                rb[0] = new RadioButton(mContext);
                rg.addView(rb[0]);

                rb[0].setTextSize(18);


                rb[0].setText("15 min");
                rb[0].setTextColor(Color.parseColor("#1b4a75"));



                rb[1] = new RadioButton(mContext);
                rg.addView(rb[1]);
                rb[1].setText("20 min");
                rb[1].setTextColor(Color.parseColor("#1b4a75"));
                rb[1].setTextSize(18);


                rb[2] = new RadioButton(mContext);
                rg.addView(rb[2]);
                rb[2].setText("30 min");
                rb[2].setTextColor(Color.parseColor("#1b4a75"));
                rb[2].setTextSize(18);
                rb[2].setChecked(true);
                //   rb[i].setId(i + 100);


                rb[3] = new RadioButton(mContext);
                rg.addView(rb[3]);
                rb[3].setText("40 min");
                rb[3].setTextColor(Color.parseColor("#1b4a75"));
                rb[3].setTextSize(18);
                rb[3].setChecked(true);





// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text



                builder.setView(rg);

                builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                    }
                });


                builder.show();



            }

        });


        return rootView;
    }
}
