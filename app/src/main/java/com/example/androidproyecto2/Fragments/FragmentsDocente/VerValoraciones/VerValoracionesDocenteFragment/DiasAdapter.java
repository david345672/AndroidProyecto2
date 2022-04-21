package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproyecto2.Clases.CustomCalendar.Dia;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.UsuarisAdapter;
import com.example.androidproyecto2.R;

import java.util.ArrayList;


public class DiasAdapter extends RecyclerView.Adapter<DiasAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<Dia> dias;
    private ArrayList<Valoracio> valoracionsMes;


    public DiasAdapter(Context context, ArrayList<Dia> dias, ArrayList<Valoracio> valoracionsMes) {
        this.context = context;
        this.dias = dias;
        this.valoracionsMes = valoracionsMes;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView lblNumDia;
        TextView lblNombreDia;
        RecyclerView ListValoraciones;


        public ViewHolder(View item) {
            super(item);
            lblNumDia = item.findViewById(R.id.lblNumDia);
            lblNombreDia = item.findViewById(R.id.lblNombreDia);
            ListValoraciones = item.findViewById(R.id.ListValoraciones);


        }

        void bindDia(Dia dia)
        {
            lblNumDia.setText(Integer.toString(dia.getNum()));
            lblNombreDia.setText(dia.getNombre());

            ArrayList<Valoracio> valoracionsDia = cargarValoracionesPorDia(valoracionsMes, dia);


        }

    }



    @Override
    public DiasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.dias_item,parent,false);

        return new DiasAdapter.ViewHolder(item);
    }


    public void onBindViewHolder(DiasAdapter.ViewHolder holder, int position)
    {
        holder.bindDia(dias.get(position));

    }



    public int getItemCount()
    {
        return dias.size();
    }


    /*
        Cargar Todas las valoraciones que tiene el usuario por dia
        - valoracionesMes = valoraciones del mes del item del ViewPager
        - dia = dia en el que estoy dentro de la recyclerView de los dias del mes
     */
    public ArrayList<Valoracio> cargarValoracionesPorDia(ArrayList<Valoracio> valoracionsMes, Dia dia)
    {
        ArrayList<Valoracio> valoracionsDia = new ArrayList<>();

        for (Valoracio vals: valoracionsMes) {

            char [] numFecha = vals.getData().toCharArray();

            char[] diasC = new char[2];
            diasC[0] = numFecha[6];
            diasC[1] = numFecha[7];
            String diaStr = String.valueOf(diasC);
            int diaVal = Integer.parseInt(diaStr);

            if (diaVal == dia.getNum())
            {
                valoracionsDia.add(vals);
            }

        }

        return valoracionsDia;

    }







}
