package com.example.sathkaaraya;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class GymSelect extends Fragment {



    public GymSelect() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_gym_select, container, false);

        AppCompatButton gymbrowe = (AppCompatButton) view.findViewById(R.id.gymbrowse);
        gymbrowe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), GymBooking.class);
                startActivity(in);
            }
        });
        return view;
    }


}