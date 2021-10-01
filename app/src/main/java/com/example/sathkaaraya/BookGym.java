package com.example.sathkaaraya;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class BookGym extends Fragment {


    private Button mDatePickerBtn;
    private Button mTimePicker;
    private Button book_btn;
    private TextView mSelectedDateText;
    private TextView mSelectedTimeText;
    //Object decleration of booking
    Booking book;
    //Database reference
    DatabaseReference dbRef;

    private String timeselected;
    private String dateselected;


    public BookGym() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_book_gym, container, false);
        mDatePickerBtn = view.findViewById(R.id.date_picker_btn);
        mTimePicker = view.findViewById(R.id.time_picker_button);
        book_btn = view.findViewById(R.id.book_btn);
        mSelectedDateText = view.findViewById(R.id.selected_date);
        mSelectedTimeText = view.findViewById(R.id.selected_time);

        book = new Booking();

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();

        long today = MaterialDatePicker.todayInUtcMilliseconds();

        calendar.setTimeInMillis(today);

        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        long january = calendar.getTimeInMillis();

        calendar.set(Calendar.MONTH, Calendar.MARCH);
        long march = calendar.getTimeInMillis();

        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        long december = calendar.getTimeInMillis();

        //CalendarConstraints(Validation)
        CalendarConstraints.Builder constraintBuilder = new CalendarConstraints.Builder();
        constraintBuilder.setValidator(DateValidatorPointForward.now());
        //MaterialDatePicker
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT A DATE FOR WORKOUT");
        builder.setSelection(today);
        builder.setCalendarConstraints(constraintBuilder.build());
        final MaterialDatePicker materialDatePicker = builder.setTheme(R.style.ThemeOverlay_App_MaterialCalendar).build();

        mDatePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materialDatePicker.show(getActivity().getSupportFragmentManager(), "DATE_PICKER");
            }
        });
        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                mSelectedDateText.setText("Selected Date : " + materialDatePicker.getHeaderText());
                dateselected = materialDatePicker.getHeaderText();

            }
        });

        //  Time picker
        mTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar=Calendar.getInstance();
                int hours=calendar.get(Calendar.HOUR_OF_DAY);
                int mins=calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog= new TimePickerDialog(getActivity(), R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar currenttime = Calendar.getInstance();
                        Calendar c=Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format= new SimpleDateFormat("k:mm a");
                        String time = format.format(c.getTime());
                        //Time Validation
                        if(c.getTimeInMillis() < currenttime.getTimeInMillis()){
                            Toast.makeText(getActivity(),"Please choose a time past current time",Toast.LENGTH_SHORT).show();
                        }else{
                            mSelectedTimeText.setText("Selected Time : " + time);
                            timeselected = time;
                        }

                    }
                },hours ,mins,false);
                timePickerDialog.show();
            }
        });

        //Method to send the data to the database
        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Booking");

                try{
                    //error message when user did not select the date
                    if(dateselected == null || dateselected.isEmpty() || dateselected.trim().isEmpty()){
                        Toast.makeText(getActivity(),"Please select a date",Toast.LENGTH_LONG).show();
                    //error message when user did not select the time
                    }else if(timeselected == null || timeselected.isEmpty() || timeselected.trim().isEmpty()){
                        Toast.makeText(getActivity(),"Please select a Time",Toast.LENGTH_LONG).show();
                    }else{
                        //set the text field when user select the time and date
                        book.setDate(dateselected.trim());
                        book.setTime(timeselected.trim());
                        //pushing the data to the text field
                        dbRef.child(getuserId()).push().setValue(book);
                        Toast.makeText(getActivity(),"Session Booked",Toast.LENGTH_LONG).show();
                        //set text fields to default method
                        clearControls();
                        restartActivity(getActivity());
                    }
                //catch method
                }catch(NumberFormatException e){
                    Toast.makeText(getActivity(),"An Error occured",Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }

    //Clear the fields of the entered data
    private void clearControls(){
        mSelectedTimeText.setText("Selected Time");
        mSelectedDateText.setText("Selected Date");
        dateselected = "";
        timeselected = "";
    }
    public static void restartActivity(Activity act){

        Intent intent=new Intent();
        intent.setClass(act, act.getClass());
        act.startActivity(intent);
        act.finish();

    }



    public static String getuserId(){
        String userID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        return  userID;
    }
}