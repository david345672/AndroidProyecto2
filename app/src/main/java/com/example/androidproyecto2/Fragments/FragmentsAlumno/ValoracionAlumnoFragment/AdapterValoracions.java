package com.example.androidproyecto2.Fragments.FragmentsAlumno.ValoracionAlumnoFragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.R;

public class AdapterValoracions extends RecyclerView.Adapter<AdapterValoracions.ViewHolder> implements View.OnTouchListener{

    private String[] mDataSet;
    private View.OnClickListener listener;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Toast.makeText(v.getContext(), "Clicked",Toast.LENGTH_LONG).show();
        return false;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView texto;
        public ViewHolder(@NonNull View item) {
            super(item);
            texto = item.findViewById(R.id.texto);

        }
        public void bindValoracio(String pr){
            texto.setText(pr);
        }
    }


    public AdapterValoracions(String[] myDataSet) {
        mDataSet = myDataSet;
    }


    @Override
    public AdapterValoracions.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_valoracion_alumno_single,parent,false);
        //item.setOnClickListener(this);
        item.setOnTouchListener(new OnSwipeTouchListener(parent.getContext()) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Toast.makeText(parent.getContext(), "Swipe Left gesture detected", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                System.out.println("TextSwipeRight");
                Toast.makeText(parent.getContext(), "Swipe Right gesture detected", Toast.LENGTH_SHORT).show();
            }
        });
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindValoracio(mDataSet[position]);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
    /*@Override
    public void onClick(View view){
        Toast.makeText(view.getContext(), "Clicked",Toast.LENGTH_LONG).show();
        if (listener == null) listener.onClick(view);
        System.out.println("CLicked");
    }*/



    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
