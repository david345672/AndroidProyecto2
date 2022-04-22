package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListSkillValoracioAdapter extends RecyclerView.Adapter<ListSkillValoracioAdapter.ViewHolder>
{

    private Context context;
    private List<LlistaSkills> llistesSkills;
    ArrayList<Valoracio> valoracionsDia;


    public ListSkillValoracioAdapter(Context context, List<LlistaSkills> llistesSkills, ArrayList<Valoracio> valoracionsDia) {
        this.context = context;
        this.llistesSkills = llistesSkills;
        this.valoracionsDia = valoracionsDia;
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

            ArrayList<Valoracio> valoracionsLlistesSkillsDia = getValoracionesDeListaSkill(llistaSkills.getId());

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


    //coger las valoraciones de la lista de skill
    public ArrayList<Valoracio> getValoracionesDeListaSkill(int idLlista)
    {
        ArrayList<Valoracio> valoracionsLLista = new ArrayList<>();

        for (Valoracio valsDia: valoracionsDia)
        {
            if (valsDia.getLlistes_skills_id() == idLlista)
            {
                valoracionsLLista.add(valsDia);
            }
        }

        return valoracionsLLista;
    }


    public void getMediasSkills(List<LlistaSkills> llistaSkills)
    {
        
    }



}
