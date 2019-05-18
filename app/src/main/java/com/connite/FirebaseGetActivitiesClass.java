package com.connite;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FirebaseGetActivitiesClass {
    // Listener defined earlier
    public interface MyCustomObjectListener {
        void onActivitiesLoaded(ArrayList<ActivityItemData> activityList);
    }

    // Member variable was defined earlier
    private MyCustomObjectListener listener;

    // Constructor where listener events are ignored
    FirebaseGetActivitiesClass() {
        // set null or default listener or accept as argument to constructor
        this.listener = null;
        loadDataAsync();
    }

    // ... setter defined here as shown earlier

    private void loadDataAsync() {

        FirebaseHandler.userRef.child("activityQueue").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ArrayList<ActivityItemData> activityList = new ArrayList<>();

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    activityList.add(child.getValue(ActivityItemData.class));
                }

                if(listener != null) {
                    Log.d("fuck", dataSnapshot+"");
                    listener.onActivitiesLoaded(activityList);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setCustomObjectListener(MyCustomObjectListener listener) {
        this.listener = listener;
    }
}