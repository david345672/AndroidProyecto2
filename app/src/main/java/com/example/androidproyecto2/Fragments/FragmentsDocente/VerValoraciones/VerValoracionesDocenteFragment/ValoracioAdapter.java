package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.CustomCalendar.Dia;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.R;

import java.util.ArrayList;

public class ValoracioAdapter extends RecyclerView.Adapter<ValoracioAdapter.ViewHolder>
{
    private int anio;
    private int mes;
    private int dia;
    private Context context;
    private ArrayList<Valoracio> valoraciones;

    public ValoracioAdapter(Context context, ArrayList<Valoracio> valoraciones) {
        this.context = context;
        this.valoraciones = valoraciones;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView lblnotaValoracion;



        public ViewHolder(View item) {
            super(item);
            lblnotaValoracion = item.findViewById(R.id.lblnotaValoracion);

        }

        void bindValoracio(Valoracio valoracio)
        {
            lblnotaValoracion.setText("nota: " + valoracio);

        }

    }


    @Override
    public ValoracioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.valoraciones_item,parent,false);

        return new ValoracioAdapter.ViewHolder(item);
    }


    public void onBindViewHolder(ValoracioAdapter.ViewHolder holder, int position)
    {
        holder.bindValoracio(valoraciones.get(position));

    }



    public int getItemCount()
    {
        return valoraciones.size();
    }




}
