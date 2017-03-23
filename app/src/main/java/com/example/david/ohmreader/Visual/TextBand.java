package com.example.david.ohmreader.Visual;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.david.ohmreader.BandColor;
import com.example.david.ohmreader.MainActivity;
import com.example.david.ohmreader.R;
import com.example.david.ohmreader.Resistor.BandWrapper;

/**
 * Created by David on 23-Mar-17.
 */

public class TextBand extends VisualBand{

    public static final int HIEGHT = 500;
    private TextView txt;
    private LinearLayout ll;

    public TextBand(BandWrapper band) {
        super(band);

        ll = (LinearLayout) View.inflate(MainActivity.appContext, R.layout.sideways_text, null);

        txt = (TextView)ll.findViewById(R.id.textView);




        this.setView(ll);

        this.getView().setMinimumHeight(HIEGHT);

        if (band == null){
            return;
        }

        setupListeners();
        updateVisual(this.getBand().getColor());
    }

    private void setupListeners(){

        if (this.getBand() == null){
            return; // no listeners for dummy buttons
        }

        this.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextBand.this.nextColor();
            }
        });

        this.getView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TextBand.this.popUpColorMenu();
                return false;
            }
        });

    }


    @Override
    protected void updateVisual(BandColor c) {

        txt.setText(c.toString());

        Log.d("Color text", c.toString());






    }
}
