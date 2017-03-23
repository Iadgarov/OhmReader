package com.example.david.ohmreader.Resistor;

import com.example.david.ohmreader.BadColorRequestException;
import com.example.david.ohmreader.BandColor;

import java.util.ArrayList;

/**
 * Created by David on 11-Mar-17.
 */

public class ToleranceBand extends Band {

    private final ArrayList<BandColor> TOLERANCE_COLORS ;

    public ToleranceBand(BandColor c, boolean is3Band){
        super(c, 4);

        TOLERANCE_COLORS = BandColor.getToleranceColors(is3Band);
    }

 /*   public ToleranceBand(BandColor c, Context con){
        super(c, con);
    }
*/

    @Override
    public double getValue() throws BadColorRequestException {

        return this.getColor().getTolerance();
    }

    @Override
    public ArrayList<BandColor> getColorList() {
        return TOLERANCE_COLORS;
    }

}
