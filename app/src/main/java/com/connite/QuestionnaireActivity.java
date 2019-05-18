package com.connite;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class QuestionnaireActivity extends AppCompatActivity {

    private static final String LOG_TAG = "QUESTIONNAIREACTIVITY";
    private NonSwipeableViewPager nsvp_QuestionnaireViewPager;
    private static double numPages = 6.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        final ProgressBar pb_QuestionnaireProgress = findViewById(R.id.pb_QuestionnaireProgress);
        pb_QuestionnaireProgress.setProgress((int) (1.0/6.0*100));

//        Init Next Button
        final Button btn_NextQuestionnaireFragment = findViewById(R.id.btn_NextQuestionnaireFragment);
        btn_NextQuestionnaireFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nsvp_QuestionnaireViewPager.setCurrentItem(nsvp_QuestionnaireViewPager.getCurrentItem()+1);
            }
        });

//        Init Viewpager
        nsvp_QuestionnaireViewPager = findViewById(R.id.nsvp_QuestionnaireViewPager);
        final QuestionnaireViewPagerAdapter questionnaireViewPagerAdapter = new QuestionnaireViewPagerAdapter(getSupportFragmentManager());
        nsvp_QuestionnaireViewPager.setAdapter(questionnaireViewPagerAdapter);
        nsvp_QuestionnaireViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                double positionDouble = (double) position;
                double progress = ((positionDouble+1.0)/numPages)*100;
                pb_QuestionnaireProgress.setProgress((int) progress);
                if (progress == 100) {
                    // set Button to final
                    btn_NextQuestionnaireFragment.setText("Finish");
                    btn_NextQuestionnaireFragment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            processQuestionnaireData();
                            finish();
                            Intent intent = new Intent(QuestionnaireActivity.this, MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                    });
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        nsvp_QuestionnaireViewPager.setCurrentItem(0);
    }

    private void processQuestionnaireData() {

    }
}
