package com.connite;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

public class QuestionnaireViewPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfPages = 6;
    private int currentPage;

    private static final String LOG_TAG = "QUESTIONNAIREADAPTER";

    public QuestionnaireViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        currentPage = position;
        Log.d(LOG_TAG, "Current viewPager position is " + position);
        Bundle arguments = new Bundle();
        arguments.putInt("fragmentNumber", position);
        return QuestionnaireFragment.newInstance(arguments);
    }

    @Override
    public int getCount() {
        return numberOfPages;
    }

}
