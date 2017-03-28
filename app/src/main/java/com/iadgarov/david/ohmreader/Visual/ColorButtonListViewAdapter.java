package com.iadgarov.david.ohmreader.Visual;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by David on 13-Mar-17.
 */

public class ColorButtonListViewAdapter extends ArrayAdapter<VisualBand> {


    Context context;
    List<VisualBand> items;

    public ColorButtonListViewAdapter(Context context, int resourceId, List<VisualBand> items) {
        super(context, resourceId, items);
        this.context = context;
        this.items = items;
    }

    //*private view holder class*//*
    private class ViewHolder {
        ButtonBand button;
        TextView txtTitle;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        convertView = getItem(position).getView();
        return convertView;
    }


}
