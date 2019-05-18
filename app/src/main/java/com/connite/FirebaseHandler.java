package com.connite;

import android.preference.Preference;
import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Dictionary;

import javax.security.auth.callback.Callback;

class FirebaseHandler {

    static FirebaseDatabase database = GlobalVariables.database;
    private static DatabaseReference reference = GlobalVariables.root;
    private static FirebaseUser user = GlobalVariables.user;
    static DatabaseReference userRef = reference.child(user.getUid());

    static void updateUserPreferences(double physicalWeight, double financialWeight, double socialWeight) {
        userRef.child("preferences").child("physicalWeight").setValue(physicalWeight);
        userRef.child(user.getUid()).child("preferences").child("financialWeight").setValue(financialWeight);
        userRef.child(user.getUid()).child("preferences").child("socialWeight").setValue(socialWeight);
    }

    static void addCurrentActivity (ActivityItemData currentActivity) {
        userRef.child("activityQueue").child(currentActivity.getPlaceId()).setValue(currentActivity);
    }

    static void clearActivityQueue () {
        userRef.child("activityQueue").setValue("");
    }

    static void addPastActivity (ActivityItemData pastActivity) {
        userRef.child("activityQueue").child(pastActivity.getPlaceId()).setValue(pastActivity);
    }

    static void addPastActivities (ArrayList<ActivityItemData> pastActivities) {
        for (ActivityItemData activityItemData : pastActivities) {
            addPastActivity(activityItemData);
        }
    }

    static void moveCurrentActivitiesToPast () {
        FirebaseGetActivitiesClass getActivitiesClass = new FirebaseGetActivitiesClass();
        getActivitiesClass.setCustomObjectListener(new FirebaseGetActivitiesClass.MyCustomObjectListener() {
            @Override
            public void onActivitiesLoaded(ArrayList<ActivityItemData> activityList) {
                clearActivityQueue();
                addPastActivities(activityList);
            }
        });
    }

}
