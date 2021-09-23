package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class whereTo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_to);
    }

    public void selectPackage(View view){
        startActivity(new Intent(this, SelectPackage.class));
    }
}