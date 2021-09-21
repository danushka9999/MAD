package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HeavyMeals extends AppCompatActivity {
    EditText editText_roomNo, editText_foodItem,editText_quantity,editText_unitPrice;
    Button btn_purchase;

    Meals meal;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heavy_meals);

        editText_roomNo = findViewById(R.id.et_roomNo);
        editText_foodItem = findViewById(R.id.et_foodItem);
        editText_quantity = findViewById(R.id.et_qty);
        editText_unitPrice = findViewById(R.id.et_unitPrice);
        btn_purchase = findViewById(R.id.btn_purchase);

        meal = new Meals();
    }

    public void purchase(View view){
        db = FirebaseDatabase.getInstance().getReference().child("Meals");

        try{
            if(TextUtils.isEmpty(editText_roomNo.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the Room No",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(editText_foodItem.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the Food Item",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(editText_quantity.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the Quantity",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(editText_unitPrice.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the UnitPrice",Toast.LENGTH_LONG).show();
            }else{
                meal.setRoomNo(editText_roomNo.getText().toString().trim());
                meal.setFoodtype(editText_foodItem.getText().toString().trim());
                meal.setQuantity(editText_quantity.getText().toString().trim());
                meal.setUnitPrice(editText_unitPrice.getText().toString().trim());

                db.push().setValue(meal);
                Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_LONG).show();
            }

        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(),"An Error occured",Toast.LENGTH_LONG).show();
        }
    }
}