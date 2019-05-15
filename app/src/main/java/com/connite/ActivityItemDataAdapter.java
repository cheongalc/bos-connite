package com.connite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ActivityItemDataAdapter extends ArrayAdapter<ActivityItemData> {
    private Context context;
    private List<ActivityItemData> activityItemData;

    public ActivityItemDataAdapter(Context context, int resource, ArrayList<ActivityItemData> objects) {
        super(context, resource, objects);
        this.context = context;
        this.activityItemData = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ActivityItemData currActivityItem = activityItemData.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        return convertView;
    }
}
