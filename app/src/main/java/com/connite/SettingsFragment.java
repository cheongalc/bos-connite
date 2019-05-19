package com.connite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class SettingsFragment extends Fragment {
    public RelativeLayout rootLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_settings, container, false);
        rootLayout = viewGroup.findViewById(R.id.rl_SettingsFragmentContainer);
        Fragment fragment = new SettingsScreen();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.rl_SettingsLayout, fragment);
        fragmentTransaction.commit();
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        try {
            FragmentInterface fragmentInterface = (FragmentInterface) getActivity();
            fragmentInterface.onSettingsFragmentCreated(rootLayout);
        } catch (ClassCastException e) {
            Log.e("ERROR", getActivity() + " must implement FragmentInterface");
        }

    }
}
