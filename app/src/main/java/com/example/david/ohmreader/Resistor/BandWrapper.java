package com.example.david.ohmreader.Resistor;

import com.example.david.ohmreader.BandColor;

import java.util.ArrayList;

/**
 * Created by David on 13-Mar-17.
 */

public class BandWrapper {

    private Band band;
    private Resistor resistor;

    public BandWrapper(Band b, Resistor r){
        this.band = b;
        this.resistor = r;
    }



    public void setColor(BandColor c){
        this.band.setColor(c);
        resistor.valueChanged();
    }

    public ArrayList<BandColor> getColorList(){
        return band.getColorList();
    }

    public BandColor getColor(){
        return band.getColor();
    }

    public int getLocationOnResistor() {
        return this.band.getLocationOnResistor();
    }


}
