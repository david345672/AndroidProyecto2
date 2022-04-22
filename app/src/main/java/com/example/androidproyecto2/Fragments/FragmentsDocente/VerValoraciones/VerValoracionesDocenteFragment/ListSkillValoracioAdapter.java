package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.R;

import java.util.ArrayList;

public class ListSkillValoracioAdapter extends RecyclerView.Adapter<ListSkillValoracioAdapter.ViewHolder>
{
    private int anio;
    private int mes;
    private int dia;
    private Context context;
    private ArrayList<Valoracio> valoraciones;

    public ListSkillValoracioAdapter(Context context, ArrayList<Valoracio> valoraciones) {
        this.context = context;
        this.valoraciones = valoraciones;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView lblnombreListaSkillVal;



        public ViewHolder(View item) {
            super(item);
            lblnombreListaSkillVal = item.findViewById(R.id.lblnombreListaSkillVal);

        }

        void bindValoracio(Valoracio valoracio)
        {
            lblnombreListaSkillVal.setText("nota: " + valoracio);

        }

    }


    @Override
    public ListSkillValoracioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.listaskill_valoraciones_item,parent,false);

        return new ListSkillValoracioAdapter.ViewHolder(item);
    }


    public void onBindViewHolder(ListSkillValoracioAdapter.ViewHolder holder, int position)
    {
        holder.bindValoracio(valoraciones.get(position));

    }



    public int getItemCount()
    {
        return valoraciones.size();
    }




}
