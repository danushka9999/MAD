package com.example.sathkaaraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
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

public class Beverages extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText editText_roomNo_beverages,editText_quantity_beverages;
    Spinner spinner3;
    Button btn_purchase_beverages,btn_view_order_bvg;
    BottomNavigationView bottomNavigationView;

    //Databse reference
    DatabaseReference db;

    //Meal object decleration
    Meals beverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beverages);

        editText_roomNo_beverages = findViewById(R.id.et_roomNo_beverages);
        editText_quantity_beverages = findViewById(R.id.et_qty_beverages);
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
                        startActivity(new Intent(getApplicationContext(), landingReceipt.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }

        });
        //Setting the spinner array
        spinner3 = findViewById(R.id.foodItemSpinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.beverageItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter);
        spinner3.setOnItemSelectedListener(this);
        btn_purchase_beverages = findViewById(R.id.btn_purchase_beverages);
        btn_view_order_bvg = findViewById(R.id.btn_list_beverages);
        btn_view_order_bvg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =  new Intent(Beverages.this,OrderList.class);
                startActivity(intent);
                finish();
            }
        });

        beverage = new Meals();
    }

    //Clear the fields of the entered data
    private void clearData(){
        editText_roomNo_beverages.setText("");
        editText_quantity_beverages.setText("");
    }

    public void purchase(View view){
        db = FirebaseDatabase.getInstance().getReference().child("Meals");

        try{
            if(TextUtils.isEmpty(editText_roomNo_beverages.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the Room No",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(spinner3.getSelectedItem().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the Food Item",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(editText_quantity_beverages.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the Quantity",Toast.LENGTH_LONG).show();
            }else{
                beverage.setRoomNo(editText_roomNo_beverages.getText().toString().trim());
                beverage.setFoodtype(spinner3.getSelectedItem().toString().trim());
                beverage.setQuantity(editText_quantity_beverages.getText().toString().trim());


              db.child(getuserId()).push().setValue(beverage);
//                db.child("bvg").setValue(beverage);
                Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_LONG).show();
                clearData();
            }

        }catch(NumberFormatException e){
            Toast.makeText(getApplicationContext(),"An Error occured",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}