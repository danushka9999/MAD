package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailsForm extends AppCompatActivity {

    EditText et_name, et_roomNo, et_startDate, et_endingDate, et_NoOfAdults ,et_NoOfChildren;
    Button btn_send;
    Form formObj;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_form);


        et_name = findViewById(R.id.et_name);
        et_roomNo = findViewById(R.id.et_roomNo_sancks);
        et_startDate = findViewById(R.id.et_startDate);
        et_endingDate = findViewById(R.id.et_endingDate);
        et_NoOfAdults = findViewById(R.id.et_NoOfAdults);
        et_NoOfChildren = findViewById(R.id.et_NoOfChildren);
        btn_send = findViewById(R.id.btn_send);

        formObj = new Form();

    }
    //method to clear all user inputs
    private void clearControls(){
        et_name.setText("");
        et_roomNo.setText("");
        et_startDate.setText("");
        et_endingDate.setText("");
        et_NoOfAdults.setText("");
        et_NoOfChildren.setText("");
    }

    public void CreateData(View view){
        dbRef = FirebaseDatabase.getInstance().getReference().child("FormDetails");
        try{
//            if(TextUtils.isEmpty(et_name.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Please enter the name", Toast.LENGTH_SHORT).show();
//            else if(TextUtils.isEmpty(et_roomNo.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Please enter the room number", Toast.LENGTH_SHORT).show();
//            else if(TextUtils.isEmpty(et_startDate.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Please enter the starting date", Toast.LENGTH_SHORT).show();
//            else if(TextUtils.isEmpty(et_endingDate.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Please enter the ending date", Toast.LENGTH_SHORT).show();
//            else if(TextUtils.isEmpty(et_NoOfAdults.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Please enter the No Of Adults", Toast.LENGTH_SHORT).show();
//            else if(TextUtils.isEmpty(et_NoOfChildren.getText().toString()))
//                Toast.makeText(getApplicationContext(), "Please enter the No of Children", Toast.LENGTH_SHORT).show();
//            else{
                //take inputs from the user and assigning them to this instance (formObj) of the FormDetails..
                formObj.setName(et_name.getText().toString().trim());
                formObj.setRoomNo(Integer.parseInt(et_roomNo.getText().toString().trim()));
                formObj.setStartingDate(et_startDate.getText().toString().trim());
                formObj.setEndingDate(et_endingDate.getText().toString().trim());
                formObj.setNoOfAdults(Integer.parseInt(et_NoOfAdults.getText().toString().trim()));
                formObj.setNoOfChildren(Integer.parseInt(et_NoOfChildren.getText().toString().trim()));

                //insert in to the database..
                dbRef.push().setValue(formObj);

                //feedback to the user via a toast..
                Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                clearControls();
//            }

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "invalid room number" + e, Toast.LENGTH_SHORT).show();

        }
    }
}