package com.example.david.ohmreader;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.david.ohmreader.Resistor.Band;
import com.example.david.ohmreader.Resistor.BandWrapper;
import com.example.david.ohmreader.Resistor.Resistor;
import com.example.david.ohmreader.Visual.ButtonBand;
import com.example.david.ohmreader.Visual.ColorButtonListViewAdapter;
import com.example.david.ohmreader.Visual.ImageBand;
import com.example.david.ohmreader.Visual.VisualBand;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static Context appContext;

    private int bandCount = -1;


    private ArrayList<VisualBand> colorButtons;

    private ArrayList<ImageView> resistorSliceViews;
    private Resistor resistor;
    private static TextView resultText;
    private SeekBar bandCounter;
    private ImageView listeners;
    private ImageView resistorBodyLeft;
    private ImageView resistorBodyRight;


    private BaseAdapter mAdapter;
    private GridView buttonGrid;

    private boolean OLD_MODE = false;




    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appContext = getApplicationContext();
        init();

        ((Switch)findViewById(R.id.visualToggle)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    OLD_MODE = true;
                }
                else{
                    OLD_MODE = false;
                }
                init();

            }
        });

    }

    private void init() {

        resultText = (TextView)findViewById(R.id.resultText);

        if (!OLD_MODE) {

            findViewById(R.id.resistors).setVisibility(View.VISIBLE);
            findViewById(R.id.ColorButtonGrid).setVisibility(View.GONE);

            initResistorBody();
            initSliceViews();
            initSeekBar();
            updateBandCount(Integer.toString(bandCounter.getProgress() + 3));
            initListenerLayer();

        }
        else{

            findViewById(R.id.resistors).setVisibility(View.GONE);
            findViewById(R.id.ColorButtonGrid).setVisibility(View.VISIBLE);
            initSeekBar();

            updateBandCount(Integer.toString(bandCounter.getProgress() + 3));
            initOldView();


        }
    }


    private void initResistorBody(){
        resistorBodyLeft = (ImageView)findViewById(R.id.body_left);
        resistorBodyRight = (ImageView)findViewById(R.id.body_right);
    }


    private void initSliceViews(){

        resistorSliceViews = new ArrayList<>();
        resistorSliceViews.add((ImageView)findViewById(R.id.band0));
        resistorSliceViews.add((ImageView)findViewById(R.id.band1));
        resistorSliceViews.add((ImageView)findViewById(R.id.band2));
        resistorSliceViews.add((ImageView)findViewById(R.id.band3));
        resistorSliceViews.add((ImageView)findViewById(R.id.band4));
        resistorSliceViews.add((ImageView)findViewById(R.id.band5));

    }

    private void initSeekBar(){

        bandCounter = ((SeekBar)findViewById(R.id.bandCountSlider));
        bandCounter.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateBandCount(Integer.toString(progress + 3));
                ((TextView)findViewById(R.id.bandCount)).setText(Integer.toString(progress + 3) + " Band Resistor");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initListenerLayer(){

        listeners = (ImageView) findViewById(R.id.listeners);
        ListenerLayer.initListener(listeners, colorButtons);

    }

    private void updateBandCount(String value){
        bandCount = Integer.parseInt(value);
        assert(bandCount >= 3 && bandCount <= 6);

        if (!OLD_MODE) {
            toggleResistorBody();
        }

        if (resistor == null || resistor.getBands().size() != bandCount) {
            try {
                resistor = new Resistor(bandCount);
            } catch (BadBandInputException e) {
                e.printStackTrace();
            }
            ; // should never happen
        }
        initButtons();

    }

    private void toggleResistorBody(){

        switch (bandCount){
            case 6:
                resistorBodyRight.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("right_empty", "drawable", this.getPackageName())));
                resistorBodyLeft.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("left_empty", "drawable", this.getPackageName()))); break;
            case 5:
                resistorBodyRight.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("right_full", "drawable", this.getPackageName())));
                resistorBodyLeft.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("left_empty", "drawable", this.getPackageName()))); break;
            default:
                resistorBodyRight.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("right_full", "drawable", this.getPackageName())));
                resistorBodyLeft.setImageDrawable(getResources().getDrawable(getResources().getIdentifier("left_full", "drawable", this.getPackageName()))); break;
        }
    }



    private void initButtons(){

        if (this.colorButtons == null){
            this.colorButtons = new ArrayList<>();
        }
        this.colorButtons.clear();

        if(!OLD_MODE){
            newButtonInit();
        }
        else{
            oldButtonInit();
        }

    }

    private void newButtonInit(){
        int i = 0;
        ArrayList<Band> bList = resistor.getBands();

        if (bandCount> 4){
            this.colorButtons.add(new ImageBand(new BandWrapper(bList.get(i++), resistor), resistorSliceViews.get(0)));
        }
        else{
            this.colorButtons.add(null);
        }

        this.colorButtons.add(new ImageBand(new BandWrapper(bList.get(i++), resistor), resistorSliceViews.get(1)));
        this.colorButtons.add(new ImageBand(new BandWrapper(bList.get(i++), resistor), resistorSliceViews.get(2)));
        this.colorButtons.add(new ImageBand(new BandWrapper(bList.get(i++), resistor), resistorSliceViews.get(3)));
        this.colorButtons.add(new ImageBand(new BandWrapper(bList.get(i++), resistor), resistorSliceViews.get(4)));

        if (bandCount> 5){
            this.colorButtons.add(new ImageBand(new BandWrapper(bList.get(i++), resistor), resistorSliceViews.get(5)));
        }
        else{
            this.colorButtons.add(null);
        }

    }




    public static void updateResultText(String resistance, String tolerance, String tempCoeff){

        String text = "";
        text += Utils.format(Double.parseDouble(resistance)) + "Ω ± " + tolerance + "%";
        if (tempCoeff != null){
            text += "\n\n" + tempCoeff + " ppm/K";
        }

        resultText.setText(text);
    }


    // old stuff:


    private void initOldView(){

        mAdapter = new ColorButtonListViewAdapter(getApplicationContext(), R.layout.color_button, colorButtons);
        buttonGrid = (GridView) findViewById(R.id.ColorButtonGrid);
        buttonGrid.setVisibility(GridView.VISIBLE);
        buttonGrid.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }





    private void oldButtonInit(){

        addDummyButton(0);

        int i = 1;

        ArrayList<Band> bands = resistor.getBands();

        for (Band b : bands){

            if ((bands.size() <= 4 && i == 0 + 1) || (bands.size() <= 5 && i == 6 + 1) || (i == 4 + 1)){
                addDummyButton(i);
                i++;

            }


            colorButtons.add(i, new ButtonBand(new BandWrapper(b, resistor)));

            //colorButtons.add(i, new TextBand(new BandWrapper(b, resistor)));


            i++;

        }

        addDummyButton(i);

        //buttonGrid.setNumColumns(resistor.getBands().size());
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }




    private void addDummyButton(int i){
        ButtonBand temp = new ButtonBand(null);
        temp.getView().setClickable(false);
        temp.getView().setVisibility(View.INVISIBLE);
        colorButtons.add(i, temp);
/*
        TextBand temp = new TextBand(null);
        temp.getView().setClickable(false);
        temp.getView().setVisibility(View.INVISIBLE);
        colorButtons.add(i, temp);*/
    }









}
