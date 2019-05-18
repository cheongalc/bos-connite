package com.connite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class QuestionnaireViewPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfPages = 7;

    private static final String LOG_TAG = "QUESTIONNAIREADAPTER";

    public QuestionnaireViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Log.d(LOG_TAG, "Current viewPager position is " + position);
        Bundle arguments = new Bundle();
        arguments.putInt("fragmentNumber", position);
        QuestionnaireFragment fragment = new QuestionnaireFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public int getCount() {
        return numberOfPages;
    }
}
