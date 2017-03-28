package com.iadgarov.david.ohmreader.Resistor;

import com.iadgarov.david.ohmreader.BadColorRequestException;
import com.iadgarov.david.ohmreader.BandColor;

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

    public String m_toString(BandColor c){
        double d = -1;
        try{
            d = c.getTolerance();
        }
        catch(Exception e){}

        return "Tolerance: ±" + Double.toString(d) + "%";
    }

}
