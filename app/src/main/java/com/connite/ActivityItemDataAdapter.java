package com.connite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
            convertView = inflater.inflate(R.layout.list_activity_item, null);
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
        viewHolder.icon.setImageURI(currActivityItem.getImageUri());
        return convertView;
    }

    static class ActivityItemViewHolder {
        TextView name;
        TextView description;
        TextView namedLocation;
        ImageView icon;
    }
}


