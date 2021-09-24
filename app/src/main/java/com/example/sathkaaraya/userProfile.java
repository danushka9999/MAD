package com.example.sathkaaraya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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


     int TAKE_IMAGE_CODE =10001 ;
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
//        profileImage=findViewById(R.id.im_profile);
        fStore=FirebaseFirestore.getInstance();
        logedInUser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("registrationDetails");
        loggedInUserID=logedInUser.getUid();

        final TextView tv_name=findViewById(R.id.tv_updateuser);
        final TextView tv_email=findViewById(R.id.tv_email);
        final TextView tv_phoneNumber=findViewById(R.id.tv_phonenumber);
        final TextView tv_country=findViewById(R.id.tv_updateCountry);
//        profileImage=findViewById(R.id.iv_image);

        reference.child(loggedInUserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                 registrationDetails register=snapshot.getValue(registrationDetails.class);
                 if(register != null){
                     String firstname = register.getFirstname();
                     String lastName =register.getLastName();
                     String email=register.getEmail();
                     String phoneCode=register.getPhoneCode();
                     String phoneNumber=register.getPhoneNumber();
                     String country=register.getCountry();

                     tv_name.setText(firstname+" "+lastName);
                     tv_email.setText(email);
                     tv_phoneNumber.setText(phoneCode+" "+phoneNumber);
                     tv_country.setText(country);
                 }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"error in showing data",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void editUser (View view){
        startActivity(new Intent(this,UpdateUser.class));
    }

    public static String getuserId(){
        String userID=FirebaseAuth.getInstance().getCurrentUser().getUid();
        return  userID;
    }



//    public void handleImageClick(View view) {
//
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(intent, TAKE_IMAGE_CODE);
//        }
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == TAKE_IMAGE_CODE) {
//            switch (resultCode) {
//                case RESULT_OK:
//                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
//                    profileImage.setImageBitmap(bitmap);
//                    handleUpload(bitmap);
//            }
//        }
//    }

//    private void handleUpload(Bitmap bitmap) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//
//        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//        final StorageReference reference = FirebaseStorage.getInstance().getReference()
//                .child("profileImages")
//                .child(uid + ".jpeg");
//
//        reference.putBytes(baos.toByteArray())
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        getDownloadUrl(reference);
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e(TAG, "onFailure: ",e.getCause() );
//                    }
//                });
//    }


}