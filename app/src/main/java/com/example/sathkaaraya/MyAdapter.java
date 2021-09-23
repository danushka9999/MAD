package com.example.sathkaaraya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Meals> list;

    public MyAdapter(Context context, ArrayList<Meals> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyAdapter.MyViewHolder holder, int position) {
        Meals meal = list.get(position);
        holder.roomNo.setText(meal.getRoomNo());
        holder.foodItem.setText(meal.getFoodtype());
        holder.quantity.setText(meal.getQuantity());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView roomNo,foodItem,quantity;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            roomNo = itemView.findViewById(R.id.tv_room_no);
            foodItem = itemView.findViewById(R.id.tv_food_item);
            quantity = itemView.findViewById(R.id.tv_quantity);
        }
    }
}
