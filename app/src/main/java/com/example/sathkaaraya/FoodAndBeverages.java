package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FoodAndBeverages extends AppCompatActivity implements View.OnClickListener {
    CardView cardView_heavyMeals;
    CardView cardView_snacks;
    CardView cardView_beverages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_and_beverages);

        cardView_heavyMeals = findViewById(R.id.cv_heavyMeals);
        cardView_heavyMeals.setOnClickListener(this);

        cardView_snacks = findViewById(R.id.cv_snacks);
        cardView_snacks.setOnClickListener(this);

        cardView_beverages = findViewById(R.id.cv_beverages);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cv_heavyMeals:
                startActivity(new Intent(this,HeavyMeals.class));
                break;
            case R.id.cv_snacks:
                startActivity(new Intent(this,Snacks.class));
        }

    }
}