package com.connite;

import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SuggestionsActivity extends AppCompatActivity implements SuggestionFragmentInterface {

    private static final String LOG_TAG = "SUGGESTIONSACTIVITY";
    private ArrayList<ActivityItemData> relevantSuggestionsList;
    private ArrayList<ActivityItemData> acceptedSuggestionsList = new ArrayList<>();
    private int suggestionsLeft;
    private ArrayList<ActivityItemData> declinedSuggestionsList = new ArrayList<>();

    private NonSwipeableViewPager nsvp_SuggestionsViewPager;
    private SuggestionViewPagerAdapter suggestionViewPagerAdapter;


    private FusedLocationProviderClient fusedLocationProviderClient;

    OkHttpClient client = new OkHttpClient();
    ArrayList<String> existingPlaceIDs = new ArrayList<>();
    long lastRadius = 0;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {

                        if (location != null) {
                            GlobalVariables.userLocation = location;
                        }
                    });
        } catch (SecurityException e) {
            Log.e(LOG_TAG, "Security exception: ", e.getCause());
        }

        try {
            sendReqToLambda();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LinearLayout ll_SuggestionsActivityCloseHorizontalContainer = findViewById(R.id.ll_SuggestionsActivityCloseHorizontalContainer);
        ll_SuggestionsActivityCloseHorizontalContainer.setOnClickListener(view -> {
            updateAcceptedSuggestions();
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        });
    }

    private void initViewPager(ArrayList<ActivityItemData> newData) {
        relevantSuggestionsList = newData;
        int numOfPages = relevantSuggestionsList.size();
        suggestionsLeft = relevantSuggestionsList.size();
        nsvp_SuggestionsViewPager = findViewById(R.id.nsvp_SuggestionsViewPager);
        suggestionViewPagerAdapter = new SuggestionViewPagerAdapter(getSupportFragmentManager(), numOfPages, relevantSuggestionsList);
        nsvp_SuggestionsViewPager.setAdapter(suggestionViewPagerAdapter);
        nsvp_SuggestionsViewPager.setCurrentItem(0);
    }

    private void updateAcceptedSuggestions() {

    }

    private ArrayList<ActivityItemData> sendReqToLambda() throws JSONException {
        final ArrayList<ActivityItemData> arrayList = new ArrayList<>();
        final JSONObject postJSON = new JSONObject();
//        double userLatitude = GlobalVariables.userLocation.getLatitude();
//        double userLongitude = GlobalVariables.userLocation.getLongitude();
        double userLatitude = 1.2956775;
        double userLongitude = 103.8561596;
        String coordinates = userLatitude + "," + userLongitude;
        postJSON.put("coordinates", coordinates);
        JSONArray placeIDs = new JSONArray();
        for (int i = 0; i < existingPlaceIDs.size(); i++) {
            placeIDs.put(existingPlaceIDs.get(i));
        }
        postJSON.put("places_list", placeIDs);
        postJSON.put("last_radius", lastRadius);
        FirebaseHandler.userRef.child("preferences").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    postJSON.put("preferences", dataSnapshot.getValue());
                    class Handler extends AsyncTask {
                        @Override
                        protected Object doInBackground(Object[] objects) {
                            Log.w(LOG_TAG, "SENDING STUFF TO LAMBDA");
                            RequestBody body = RequestBody.create(JSON, postJSON.toString());
                            Request request = new Request.Builder()
                                    .url("https://i7gamrq0o7.execute-api.ap-southeast-1.amazonaws.com/prod/")
                                    .header("Content-Type", "application/json; charset=utf-8")
                                    .header("Host", "i7gamrq0o7.execute-api.ap-southeast-1.amazonaws.com")
                                    .post(body)
                                    .build();
                            try {
                                Response response = client.newCall(request).execute();
                                return response.body().string();
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Object o) {
                            String response = String.valueOf(o);
                            try {
                                JSONObject responseJSON = new JSONObject(response);
                                // update the last radius and the existing place IDs
                                Log.w(LOG_TAG, responseJSON.toString());
                                lastRadius = responseJSON.getLong("lastRadius");
                                JSONArray array = responseJSON.getJSONArray("idList");
                                for (int i = 0; i < array.length(); i++) {
                                    existingPlaceIDs.add(array.getString(i));
                                }
                                JSONArray placesListArray = responseJSON.getJSONArray("placesList");
                                for (int i = 0; i < placesListArray.length(); i++) {
                                    JSONObject currPlace = placesListArray.getJSONObject(i);
                                    arrayList.add(new ActivityItemData(currPlace));
                                }
                                SuggestionsActivity.this.runOnUiThread(() -> initViewPager(arrayList));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    Handler handler = new Handler();
                    handler.execute();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        for (int i = 0; i < 5; i++) {
//            arrayList.add(new ActivityItemData(
//                    "Swimming",
//                    "Swimming builds endurance, muscle strength, and maintains a healthy heart and lungs.",
//                    "Lorong 6 Toa Payoh, Swimming Complex, Singapore 319392",
//                    1.330613,
//                    103.850156,
//                    "https://lh5.googleusercontent.com/p/AF1QipNG0TFYMjChYBEpanHyTTffOBF-UQkPAvB7E9zi=w203-h114-k-no",
//                    "6969696",
//                    2));
//            arrayList.add(new ActivityItemData(
//                    "Cycling",
//                    "Swimming builds endurance, muscle strength, and maintains a healthy heart and lungs.",
//                    "Lorong 6 Toa Payoh, Swimming Complex, Singapore 319392",
//                    1.330613,
//                    103.850156,
//                    "https://lh5.googleusercontent.com/p/AF1QipNG0TFYMjChYBEpanHyTTffOBF-UQkPAvB7E9zi=w203-h114-k-no",
//                    "6969696",
//                    2));
//        }
        return arrayList;
    }

    @Override
    public void onSuggestionAction(ActivityItemData currActivityItem, boolean isAccepted) {
        if (isAccepted) {
            // suggestion has been accepted
            if (!acceptedSuggestionsList.contains(currActivityItem)) {
                acceptedSuggestionsList.add(currActivityItem);
                FirebaseHandler.addCurrentActivity(currActivityItem);
                suggestionsLeft--;
            }
        } else {
            // suggestion has been declined
            if (!declinedSuggestionsList.contains(currActivityItem)) {
                declinedSuggestionsList.add(currActivityItem);
                suggestionsLeft--;
            }
        }
        nsvp_SuggestionsViewPager.setCurrentItem(nsvp_SuggestionsViewPager.getCurrentItem()+1);
        checkUserCycledThroughAllSuggestions();
    }

    private void checkUserCycledThroughAllSuggestions() {
        Log.d(LOG_TAG, "Accepted: " + acceptedSuggestionsList.toString());
        Log.d(LOG_TAG, "Declined: " + declinedSuggestionsList.toString());
        Log.d(LOG_TAG, "Suggestions Left: " + suggestionsLeft);
        if (suggestionsLeft == 0) {
            // the user has cycled through everything
            // it is time to re-query the lambda
            Log.w(LOG_TAG, "Starting new round...");
            try {
                sendReqToLambda();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}
