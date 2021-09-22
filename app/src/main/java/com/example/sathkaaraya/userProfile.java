package com.example.sathkaaraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class userProfile extends AppCompatActivity {


    ImageView profileImage;
    FirebaseFirestore fStore;
    private FirebaseUser logedInUser;
    private DatabaseReference reference;
    private String loggedInUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
//        getSupportActionBar().setTitle("Anjana");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        profileImage=findViewById(R.id.im_profile);
        fStore=FirebaseFirestore.getInstance();
        logedInUser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("registrationDetails");
        loggedInUserID=logedInUser.getUid();

        final TextView tv_firstName=findViewById(R.id.tv_firstname);
        final TextView tv_email=findViewById(R.id.tv_email);
        final TextView tv_phoneNumber=findViewById(R.id.tv_phonenumber);

        reference.child(loggedInUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                 registrationDetails register=snapshot.getValue(registrationDetails.class);
                 if(register != null){
                     String firstname = register.getFirstname();
                     String email=register.getEmail();
                     String phoneNumber=register.getPhoneNumber();

                     tv_firstName.setText(firstname);
                     tv_email.setText(email);
                     tv_phoneNumber.setText(phoneNumber);
                 }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"error in showing data",Toast.LENGTH_SHORT).show();
            }
        });

    }


}