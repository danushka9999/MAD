
package com.example.sathkaaraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

import static com.example.sathkaaraya.userProfile.getuserId;

public class DetailsForm extends AppCompatActivity {

    EditText et_name, et_roomNo,et_startDate, et_endingDate, et_NoOfAdults, et_NoOfChildren;
    Button btn_send;
    Form formObj;
    String userId;
    DatabaseReference dbRef;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_form);
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


        et_name = findViewById(R.id.et_name);
        et_roomNo = findViewById(R.id.et_roomNo);
        et_startDate = findViewById(R.id.et_startDate);
        et_endingDate = findViewById(R.id.et_endingDate);
        et_NoOfAdults = findViewById(R.id.et_NoOfAdults);
        et_NoOfChildren = findViewById(R.id.et_NoOfChildren);
        btn_send = findViewById(R.id.btn_send);



        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        et_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DetailsForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        et_startDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        et_endingDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DetailsForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        et_endingDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        formObj = new Form();

    }



    //method to clear all user inputs
    private void clearControls() {
        et_name.setText("");
        et_roomNo.setText("");
        et_startDate.setText("");
        et_endingDate.setText("");
        et_NoOfAdults.setText("");
        et_NoOfChildren.setText("");
    }

    public void CreateData(View view) {
        dbRef = FirebaseDatabase.getInstance().getReference().child("FormDetails");
        try{
            if(TextUtils.isEmpty(et_name.getText().toString())) {
                et_name.setError("please enter a name");
                et_name.requestFocus();
                return;
            }
            else if(TextUtils.isEmpty(et_roomNo.getText().toString())) {
                et_roomNo.setError("please enter a Room Number");
                et_roomNo.requestFocus();
                return;
            }
            else if(TextUtils.isEmpty(et_startDate.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a starting date", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_endingDate.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a ending date", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_NoOfAdults.getText().toString())) {
                et_NoOfAdults.setError("please enter No of adults");
                et_NoOfAdults.requestFocus();
                return;
            }
            else if(TextUtils.isEmpty(et_NoOfChildren.getText().toString())) {
                et_NoOfChildren.setError("please enter No of children");
                et_NoOfChildren.requestFocus();
                return;
            }
            else {
                //take inputs from the user and assigning them to this instance (formObj) of the FormDetails..
                formObj.setName(et_name.getText().toString().trim());
                formObj.setRoomNo(Integer.parseInt(et_roomNo.getText().toString().trim()));
                formObj.setStartingDate(et_startDate.getText().toString().trim());
                formObj.setEndingDate(et_endingDate.getText().toString().trim());
                formObj.setNoOfAdults(Integer.parseInt(et_NoOfAdults.getText().toString().trim()));
                formObj.setNoOfChildren(Integer.parseInt(et_NoOfChildren.getText().toString().trim()));


                dbRef.child(getuserId()).setValue(formObj);

                Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();

            }

        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Room Number", Toast.LENGTH_SHORT).show();

        }

//        startActivity(new Intent(this, ShowDetails.class));

    }

    public void Update(View view) {
        dbRef = FirebaseDatabase.getInstance().getReference().child("FormDetails");
        try{
            if(TextUtils.isEmpty(et_name.getText().toString())) {
                et_name.setError("please enter a name");
                et_name.requestFocus();
                return;
            }
            else if(TextUtils.isEmpty(et_roomNo.getText().toString())) {
                et_roomNo.setError("please enter a Room Number");
                et_roomNo.requestFocus();
                return;
            }
            else if(TextUtils.isEmpty(et_startDate.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a starting date", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_endingDate.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please enter a ending date", Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(et_NoOfAdults.getText().toString())) {
                et_NoOfAdults.setError("please enter No of adults");
                et_NoOfAdults.requestFocus();
                return;
            }
            else if(TextUtils.isEmpty(et_NoOfChildren.getText().toString())) {
                et_NoOfChildren.setError("please enter No of children");
                et_NoOfChildren.requestFocus();
                return;
            }
            else {
                //take inputs from the user and assigning them to this instance (formObj) of the FormDetails..
                formObj.setName(et_name.getText().toString().trim());
                formObj.setRoomNo(Integer.parseInt(et_roomNo.getText().toString().trim()));
                formObj.setStartingDate(et_startDate.getText().toString().trim());
                formObj.setEndingDate(et_endingDate.getText().toString().trim());
                formObj.setNoOfAdults(Integer.parseInt(et_NoOfAdults.getText().toString().trim()));
                formObj.setNoOfChildren(Integer.parseInt(et_NoOfChildren.getText().toString().trim()));


                dbRef.child(getuserId()).setValue(formObj);

                Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
                clearControls();

            }

        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(), "Invalid Room Number", Toast.LENGTH_SHORT).show();

        }

//        startActivity(new Intent(this, ShowDetails.class));

    }

}