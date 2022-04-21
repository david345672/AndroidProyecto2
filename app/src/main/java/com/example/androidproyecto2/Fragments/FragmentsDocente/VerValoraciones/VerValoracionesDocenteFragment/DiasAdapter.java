package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.CustomCalendar.Dia;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.UsuarisAdapter;
import com.example.androidproyecto2.R;

import java.util.ArrayList;


public class DiasAdapter extends RecyclerView.Adapter<DiasAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<Dia> dias;


    public DiasAdapter(Context context, ArrayList<Dia> dias) {
        this.context = context;
        this.dias = dias;

    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView lblNumDia;
        TextView lblNombreDia;
        RecyclerView ListValoraciones;


        public ViewHolder(View item) {
            super(item);
            lblNumDia = item.findViewById(R.id.lblNumDia);
            lblNombreDia = item.findViewById(R.id.lblNombreDia);
            ListValoraciones = item.findViewById(R.id.ListValoraciones);


        }

        void bindDia(Dia dia)
        {
            lblNumDia.setText(Integer.toString(dia.getNum()));
            lblNombreDia.setText(dia.getNombre());


//            ValoracioAdapter valoracioAdapter = new UsuarisAdapter(context,);
//            ListValoraciones.setHasFixedSize(true);
//            ListValoraciones.setLayoutManager(new LinearLayoutManager(context,
//                    LinearLayoutManager.HORIZONTAL,
//                    false));
//
//            ListValoraciones.setAdapter(valoracioAdapter);


        }

    }



    @Override
    public DiasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.dias_item,parent,false);

        return new DiasAdapter.ViewHolder(item);
    }


    public void onBindViewHolder(DiasAdapter.ViewHolder holder, int position)
    {
        holder.bindDia(dias.get(position));

    }



    public int getItemCount()
    {
        return dias.size();
    }




}
