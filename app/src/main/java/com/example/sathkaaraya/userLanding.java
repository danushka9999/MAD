package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class userLanding extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing);


    }

    public void Join(View view){
        startActivity(new Intent(userLanding.this,RegistrationPage.class));
    }
    public void landingSign(View view){
        startActivity(new Intent(userLanding.this,LoginPage.class));
    }
    public void tempUserProfile(View view){
        startActivity(new Intent(userLanding.this,userProfile.class));
    }
    public void receipt(View view){
        startActivity(new Intent(userLanding.this,landingReceipt.class));
    }
}