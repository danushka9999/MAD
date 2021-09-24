package com.example.sathkaaraya;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class landingReceipt extends AppCompatActivity {

    Spinner packagename;
    EditText numDays;
    int bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_receipt);

        Spinner countrySpinner=(Spinner) findViewById(R.id.sp_getPackage);
        ArrayAdapter<String> packageAdapter=new ArrayAdapter<String>(landingReceipt.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.packages));
        packageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(packageAdapter);


    }



    public void getReceipts(View view){
        packagename=findViewById(R.id.sp_getPackage);
        numDays=findViewById(R.id.et_numOfDays);

        String pkName=(String)(packagename.getSelectedItem().toString());
      String numDay=(numDays.getText().toString()).trim();
        int days=Integer.parseInt(numDay);

        if(pkName.equals("Bronze")){
            bill=(4500*days);
        }
        else if(pkName.equals("Gold")){
            bill=(8000*days);
        }
        else {
            bill=(15000*days);
        }


        startActivity(new Intent(this,ReceiptPage.class));
    }
}