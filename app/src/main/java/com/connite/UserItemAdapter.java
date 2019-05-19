package com.connite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.service.autofill.UserData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserItemAdapter extends ArrayAdapter<UserClass> {

    Context context;
    ArrayList<UserClass> userData;


    public UserItemAdapter(Context context, int resource, ArrayList<UserClass> objects) {
        super(context, resource, objects);
        this.context = context;
        this.userData = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final UserClass curUser = userData.get(position);
//        ViewHolder efficient passing of list items
        UserItemAdapter.UserViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_list_item, null);
            viewHolder = new UserItemAdapter.UserViewHolder();
            viewHolder.name = convertView.findViewById(R.id.tv_UserName);
            viewHolder.subText= convertView.findViewById(R.id.tv_UserSub);
            viewHolder.icon = convertView.findViewById(R.id.civ_UserProfileImage);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (UserItemAdapter.UserViewHolder) convertView.getTag();
        }
//        Initialize content in each list item
        viewHolder.name.setText(curUser.getName());
        viewHolder.subText.setText(curUser.getSubText());
        Glide.with(context).load(curUser.getProfileUrl()).placeholder(R.color.progressBarPink).into(viewHolder.icon);

//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Intent intent = new Intent(Intent.ACTION_VIEW)
//                        .setType("plain/text")
//                        .setData(Uri.parse(curUser.getSubText()));
//                context.startActivity(intent);
//            }
//        });

        return convertView;
    }

    static class UserViewHolder {
        TextView name;
        TextView subText;
        CircleImageView icon;
    }


}
