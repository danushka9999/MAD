package com.example.sathkaaraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class RegistrationPage extends AppCompatActivity {
   Button save;
   EditText firstName,lastName,email,phoneNumber,password,confPass;
   Spinner country,phoneCode;
   registrationDetails register;
   DatabaseReference dbref;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        mAuth = FirebaseAuth.getInstance();

        Spinner countrySpinner=(Spinner) findViewById(R.id.countrySpinner);
        ArrayAdapter<String> countryAdapter=new ArrayAdapter<String>(RegistrationPage.this,
            android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.countryNames));
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);

        Spinner countryCodeSpinner=(Spinner) findViewById(R.id.countryCodeSpinner);
        ArrayAdapter<String> countryCodeAdapter=new ArrayAdapter<String>(RegistrationPage.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.countryCodes));
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countryCodeSpinner.setAdapter(countryCodeAdapter);

    }


    public void Save(View view){
        try {

            firstName = findViewById(R.id.et_firstName);
            lastName=findViewById(R.id.et_RegLastName);
            email=findViewById(R.id.et_signInEmail);
            country=(Spinner) findViewById(R.id.countrySpinner);
            phoneCode=findViewById(R.id.countryCodeSpinner);
            phoneNumber=findViewById(R.id.et_number);
            password=findViewById(R.id.et_password);
            save = findViewById(R.id.btn_register);


//            dbref = FirebaseDatabase.getInstance().getReference().child("registrationDetails");

//            register.setFirstname(firstName.getText().toString().trim());
//            register.setLastName(lastName.getText().toString().trim());
//            register.setEmail(email.getText().toString().trim());
//            register.setCountry(country.getSelectedItem().toString());
//            register.setPhoneCode(phoneCode.getSelectedItem().toString());
//            register.setPhoneNumber(phoneNumber.getText().toString().trim());
//            register.setPassword(password.getText().toString().trim());

            String FirstName=(firstName.getText().toString().trim());
            String LastName=(lastName.getText().toString().trim());
            String Email=(email.getText().toString().trim());
            String Country=(String)(country.getSelectedItem().toString());
            String PhoneCode=(phoneCode.getSelectedItem().toString());
           String PhoneNumber=(phoneNumber.getText().toString().trim());
           String Password=(password.getText().toString().trim());

           if(FirstName.isEmpty()){
               firstName.setError("first name is required");
               firstName.requestFocus();
               return;

           }
           else if(Email.isEmpty()){
               email.setError("Email is Required");
               email.requestFocus();
               return;
           }
           else if (Password.isEmpty()){
               password.setError("Password is Required");
               password.requestFocus();
               return;
           }
           else if(Password.length()<8){
               password.setError("Password should contain more than 7 characters");
               password.requestFocus();
               return;
           }

           else {

//            register.setEmail(email.getText().toString().trim());
//            register.setPassword(password.getText().toString().trim());
//
//            String authEmail=register.getEmail();
//            String authPassword=register.getPassword();
               mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           registrationDetails register = new registrationDetails(FirstName, LastName, Email, Country, PhoneCode, PhoneNumber, Password);
                           FirebaseDatabase.getInstance().getReference("registrationDetails")
                                   .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(register).addOnCompleteListener(new OnCompleteListener<Void>() {
                               @Override
                               public void onComplete(@NonNull @NotNull Task<Void> task) {
                                   if (task.isSuccessful()) {
                                       Toast.makeText(getApplicationContext(), "data sent to database successfully", Toast.LENGTH_LONG).show();
                                   } else {
                                       Toast.makeText(getApplicationContext(), "error in sending data", Toast.LENGTH_LONG).show();
                                   }
                               }
                           });
                       } else {
                           Toast.makeText(getApplicationContext(), "error in sending data", Toast.LENGTH_LONG).show();
                       }
                   }
               });
           }

        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"error"+e,Toast.LENGTH_LONG).show();
        }
    }
}