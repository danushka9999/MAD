package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RegistrationPage extends AppCompatActivity {
   Button button;

    private static int ScrenOnTime = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        Spinner countrySpinner=(Spinner) findViewById(R.id.countrySpinner);
        ArrayAdapter<String> countryAdapter=new ArrayAdapter<String>(RegistrationPage.this,
            android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.countryNames));
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);

        Spinner countryCodeSpinner=(Spinner) findViewById(R.id.countryCodeSpinner);
        ArrayAdapter<String> countryCodeAdapter=new ArrayAdapter<String>(RegistrationPage.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.countryCodes));
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryCodeSpinner.setAdapter(countryCodeAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(RegistrationPage.this,LoginPage.class);
                startActivity(intent);
                finish();
            }
        },ScrenOnTime);

    }
}