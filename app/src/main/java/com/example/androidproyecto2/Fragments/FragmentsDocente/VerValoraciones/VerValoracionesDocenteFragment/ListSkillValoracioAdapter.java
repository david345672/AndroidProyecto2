package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.R;

import java.util.ArrayList;
import java.util.List;

public class ListSkillValoracioAdapter extends RecyclerView.Adapter<ListSkillValoracioAdapter.ViewHolder>
{
    private int anio;
    private int mes;
    private int dia;
    private Context context;
    private List<LlistaSkills> llistesSkills;

    public ListSkillValoracioAdapter(Context context, List<LlistaSkills> llistesSkills) {
        this.context = context;
        this.llistesSkills = llistesSkills;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView lblnombreListaSkillVal;

        public ViewHolder(View item) {
            super(item);
            lblnombreListaSkillVal = item.findViewById(R.id.lblnombreListaSkillVal);

        }

        void bindLlistaSkills(LlistaSkills llistaSkills)
        {
            lblnombreListaSkillVal.setText(llistaSkills.getNom());

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
        holder.bindLlistaSkills(llistesSkills.get(position));

    }



    public int getItemCount()
    {
        return llistesSkills.size();
    }




}
