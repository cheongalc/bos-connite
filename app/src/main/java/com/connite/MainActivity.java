package com.connite;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ExpandableHeightListView ehlv_ActivityItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateActivityItemList();
    }

    public void updateActivityItemList() {
        ArrayList<ActivityItemData> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(
                    new ActivityItemData(
                            "Swimming",
                            "Swimming builds endurance, muscle strength, and maintains a healthy heart and lungs.",
                            "Lorong 6 Toa Payoh, Swimming Complex, Singapore 319392",
                            1,
                            1,
                            "https://lh5.googleusercontent.com/p/AF1QipNG0TFYMjChYBEpanHyTTffOBF-UQkPAvB7E9zi=w203-h114-k-no"));
        }
        ArrayAdapter<ActivityItemData> adapter = new ActivityItemDataAdapter(this, 0, arrayList);
        ehlv_ActivityItemList = findViewById(R.id.ehlv_ActivityItemList);
        ehlv_ActivityItemList.setAdapter(adapter);
        ehlv_ActivityItemList.setExpanded(true);
    }

    public void startSuggestionsActivity(View view) {
        Toast.makeText(this, "Suggestions", Toast.LENGTH_SHORT).show();
    }

    public void startUserInfoActivity(View view) {
        Toast.makeText(this, "User Info", Toast.LENGTH_SHORT).show();
    }
}
