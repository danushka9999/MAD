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
    int Foodbill,Gymbill,Travelbill;
    int showBill;
    public static String name;
    public static int Foodprice,Gymprice,Travelprice;
    public static int staticdays;
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
    public String getPackage(String name){
     if(name.equals("Bronze")){
         return "bronze";
     }
     else if(name.equals("gold")){
         return "gold";
     }
     else{
         return "premium";
     }

    }

    public int getBill(int bill){
        showBill=bill;
        return showBill;
    };



    public void getReceipts(View view){

        packagename=findViewById(R.id.sp_getPackage);
        numDays=findViewById(R.id.et_numOfDays);
        String pkName=(String)(packagename.getSelectedItem().toString());
        String numDay=(numDays.getText().toString()).trim();

        int days=Integer.parseInt(numDay);
        staticdays=days;
        if(pkName.equals("Bronze")){
            Foodbill=(2500*days);
            Gymbill=(1500*days);
            Travelbill=(22500*days);
        }
        else if(pkName.equals("Gold")){
            Foodbill=(4500*days);
            Gymbill=(2500*days);
            Travelbill=(45500*days);
        }
        else {
            Foodbill=(5500*days);
            Gymbill=(3200*days);
            Travelbill=(55000*days);
        }

        getPackage(pkName);
        name=pkName;
        Foodprice=Foodbill;
        Gymprice=Gymbill;
        Travelprice=Travelbill;
        startActivity(new Intent(this,ReceiptPage.class));
    }
    public String getName(){
        return name;
    }
    public int getFoodPrice(){
        return Foodprice;
    }
    public int getGymprice(){
        return Gymprice;
    }
    public int getTravelPrice(){
        return Travelprice;
    }
    public int getDays(){
        return staticdays;
    }
}