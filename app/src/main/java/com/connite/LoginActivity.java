package com.connite;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

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
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
//            this means that the user has already signed in. move to mainactivity
            startMainActivity();
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
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
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
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            Successfully signed in to firebase
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startMainActivity();
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
}