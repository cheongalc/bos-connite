package com.connite;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class FirebaseGetUserPreferences {
    // Listener defined earlier
    public interface MyCustomObjectListener {
        void onPreferencesRetrieved(Dictionary<String,String> preferences);
    }

    // Member variable was defined earlier
    private FirebaseGetUserPreferences.MyCustomObjectListener listener;

    // Constructor where listener events are ignored
    FirebaseGetUserPreferences() {
        // set null or default listener or accept as argument to constructor
        this.listener = null;
        loadDataAsync();
    }

    // ... setter defined here as shown earlier

    private void loadDataAsync() {

        FirebaseHandler.userRef.child("preferences").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Dictionary<String,String> preferences = null;

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    preferences.put(child.getKey()+"",child.getValue()+"");
                }

                if(listener != null) {
                    Log.d("fuck", dataSnapshot+"");
                    listener.onPreferencesRetrieved(preferences);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setCustomObjectListener(FirebaseGetUserPreferences.MyCustomObjectListener listener) {
        this.listener = listener;
    }
}
