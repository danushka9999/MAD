package com.example.sathkaaraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class ShowDetails extends AppCompatActivity {
    EditText updateName, updateRoomNo, updateStartingDate, updateEndingDate, updateNoOfAdults, updateNoOfChildren;
    Button btnShow, btnUpdate;
    DatabaseReference dbRef;
    Form formObj = new Form();

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
        btnShow = findViewById(R.id.btn_show);
        btnUpdate = findViewById(R.id.btn_update);


    }

    //method to clear all user inputs
//    private void clearControls() {
//        updateName.setText("");
//        updateRoomNo.setText("");
//        updateStartingDate.setText("");
//        updateEndingDate.setText("");
//        updateNoOfAdults.setText("");
//        updateNoOfChildren.setText("");
//    }

    public void Show(View view) {
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("FormDetails").child("std1");
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Form form = snapshot.getValue(Form.class);
                if (snapshot.hasChildren()) {
                    String name = form.getName();
                    updateName.setText(name);
//                    updateRoomNo.setText(dataSnapshot.child("roomNo").getValue().toString());
//                    updateStartingDate.setText(dataSnapshot.child("startingDate").getValue().toString());
//                    updateEndingDate.setText(dataSnapshot.child("endingDate").getValue().toString());
//                    updateNoOfAdults.setText(dataSnapshot.child("noOfAdults").getValue().toString());
//                    updateNoOfChildren.setText(dataSnapshot.child("noOfChildren").getValue().toString());
                } else
                    Toast.makeText(getApplicationContext(), "no source to display", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });
    }

    public void UpdateData(View view){
        DatabaseReference updRef = FirebaseDatabase.getInstance().getReference().child("FormDetails");
        updRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("std1")){
                    try {
//                        formObj.setName(updateName.getText().toString().trim());
//                        formObj.setRoomNo(Integer.parseInt(updateRoomNo.getText().toString().trim()));
//                        formObj.setStartingDate(updateStartingDate.getText().toString().trim());
//                        formObj.setEndingDate(updateEndingDate.getText().toString().trim());
//                        formObj.setNoOfAdults(Integer.parseInt(updateNoOfAdults.getText().toString().trim()));
//                        formObj.setNoOfChildren(Integer.parseInt(updateNoOfChildren.getText().toString().trim()));

                        dbRef = FirebaseDatabase.getInstance().getReference().child("FormDetails").child("std1");
                        dbRef.setValue(formObj);
//                        clearControls();

                        Toast.makeText(getApplicationContext(), "data updated successfully", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(), "invalid field", Toast.LENGTH_SHORT).show();

                    }
                }
                else
                    Toast.makeText(getApplicationContext(), "no source to update", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }



    public void Receipt(View view) {
        startActivity(new Intent(this, Receipt.class));
    }
}