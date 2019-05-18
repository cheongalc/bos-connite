package com.connite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class QuestionnaireFragment extends Fragment {
   public RelativeLayout rootLayout;
   private int fragmentNumber;

   private static final String LOG_TAG = "QUESTIONNAIREFRAAGMENT";

    public QuestionnaireFragment() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            fragmentNumber = arguments.getInt("fragmentNumber");
            Log.d(LOG_TAG, "Current fragment number is " + fragmentNumber);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = null;
        switch (fragmentNumber) {
            case 0:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_age_group, container, false);
                break;
            case 1:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_bmi, container, false);
                break;
            case 2:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_activeness, container, false);
                break;
            case 3:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_social, container, false);
                break;
            case 4:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_food, container, false);
                break;
            case 5:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_sports, container, false);
                break;
            case 6:
                viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_questionaire_financial, container, false);
                break;
        }
        rootLayout = (RelativeLayout) viewGroup;
        return viewGroup;
    }
}
