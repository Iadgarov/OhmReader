package com.example.david.ohmreader.Resistor;

import android.util.Log;

import com.example.david.ohmreader.BadColorRequestException;
import com.example.david.ohmreader.BandColor;

import java.util.ArrayList;

/**
 * Created by David on 10-Mar-17.
 */

public abstract class Band {

    private BandColor color;
    private int locationOnResistor; // number band if it were on a 6 band resistor

    public Band(BandColor c, int locationOnResistor){
        this.color = c;
        this.locationOnResistor = locationOnResistor;

    }

    protected void setColor(BandColor c){
        if (!this.getColorList().contains(c)){
            Log.d("Band Color Error!", "");
            Log.d("Band Type", this.getClass().getName());
            Log.d("Color", c.name());
        }
        this.color = c;
    }

    public BandColor getColor(){
        return this.color;
    }

    public int getLocationOnResistor(){
        return this.locationOnResistor;
    }

    public abstract double getValue() throws BadColorRequestException;

    public abstract ArrayList<BandColor> getColorList();

}
