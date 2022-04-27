package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.NotificacionesFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.androidproyecto2.Clases.CustomCalendar.Dia;
import com.example.androidproyecto2.Clases.CustomCalendar.Mes;
import com.example.androidproyecto2.Clases.Notificacio;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment.DiasAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class CalendarMesesAdapterNotificaciones extends PagerAdapter
{
    private Context context;
    private ArrayList<Mes> meses;
    private MainActivity activity;
    private List<Notificacio> notificacions;

    public CalendarMesesAdapterNotificaciones(Context context, ArrayList<Mes> meses, MainActivity activity, List<Notificacio> notificacions) {
        this.context = context;
        this.meses = meses;
        this.activity = activity;
        this.notificacions = notificacions;
    }


    @Override
    public int getCount() {
        return meses.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.calendar_vpager_mes_item,container,false);

        TextView lblMesAño = view.findViewById(R.id.lblMesAño);
        Mes mes = meses.get(position);

        ArrayList<Notificacio> notificacionsMes = cogerNotificacionesDeMes(mes.getNum(), notificacions);
        HashSet<Dia> diasMesNotificacions = cogerDiasMesDeNotificaciones(mes.getDias(),notificacionsMes);

        ArrayList<Dia> diasMes = new ArrayList<>(diasMesNotificacions);
        Collections.sort(diasMes);

        lblMesAño.setText(mes.getNombre() + " de " + mes.getAño());
        RecyclerView ListDias = view.findViewById(R.id.ListDias);
        DiasAdapterNotificaciones diasAdapterNotificaciones = new DiasAdapterNotificaciones(context,diasMes,notificacionsMes,activity);
        ListDias.setHasFixedSize(true);
        ListDias.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,
                false));

        ListDias.setAdapter(diasAdapterNotificaciones);


        container.addView(view);

        return view;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }


    public ArrayList<Notificacio> cogerNotificacionesDeMes(int posMes, List<Notificacio> notificacions)
    {

        ArrayList<Notificacio> notificacionsDeMeses = new ArrayList<>();

        for (int i = 0; i < notificacions.size(); i++)
        {
            char [] numFecha = notificacions.get(i).getData().toCharArray();
            String mesStr = new StringBuilder(numFecha[4]).append(numFecha[5]).toString();
            int mes = Integer.parseInt(mesStr);
            //Toast.makeText(context, "mes: " + mes, Toast.LENGTH_SHORT).show();
            if (posMes == mes)
            {
                notificacionsDeMeses.add(notificacions.get(i));
            }

        }
        return notificacionsDeMeses;
    }


    //Coger todos los dias sin repedidos del mes donde el usuario tiene notificaciones de ese mes

    public HashSet<Dia> cogerDiasMesDeNotificaciones(ArrayList<Dia> dias, List<Notificacio> notificacions)
    {
        HashSet<Dia> diasNotifiacions = new HashSet<>();

        for (int i = 0; i < notificacions.size(); i++)
        {
            char [] numFecha = notificacions.get(i).getData().toCharArray();

            char[] diasC = new char[2];
            diasC[0] = numFecha[6];
            diasC[1] = numFecha[7];
            String diaStr = String.valueOf(diasC);
            int dia = Integer.parseInt(diaStr);

            for (int j = 0; j < dias.size(); j++)
            {
                if (dia == dias.get(j).getNum())
                {
                    diasNotifiacions.add(dias.get(j));
                }
            }

        }


        return diasNotifiacions;
    }



}
