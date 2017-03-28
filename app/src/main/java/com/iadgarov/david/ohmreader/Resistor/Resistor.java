package com.iadgarov.david.ohmreader.Resistor;



import com.iadgarov.david.ohmreader.BadBandInputException;
import com.iadgarov.david.ohmreader.BadColorRequestException;
import com.iadgarov.david.ohmreader.BandColor;
import com.iadgarov.david.ohmreader.MainActivity;
import com.iadgarov.david.ohmreader.Utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by David on 20-Feb-17.
 */

public class Resistor {

    private double resistance; // in ohms
    private double tolerance; // in %
    private int tempCoeff; // in ppm/K

    private int bandCount; // number of bands on resistor
    private ArrayList<Band> bands;

    public Resistor(int bandCount) throws BadBandInputException {
        this(getDefaultColors(bandCount));
    }


    public Resistor(Band... _bands) throws BadBandInputException{
        this.bandCount = _bands.length;
        this.bands = new ArrayList<Band>(Arrays.asList(_bands));

        if (bandCount == 3){
            bands.add(new ToleranceBand(BandColor.BLANK, bandCount == 3)); // for 3 bands use default tolerance
        }



        valueChanged();
    }




    public Resistor(BandColor... _bands) throws BadBandInputException{
        bandCount = (Arrays.asList(_bands).contains(BandColor.BLANK)) ? _bands.length -1 : _bands.length;
        bands = new ArrayList<>();

        int digitCount = bandCountToDigitCount(bandCount);

        int location = (digitCount > 2) ? 0 : 1;

        int i = 0;
        for (; i < digitCount; i++){
            bands.add(new DigitBand(_bands[i], location++));
        }
        bands.add(new MultiplierBand(_bands[i++], location++));//(digitCount < 3) ? i+1 : i));
        bands.add(new ToleranceBand((bandCount == 3) ? BandColor.BLANK : _bands[i++], bandCount == 3)); // for 3 bands use default value for tolerance

        if (this.bandCount >= 6){
            bands.add(new TemperatureBand(_bands[i++]));
        }


        valueChanged();
    }

    public double getTolerance(){
        return tolerance;
    }

    public double getResistance(){
        return resistance;
    }

    public int getTempertureCoeff(){
        if (this.bandCount < 6){
            return -1;
        }
        return tempCoeff;
    }

    public ArrayList<Band> getBands(){
        return this.bands;
    }

    public Band getBandByNumber(int i){

        switch (i){
            case 0:
                if (this.bandCount > 4){
                    return this.bands.get(0);
                }
                break;
            case 1:
                return this.bands.get( (this.bandCount > 4) ? i : i-1);
            case 2:
                return this.bands.get( (this.bandCount > 4) ? i : i-1);
            case 3:
                return this.bands.get( (this.bandCount > 4) ? i : i-1);
            case 4:
                return this.bands.get( (this.bandCount > 4) ? i : i-1);
            case 5:
                if (this.bandCount > 4){
                    return this.bands.get(5);
                }
                break;
        }

        return null;
    }

    // call this method when resistor value changes
    public void valueChanged(){

        try {
            refreshVaules();
        }
        catch(BadBandInputException e){
            e.printStackTrace();
        }
        if (bandCount < 6) {
            MainActivity.updateResultText(Double.toString(this.getResistance()), Double.toString(this.getTolerance()), null);
        }
        else{
            MainActivity.updateResultText(Double.toString(this.getResistance()), Double.toString(this.getTolerance()), Integer.toString(this.getTempertureCoeff()));
        }
    }

    private void refreshVaules() throws BadBandInputException{
        computeResistance();
        computeTolerance();
        computeTempCoeff();

    }

    private void computeTempCoeff() throws BadBandInputException{
        if (this.bandCount < 6){
            return;
        }
        try {
            this.tempCoeff = this.bands.get(this.bandCount - 1).getColor().getTemperatureCoeff();
        }
        catch(BadColorRequestException e){
            throw new BadBandInputException();
        }
    }

    private void computeResistance() throws BadBandInputException{

        int digits = 0;
        int digitCount  = bandCountToDigitCount(bandCount);
        int i = 0;
        for (; i < digitCount; i++){
            try {
                digits += (int)(Math.pow(10, digitCount - i -1) * bands.get(i).getValue());
            }
            catch(Exception e){
                throw new BadBandInputException();
            }
        }

        try{
            resistance = digits * bands.get(i++).getValue();
        }
        catch(BadColorRequestException e){
            throw new BadBandInputException();
        }



    }

    private void computeTolerance() throws BadBandInputException {

        int b = bandCountToDigitCount(this.bandCount);
        try{
            tolerance = (double)(bands.get(b + 1).getValue());
        }
        catch (BadColorRequestException e){
            throw new BadBandInputException();
        }
    }

    private static BandColor[] getDefaultColors(int bandCount){

        int digitCount = bandCountToDigitCount(bandCount);
        BandColor[] c = new BandColor[bandCount == 3 ? 4 : bandCount];
        ArrayList<BandColor> colorChoices;

        int i = 0;
        for (; i < digitCount; i++){
            colorChoices = new DigitBand(null, -1).getColorList();
            c[i] = Utils.randomItemFromList(colorChoices);
        }

        colorChoices = new MultiplierBand(null, -1).getColorList();
        c[i++] = Utils.randomItemFromList(colorChoices); // multiplier band

        colorChoices = new ToleranceBand(null, bandCount == 3).getColorList();
        c[i++] = Utils.randomItemFromList(colorChoices); // tolerance band

        if (bandCount >= 6){
            colorChoices = new TemperatureBand(null).getColorList();
            c[i++] =  Utils.randomItemFromList(colorChoices);
        }



        return c;
    }


    private static int bandCountToDigitCount(int bandCount){

        switch(bandCount){
            case 3:
            case 4: return 2;
            case 5:
            case 6: return 3;
        }
        return -1;
    }



}
