package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.NotificacionesFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.CustomCalendar.Dia;
import com.example.androidproyecto2.Clases.Notificacio;
import com.example.androidproyecto2.R;

import java.util.ArrayList;

public class NotificacionsAdapter extends RecyclerView.Adapter<NotificacionsAdapter.ViewHolder>
{

    private Context context;
    private ArrayList<Notificacio> notificacionsDia;

    public NotificacionsAdapter(Context context, ArrayList<Notificacio> notificacionsDia) {
        this.context = context;
        this.notificacionsDia = notificacionsDia;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView lblHora;
        TextView lblMissatge;


        public ViewHolder(View item) {
            super(item);
            lblHora = item.findViewById(R.id.lblHora);
            lblMissatge = item.findViewById(R.id.lblMissatge);

        }

        void bindNotificacio(Notificacio notificacio)
        {
            String [] date_time = notificacio.getData().split("_");
            char [] numeros = date_time[1].toCharArray();

            lblHora.setText(date_time[1]);
            lblMissatge.setText(notificacio.getMissatge());


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




}
