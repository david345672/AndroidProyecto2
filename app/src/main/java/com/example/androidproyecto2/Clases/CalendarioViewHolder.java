package com.example.androidproyecto2.Clases;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.R;

public class CalendarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public final TextView dayofMonth;
    private final CalendarioAdapter.OnItemListener onItemListener;

    public CalendarioViewHolder(@NonNull View itemView, CalendarioAdapter.OnItemListener onItemListener)
    {
        super(itemView);
        dayofMonth = itemView.findViewById(R.id.celldayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(),(String) dayofMonth.getText());
    }
}
