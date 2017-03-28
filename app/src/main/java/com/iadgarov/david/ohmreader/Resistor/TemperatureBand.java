package com.iadgarov.david.ohmreader.Resistor;

import com.iadgarov.david.ohmreader.BadColorRequestException;
import com.iadgarov.david.ohmreader.BandColor;

import java.util.ArrayList;

/**
 * Created by David on 14-Mar-17.
 */

public class TemperatureBand extends Band {

    private static final ArrayList<BandColor> TEMPERATURE_COLORS = BandColor.getTempertureColors();

    public TemperatureBand(BandColor c) {
        super(c, 5);
    }

    @Override
    public double getValue() throws BadColorRequestException {
        return this.getColor().getTemperatureCoeff();
    }

    @Override
    public ArrayList<BandColor> getColorList() {
        return TEMPERATURE_COLORS;
    }

    public String m_toString(BandColor c){
        double d = -1;
        try{
            d = c.getTemperatureCoeff();
        }
        catch(Exception e){}

        return "TC: " + Double.toString(d) + " ppm/K";
    }

}
