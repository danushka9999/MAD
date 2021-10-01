package com.example.sathkaaraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UpdateUser extends AppCompatActivity {
    EditText firstName,lastName,email;
    DatabaseReference reference;
    userProfile user=new userProfile();
    String userID= user.getuserId();
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        reference=FirebaseDatabase.getInstance().getReference("registrationDetails");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.personBN);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.personBN:
                        return true;
                    case R.id.dashboardBN:
                        startActivity(new Intent(getApplicationContext(), Services.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.receiptBN:
                        startActivity(new Intent(getApplicationContext(), landingReceipt.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }

        });
    }

    public void updateUser(View view){
        firstName=findViewById(R.id.et_editFirstName);
        lastName=findViewById(R.id.et_editLastName);
        email=findViewById(R.id.et_editEmail);

        String firstname=(firstName.getText().toString().trim());
        String lastname=(lastName.getText().toString().trim());
        String Email=(email.getText().toString().trim());


        reference.child(userID).child("firstname").setValue(firstname);
        reference.child(userID).child("lastName").setValue(lastname);
        reference.child(userID).child("email").setValue(Email);

        startActivity(new Intent(this,userProfile.class));

    }
}