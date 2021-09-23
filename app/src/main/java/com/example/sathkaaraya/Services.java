package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

    }

    public void detailsForm(View view){
        startActivity(new Intent(this, DetailsForm.class));
    }

    public void onClickFood(View view){
        startActivity(new Intent(this,FoodAndBeverages.class));
    }
}