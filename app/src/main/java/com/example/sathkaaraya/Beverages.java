package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Beverages extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText editText_roomNo_beverages,editText_quantity_beverages;
    Spinner spinner3;
    Button btn_purchase_beverages;

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
        //Setting the spinner array
        spinner3 = findViewById(R.id.foodItemSpinner3);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.beverageItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter);
        spinner3.setOnItemSelectedListener(this);
        btn_purchase_beverages = findViewById(R.id.btn_purchase_beverages);

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


//              db.push().setValue(beverage);
                db.child("bvg").setValue(beverage);
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