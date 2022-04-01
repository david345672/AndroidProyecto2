package com.example.androidproyecto2.Fragments.FragmentsDocente.ValoracionDocenteFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.Skill;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.util.ConcurrentModificationException;
import java.util.List;

public class SkillsValoracionAdapterViewPager extends PagerAdapter
{
    private MainActivity activity;
    private Context context;
    private List<Skill> skills;


    public SkillsValoracionAdapterViewPager(Context context, List<Skill> skills, MainActivity activity) {
        this.context = context;
        this.skills = skills;
        this.activity = activity;
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.skill_valoracion_docente_viewpager_item,container,false);

        TableLayout tableSkill = view.findViewById(R.id.tableSkill);

        for (int i = 0; i < skills.get(position).getKpis().size(); i++)
        {
            TableRow row = new TableRow(activity);
            TextView SubSkill = new TextView(activity);
            SubSkill.setText(skills.get(position).getKpis().get(i).getNom());
            row.addView(SubSkill);

//            RadioGroup radioGroup = new RadioGroup(context);
//            RadioButton rdb1 = new RadioButton(context);
//            RadioButton rdb2 = new RadioButton(context);
//            RadioButton rdb3 = new RadioButton(context);
//            RadioButton rdb4 = new RadioButton(context);
//            radioGroup.addView(rdb1);
//            radioGroup.addView(rdb2);
//            radioGroup.addView(rdb3);
//            radioGroup.addView(rdb4);
//
//            row.addView(rdb1);
//            row.addView(rdb2);
//            row.addView(rdb3);
//            row.addView(rdb4);

            tableSkill.addView(row);

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
