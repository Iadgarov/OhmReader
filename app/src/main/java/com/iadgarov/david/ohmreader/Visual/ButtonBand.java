package com.iadgarov.david.ohmreader.Visual;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import com.iadgarov.david.ohmreader.BandColor;
import com.iadgarov.david.ohmreader.MainActivity;
import com.iadgarov.david.ohmreader.R;
import com.iadgarov.david.ohmreader.Resistor.BandWrapper;
import com.iadgarov.david.ohmreader.Utils;

/**
 * Created by David on 25-Feb-17.
 */

public class ButtonBand extends VisualBand {

    public static final int HIEGHT = 400;

    public ButtonBand(BandWrapper band) {
        super(band);

        this.setView(new Button(MainActivity.appContext));
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
                ButtonBand.this.nextColor();
            }
        });

        this.getView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ButtonBand.this.popUpColorMenu();
                return false;
            }
        });

    }


    @Override
    protected void updateVisual(BandColor c) {
        Resources resources = MainActivity.appContext.getResources();
        int resourceId;

        switch(c){
            case GOLD:
                resourceId = resources.getIdentifier("gold", "drawable", MainActivity.appContext.getPackageName());
                this.getView().setBackgroundDrawable(resources.getDrawable(resourceId));
                break;
            case SILVER:
                resourceId = resources.getIdentifier("silver", "drawable", MainActivity.appContext.getPackageName());
                this.getView().setBackgroundDrawable(resources.getDrawable(resourceId));
                break;
            default:
                this.getView().setBackgroundColor(c.getColorCode());
        }



        switch(c){
            case BLACK:
                ((Button)this.getView()).setText(Utils.stringToVertical(c.toString()));

                ((Button)this.getView()).setTextColor(Color.WHITE);
                break;
            case GREY:
                ((Button)this.getView()).setText(Utils.stringToVertical(c.toString()));

                ((Button)this.getView()).setTextColor(Color.WHITE);
                break;
            default:
                ((Button)this.getView()).setText(Utils.stringToVertical(c.toString()));

                ((Button)this.getView()).setTextColor(Color.BLACK - c.getColorCode());
        }

    }
}
