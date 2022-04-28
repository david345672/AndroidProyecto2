package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.NotificacionesFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.CustomCalendar.Dia;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Notificacio;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.NotificacionsService;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificacionsAdapter extends RecyclerView.Adapter<NotificacionsAdapter.ViewHolder>
{

    private Context context;
    private ArrayList<Notificacio> notificacionsDia;
    private MainActivity activity;

    public NotificacionsAdapter(Context context, ArrayList<Notificacio> notificacionsDia, MainActivity activity) {
        this.context = context;
        this.notificacionsDia = notificacionsDia;
        this.activity = activity;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView lblHora;
        TextView lblMissatge;
        Button btnBorrarNotificacion;
        Button btnEditarNotificacion;


        public ViewHolder(View item) {
            super(item);
            lblHora = item.findViewById(R.id.lblHora);
            lblMissatge = item.findViewById(R.id.lblMissatge);
            btnBorrarNotificacion = item.findViewById(R.id.btnBorrarNotificacion);
            btnEditarNotificacion = item.findViewById(R.id.btnEditarNotificacion);
        }

        void bindNotificacio(Notificacio notificacio)
        {
            String [] date_time = notificacio.getData().split("_");
            char [] numeros = date_time[1].toCharArray();

            lblHora.setText(Character.toString(numeros[0]) + Character.toString(numeros[1]) + ":" + Character.toString(numeros[2]) + Character.toString(numeros[3]));
            lblMissatge.setText(notificacio.getMissatge());


            btnBorrarNotificacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    borrarNotificacion(notificacio);
                }
            });


            btnEditarNotificacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }

    }


    @Override
    public NotificacionsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.notificacion_item,parent,false);

        return new NotificacionsAdapter.ViewHolder(item);
    }


    public void onBindViewHolder(NotificacionsAdapter.ViewHolder holder, int position)
    {
        holder.bindNotificacio(notificacionsDia.get(position));

    }



    public int getItemCount()
    {
        return notificacionsDia.size();
    }



    public void borrarNotificacion(Notificacio notificacio)
    {
        NotificacionsService notificacionsService = Api.getApi().create(NotificacionsService.class);
        Call<Notificacio> notificacioCall = notificacionsService.Deletenotificacions(notificacio.getId());

        notificacioCall.enqueue(new Callback<Notificacio>() {
            @Override
            public void onResponse(Call<Notificacio> call, Response<Notificacio> response) {
                switch (response.code())
                {
                    case 200:

                        Toast.makeText(activity, "Notificacion borrada", Toast.LENGTH_SHORT).show();
                        NotificacionesFragment notificacionesFragment = new NotificacionesFragment();
                        notificacionesFragment.recargarNotificacionesUsuario(activity.usuariLogin.getId());
                        break;
                    case 400:
                        Gson gson = new Gson();
                        MissatgeError missatgeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                        Toast.makeText(activity, missatgeError.getMessage(), Toast.LENGTH_LONG).show();
                        break;
                    case 404:
                        Toast.makeText(activity,"Registre no trobat", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFailure(Call<Notificacio> call, Throwable t) {
                Toast.makeText(activity,t.getCause() + " - " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }






}
