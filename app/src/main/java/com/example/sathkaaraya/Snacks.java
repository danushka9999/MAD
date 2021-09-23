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

public class Snacks extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText editText_roomNo_snacks,editText_quantity_snacks;
    Spinner spinner2;
    Button btn_purchaseSnack;

    //Databse reference
    DatabaseReference db;

    //Meal object decleration
    Meals snack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);

        editText_roomNo_snacks = findViewById(R.id.et_roomNo_sancks);
        editText_quantity_snacks = findViewById(R.id.et_qty_snacks);
        //Setting the spinner array
        spinner2 = findViewById(R.id.foodItemSpinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.sanckItems, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);
        btn_purchaseSnack = findViewById(R.id.btn_purchase_snacks);

        snack = new Meals();

    }

    //Clear the fields of the entered data
    private void clearData(){
        editText_roomNo_snacks.setText("");
        editText_quantity_snacks.setText("");
    }

    public void purchase(View view){
        db = FirebaseDatabase.getInstance().getReference().child("Meals");

        try{
            if(TextUtils.isEmpty(editText_roomNo_snacks.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the Room No",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(spinner2.getSelectedItem().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the Food Item",Toast.LENGTH_LONG).show();
            }else if(TextUtils.isEmpty(editText_quantity_snacks.getText().toString().trim())){
                Toast.makeText(getApplicationContext(),"Enter the Quantity",Toast.LENGTH_LONG).show();
            }else{
                snack.setRoomNo(editText_roomNo_snacks.getText().toString().trim());
                snack.setFoodtype(spinner2.getSelectedItem().toString().trim());
                snack.setQuantity(editText_quantity_snacks.getText().toString().trim());


//                db.push().setValue(snack);
                db.child("snack").setValue(snack);
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