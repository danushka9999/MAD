package com.example.sathkaaraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Services extends AppCompatActivity{
    BottomNavigationView bottomNavigationView;
    private static final String TAG = "Services";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.dashboardBN);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.personBN:
                        startActivity(new Intent(getApplicationContext(), userProfile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.dashboardBN:
                        return true;
                    case R.id.receiptBN:
                        startActivity(new Intent(getApplicationContext(), ReceiptPage.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }

        });




    }

    public void WhereTo(View view){

        startActivity(new Intent(this, whereTo.class));
    }

    public void onClickFood(View view){
        startActivity(new Intent(this,FoodAndBeverages.class));
    }
    public void gymandactivities(View view){
        startActivity(new Intent(this, GymAndActivities.class));
    }

}