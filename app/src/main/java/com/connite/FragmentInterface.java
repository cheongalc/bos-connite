package com.connite;

import android.widget.RelativeLayout;

public interface FragmentInterface {
    void onHomeFragmentCreated(RelativeLayout rootLayout);
    void onConnectFragmentCreated(RelativeLayout rootLayout);
    void onPastActivitiesFragmentCreated(RelativeLayout rootLayout);
    void onSettingsFragmentCreated(RelativeLayout rootLayout);
}
