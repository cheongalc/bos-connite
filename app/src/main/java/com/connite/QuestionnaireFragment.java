package com.connite;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

public class QuestionnaireFragment extends Fragment {
    public RelativeLayout rootLayout;

    private static final String LOG_TAG = "QUESTIONNAIREFRAAGMENT";

    private QuestionnaireActivity activity;

    public static QuestionnaireFragment newInstance(Bundle arguments) {
        QuestionnaireFragment fragment = new QuestionnaireFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int fragmentNumber = getArguments().getInt("fragmentNumber");
        ViewGroup viewGroup = null;
        this.activity = (QuestionnaireActivity) getActivity();
        switch (fragmentNumber) {
            case 0:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_age_group, container, false);
                RadioGroup radioGroup = viewGroup.findViewById(R.id.rg_AgeGroupOptionsGroup);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        activity.dataClass.ageGroup = checkedId - R.id.rb_AgeGroup1;
                    }
                });
                activity.dataClass.ageGroupFragmentRootView = (RelativeLayout) viewGroup;
                break;
            case 1:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_bmi, container, false);
                activity.dataClass.bmiFragmentRootView = (RelativeLayout) viewGroup;
                break;
            case 2:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_activeness, container, false);
                RadioGroup radioGroupAct = viewGroup.findViewById(R.id.rg_ActivenessOptionsGroup);
                radioGroupAct.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        activity.dataClass.activeness = checkedId - R.id.rb_ActivenessOption1;
                    }
                });
                activity.dataClass.activenessFragmentRootView = (RelativeLayout) viewGroup;
                break;
            case 3:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_social, container, false);
                activity.dataClass.socialFragmentRootView = (RelativeLayout) viewGroup;
                break;
            case 4:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_sports, container, false);
                activity.dataClass.sportsFragmentRootView = (RelativeLayout) viewGroup;
                break;
            case 5:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_financial, container, false);
                RadioGroup radioGroupFin = viewGroup.findViewById(R.id.rg_FinancialOptionsGroup);
                radioGroupFin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        activity.dataClass.finance = checkedId - R.id.rb_FinancialOption1;
                    }
                });
                activity.dataClass.financialFragmentRootView = (RelativeLayout) viewGroup;
                break;
        }
        rootLayout = (RelativeLayout) viewGroup;
        return viewGroup;
    }
}
