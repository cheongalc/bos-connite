package com.connite;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FragmentInterface {

    private static String LOG_TAG = "MAINACTIVITY";

//    Home Fragment Variables
    private FragmentManager fragmentManager;
    private RelativeLayout homeFragmentRootLayout;
    private boolean navbarOpenToggle = false;
    private View v_translucentHide;
    private RelativeLayout rl_userInfoNavbarContainer;
    private static int mediumAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        mediumAnimationDuration = getResources().getInteger(android.R.integer.config_mediumAnimTime);
        v_translucentHide = findViewById(R.id.v_translucentHide);
        rl_userInfoNavbarContainer = findViewById(R.id.rl_userInfoNavbarContainer);
        v_translucentHide.setVisibility(View.GONE);
        rl_userInfoNavbarContainer.setVisibility(View.GONE);

        showHomeFragment();
    }

    public void showHomeFragment() {
        HomeFragment fragment = new HomeFragment();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fl_fragmentContainer, fragment);
        transaction.commit();
    }

    public void startSuggestionsActivity(View view) {
        Intent intent = new Intent(this, SuggestionsActivity.class);
        startActivity(intent);
    }

    public void toggleNavbar(View view) {
        if (!navbarOpenToggle) {
            // Fade the navbar in.
            fadeViewIn(v_translucentHide);
            fadeViewIn(rl_userInfoNavbarContainer);
        } else {
            // Fade the navbar out.
            fadeViewOut(v_translucentHide);
            fadeViewOut(rl_userInfoNavbarContainer);
        }
        navbarOpenToggle = !navbarOpenToggle;
        Toast.makeText(this, "Animate Opacity", Toast.LENGTH_SHORT).show();
    }

    private void fadeViewIn(View view) {
        view.setAlpha(0f);
        view.setVisibility(View.VISIBLE);
        view.animate().alpha(1f).setDuration(mediumAnimationDuration).setListener(null);
    }

    private void fadeViewOut(final View view) {
        view.animate().alpha(0f).setDuration(mediumAnimationDuration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                view.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onHomeFragmentCreated(RelativeLayout rootLayout) {
        homeFragmentRootLayout = rootLayout;
    }

    @Override
    public void onContactFragmentCreated(RelativeLayout rootLayout) {

    }

    @Override
    public void onPastActivitiesFragmentCreated(RelativeLayout rootLayout) {

    }

    public void showConnectFragment(View view) {
    }

    public void showSettingsFragment(View view) {
    }

    public void showPastActivitiesFragment(View view) {
    }
}
