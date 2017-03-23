package com.example.david.ohmreader;

import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by David on 20-Feb-17.
 */

public enum BandColor {

    // color(digit, multiplier, tolerance%)
    // -1 = illegal color for this attribute
    BLACK   (0, 1,                  -1,     250,    ContextCompat.getColor(MainActivity.appContext, R.color.Black)),
    BROWN   (1, 10,                 1,      100,    ContextCompat.getColor(MainActivity.appContext, R.color.Brown)),
    RED     (2, 100,                2,      50,     ContextCompat.getColor(MainActivity.appContext, R.color.Red)),
    ORANGE  (3, 1000,               -1,     15,     ContextCompat.getColor(MainActivity.appContext, R.color.Orange)),
    YELLOW  (4, 10000,              -1,     25,     ContextCompat.getColor(MainActivity.appContext, R.color.Yellow)),
    GREEN   (5, 100000,             0.5,    20,     ContextCompat.getColor(MainActivity.appContext, R.color.Green)),
    BLUE    (6, Math.pow(10,6),     0.25,   10,     ContextCompat.getColor(MainActivity.appContext, R.color.Blue)),
    VIOLET  (7, 10*Math.pow(10,6),  0.1,    5,      ContextCompat.getColor(MainActivity.appContext, R.color.Violet)),
    GREY    (8, 100*Math.pow(10,6), 0.05,   1,      ContextCompat.getColor(MainActivity.appContext, R.color.Gray)),
    WHITE   (9, Math.pow(10,9),     -1,     -1,     ContextCompat.getColor(MainActivity.appContext, R.color.White)),
    GOLD    (-1, 0.1,               5,      -1,     ContextCompat.getColor(MainActivity.appContext, R.color.Gold)),
    SILVER  (-1, 0.01,              10,     -1,     ContextCompat.getColor(MainActivity.appContext, R.color.Silver)),
    BLANK   (-1, -1,                20,     -1,     android.graphics.Color.TRANSPARENT);


    private int id;
    private int digit;
    private double multiplier;
    private double tolerance;
    private int temperatureCoeff;
    private int color;

    private BandColor(int digit, double multiplier, double tolerance, int tempCoeff, int color) {
        if (digit == -1){
            id = (this.name() == "GOLD") ? 10 : 11;
        }
        else{
            id = digit;
        }
        this.digit = digit;
        this.multiplier = multiplier;
        this.tolerance = tolerance;
        this.temperatureCoeff = tempCoeff;

       //Log.d("color =", String.format("#%06X", ( R.color.Black)));
        this.color = color;
    }

    public int getDigit() throws BadColorRequestException {
        if (this.digit == -1){
            throw new BadColorRequestException();
        }
        return digit;
    }

    public double getMultiplier() throws BadColorRequestException {
        if (this.multiplier == -1){
            throw new BadColorRequestException();
        }
        return multiplier;
    }

    public double getTolerance() throws BadColorRequestException {
        if (this.tolerance == -1){
            throw new BadColorRequestException();
        }
        return tolerance;
    }

    public int getTemperatureCoeff() throws BadColorRequestException {
        if (this.temperatureCoeff == -1){
            throw new BadColorRequestException();
        }
        return temperatureCoeff;
    }

    public int getColorCode(){
        return this.color;
    }



    public static ArrayList<BandColor> getDigitColors() {
        ArrayList<BandColor> r = new ArrayList<BandColor>(Arrays.asList(BandColor.values()));
        r.remove(BandColor.GOLD);
        r.remove(BandColor.SILVER);
        r.remove(BandColor.BLANK);
        return r;
    }

    public static ArrayList<BandColor> getMultiplierColors() {
        ArrayList<BandColor> r = new ArrayList<BandColor>(Arrays.asList(BandColor.values()));
        r.remove(BandColor.BLANK);
        return r;
    }

    public static ArrayList<BandColor> getToleranceColors(boolean is3Band) {

        if (is3Band){
            ArrayList<BandColor> a = new ArrayList<>();
            a.add(BandColor.BLANK);
            return a;
        }

        ArrayList<BandColor> r = new ArrayList<BandColor>(Arrays.asList(BandColor.values()));
        r.remove(BandColor.BLACK);
        r.remove(BandColor.ORANGE);
        r.remove(BandColor.YELLOW);
        r.remove(BandColor.WHITE);
        r.remove(BandColor.BLANK);


        return r;
    }

    public static ArrayList<BandColor> getTempertureColors(){
        ArrayList<BandColor> r = getDigitColors();
        r.remove(BandColor.WHITE);
        return r;
    }




}


