package com.example.androidproyecto2.Fragments.FragmentsDocente.ValoracionDocenteFragment;

import android.annotation.SuppressLint;
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
    //private Valoracio valoracio;
    private List<Valoracio> valoracions;

    public KpiAdapterValoracion(Context context, List<Kpi> kpis, MainActivity activity, Skill skill,List<Valoracio> valoracions) {
        this.context = context;
        this.kpis = kpis;
        this.activity = activity;
        this.skill = skill;
        this.valoracions = valoracions;
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

        void bindKpi(Kpi kpi, Valoracio valoracio)
        {
            subskill.setText(kpi.getNom());

            rdb1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date currentTime = Calendar.getInstance().getTime();

                    Timestamp param = new Timestamp(currentTime.getTime());
                    //Toast.makeText(context, "Usuari Valorat: " + activity.usuariValorat.getNom() + ", LlistaSkillSelect: " + activity.llistaSkillSelected.getNom() + ", SkillValorada: " + skill.getNom() + ", Kpi: " + kpi.getNom() + ", UsuarioQueValora: " + activity.usuariLogin.getNomUsuari() + ", nota: " + rdb1.getText() + ", dataActual: " + currentTime, Toast.LENGTH_LONG).show();
                    //Valoracio valoracioaux = new Valoracio(kpi.getId(),activity.usuariValorat.getId(),40,param,Integer.parseInt((String) rdb1.getText()),skill.getId(),activity.llistaSkillSelected.getId());

                    valoracio.setNota(Integer.parseInt((String) rdb1.getText()));
                    valoracio.setData(param);
                    //Toast.makeText(context, valoracio.getNota(), Toast.LENGTH_SHORT).show();

                    //Toast.makeText(context, valoracio.toString(), Toast.LENGTH_LONG).show();

                }
            });

            rdb2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date currentTime = Calendar.getInstance().getTime();
                    Timestamp param = new Timestamp(currentTime.getTime());
                    //Toast.makeText(context, "Usuari Valorat: " + activity.usuariValorat.getNom() + ", LlistaSkillSelect: " + activity.llistaSkillSelected.getNom() + ", SkillValorada: " + skill.getNom() + ", Kpi: " + kpi.getNom() + ", UsuarioQueValora: " + activity.usuariLogin.getNomUsuari() + ", nota: " + rdb2.getText() + ", dataActual: " + currentTime, Toast.LENGTH_LONG).show();
                    //Valoracio valoracioaux = new Valoracio(kpi.getId(),activity.usuariValorat.getId(),40,(Timestamp) param,Integer.parseInt((String) rdb2.getText()),skill.getId(),activity.llistaSkillSelected.getId());
                    //valoracio = valoracioaux;
                    //Toast.makeText(context, valoracio.toString(), Toast.LENGTH_LONG).show();
                    valoracio.setNota(Integer.parseInt((String) rdb2.getText()));
                    valoracio.setData(param);
                }
            });

            rdb3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date currentTime = Calendar.getInstance().getTime();
                    Timestamp param = new Timestamp(currentTime.getTime());
                    //Toast.makeText(context, "Usuari Valorat: " + activity.usuariValorat.getNom() + ", LlistaSkillSelect: " + activity.llistaSkillSelected.getNom() + ", SkillValorada: " + skill.getNom() + ", Kpi: " + kpi.getNom() + ", UsuarioQueValora: " + activity.usuariLogin.getNomUsuari() + ", nota: " + rdb3.getText()+ ", dataActual: " + currentTime, Toast.LENGTH_LONG).show();
                    valoracio.setNota(Integer.parseInt((String) rdb3.getText()));
                    valoracio.setData(param);
                }
            });

            rdb4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Date currentTime = Calendar.getInstance().getTime();
                    Timestamp param = new Timestamp(currentTime.getTime());
                    //Toast.makeText(context, "Usuari Valorat: " + activity.usuariValorat.getNom() + ", LlistaSkillSelect: " + activity.llistaSkillSelected.getNom() + ", SkillValorada: " + skill.getNom() + ", Kpi: " + kpi.getNom() + ", UsuarioQueValora: " + activity.usuariLogin.getNomUsuari() + ", nota: " + rdb4.getText()+ ", dataActual: " + currentTime, Toast.LENGTH_LONG).show();
                    valoracio.setNota(Integer.parseInt((String) rdb4.getText()));
                    valoracio.setData(param);
                }
            });



        }

        void bindValoracio(Valoracio valoracio)
        {

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
        holder.bindKpi(kpis.get(position),valoracions.get(position));

    }



    public int getItemCount()
    {
        return kpis.size();
    }






}
