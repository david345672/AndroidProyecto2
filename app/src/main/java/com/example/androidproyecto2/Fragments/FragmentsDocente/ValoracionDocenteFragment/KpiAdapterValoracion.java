package com.example.androidproyecto2.Fragments.FragmentsDocente.ValoracionDocenteFragment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.Kpi;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Skill;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.UsuarisAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.ValoracionsService;
import com.google.gson.Gson;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KpiAdapterValoracion extends RecyclerView.Adapter<KpiAdapterValoracion.ViewHolder>
{

    private Context context;
    private List<Kpi> kpis;
    private MainActivity activity;
    private Skill skill;

    public KpiAdapterValoracion(Context context, List<Kpi> kpis, MainActivity activity, Skill skill) {
        this.context = context;
        this.kpis = kpis;
        this.activity = activity;
        this.skill = skill;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView subskill;
        RadioButton rdb1;
        RadioButton rdb2;
        RadioButton rdb3;
        RadioButton rdb4;

        public ViewHolder(View itemView) {
            super(itemView);

            subskill = itemView.findViewById(R.id.subskill);
            rdb1 = itemView.findViewById(R.id.rdb1);
            rdb2 = itemView.findViewById(R.id.rdb2);
            rdb3 = itemView.findViewById(R.id.rdb3);
            rdb4 = itemView.findViewById(R.id.rdb4);

        }

        void bindKpi(Kpi kpi)
        {

            subskill.setText(kpi.getNom());

            rdb1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date currentTime = Calendar.getInstance().getTime();

                    Timestamp param = new Timestamp(currentTime.getTime());
                    //Toast.makeText(context, "Usuari Valorat: " + activity.usuariValorat.getNom() + ", LlistaSkillSelect: " + activity.llistaSkillSelected.getNom() + ", SkillValorada: " + skill.getNom() + ", Kpi: " + kpi.getNom() + ", UsuarioQueValora: " + activity.usuariLogin.getNomUsuari() + ", nota: " + rdb1.getText() + ", dataActual: " + currentTime, Toast.LENGTH_LONG).show();
                    Valoracio valoracio = new Valoracio(kpi.getId(),activity.usuariValorat.getId(),40,param,Integer.parseInt((String) rdb1.getText()),skill.getId(),activity.llistaSkillSelected.getId());
                    //Toast.makeText(context, valoracio.toString(), Toast.LENGTH_LONG).show();
                    insertValoracio(valoracio);

                }
            });

            rdb2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date currentTime = Calendar.getInstance().getTime();
                    Object param = new Timestamp(currentTime.getTime());
                    //Toast.makeText(context, "Usuari Valorat: " + activity.usuariValorat.getNom() + ", LlistaSkillSelect: " + activity.llistaSkillSelected.getNom() + ", SkillValorada: " + skill.getNom() + ", Kpi: " + kpi.getNom() + ", UsuarioQueValora: " + activity.usuariLogin.getNomUsuari() + ", nota: " + rdb2.getText() + ", dataActual: " + currentTime, Toast.LENGTH_LONG).show();
                    Valoracio valoracio = new Valoracio(kpi.getId(),activity.usuariValorat.getId(),40,(Timestamp) param,Integer.parseInt((String) rdb2.getText()),skill.getId(),activity.llistaSkillSelected.getId());
                    Toast.makeText(context, valoracio.toString(), Toast.LENGTH_LONG).show();
                }
            });

            rdb3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date currentTime = Calendar.getInstance().getTime();
                    Object param = new Timestamp(currentTime.getTime());
                    //Toast.makeText(context, "Usuari Valorat: " + activity.usuariValorat.getNom() + ", LlistaSkillSelect: " + activity.llistaSkillSelected.getNom() + ", SkillValorada: " + skill.getNom() + ", Kpi: " + kpi.getNom() + ", UsuarioQueValora: " + activity.usuariLogin.getNomUsuari() + ", nota: " + rdb3.getText()+ ", dataActual: " + currentTime, Toast.LENGTH_LONG).show();
                    Valoracio valoracio = new Valoracio(kpi.getId(),activity.usuariValorat.getId(),40,(Timestamp) param,Integer.parseInt((String) rdb3.getText()),skill.getId(),activity.llistaSkillSelected.getId());
                    Toast.makeText(context, valoracio.toString(), Toast.LENGTH_LONG).show();
                }
            });

            rdb4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date currentTime = Calendar.getInstance().getTime();
                    Object param = new Timestamp(currentTime.getTime());
                    //Toast.makeText(context, "Usuari Valorat: " + activity.usuariValorat.getNom() + ", LlistaSkillSelect: " + activity.llistaSkillSelected.getNom() + ", SkillValorada: " + skill.getNom() + ", Kpi: " + kpi.getNom() + ", UsuarioQueValora: " + activity.usuariLogin.getNomUsuari() + ", nota: " + rdb4.getText()+ ", dataActual: " + currentTime, Toast.LENGTH_LONG).show();
                    Valoracio valoracio = new Valoracio(kpi.getId(),activity.usuariValorat.getId(),40,(Timestamp) param,Integer.parseInt((String) rdb4.getText()),skill.getId(),activity.llistaSkillSelected.getId());
                    Toast.makeText(context, valoracio.toString(), Toast.LENGTH_LONG).show();
                }
            });

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


    public void insertValoracio(Valoracio valoracio){
        ValoracionsService valoracionsService = Api.getApi().create(ValoracionsService.class);
        Call<Valoracio> valoracioCall = valoracionsService.insertValoracio(valoracio);

        valoracioCall.enqueue(new Callback<Valoracio>() {
            @Override
            public void onResponse(Call<Valoracio> call, Response<Valoracio> response) {
                switch (response.code())
                {
                    case 201:
                        Toast.makeText(context, "valoracio afegida", Toast.LENGTH_LONG).show();
                        System.out.println("valoracion afegida");
                        break;
                    case 400:
                        Gson gson = new Gson();
                        System.out.println("valoracion afegida err");
                        MissatgeError missatgeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                        Toast.makeText(context, missatgeError.getMessage(), Toast.LENGTH_LONG).show();
                        break; 
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Valoracio> call, Throwable t) {
                Toast.makeText(context, t.getCause() + " - " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



    }



}