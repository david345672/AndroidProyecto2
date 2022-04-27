package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.NotificacionesFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.CustomCalendar.Dia;
import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Notificacio;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment.ListSkillValoracioAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.LlistesSkillsService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DiasAdapterNotificaciones extends RecyclerView.Adapter<DiasAdapterNotificaciones.ViewHolder>
{
    private Context context;
    private ArrayList<Dia> dias;
    private ArrayList<Notificacio> notificacionsMes;
    private MainActivity activity;


    public DiasAdapterNotificaciones(Context context, ArrayList<Dia> dias, ArrayList<Notificacio> notificacionsMes, MainActivity activity) {
        this.context = context;
        this.dias = dias;
        this.notificacionsMes = notificacionsMes;
        this.activity = activity;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView lblNumDia;
        TextView lblNombreDia;
        RecyclerView ListNotificaciones;


        public ViewHolder(View item) {
            super(item);
            lblNumDia = item.findViewById(R.id.lblNumDia);
            lblNombreDia = item.findViewById(R.id.lblNombreDia);
            ListNotificaciones = item.findViewById(R.id.ListNotificaciones);

        }

        void bindDia(Dia dia)
        {
            lblNumDia.setText(Integer.toString(dia.getNum()));
            lblNombreDia.setText(dia.getNombre());

            ArrayList<Notificacio> notificacionsDia = cargarNotificacionesPorDia(notificacionsMes, dia);

            NotificacionsAdapter notificacionsAdapter = new NotificacionsAdapter(context,notificacionsDia);
            ListNotificaciones.setHasFixedSize(true);
            ListNotificaciones.setLayoutManager(new LinearLayoutManager(context,
                    LinearLayoutManager.HORIZONTAL,
                    false));

            ListNotificaciones.setAdapter(notificacionsAdapter);


        }

    }



    @Override
    public DiasAdapterNotificaciones.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.dias_notificaciones_item,parent,false);

        return new DiasAdapterNotificaciones.ViewHolder(item);
    }


    public void onBindViewHolder(DiasAdapterNotificaciones.ViewHolder holder, int position)
    {
        holder.bindDia(dias.get(position));

    }



    public int getItemCount()
    {
        return dias.size();
    }


    /*
        Cargar Todas las notificaciones que a hecho el usuario por dia
     */
    public ArrayList<Notificacio> cargarNotificacionesPorDia(ArrayList<Notificacio> notificacionsMes, Dia dia)
    {
        ArrayList<Notificacio> notificacionsDia = new ArrayList<>();

        for (Notificacio nots: notificacionsMes) {

            char [] numFecha = nots.getData().toCharArray();

            char[] diasC = new char[2];
            diasC[0] = numFecha[6];
            diasC[1] = numFecha[7];
            String diaStr = String.valueOf(diasC);
            int diaVal = Integer.parseInt(diaStr);

            if (diaVal == dia.getNum())
            {
                notificacionsDia.add(nots);
            }

        }

        return notificacionsDia;

    }






}
