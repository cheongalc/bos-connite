package com.connite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuestionnaireActivity extends AppCompatActivity {

    private NonSwipeableViewPager nsvp_QuestionnaireViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

//        Init Viewpager
        nsvp_QuestionnaireViewPager = findViewById(R.id.nsvp_QuestionnaireViewPager);
        final QuestionnaireViewPagerAdapter questionnaireViewPagerAdapter = new QuestionnaireViewPagerAdapter(getSupportFragmentManager());
        nsvp_QuestionnaireViewPager.setAdapter(questionnaireViewPagerAdapter);
        nsvp_QuestionnaireViewPager.setCurrentItem(0);

//        Init Next Button
        Button btn_NextQuestionnaireFragment = findViewById(R.id.btn_NextQuestionnaireFragment);
        btn_NextQuestionnaireFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nsvp_QuestionnaireViewPager.setCurrentItem(nsvp_QuestionnaireViewPager.getCurrentItem()+1);
            }
        });
    }
}
