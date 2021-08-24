package com.example.disastermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 2000;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        final FirebaseUser currentUser = mAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                if(currentUser == null) {

                    Intent splashIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(splashIntent);
                    finish();
                }

                else if (currentUser.getEmail().equals("disastermanagement50@gmail.com")) {

                    Intent splashIntent = new Intent(MainActivity.this, AdminNavDrawer.class);
                    startActivity(splashIntent);
                    finish();

                }

                else {
                    Intent splashIntent = new Intent(MainActivity.this, VolunteerNavDrawer.class);
                    startActivity(splashIntent);
                    finish();
                }
            }
        },SPLASH_TIME_OUT);
    }
}
