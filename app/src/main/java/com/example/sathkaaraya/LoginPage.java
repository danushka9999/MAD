package com.example.sathkaaraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;



public class LoginPage extends AppCompatActivity {



    TextView email,password;
    Button signIn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.et_signInEmail);
        password=findViewById(R.id.et_signInPass);
        signIn=findViewById(R.id.btn_signin);




    }

    public void signIn(View sign){


        String Email=(email.getText().toString().trim());
        String Password=(password.getText().toString().trim());

        mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    startActivity(new Intent(LoginPage.this,Services.class));
                }else{
                    Toast.makeText(getApplicationContext(),"error in sign in",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}