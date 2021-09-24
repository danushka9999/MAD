package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UpdateUser extends AppCompatActivity {
    EditText firstName,lastName,email;
    DatabaseReference reference;
    userProfile user=new userProfile();
    String userID= user.getuserId();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        reference=FirebaseDatabase.getInstance().getReference("registrationDetails");
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