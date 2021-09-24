package com.example.sathkaaraya;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ActivitySelect extends Fragment {



    public ActivitySelect() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_activity_select, container, false);

        AppCompatButton activitybrowse = (AppCompatButton) view.findViewById(R.id.activitybutton);
        activitybrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), ActivityBokking.class);
                startActivity(in);
            }
        });
        return view;
    }
}