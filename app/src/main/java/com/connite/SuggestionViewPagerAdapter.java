package com.connite;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class SuggestionViewPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfPages;
    private ArrayList<ActivityItemData> activityItemData;

    public SuggestionViewPagerAdapter(FragmentManager fm, int numberOfPages, ArrayList<ActivityItemData> activityItemData) {
        super(fm);
        this.numberOfPages = numberOfPages;
        this.activityItemData = activityItemData;
    }

    @Override
    public Fragment getItem(int position) {
        return SuggestionFragment.newInstance(activityItemData.get(position));
    }

    @Override
    public int getCount() {
        return numberOfPages;
    }
}
