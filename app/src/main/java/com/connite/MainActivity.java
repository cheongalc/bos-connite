package com.connite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSuggestionsActivity(View view) {
        Toast.makeText(this, "Suggestions", Toast.LENGTH_SHORT).show();
    }

    public void startUserInfoActivity(View view) {
        Toast.makeText(this, "User Info", Toast.LENGTH_SHORT).show();
    }
}
