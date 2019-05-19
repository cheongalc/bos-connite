package com.connite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
//        ViewHolder efficient passing of list items
        ActivityItemViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_activity_item, null);
            viewHolder = new ActivityItemViewHolder();
            viewHolder.name = convertView.findViewById(R.id.tv_ActivityName);
            viewHolder.description = convertView.findViewById(R.id.tv_ActivityDescription);
            viewHolder.namedLocation = convertView.findViewById(R.id.tv_ActivityNamedLocation);
            viewHolder.icon = convertView.findViewById(R.id.iv_ActivityItemImage);
            viewHolder.navigateButton = convertView.findViewById(R.id.btn_NavigateActivity);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ActivityItemViewHolder) convertView.getTag();
        }
//        Initialize content in each list item
        viewHolder.name.setText(currActivityItem.getName());
        viewHolder.description.setText(currActivityItem.getDescription());
        viewHolder.namedLocation.setText(currActivityItem.getNamedLocation());
        Glide.with(context).load(currActivityItem.getImageUrl()).placeholder(R.color.progressBarPink).into(viewHolder.icon);
        final double activityItemLatitude = currActivityItem.getLatitude();
        final double activityItemLongitude = currActivityItem.getLongitude();
        viewHolder.navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String link = "http://maps.google.com/maps?daddr=" + activityItemLatitude + "," + activityItemLongitude;
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(link));
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ActivityItemViewHolder {
        TextView name;
        TextView description;
        TextView namedLocation;
        ImageView icon;
        Button navigateButton;
    }
}


