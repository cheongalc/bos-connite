package com.connite;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class SettingsScreen extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }
}
