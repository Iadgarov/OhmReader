package com.example.david.ohmreader.Resistor;

import com.example.david.ohmreader.BadColorRequestException;
import com.example.david.ohmreader.BandColor;

import java.util.ArrayList;

/**
 * Created by David on 11-Mar-17.
 */

public class MultiplierBand extends Band {

    private static final ArrayList<BandColor> MULT_COLORS = BandColor.getMultiplierColors();

    public MultiplierBand(BandColor c, int bandNumber){
        super(c, bandNumber);
    }

/*    public MultiplierBand(BandColor c, Context con){
        super(c, con);
    }*/


    @Override
    public double getValue() throws BadColorRequestException {

        return this.getColor().getMultiplier();
    }

    @Override
    public ArrayList<BandColor> getColorList() {
        return MULT_COLORS;
    }
}
