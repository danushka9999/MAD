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

import static com.example.sathkaaraya.userProfile.getuserId;

public class ShowDetails extends AppCompatActivity {
    EditText updateName, updateRoomNo;
    Button btnShow, btnUpdate;
    DatabaseReference dbRef;
    Form formObj = new Form();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);

        updateName = findViewById(R.id.ed_updateName);
        updateRoomNo = findViewById(R.id.et_roomNo);
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
        DatabaseReference readRef = FirebaseDatabase.getInstance().getReference().child("FormDetails").child(getuserId());
        readRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChildren()) {
                    updateName.setText(dataSnapshot.child("name").getValue().toString());
                    updateRoomNo.setText(dataSnapshot.child("roomNo").getValue().toString());
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


    public void Receipt(View view) {
        startActivity(new Intent(this, Receipt.class));
    }
}