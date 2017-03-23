package com.example.david.ohmreader;

import android.graphics.Color;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.david.ohmreader.Visual.VisualBand;

import java.util.ArrayList;


/**
 * Created by David on 22-Mar-17.
 */

public final class ListenerLayer {

    private static ImageView layer;
    private static ArrayList<VisualBand> actors;

    private ListenerLayer(){}



    public static void initListener(ImageView l, ArrayList<VisualBand> a){

        layer = l;
        actors = a;


        final GestureDetector gestureDetector = new GestureDetector(MainActivity.appContext, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return onTouch(motionEvent, true);
            }

            @Override
            public void onLongPress(MotionEvent motionEvent) {
                onTouch(motionEvent, false);

            }
        });

        layer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    private static boolean onTouch(MotionEvent motionEvent, boolean shortClick){
        final int x = (int) motionEvent.getX();
        final int y = (int) motionEvent.getY();

        int touchColor = Utils.getHotspotColor (layer, x - 1, y - 1);
        if (touchColor == -1){
            return false;
        }

        int tolerance = 25;
        listenerLayerOnTouchHelper(touchColor, tolerance, shortClick);

        return true;
    }

    private static void listenerLayerOnTouchHelper(int touchColor, int tolerance, boolean shortClick){

        if (actors.get(0) != null && Utils.closeMatch (Color.argb(0, 255, 0, 255), touchColor, tolerance)) {
            Log.d("Slice Click", "Violet");
            doClick(0, shortClick);
        }
        else if (Utils.closeMatch (Color.argb(0, 255, 0, 0), touchColor, tolerance)) {
            Log.d("Slice Click", "Red");
            doClick(1, shortClick);
        }
        else if (Utils.closeMatch (Color.argb(0, 0, 255, 0), touchColor, tolerance)) {
            Log.d("Slice Click", "Green");
            doClick(2, shortClick);
        }
        else if (Utils.closeMatch (Color.argb(0, 0, 0, 255), touchColor, tolerance)) {
            Log.d("Slice Click", "Blue");
            doClick(3, shortClick);
        }
        else if (Utils.closeMatch (Color.argb(0, 255, 255, 0), touchColor, tolerance)) {
            Log.d("Slice Click", "Yellow");
            doClick(4, shortClick);
        }
        else if (actors.get(5) != null && Utils.closeMatch (Color.argb(0, 0, 255, 255), touchColor, tolerance)) {
            Log.d("Slice Click", "Red");
            doClick(5, shortClick);
        }
    }

    private static void doClick(int i, boolean shortClick){
        if (shortClick){
            actors.get(i).nextColor();
        }
        else{
            actors.get(i).popUpColorMenu();
        }
    }


}
