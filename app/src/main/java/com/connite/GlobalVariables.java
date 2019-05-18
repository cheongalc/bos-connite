package com.connite;

import android.location.Location;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GlobalVariables {
    static FirebaseUser user;
    static FirebaseDatabase database;
    static DatabaseReference root;
    static Location userLocation;
}
