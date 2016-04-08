package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

public class Announcement_user extends Fragment {


    TabHost th1,th2;
    TextView tv_mess_announce,tv_canteen_announce;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.announcement_user, container, false);


        tv_mess_announce=(TextView)rootView.findViewById(R.id.mess_annouce);
        tv_canteen_announce=(TextView)rootView.findViewById(R.id.canteen_announce);







        th1=(TabHost)rootView.findViewById(R.id.tabHost);

        th1.setup();
        TabHost.TabSpec specs=th1.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("Mess Vendor");
        th1.addTab(specs);
        specs=th1.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Canteen");
        th1.addTab(specs);


        th1.getTabWidget().getChildAt(1)
                .setBackgroundColor(Color.parseColor("#7dfed0"));


        th1.getTabWidget().getChildAt(0)
                .setBackgroundColor(Color.TRANSPARENT);

        th1.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            public void onTabChanged(String arg0) {
                for (int i = 0; i < th1.getTabWidget().getChildCount(); i++) {
                    th1.getTabWidget().getChildAt(i)
                            .setBackgroundColor(Color.parseColor("#7dfed0"));
                    ; // unselected
                }
                th1.getTabWidget().getChildAt(th1.getCurrentTab())
                        .setBackgroundColor(Color.TRANSPARENT); // selected

            }
        });


        return rootView;
    }









}
