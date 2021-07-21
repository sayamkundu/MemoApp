package com.sayam.memo;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class Memo extends Application implements FirebaseAuth.AuthStateListener {

    @Override
    public void onCreate() {

        super.onCreate();
        FirebaseAuth
                .getInstance()
                .addAuthStateListener(this);
    }


    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

        Intent       intent;
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser == null) {
            intent = new Intent(this, MainActivity.class);
        }
        else {

            //logged in with USER : currentUser
            intent = new Intent(this, LoginActivity.class);
            intent.putExtra("current.user", currentUser);
        }

        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
