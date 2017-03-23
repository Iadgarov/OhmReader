package com.example.david.ohmreader.Visual;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.david.ohmreader.BandColor;
import com.example.david.ohmreader.MainActivity;
import com.example.david.ohmreader.Resistor.BandWrapper;
import com.example.david.ohmreader.Utils;

import java.util.ArrayList;

/**
 * Created by David on 21-Mar-17.
 */

public abstract class VisualBand{

    private int clickCount = 0;
    private ArrayList<BandColor> colors;
    private BandWrapper band;
    private View view;


    public VisualBand(BandWrapper band){


        if (band == null){
            return;
        }

        this.band = band;
        this.colors = band.getColorList();
        this.clickCount = colors.indexOf(band.getColor());

    }



    protected abstract void updateVisual(BandColor c);



    public ArrayList<BandColor> getColors() {
        return colors;
    }

    public BandWrapper getBand() {
        return band;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public void setColors(ArrayList<BandColor> colors) {
        this.colors = colors;
    }

    public void setBand(BandWrapper band) {
        this.band = band;
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void changeColor(BandColor c){

        this.band.setColor(c);
        this.clickCount = colors.indexOf(band.getColor());
        updateVisual(c);
    }



    public void nextColor(){
        changeColor(this.colors.get((this.clickCount + 1) % this.colors.size()));
    }

    public void popUpColorMenu(){

        PopupMenu popup = new PopupMenu(MainActivity.appContext, view);

        Utils.setForceShowIcon(popup);

        for (BandColor c : this.band.getColorList()) {

            MenuItem temp = popup.getMenu().add(c.toString());
            ShapeDrawable icon = new ShapeDrawable(new RectShape());
            icon.setIntrinsicHeight(100);
            icon.setIntrinsicWidth(100);
            icon.getPaint().setColor(c.getColorCode());
            temp.setIcon(icon);

        }


        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("Menu Item Chosen",item.toString());
                VisualBand.this.changeColor(BandColor.valueOf(item.getTitle().toString()));
                return true;
            }
        });


        popup.show(); //showing popup menu


    }




}
