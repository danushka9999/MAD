package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.sathkaaraya.userProfile.getuserId;

public class HeavyMeals extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText editText_roomNo_heavyMeals,editText_quantity_heavyMeals;
    Button btn_purchase_heavyMeals,btn_vieworder;
    Spinner spinner;
    BottomNavigationView bottomNavigationView;

    //Object decleration of meals
    Meals meal;

    //Database reference
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heavy_meals);

        editText_roomNo_heavyMeals = findViewById(R.id.et_roomNo_heavyMeals);
        editText_quantity_heavyMeals = findViewById(R.id.et_qty_heavyMeals);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.dashboardBN);

        //Setting the spinner array
        spinner = findViewById(R.id.foodItemSpinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.heavyMealItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        btn_purchase_heavyMeals = findViewById(R.id.btn_purchase_heavy);
        btn_vieworder = findViewById(R.id.btn_list_heavyMeals);
        btn_vieworder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =  new Intent(HeavyMeals.this,OrderList.class);
                startActivity(intent);
                finish();
            }
        });

        meal = new Meals();
    }

    //Clear the fields of the entered data
    private void clearData(){
        editText_roomNo_heavyMeals.setText("");
        editText_quantity_heavyMeals.setText("");
    }

    //Method to send the data to the database
    public void purchase(View view){
        db = FirebaseDatabase.getInstance().getReference().child("Meals");

        try{
            if(TextUtils.isEmpty(editText_roomNo_heavyMeals.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the Room No",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(spinner.getSelectedItem().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the Food Item",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(editText_quantity_heavyMeals.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the Quantity",Toast.LENGTH_LONG).show();
            }else{
                meal.setRoomNo(editText_roomNo_heavyMeals.getText().toString().trim());
                meal.setFoodtype(spinner.getSelectedItem().toString().trim());
                meal.setQuantity(editText_quantity_heavyMeals.getText().toString().trim());

                db.child(getuserId()).push().setValue(meal);
//                db.child("heavyMeal").setValue(meal);
                Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_LONG).show();
                clearData();
            }

        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(),"An Error occured",Toast.LENGTH_LONG).show();
        }
    }


    //Override method onItemSelected for the spinner
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}