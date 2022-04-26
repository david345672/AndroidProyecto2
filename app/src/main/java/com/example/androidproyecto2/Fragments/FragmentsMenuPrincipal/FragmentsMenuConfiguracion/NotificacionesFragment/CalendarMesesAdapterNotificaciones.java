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

    public CalendarMesesAdapterNotificaciones(Context context, ArrayList<Mes> meses, MainActivity activity) {
        this.context = context;
        this.meses = meses;
        this.activity = activity;
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


        lblMesAño.setText(mes.getNombre() + " de " + mes.getAño());
        RecyclerView ListDias = view.findViewById(R.id.ListDias);
        DiasAdapterNotificaciones diasAdapter = new DiasAdapterNotificaciones(context,mes.getDias(),activity);
        ListDias.setHasFixedSize(true);
        ListDias.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,
                false));

        ListDias.setAdapter(diasAdapter);


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


    public ArrayList<Valoracio> cogerValoracionesDeMes(int posMes, List<Valoracio> valoracions)
    {

        ArrayList<Valoracio> valoracionesDeMeses = new ArrayList<>();

        for (int i = 0; i < valoracions.size(); i++)
        {
            char [] numFecha = valoracions.get(i).getData().toCharArray();
            String mesStr = new StringBuilder(numFecha[4]).append(numFecha[5]).toString();
            int mes = Integer.parseInt(mesStr);
            //Toast.makeText(context, "mes: " + mes, Toast.LENGTH_SHORT).show();
            if (posMes == mes)
            {
                valoracionesDeMeses.add(valoracions.get(i));
            }

        }
        return valoracionesDeMeses;
    }


    //Coger todos los dias sin repedidos del mes donde se han hecho valoraciones de ese mes
    /*
     - ArrayList<Dia> dias = arrayList de dias de el mes
     - ArrayList<Valoracio> valoracions = arrayList de valoraciones de ese mes
     */
    public HashSet<Dia> cogerDiasMesDeValoraciones(ArrayList<Dia> dias, List<Valoracio> valoracions)
    {
        HashSet<Dia> diasValoracion = new HashSet<>();

        for (int i = 0; i < valoracions.size(); i++)
        {
            char [] numFecha = valoracions.get(i).getData().toCharArray();

            char[] diasC = new char[2];
            diasC[0] = numFecha[6];
            diasC[1] = numFecha[7];
            String diaStr = String.valueOf(diasC);
            int dia = Integer.parseInt(diaStr);

            for (int j = 0; j < dias.size(); j++)
            {
                if (dia == dias.get(j).getNum())
                {
                    diasValoracion.add(dias.get(j));
                }
            }

        }


        return diasValoracion;
    }



}
