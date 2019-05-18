package com.connite;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private static String LOG_TAG = "LOGINACTIVITY";

    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient googleSignInClient;
    private static int signInRequestCode = 666;

    private RelativeLayout rl_LoginProgressOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);
//      init google sign in options
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        firebaseAuth = FirebaseAuth.getInstance();
//        init firebase database
        GlobalVariables.database = FirebaseDatabase.getInstance();
        GlobalVariables.root = GlobalVariables.database.getReference();

        Intent intent = getIntent();
        boolean isFirstStart = intent.getBooleanExtra("isFirstStart", true);

        if (isFirstStart && firebaseAuth.getCurrentUser() != null) {
//            this means that the user has already signed in. move to mainactivity
            Log.d(LOG_TAG, "The user has already signed in.");
            GlobalVariables.user = firebaseAuth.getCurrentUser();
            updateUserInfo();
//            startQuestionnaireActivity();
            if (checkFirstTimeRun()) startQuestionnaireActivity();
            else startMainActivity();
        } else {
//            the user has not signed in yet. show the login progress overlay.
            rl_LoginProgressOverlay = findViewById(R.id.rl_LoginProgressOverlay);
            Button btn_GoogleSignInButton = findViewById(R.id.btn_GoogleSignInButton);
            btn_GoogleSignInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Sign in to firebase
                    rl_LoginProgressOverlay.setVisibility(View.VISIBLE);
                    Intent signInIntent = googleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, signInRequestCode);
                }
            });
        }
    }

    private void startMainActivity() {
        finish();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        the result arrived from the sign in intent
        if (requestCode == signInRequestCode) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //google sign in was successful, authenticate with firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
                Toast.makeText(this, "Successfully signed in to Google!", Toast.LENGTH_SHORT).show();
            } catch (ApiException e) {
                //google sign in failed, toast to the self
                //status code 12501 is returned when self cancels the login
                if (e.getStatusCode() != 12501) {
                    Log.e(LOG_TAG, "Signin to google failed: ", task.getException());
                    Toast.makeText(this, "Signin failed with status code " + e.getStatusCode(), Toast.LENGTH_LONG).show();
                }
                rl_LoginProgressOverlay.setVisibility(View.GONE);
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(LOG_TAG, "firebaseAuthWithGoogle: " + account.getId());
        Log.d(LOG_TAG, "firebaseAuthWithGoogleToken: " + account.getIdToken());
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Successfully signed in to firebase
                            GlobalVariables.user = firebaseAuth.getCurrentUser();
                            updateUserInfo();
                            if (checkFirstTimeRun()) startQuestionnaireActivity();
                            else startMainActivity();

                            Toast.makeText(LoginActivity.this, "Successfully signed in to Firebase with Google!", Toast.LENGTH_SHORT).show();
                        } else {
//                            sign in failed for some reason
                            Log.e(LOG_TAG, "Sign in to firebase with google failed: ", task.getException());
                            Toast.makeText(LoginActivity.this, "Sign in to firebase failed.", Toast.LENGTH_SHORT).show();
                            rl_LoginProgressOverlay.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void startQuestionnaireActivity() {
        Intent intent = new Intent(this, QuestionnaireActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    private boolean checkFirstTimeRun() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("ConniteSharedPreferences", Context.MODE_PRIVATE);
        Log.d(LOG_TAG, String.valueOf(sharedPreferences.getBoolean("isAppFirstTimeRun", false)));
        if (sharedPreferences.contains("isAppFirstTimeRun")) {
            // isAppFirstTimeRun has been defined before, hence it is NOT the first run
            return false;
        } else {
            // there is no such item in SharedPreferences, therefore it is the first run
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isAppFirstTimeRun", false);
            editor.apply();
            return true;
        }

    }

    private void updateUserInfo() {
        DatabaseReference firebaseUserReference = GlobalVariables.root.child(GlobalVariables.user.getUid());
        firebaseUserReference.child("email").setValue(GlobalVariables.user.getEmail());
    }
}
