package com.example.androidproyecto2.Fragments.FragmentsDocente.ValoracionDocenteFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.Kpi;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.UsuarisAdapter;
import com.example.androidproyecto2.R;

import java.util.List;

public class KpiAdapterValoracion extends RecyclerView.Adapter<KpiAdapterValoracion.ViewHolder>
{

    private Context context;
    private List<Kpi> kpis;

    public KpiAdapterValoracion(Context context, List<Kpi> kpis) {
        this.context = context;
        this.kpis = kpis;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView subskill;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subskill = itemView.findViewById(R.id.subskill);
        }

        void bindKpi(Kpi kpi)
        {
            subskill.setText(kpi.getNom());
        }
    }



    @Override
    public KpiAdapterValoracion.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.kpi_valoracion_item,parent,false);

        return new KpiAdapterValoracion.ViewHolder(item);
    }


    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.bindKpi(kpis.get(position));
    }



    public int getItemCount()
    {
        return kpis.size();
    }


}
