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
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Skill;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.ValoracionsService;
import com.google.gson.Gson;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        TextView nomSkill = view.findViewById(R.id.nomSkill);
        nomSkill.setText(skills.get(position).getNom());
        List<Valoracio> valoracions = new ArrayList<>();

        for (int i = 0; i < skills.get(position).getKpis().size();i++){
            Date currentTime = Calendar.getInstance().getTime();
            Timestamp param = new Timestamp(new Date().getTime());
            Valoracio valoracio = new Valoracio(skills.get(position).getKpis().get(i).getId(),activity.usuariValorat.getId(),activity.usuariLogin.getId(),param,-1,activity.llistaSkillSelected.getId(),skills.get(position).getId(),"");
            valoracions.add(valoracio);

        }

        RecyclerView ListKpiSkill = view.findViewById(R.id.ListKpiSkill);
        KpiAdapterValoracion kpiAdapterValoracion = new KpiAdapterValoracion(context,skills.get(position).getKpis(),activity, skills.get(position),valoracions);
        ListKpiSkill.setHasFixedSize(true);
        ListKpiSkill.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,
                false));

        ListKpiSkill.setAdapter(kpiAdapterValoracion);
        Button BtnValorar = view.findViewById(R.id.BtnValorar);
        BtnValorar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < valoracions.size();i ++)
                {
                    insertValoracio(valoracions.get(i));
                }

            }
        });




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

    private void insertValoracio(Valoracio valoracio){
        ValoracionsService valoracionsService = Api.getApi().create(ValoracionsService.class);
        Call<Valoracio> valoracioCall = valoracionsService.insertValoracio(valoracio);

        valoracioCall.enqueue(new Callback<Valoracio>() {
            @Override
            public void onResponse(Call<Valoracio> call, Response<Valoracio> response) {
                switch (response.code())
                {
                    case 201:
                        Toast.makeText(context, "valoracio afegida", Toast.LENGTH_LONG).show();
                        break;
                    case 400:
                        Gson gson = new Gson();
                        MissatgeError missatgeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                        //Toast.makeText(context, missatgeError.getMessage(), Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Valoracio> call, Throwable t) {
                //Toast.makeText(context, t.getCause() + " - " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



    }




}
