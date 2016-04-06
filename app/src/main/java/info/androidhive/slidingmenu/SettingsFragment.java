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
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    NumberPicker picker1,picker2,picker3;
    TabHost th1,th2;

    private RadioButton sound, vibration;
    public SettingsFragment(){}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);




        sound = (RadioButton) rootView.findViewById(R.id.sound);
        vibration = (RadioButton) rootView.findViewById(R.id.vibration);


        picker1 = (NumberPicker) rootView.findViewById(R.id.numberPicker);
        picker1.setMinValue(0);
        picker1.setMaxValue(2);
        picker1.setDisplayedValues(new String[]{"Meal Wise", "Daily", "Never"});
        picker1.setValue(1);

        picker2 = (NumberPicker) rootView.findViewById(R.id.numberPicker2);
        picker2.setMinValue(0);
        picker2.setMaxValue(6);
        picker2.setDisplayedValues(new String[]{"Mon", "Tue", "Wed", "Thurs", "Fri", "Sat", "Sun"});
        picker2.setValue(1);

        picker3 = (NumberPicker) rootView.findViewById(R.id.numberPicker3);
        picker3.setMinValue(0);
        picker3.setMaxValue(3);
        picker3.setDisplayedValues(new String[]{"BreakFast", "Lunch", "Snacks", "Dinner"});
        picker3.setValue(1);

        picker1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        picker3.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        th1=(TabHost)rootView.findViewById(R.id.tabHost);

        th1.setup();
        TabHost.TabSpec specs=th1.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("Notify Me");
        th1.addTab(specs);
        specs=th1.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Ask me for");
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
