package com.example.androidproyecto2.Fragments.FragmentsDocente.ValoracionDocenteFragment;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.Skill;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

public class SkillsValoracionAdapterViewPager extends PagerAdapter
{
    private MainActivity activity;
    private Context context;
    private List<Skill> skills;
    private List<Valoracio> valoracions;


    public SkillsValoracionAdapterViewPager(Context context, List<Skill> skills, MainActivity activity) {
        this.context = context;
        this.skills = skills;
        this.activity = activity;

    }



    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.skill_valoracion_docente_viewpager_item,container,false);

        TextView nomSkill = view.findViewById(R.id.nomSkill);
        nomSkill.setText(skills.get(position).getNom());

        RecyclerView ListKpiSkill = view.findViewById(R.id.ListKpiSkill);
        KpiAdapterValoracion kpiAdapterValoracion = new KpiAdapterValoracion(context,skills.get(position).getKpis(),activity, skills.get(position));
        ListKpiSkill.setHasFixedSize(true);
        ListKpiSkill.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,
                false));

        ListKpiSkill.setAdapter(kpiAdapterValoracion);


        for (int i = 0; i < skills.get(position).getKpis().size();i ++){
            Date currentTime = Calendar.getInstance().getTime();
            Object param = new Timestamp(currentTime.getTime());
            Valoracio valoracio = new Valoracio(skills.get(position).getKpis().get(i).getId(),activity.usuariValorat.getId(),40,(Timestamp) param,-1,skills.get(position).getId(),activity.llistaSkillSelected.getId());
            valoracions.add(valoracio);
            Toast.makeText(activity, "Tipus Valoracio: "+ valoracio.getNota(), Toast.LENGTH_SHORT).show();

        }


        container.addView(view);
        return view;
    }







    @Override
    public int getCount() {
        return skills.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }


    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }


}
