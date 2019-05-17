package com.connite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

public class UserInfoActivity extends AppCompatActivity {

    private String LOG_TAG = "USERINFOACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Log.d(LOG_TAG, "Test");
            closeUserInfoActivity(null);
            return true;
        } else return super.onKeyDown(keyCode, event);
    }

    public void closeUserInfoActivity(View view) {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void startConnectActivity(View view) {
        Toast.makeText(this, "Connect Activity", Toast.LENGTH_SHORT).show();
    }

    public void startPastActivitiesActivity(View view) {
        Toast.makeText(this, "Past Activities", Toast.LENGTH_SHORT).show();
    }

    public void startSettingsActivity(View view) {
        Toast.makeText(this, "Settings Activity", Toast.LENGTH_SHORT).show();
    }
}
