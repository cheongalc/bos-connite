package com.connite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class QuestionnaireActivity extends AppCompatActivity {

    private NonSwipeableViewPager nsvp_QuestionnaireViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

//        Init Viewpager
        nsvp_QuestionnaireViewPager = findViewById(R.id.nsvp_QuestionnaireViewPager);

    }
}
