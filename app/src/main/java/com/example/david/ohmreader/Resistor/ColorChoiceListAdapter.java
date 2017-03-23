package com.example.david.ohmreader.Resistor;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.david.ohmreader.BandColor;
import com.example.david.ohmreader.MainActivity;

/**
 * Created by David on 16-Mar-17.
 */

public class ColorChoiceListAdapter extends ArrayAdapter<BandColor> {


    public ColorChoiceListAdapter(Context context, int textViewResourceId, BandColor[] objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private class ViewHolder {
        ImageView icon;
        TextView txtTitle;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {


        BandColor v = getItem(position);

        LinearLayout layout = new LinearLayout(MainActivity.appContext);
        ImageView icon = new ImageView(MainActivity.appContext);
        icon.setBackgroundDrawable(new ColorDrawable(v.getColorCode()));

        TextView t = new TextView(MainActivity.appContext);
        t.setText(v.toString());

        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(icon);
        layout.addView(t);

        return layout;

/*        LayoutInflater inflater = getLayoutInflater();
        View row = inflater.inflate(R.layout.row, parent, false);
        TextView label = (TextView)row.findViewById(R.id.weekofday);
        label.setText(getItem(position).toString());

        ImageView icon=(ImageView)row.findViewById(R.id.icon);

        if (DayOfWeek[position]=="Sunday"){
            icon.setImageResource(R.drawable.icon);
        }
        else{
            icon.setImageResource(R.drawable.icongray);
        }

        return row;*/
    }
}


