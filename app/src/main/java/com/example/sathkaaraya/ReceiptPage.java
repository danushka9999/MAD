package com.example.sathkaaraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ReceiptPage extends AppCompatActivity {


    TextView FoodPackage,GymPackage,TravelPackage,Foodprice,Gymprice,Travelprice;
    BottomNavigationView bottomNavigationView;

    landingReceipt lan=new landingReceipt();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt_page);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.receiptBN);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.personBN:
                        startActivity(new Intent(getApplicationContext(), userProfile.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.dashboardBN:
                        startActivity(new Intent(getApplicationContext(), Services.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.receiptBN:
                        return true;

                }
                return false;
            }

        });
        try {
            FoodPackage = (findViewById(R.id.tv_foodPackage));
            GymPackage=(findViewById(R.id.tv_GymPackage));
            TravelPackage=(findViewById(R.id.tv_TravelPackage));
            Foodprice=(findViewById(R.id.tv_Foodprice));
            Gymprice=(findViewById(R.id.tv_GymPrice));
            Travelprice=findViewById(R.id.tv_travelPrice);

            FoodPackage.setText(lan.getName());
            GymPackage.setText(lan.getName());
            TravelPackage.setText(lan.getName());
            String FoodPrice= String.valueOf(lan.getFoodPrice());
            String GymPrice=String.valueOf(lan.getGymprice());
            String TravelPrice=String.valueOf(lan.getTravelPrice());
            String Days=String.valueOf(lan.getDays());
            Foodprice.setText("No of Days("+Days+") * "+lan.getName()+" Package"+"= Rs."+FoodPrice);
            Gymprice.setText("No of Days("+Days+") * "+lan.getName()+" Package"+"= Rs."+GymPrice);
            Travelprice.setText("No of Days("+Days+") * "+lan.getName()+" Package"+"= Rs."+TravelPrice);

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error"+e,Toast.LENGTH_SHORT).show();
        }
    }
}