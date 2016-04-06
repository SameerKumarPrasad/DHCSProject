package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

public class CanteenFragment extends Fragment {
	
	public CanteenFragment(){}

    TextView roll_plus,roll_minus,roll,idli_plus,idli_minus,idli,dosa_plus,dosa_minus,dosa,vada_plus,vada_minus,vada;
    TextView chowmein_plus,chowmein_minus,chowmein,hakka_plus,hakka_minus,hakka,momos_plus,momos_minus,momos;
    TextView samosa_plus,samosa_minus,samosa,burger_plus,burger_minus,burger;
    int roll_count=0,idli_count=0,samosa_count=0,burger_count=0,momos_count=0,chowmein_count=0,hakka_count=0,dosa_count=0,vada_count=0;

    TabHost th1,th2;
    LinearLayout tab3ly;
    String m_text;
    Button proceed;
    ImageButton imgButton1;
    private Context mContext;
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_canteen, container, false);
        proceed=(Button)rootView.findViewById(R.id.proceed);
        tab3ly=(LinearLayout)rootView.findViewById(R.id.tab3ly);

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

                    ; // unselected
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



                Intent intent = new Intent(getActivity(), order.class);
                startActivity(intent);

            }
        });
        return rootView;
    }
}
