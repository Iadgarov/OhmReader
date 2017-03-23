package com.example.david.ohmreader.Visual;

import android.widget.ImageView;

import com.example.david.ohmreader.BandColor;
import com.example.david.ohmreader.MainActivity;
import com.example.david.ohmreader.Resistor.BandWrapper;
import com.example.david.ohmreader.Utils;

/**
 * Created by David on 19-Mar-17.
 */

public class ImageBand extends VisualBand {


    public ImageBand(BandWrapper band, ImageView iv) {
        super(band);

        this.setView(iv);
        updateVisual(this.getBand().getColor());

    }

    @Override
    protected void updateVisual(BandColor c) {

        if(this.getView() == null){
            return;
        }

        ((ImageView)this.getView()).setImageDrawable(Utils.colorToImage(this.getBand().getColor(), this.getBand().getLocationOnResistor(), MainActivity.appContext));
    }





}
