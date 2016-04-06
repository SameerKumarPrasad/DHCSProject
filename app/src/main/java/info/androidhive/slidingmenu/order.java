package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

/**
 * Created by Kapil Khatri on 30-Mar-16.
 */
public class order extends Activity {

    Button delivery,take,cash,paytm;
    Button order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_order);


        order=(Button)findViewById(R.id.order);
        order.setBackgroundColor(Color.parseColor("#26b8a6"));

        delivery=(Button)findViewById(R.id.delivery);

        take=(Button)findViewById(R.id.take);

        cash=(Button)findViewById(R.id.cash);

        paytm=(Button)findViewById(R.id.paytm);

        delivery.setBackgroundColor(Color.parseColor("#f57169"));
       ;
        paytm.setBackgroundColor(Color.parseColor("#f57169"));

        take.setBackgroundColor(Color.LTGRAY);

        cash.setBackgroundColor(Color.LTGRAY);

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                delivery.setBackgroundColor(Color.parseColor("#f57169"));
                take.setBackgroundColor(Color.LTGRAY);
            }
        });

        take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                take.setBackgroundColor(Color.parseColor("#f57169"));
                delivery.setBackgroundColor(Color.LTGRAY);
            }
        });
        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cash.setBackgroundColor(Color.parseColor("#f57169"));
                paytm.setBackgroundColor(Color.LTGRAY);
            }
        });
        paytm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                paytm.setBackgroundColor(Color.parseColor("#f57169"));
                cash.setBackgroundColor(Color.LTGRAY);
            }
        });
    }


}
