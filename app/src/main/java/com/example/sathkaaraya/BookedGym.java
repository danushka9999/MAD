package com.example.sathkaaraya;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BookedGym extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference dbRef;
    GymAdapter gymAdapter;
    ArrayList<Booking> list;

    public BookedGym() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_booked_gym, container, false);

        recyclerView = view.findViewById(R.id.bookingList);
        dbRef = FirebaseDatabase.getInstance().getReference().child("Booking").child(getuserId());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        gymAdapter = new GymAdapter(getActivity(),list);
        recyclerView.setAdapter(gymAdapter);

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Booking book =  dataSnapshot.getValue(Booking.class);
                    list.add(book);
                }
                gymAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return view;
    }
    public static String getuserId(){
        String userID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        return  userID;
    }
}