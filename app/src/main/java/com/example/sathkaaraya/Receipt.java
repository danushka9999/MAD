package com.example.sathkaaraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Receipt extends AppCompatActivity {
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);
    }

    public void DeleteData(View view){
        DatabaseReference delRef = FirebaseDatabase.getInstance().getReference().child("FormDetails");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild("std1")){
                    dbRef = FirebaseDatabase.getInstance().getReference().child("FormDetails").child("std1");
                    dbRef.removeValue();
//                    clearControls();
                    Toast.makeText(getApplicationContext(), "data deleted successfully", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "No source to delete", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}