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
        TextView nomSkill = view.findViewById(R.id.nomSkill);
        nomSkill.setText(skills.get(position).getNom());

        RecyclerView ListKpiSkill = view.findViewById(R.id.ListKpiSkill);
        KpiAdapterValoracion kpiAdapterValoracion = new KpiAdapterValoracion(context,skills.get(position).getKpis());
        ListKpiSkill.setHasFixedSize(true);
        ListKpiSkill.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,
                false));

        ListKpiSkill.setAdapter(kpiAdapterValoracion);




//        for (int i = 0; i < skills.get(position).getKpis().size(); i++)
//        {
//            TableRow row = new TableRow(context);
//            row.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
//            TextView SubSkill = new TextView(context);
//            SubSkill.setText(skills.get(position).getKpis().get(i).getNom());
//            SubSkill.setGravity(Gravity.CENTER_VERTICAL);
//            SubSkill.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
////            SubSkill.setLayoutParams(new TableLayout.LayoutParams
////                    (0, TableLayout.LayoutParams.WRAP_CONTENT, 3));
//
//            row.addView(SubSkill);
//
//            RadioButton rdb1 = new RadioButton(context);
//            RadioButton rdb2 = new RadioButton(context);
//            RadioButton rdb3 = new RadioButton(context);
//            RadioButton rdb4 = new RadioButton(context);
////            RadioGroup radioGroup = new RadioGroup(context);
//
////            rdb1.setLayoutParams(new TableLayout.LayoutParams
////                    (0, TableLayout.LayoutParams.WRAP_CONTENT, 1));
//
////            rdb2.setLayoutParams(new TableLayout.LayoutParams
////                    (0, TableLayout.LayoutParams.WRAP_CONTENT, 1));
//
////            rdb3.setLayoutParams(new TableLayout.LayoutParams
////                    (0, TableLayout.LayoutParams.WRAP_CONTENT, 1));
//
////            rdb4.setLayoutParams(new TableLayout.LayoutParams
////                    (0, TableLayout.LayoutParams.WRAP_CONTENT, 1));
////            radioGroup.addView(rdb1);
////            radioGroup.addView(rdb2);
////            radioGroup.addView(rdb3);
////            radioGroup.addView(rdb4);
//
//            row.addView(rdb1);
//            row.addView(rdb2);
//            row.addView(rdb3);
//            row.addView(rdb4);
//
//            tableSkill.addView(row);
//
//        }



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
