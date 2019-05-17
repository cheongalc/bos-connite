package com.connite;

import android.widget.RelativeLayout;

public interface FragmentInterface {
    void onHomeFragmentCreated(RelativeLayout rootLayout);
    void onContactFragmentCreated(RelativeLayout rootLayout);
    void onPastActivitiesFragmentCreated(RelativeLayout rootLayout);
}
