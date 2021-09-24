package com.example.sathkaaraya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GymAdapter extends RecyclerView.Adapter<GymAdapter.GymViewHolder>{

    Context context;
    ArrayList<Booking> list;

    public GymAdapter(Context context, ArrayList<Booking> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GymViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.itemgym,parent,false);
        return new GymViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull GymViewHolder holder, int position) {
        Booking book = list.get(position);
        holder.time.setText(book.getTime());
        holder.date.setText(book.getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class GymViewHolder extends RecyclerView.ViewHolder{

        TextView time, date;
        public GymViewHolder(@NonNull View itemView) {
            super(itemView);

            time= itemView.findViewById(R.id.booked_time);
            date= itemView.findViewById(R.id.booked_date);
        }
    }

}
