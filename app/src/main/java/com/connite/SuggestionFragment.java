package com.connite;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SuggestionFragment extends Fragment {

    public LinearLayout rootLayout;

    public static SuggestionFragment newInstance(ActivityItemData activityItemData) {
        Bundle arguments = new Bundle();
        arguments.putParcelable("activityItemData", (Parcelable) activityItemData);
        SuggestionFragment fragment = new SuggestionFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActivityItemData currActivityItem = getArguments().getParcelable("activityItemData");
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_suggestion, container, false);
        rootLayout = (LinearLayout) viewGroup;

        ImageView iv_SuggestionImage = viewGroup.findViewById(R.id.iv_SuggestionImage);
        Glide.with(this).load(currActivityItem.getImageUrl()).into(iv_SuggestionImage);

        TextView tv_SuggestionTitle = viewGroup.findViewById(R.id.tv_SuggestionTitle);
        tv_SuggestionTitle.setText(currActivityItem.getName());

        TextView tv_SuggestionDescription = viewGroup.findViewById(R.id.tv_SuggestionDescription);
        tv_SuggestionDescription.setText(currActivityItem.getDescription());

        int costLevel = currActivityItem.getCostLevel();
        int[] costLevelDollarViews = {
                R.id.iv_SuggestionPriceLevel1,
                R.id.iv_SuggestionPriceLevel2,
                R.id.iv_SuggestionPriceLevel3,
                R.id.iv_SuggestionPriceLevel4,
                R.id.iv_SuggestionPriceLevel5
        };
        for (int i = costLevel; i < 5; i++) {
            ImageView iv_SuggestionPriceLevelI = viewGroup.findViewById(costLevelDollarViews[i]);
            iv_SuggestionPriceLevelI.setBackgroundColor(getResources().getColor(R.color.colorDarkGray));
        }

        TextView tv_SuggestionNamedLocation = viewGroup.findViewById(R.id.tv_SuggestionNamedLocation);
        tv_SuggestionNamedLocation.setText(currActivityItem.getNamedLocation());

        return viewGroup;
    }
}
