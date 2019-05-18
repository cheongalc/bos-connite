package com.connite;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class QuestionnaireActivity extends AppCompatActivity {

    private static final String LOG_TAG = "QUESTIONNAIREACTIVITY";
    private NonSwipeableViewPager nsvp_QuestionnaireViewPager;
    private static double numPages = 6.0;

    public QuestionnaireDataClass dataClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);


        final ProgressBar pb_QuestionnaireProgress = findViewById(R.id.pb_QuestionnaireProgress);
        pb_QuestionnaireProgress.setProgress((int) (1.0/6.0*100));

        dataClass = new QuestionnaireDataClass();



//        Init Next Button
        final Button btn_NextQuestionnaireFragment = findViewById(R.id.btn_NextQuestionnaireFragment);
        btn_NextQuestionnaireFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nsvp_QuestionnaireViewPager.setCurrentItem(nsvp_QuestionnaireViewPager.getCurrentItem()+1);
                RadioButton rad = dataClass.ageGroupFragmentRootView.findViewById(R.id.rb_AgeGroup1);
                Log.d("ageGroupRelative", rad.isChecked()+"");
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
                            RadioButton rad = dataClass.ageGroupFragmentRootView.findViewById(R.id.rb_AgeGroup1);
                            Log.d("ageGroupRelative", rad.isChecked()+"");
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
        int ageGroup = dataClass.ageGroup;
        int activeness = dataClass.activeness;
        int financial = dataClass.finance;

        EditText etHeight = dataClass.bmiFragmentRootView.findViewById(R.id.et_BMIHeightInput);
        EditText etWeight = dataClass.bmiFragmentRootView.findViewById(R.id.et_BMIWeightInput);
        int height = Integer.parseInt(etHeight.getText().toString());
        int weight = Integer.parseInt(etWeight.getText().toString());

        SeekBar socialSeekBar = dataClass.socialFragmentRootView.findViewById(R.id.sb_SocialScale);
        int socialLevel = socialSeekBar.getProgress();
        dataClass.socialWeight = socialLevel*0.5 + 0.5;

        computeFinancialWeight(financial);
        computeBMI(height, weight);
        computeAgeGroupWeight(ageGroup);
        computeActivenessWeight(activeness);

        dataClass.physicalWeight = dataClass.activenessWeight*dataClass.ageGroupWeight*dataClass.bmiWeight*0.5 +0.5;

        
    }

    private void computeBMI(int height, int weight){
        double heightInM = height/100.0;
        double bmi = weight/(heightInM*heightInM);

        double bmiWeight;

        if(bmi <= 18.5) {
            bmiWeight = 0.9;
        } else if(bmi <= 25) {
            bmiWeight = 0.8;
        } else {
            bmiWeight = 1.0;
        }

        dataClass.bmiWeight = bmiWeight;
    }

    private void computeAgeGroupWeight (int ageGroup) {
        double ageGroupWeight = 0.0;

        switch (ageGroup) {
            case 0:
                ageGroupWeight = 0.8;
                break;
            case 1:
                ageGroupWeight = 1.0;
                break;
            case 2:
                ageGroupWeight = 0.7;
                break;
            case 3:
                ageGroupWeight = 0.6;
                break;
            case 4:
                ageGroupWeight = 0.5;
                break;
        }

        dataClass.ageGroupWeight = ageGroupWeight;
    }

    private void computeFinancialWeight (int financial) {
        double financialWeight = 0.0;

        switch (financial) {
            case 0:
                financialWeight = 1.0;
                break;
            case 1:
                financialWeight = 0.8;
                break;
            case 2:
                financialWeight = 0.7;
                break;
            case 3:
                financialWeight = 0.6;
                break;
            case 4:
                financialWeight = 0.5;
                break;
        }
        dataClass.financialWeight = financialWeight;
    }

    private void computeActivenessWeight (int activeness) {
        double activenessWeight = 0.0;

        switch (activeness) {
            case 0:
                activenessWeight = 1.0;
                break;
            case 1:
                activenessWeight = 0.8;
                break;
            case 2:
                activenessWeight = 0.7;
                break;
            case 3:
                activenessWeight = 0.6;
                break;
            case 4:
                activenessWeight = 0.5;
                break;
        }
        dataClass.activenessWeight = activenessWeight;
    }
}
