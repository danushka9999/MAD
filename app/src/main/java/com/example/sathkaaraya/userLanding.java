package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class userLanding extends AppCompatActivity {
    private static int ScrenOnTime = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(userLanding.this,RegistrationPage.class);
                startActivity(intent);
                finish();
            }
        },ScrenOnTime);
    }
}