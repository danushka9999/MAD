package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class UpdateUser extends AppCompatActivity {
    EditText firstName;
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
//        firstName=findViewById(R.id.et_updateFirstName);
        String FirstName=(firstName.getText().toString().trim());


        reference.child(userID).child("firstname").setValue(FirstName);

    }
}