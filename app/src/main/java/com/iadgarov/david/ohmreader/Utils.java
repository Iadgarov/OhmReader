package com.iadgarov.david.ohmreader;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.PopupMenu;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by David on 15-Mar-17.
 */

public final class Utils {

    private Utils(){}

    public static int intToColor(int t) {
        return ContextCompat.getColor(MainActivity.appContext, t);
    }

    public static <T> T randomItemFromList(List<T> l) {
        return l.get(new Random().nextInt(l.size()));
    }

    private static final NavigableMap<Double, String> suffixes = new TreeMap<>();

    static {
        suffixes.put(1_000D, " k");
        suffixes.put(1_000_000D, " M");
        suffixes.put(1_000_000_000D, " G");
        suffixes.put(1_000_000_000_000D, " T");
        suffixes.put(1_000_000_000_000_000D, " P");
        suffixes.put(1_000_000_000_000_000_000D, " E");
    }

    public static String format(Double value) {
        //Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
        if (value == Long.MIN_VALUE) return format(Double.MIN_VALUE + 1);
        if (value < 0) return "-" + format(-value);

        DecimalFormat numberFormat = new DecimalFormat("#.##");

        if (value < 1000) return numberFormat.format(value); //deal with easy case

        Map.Entry<Double, String> e = suffixes.floorEntry(value);
        Double divideBy = e.getKey();
        String suffix = e.getValue();



        Double truncated = value / (divideBy / 10); //the number part of the output times 10
        boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);

        Double t = hasDecimal ? (truncated / 10d) : (truncated / 10);
        return numberFormat.format(t) + suffix;

    }

    public static String getMultiplier(double d){
        Map.Entry<Double, String> e = suffixes.floorEntry(d);
        if (e == null){
            return Double.toString(d);
        }
        Double divideBy = e.getKey();
        String suffix = e.getValue();
        return suffix;
    }


    public static void setForceShowIcon(PopupMenu popupMenu) {
        try {
            Field[] fields = popupMenu.getClass().getDeclaredFields();
            for (Field field : fields) {
                if ("mPopup".equals(field.getName())) {
                    field.setAccessible(true);
                    Object menuPopupHelper = field.get(popupMenu);
                    Class<?> classPopupHelper = Class.forName(menuPopupHelper
                            .getClass().getName());
                    Method setForceIcons = classPopupHelper.getMethod(
                            "setForceShowIcon", boolean.class);
                    setForceIcons.invoke(menuPopupHelper, true);
                    break;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static String stringToVertical(String s){
        String result = "";
        for (int i = 0; i < s.length(); i++){
            result += (i == 0) ? s.toUpperCase().charAt(i) : s.toLowerCase().charAt(i);
            result += (i != s.length() - 1) ? '\n' : "";
        }
        result = result.substring(0, result.length());
        return result;
    }


    public static int getHotspotColor (ImageView i, int x, int y) {
        ImageView img = i;
        img.setDrawingCacheEnabled(true);
        Bitmap hotspots;
        try {
            hotspots = Bitmap.createBitmap(img.getDrawingCache());
        }
        catch (Exception e){
            return -1;
        }
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }


    public static boolean closeMatch (int color1, int color2, int tolerance) {
        if ((int) Math.abs (Color.red (color1) - Color.red (color2)) > tolerance ) {
            return false;
        }
        if ((int) Math.abs (Color.green (color1) - Color.green (color2)) > tolerance ) {
            return false;
        }
        if ((int) Math.abs (Color.blue (color1) - Color.blue (color2)) > tolerance ) {
            return false;
        }
        return true;
    } // end match

    public static Drawable colorToImage(BandColor c, int band, Context con){

        String s = "";
        s += c.toString().toLowerCase();
        s += Integer.toString(band);

        Resources resources = con.getResources();
        final int resourceId = resources.getIdentifier(s, "drawable", con.getPackageName());

        Log.d("Resource: ", s + "   id: " + Integer.toString(resourceId));
        return resources.getDrawable(resourceId);


    }

}
