package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.example.androidproyecto2.Clases.CustomCalendar.Mes;
import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.UsuarisAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.util.ArrayList;

public class CalendarMesesAdapter extends PagerAdapter
{
    private Context context;
    private ArrayList<Mes> meses;
    private MainActivity activity;

    public CalendarMesesAdapter(Context context, ArrayList<Mes> meses,MainActivity activity) {
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
        DiasAdapter diasAdapter = new DiasAdapter(context,mes.getDias());
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


}
