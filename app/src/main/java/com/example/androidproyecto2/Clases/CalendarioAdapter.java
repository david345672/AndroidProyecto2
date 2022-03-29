package com.example.androidproyecto2.Clases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.R;

import java.util.ArrayList;


public class CalendarioAdapter extends RecyclerView.Adapter<CalendarioViewHolder>
{

    private final ArrayList<String> dayofmonth;
    private final OnItemListener onItemListener;

    public CalendarioAdapter(ArrayList<String> dayofmonth, OnItemListener onItemListener) {
        this.dayofmonth = dayofmonth;
        this.onItemListener = onItemListener;
    }




    @NonNull
    @Override
    public CalendarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendario_notificaciones, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarioViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarioViewHolder holder, int position) {
            holder.dayofMonth.setText(dayofmonth.get(position));
    }

    @Override
    public int getItemCount() {
        return dayofmonth.size();
    }
    public  interface OnItemListener{
        void onItemClick(int position, String dayText);
    }
}
