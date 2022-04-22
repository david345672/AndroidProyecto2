package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.Skill;
import com.example.androidproyecto2.Clases.SkillMedia;
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

            ArrayList<SkillMedia> mediasValoracionesSkill = getMediasSkills(llistaSkills.getSkills(),valoracionsLlistesSkillsDia);


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

    //Hacer la media de las valoraciones de la lista de skill filtrando por skills y devolver un arrayList con el nombre de la Skill y su media
    public ArrayList<SkillMedia> getMediasSkills(List<Skill> skills, ArrayList<Valoracio> valoracions)
    {

        ArrayList<SkillMedia> mediasSkills = new ArrayList<>();

        int i = 0;
        for (Skill skill: skills)
        {
            int sumaNota = 0;
            int contVals = 0;
            for (Valoracio vals: valoracions)
            {
                if(skill.getId() == vals.getSkills_id())
                {
                    contVals++;
                    sumaNota = sumaNota + vals.getNota();
                }
            }

            float media = sumaNota / (float)contVals;
            mediasSkills.add(new SkillMedia(skill.getNom(), media));

            i++;
        }

        return mediasSkills;
    }



}
