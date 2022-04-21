package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.androidproyecto2.Clases.CustomCalendar.Dia;
import com.example.androidproyecto2.Clases.CustomCalendar.Mes;
import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.UsuarisAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class CalendarMesesAdapter extends PagerAdapter
{
    private Context context;
    private ArrayList<Mes> meses;
    private MainActivity activity;
    private List<Valoracio> valoracions;


    public CalendarMesesAdapter(Context context, ArrayList<Mes> meses,MainActivity activity, List<Valoracio> valoracions) {
        this.context = context;
        this.meses = meses;
        this.activity = activity;
        this.valoracions = valoracions;
    }


    @Override
    public int getCount() {
        return meses.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.calendar_vpager_mes_item,container,false);

        Button btnVerValoracionesMensuales = view.findViewById(R.id.btnVerValoracionesMensuales);
        TextView lblMesAño = view.findViewById(R.id.lblMesAño);
        Mes mes = meses.get(position);

        ArrayList<Valoracio> valoracionsMes = cogerValoracionesDeMes(mes.getNum(), valoracions);
        HashSet<Dia> diasMesValoraciones = cogerDiasMesDeValoraciones(mes.getDias(),valoracions);

        ArrayList<Dia> diasMes = new ArrayList<>(diasMesValoraciones);
        Collections.sort(diasMes);


        lblMesAño.setText(mes.getNombre() + " de " + mes.getAño());
        RecyclerView ListDias = view.findViewById(R.id.ListDias);
        DiasAdapter diasAdapter = new DiasAdapter(context,diasMes);
        ListDias.setHasFixedSize(true);
        ListDias.setLayoutManager(new LinearLayoutManager(context,
                LinearLayoutManager.VERTICAL,
                false));

        ListDias.setAdapter(diasAdapter);


        btnVerValoracionesMensuales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Valoracio vals: valoracionsMes) {
                    Toast.makeText(activity, vals.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

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


    //Coger todos los dias sin repedidos de la valoraciones
    public HashSet<Dia> cogerDiasMesDeValoraciones(ArrayList<Dia> dias, List<Valoracio> valoracions)
    {
        HashSet<Dia> diasValoracion = new HashSet<>();

        for (int i = 0; i < valoracions.size(); i++)
        {
            char [] numFecha = valoracions.get(i).getData().toCharArray();
            String diaStr = new StringBuilder(numFecha[6]).append(numFecha[7]).toString();
            int dia = Integer.parseInt(diaStr);
            Toast.makeText(context, "numDia: " + diaStr, Toast.LENGTH_SHORT).show();
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
