package com.iadgarov.david.ohmreader.Visual;

import android.widget.ImageView;

import com.iadgarov.david.ohmreader.BandColor;
import com.iadgarov.david.ohmreader.MainActivity;
import com.iadgarov.david.ohmreader.Resistor.BandWrapper;
import com.iadgarov.david.ohmreader.Utils;

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
