package com.connite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class SuggestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        ArrayList<ActivityItemData> relevantActivitiesList = sendReqToLambda();
        int numOfPages = relevantActivitiesList.size();

        NonSwipeableViewPager nsvp_SuggestionsViewPager = findViewById(R.id.nsvp_SuggestionsViewPager);
        final SuggestionViewPagerAdapter suggestionViewPagerAdapter = new SuggestionViewPagerAdapter(getSupportFragmentManager(), numOfPages, relevantActivitiesList);
        nsvp_SuggestionsViewPager.setAdapter(suggestionViewPagerAdapter);
    }

    private ArrayList<ActivityItemData> sendReqToLambda() {
        ArrayList<ActivityItemData> arrayList = new ArrayList<>();
        arrayList.add(new ActivityItemData(
                "Swimming",
                "Swimming builds endurance, muscle strength, and maintains a healthy heart and lungs.",
                "Lorong 6 Toa Payoh, Swimming Complex, Singapore 319392",
                1.330613,
                103.850156,
                "https://lh5.googleusercontent.com/p/AF1QipNG0TFYMjChYBEpanHyTTffOBF-UQkPAvB7E9zi=w203-h114-k-no",
                "6969696",
                2));
        return arrayList;
    }
}
