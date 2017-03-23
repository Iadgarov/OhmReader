package com.example.david.ohmreader.Resistor;

import com.example.david.ohmreader.BadColorRequestException;
import com.example.david.ohmreader.BandColor;

import java.util.ArrayList;

/**
 * Created by David on 10-Mar-17.
 */

public class DigitBand extends Band {

    private static final ArrayList<BandColor> DIGIT_COLORS = BandColor.getDigitColors();

    public DigitBand(BandColor c, int bandNumber){
        super(c, bandNumber);
    }

    /*public DigitBand(BandColor c, Context con){
        super(c, con);
    }
*/

    @Override
    public double getValue() throws BadColorRequestException {

        return (double)(this.getColor().getDigit());
    }

    @Override
    public ArrayList<BandColor> getColorList() {
        return DIGIT_COLORS;
    }
}
