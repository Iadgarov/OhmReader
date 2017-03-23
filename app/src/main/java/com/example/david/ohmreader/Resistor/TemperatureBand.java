package com.example.david.ohmreader.Resistor;

import com.example.david.ohmreader.BadColorRequestException;
import com.example.david.ohmreader.BandColor;

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

}
