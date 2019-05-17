package com.connite;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements FragmentInterface {

    private static String LOG_TAG = "MAINACTIVITY";

//    Variables for navbar
    private boolean navbarIsOpen = false;
    private View v_translucentHide;
    private RelativeLayout rl_userInfoNavbarContainer;
    private static int mediumAnimationDuration;

//    Variables for fragment management
    private FragmentManager fragmentManager;
    private RelativeLayout homeFragmentRootLayout,
            connectFragmentRootLayout,
            pastActivitiesFragmentRootLayout,
            settingsFragmentRootLayout;
    private String currentFragment = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Initialize navbar
        mediumAnimationDuration = getResources().getInteger(android.R.integer.config_mediumAnimTime);
        v_translucentHide = findViewById(R.id.v_TranslucentEffectMask);
        v_translucentHide.setVisibility(View.GONE);
        v_translucentHide.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { toggleNavbar(); }});
        rl_userInfoNavbarContainer = findViewById(R.id.rl_NavbarContainer);
        rl_userInfoNavbarContainer.setVisibility(View.GONE);
        CardView cv_userHeaderCard = findViewById(R.id.cv_UserHeaderCard);
        cv_userHeaderCard.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { toggleNavbar(); }});
        LinearLayout ll_NavbarHomeContainer = findViewById(R.id.ll_NavbarHomeContainer);
        ll_NavbarHomeContainer.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { showFragment("Home"); }});
        LinearLayout ll_NavbarConnectContainer = findViewById(R.id.ll_NavbarConnectContainer);
        ll_NavbarConnectContainer.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showFragment("Connect"); }});
        LinearLayout ll_NavbarPastActivitiesContainer = findViewById(R.id.ll_NavbarPastActivitiesContainer);
        ll_NavbarPastActivitiesContainer.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view) { showFragment("PastActivities"); }});
        LinearLayout ll_NavbarSettingsContainer = findViewById(R.id.ll_NavbarSettingsContainer);
        ll_NavbarSettingsContainer.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view) { showFragment("Settings"); }});

//        Initialize fragment management
        fragmentManager = getSupportFragmentManager();
        showFragment("Home");

//        Initialize suggestions activity
    }

    public void showFragment(String fragmentName) {
        if (currentFragment.equals(fragmentName)) return ;
        currentFragment = fragmentName;
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (fragmentName) {
            case "Home":
                fragment = new HomeFragment();
                break;
            case "Connect":
                fragment = new ConnectFragment();
                break;
            case "PastActivities":
                fragment = new PastActivitiesFragment();
                break;
            case "Settings":
                fragment = new SettingsFragment();
                break;
        }
        transaction.replace(R.id.fl_MainActivityFragmentContainer, fragment);
        transaction.commit();
        if (navbarIsOpen) toggleNavbar();
    }

    public void startSuggestionsActivity(View view) {
        Intent intent = new Intent(this, SuggestionsActivity.class);
        startActivity(intent);
    }

    public void toggleNavbar() {
        if (!navbarIsOpen) {
            // Fade the navbar in.
            fadeViewIn(v_translucentHide);
            fadeViewIn(rl_userInfoNavbarContainer);
        } else {
            // Fade the navbar out.
            fadeViewOut(v_translucentHide);
            fadeViewOut(rl_userInfoNavbarContainer);
        }
        navbarIsOpen = !navbarIsOpen;
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
    public void onConnectFragmentCreated(RelativeLayout rootLayout) {
        connectFragmentRootLayout = rootLayout;
    }

    @Override
    public void onPastActivitiesFragmentCreated(RelativeLayout rootLayout) {
        pastActivitiesFragmentRootLayout = rootLayout;
    }

    @Override
    public void onSettingsFragmentCreated(RelativeLayout rootLayout) {
        settingsFragmentRootLayout = rootLayout;
    }
}
