package com.connite;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
        ActivityItemViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_activity_item, null);
            viewHolder = new ActivityItemViewHolder();
            viewHolder.name = convertView.findViewById(R.id.tv_ActivityName);
            viewHolder.description = convertView.findViewById(R.id.tv_ActivityDescription);
            viewHolder.namedLocation = convertView.findViewById(R.id.tv_ActivityNamedLocation);
            viewHolder.icon = convertView.findViewById(R.id.iv_ActivityItemImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ActivityItemViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(currActivityItem.getName());
        viewHolder.description.setText(currActivityItem.getDescription());
        viewHolder.namedLocation.setText(currActivityItem.getNamedLocation());
        Log.d("uri", currActivityItem.getImageUrl());
        Glide.with(context).load(currActivityItem.getImageUrl()).placeholder(R.mipmap.chika).into(viewHolder.icon);
        return convertView;
    }

    static class ActivityItemViewHolder {
        TextView name;
        TextView description;
        TextView namedLocation;
        ImageView icon;
    }
}


