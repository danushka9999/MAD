package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class ShowDetails extends AppCompatActivity {
    EditText updateName, updateRoomNo, updateStartingDate, updateEndingDate, updateNoOfAdults, updateNoOfChildren;
    Button btnShow, btnUpdate;
    Form formObj;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        updateName = findViewById(R.id.ed_updateName);
        updateRoomNo = findViewById(R.id.et_roomNo_sancks);
        updateStartingDate = findViewById(R.id.et_startDate);
        updateEndingDate = findViewById(R.id.et_endingDate);
        updateNoOfAdults = findViewById(R.id.et_NoOfAdults);
        updateNoOfChildren = findViewById(R.id.et_NoOfChildren);
        btnShow = findViewById(R.id.btn_send);
        btnUpdate = findViewById(R.id.btn_send);

        formObj = new Form();
    }

    //method to clear all user inputs
    private void clearControls(){
        updateName.setText("");
        updateRoomNo.setText("");
        updateStartingDate.setText("");
        updateEndingDate.setText("");
        updateNoOfAdults.setText("");
        updateNoOfChildren.setText("");
    }

    public void Update(View view){

    }
}