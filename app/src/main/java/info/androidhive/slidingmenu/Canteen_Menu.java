package info.androidhive.slidingmenu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

/**
 * Created by Kapil Khatri on 05-Apr-16.
 */
public class Canteen_Menu extends Fragment {


    private Context mContext;
    String m_Text;
    ImageButton menu_change;
    TextView roll,idli,samosa,burger,momos,chowmein,hakka,dosa,vada;
    String x;
    int pos=0;

    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.canteen_menu, container, false);



        menu_change =(ImageButton)rootView.findViewById(R.id.edit_menu);


        roll =(TextView)rootView.findViewById(R.id.roll);
        idli =(TextView)rootView.findViewById(R.id.idli);
        samosa =(TextView)rootView.findViewById(R.id.samosa);
        burger =(TextView)rootView.findViewById(R.id.burger);
        momos =(TextView)rootView.findViewById(R.id.momos);
        chowmein =(TextView)rootView.findViewById(R.id.chowmein);
        hakka =(TextView)rootView.findViewById(R.id.hakka);
        dosa =(TextView)rootView.findViewById(R.id.dosa);
        vada =(TextView)rootView.findViewById(R.id.vada);

        menu_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Edit Menu");

// Set up the input

                LinearLayout layout = new LinearLayout(mContext);
                layout.setOrientation(LinearLayout.VERTICAL);






                final NumberPicker picker1 = new NumberPicker(mContext);
                picker1.setMinValue(0);
                picker1.setMaxValue(8);
                picker1.setDisplayedValues(new String[]{"Idli","Dosa","Vada","Chowmein","Spring Roll","Hakka","Samosa","Momos","Burger"});
                picker1.setValue(1);
                layout.addView(picker1);


                final TextView price = new EditText(mContext);
                price.setHint("Price");
                layout.addView(price);

              ;

// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                price.setInputType(InputType.TYPE_CLASS_NUMBER);


                builder.setView(layout);


// Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            pos=picker1.getValue();

                        if(pos==0)
                        {
                            idli.setText(price.getText());
                        }
                        if(pos==1)
                        {
                            dosa.setText(price.getText());
                        }
                        if(pos==2)
                        {
                            vada.setText(price.getText());
                        }
                        if(pos==3)
                        {
                            chowmein.setText(price.getText());
                        }
                        if(pos==4)
                        {
                            roll.setText(price.getText());
                        }
                        if(pos==5)
                        {
                            hakka.setText(price.getText());
                        }
                        if(pos==6)
                        {
                            samosa.setText(price.getText());
                        }
                        if(pos==7)
                        {
                            momos.setText(price.getText());
                        }
                        if(pos==8)
                        {
                             burger.setText(price.getText());
                        }

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


        return rootView;
    }
}
