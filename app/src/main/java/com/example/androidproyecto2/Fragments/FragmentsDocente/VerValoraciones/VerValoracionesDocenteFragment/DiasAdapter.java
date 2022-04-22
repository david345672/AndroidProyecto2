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
import com.example.androidproyecto2.Clases.CustomCalendar.Mes;
import com.example.androidproyecto2.Clases.Grups_has_alumnes;
import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.UsuarisAdapter;
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


public class DiasAdapter extends RecyclerView.Adapter<DiasAdapter.ViewHolder>
{
    private Context context;
    private ArrayList<Dia> dias;
    private ArrayList<Valoracio> valoracionsMes;
    private List<LlistaSkills> llistesSkills;


    public DiasAdapter(Context context, ArrayList<Dia> dias, ArrayList<Valoracio> valoracionsMes) {
        this.context = context;
        this.dias = dias;
        this.valoracionsMes = valoracionsMes;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView lblNumDia;
        TextView lblNombreDia;
        RecyclerView ListListasSkills;


        public ViewHolder(View item) {
            super(item);
            lblNumDia = item.findViewById(R.id.lblNumDia);
            lblNombreDia = item.findViewById(R.id.lblNombreDia);
            ListListasSkills = item.findViewById(R.id.ListListasSkills);
            //getLlistaSkill(ListListasSkills);

        }

        void bindDia(Dia dia)
        {
            lblNumDia.setText(Integer.toString(dia.getNum()));
            lblNombreDia.setText(dia.getNombre());

            ArrayList<Valoracio> valoracionsDia = cargarValoracionesPorDia(valoracionsMes, dia);
            getLlistaSkill(ListListasSkills,valoracionsDia);

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


    public void getLlistaSkill(RecyclerView ListListasSkills, ArrayList<Valoracio> valoracionsDia)
    {
        LlistesSkillsService llistesSkillsService = Api.getApi().create(LlistesSkillsService.class);
        Call<List<LlistaSkills>> llistes = llistesSkillsService.Getllistes_skills();


        llistes.enqueue(new Callback<List<LlistaSkills>>() {
            @Override
            public void onResponse(Call<List<LlistaSkills>> call, Response<List<LlistaSkills>> response) {
                switch (response.code())
                {
                    case 200:
                        llistesSkills = response.body();

                        HashSet<LlistaSkills> llistaSkills = getLlistesSkillsValoracio(valoracionsDia);

                        ArrayList<LlistaSkills> LlistesSkillsValoraciones = new ArrayList<>(llistaSkills);


                        ListSkillValoracioAdapter listSkillValoracioAdapter = new ListSkillValoracioAdapter(context,LlistesSkillsValoraciones,valoracionsDia);
                        ListListasSkills.setHasFixedSize(true);
                        ListListasSkills.setLayoutManager(new LinearLayoutManager(context,
                                LinearLayoutManager.HORIZONTAL,
                                false));

                        ListListasSkills.setAdapter(listSkillValoracioAdapter);



                        break;
                    case 400:
                        Gson gson = new Gson();
                        MissatgeError missatgeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                        Toast.makeText(context, missatgeError.getMessage(), Toast.LENGTH_LONG).show();
                        break;
                    case 404:
                        Toast.makeText(context,"Registre no trobat", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<LlistaSkills>> call, Throwable t) {

            }
        });


    }


    //Cargar todas las listas de skills donde se le a valorado a l'usuario de ese dia
    public HashSet<LlistaSkills> getLlistesSkillsValoracio(ArrayList<Valoracio> valoracionsDia)
    {
        HashSet<LlistaSkills> llistaSkills = new HashSet<>();

        for (LlistaSkills lS: llistesSkills)
        {
            for (Valoracio vals: valoracionsDia)
            {
                if(vals.getLlistes_skills_id() == lS.getId())
                {
                    llistaSkills.add(lS);
                }
            }

        }

        return llistaSkills;
    }




}
