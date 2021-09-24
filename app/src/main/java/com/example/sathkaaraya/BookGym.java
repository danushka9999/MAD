package com.example.sathkaaraya;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class BookGym extends Fragment {


    private Button mDatePickerBtn;
    private Button mTimePicker;
    private TextView mSelectedDateText;
    private TextView mSelectedTimeText;

    public BookGym() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_book_gym, container, false);
        mDatePickerBtn = view.findViewById(R.id.date_picker_btn);
        mTimePicker = view.findViewById(R.id.time_picker_button);
        mSelectedDateText = view.findViewById(R.id.selected_date);
        mSelectedTimeText = view.findViewById(R.id.selected_time);

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
                        }

                    }
                },hours ,mins,false);
                timePickerDialog.show();
            }
        });


        return view;
    }
}