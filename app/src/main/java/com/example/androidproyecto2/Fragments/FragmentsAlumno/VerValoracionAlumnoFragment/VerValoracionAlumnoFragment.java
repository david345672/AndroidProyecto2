package com.example.androidproyecto2.Fragments.FragmentsAlumno.VerValoracionAlumnoFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproyecto2.BCrypt;
import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Clases.Grups_has_docents;
import com.example.androidproyecto2.Clases.Grups_has_llistes_skills;
import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment.ListSkillValoracioAdapter;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupService;
import com.example.androidproyecto2.api.apiServices.GrupsHasDocentService;
import com.example.androidproyecto2.api.apiServices.LlistesSkillsService;
import com.example.androidproyecto2.api.apiServices.UsuarisService;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

public class VerValoracionAlumnoFragment extends Fragment {

    private MainActivity ma;
    private Grup grupselect;
    private RecyclerView ListListasSkills;
    private List<LlistaSkills> llistesSkills = new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ver_valoracion_alumno, container, false);


        return view;
    }

    public HashSet<Usuari> getDocents(List<Grups_has_docents> grups_has_docents)
    {
        ArrayList<Usuari> docentsRep = new ArrayList<>();

        for (Grups_has_docents GD: grups_has_docents) {
            docentsRep.add(GD.getUsuaris());
        }
        HashSet<Usuari> docents = new HashSet<>(docentsRep);

        return docents;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ma = (MainActivity) getActivity();
        ma.layout = "VerValoraciones";

        Button btnAtras = ma.findViewById(R.id.btnAtras);
        btnAtras.setVisibility(View.VISIBLE);

        Button valAlumnesSwitch = view.findViewById(R.id.valAlumnesSwitch);
        Button valDocentSwitch = view.findViewById(R.id.valDocentSwitch);

        valAlumnesSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button valDocentSwitch = view.findViewById(R.id.valDocentSwitch);
                valAlumnesSwitch.setBackgroundColor(Color.GREEN);
                valDocentSwitch.setBackgroundColor(R.id.btnColor);
                GrupService grupS = Api.getApi().create(GrupService.class);
                Call<Grup> grupCall = grupS.GetgrupsById(ma.idGrupo);

                //listaskill_valoraciones_item.xml
                grupCall.enqueue(new Callback<Grup>() {
                    @Override
                    public void onResponse(Call<Grup> call, Response<Grup> response) {
                        switch (response.code()){
                            case 200:
                                grupselect = null;
                                grupselect = response.body();

                                ArrayList<LlistaSkills> lls = new ArrayList<>();
                                for (Grups_has_llistes_skills ghls : grupselect.getGrups_has_llistes_skills()) {
                                    if(!lls.contains(ghls.getLlistes_skills())){
                                        lls.add(ghls.getLlistes_skills());
                                    }

                                }


                                AdapterVerValoracionCharts avvc = new AdapterVerValoracionCharts(getContext(),lls,ma);
                                ListListasSkills.setHasFixedSize(true);
                                ListListasSkills.setLayoutManager(new LinearLayoutManager(getContext(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false));

                                ListListasSkills.setAdapter(avvc);

                                break;

                            case 400:
                                Gson gson = new Gson();
                                MissatgeError missatgeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                                Toast.makeText(ma, missatgeError.getMessage(), Toast.LENGTH_LONG).show();

                                break;
                            case 404:
                                Toast.makeText(ma,"Registre no trobat", Toast.LENGTH_LONG).show();
                                break;
                        }

                    }

                    @Override
                    public void onFailure(Call<Grup> call, Throwable t) {
                        Toast.makeText(ma, "ERRRROR grupselect", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



        ListListasSkills = view.findViewById(R.id.recycharts);

        valDocentSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valDocentSwitch.setBackgroundColor(Color.GREEN);
                valAlumnesSwitch.setBackgroundColor(R.id.btnColor);
                GrupsHasDocentService grupsHasDocentService = Api.getApi().create(GrupsHasDocentService.class);
                Call<List<Grups_has_docents>> listCall = grupsHasDocentService.Getgrups_has_docents();

                listCall.enqueue(new Callback<List<Grups_has_docents>>() {
                    @Override
                    public void onResponse(Call<List<Grups_has_docents>> call, Response<List<Grups_has_docents>> response) {
                        switch (response.code())
                        {
                            case 200:
                                List<Grups_has_docents> grupsDocents = response.body();
                                HashSet<Usuari> HashDocents = getDocents(grupsDocents);
                                List<Usuari> docents = new ArrayList<>(HashDocents);

                                cargarValoracionesListaSkill(docents,view);



                                break;
                            case 400:
                                Gson gson = new Gson();
                                MissatgeError missatgeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                                Toast.makeText(ma, missatgeError.getMessage(), Toast.LENGTH_LONG).show();
                                break;
                            case 404:
                                Toast.makeText(ma,"Registre no trobat", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Grups_has_docents>> call, Throwable t) {

                    }
                });

            }
        });




    }



    public void cargarValoracionesListaSkill(List<Usuari> usuaris, View view)
    {
        LlistesSkillsService llistesSkillsService = Api.getApi().create(LlistesSkillsService.class);
        Call<List<LlistaSkills>> listCall = llistesSkillsService.Getllistes_skills();


        listCall.enqueue(new Callback<List<LlistaSkills>>() {
            @Override
            public void onResponse(Call<List<LlistaSkills>> call, Response<List<LlistaSkills>> response) {
                switch (response.code()){
                    case 200:
                        llistesSkills = response.body();

                        List<Valoracio> ValoracionesDocentes =  cogerValoracionesDocentesGrupo(ma.usuariLogin.getValoracions(), usuaris);
                        HashSet<LlistaSkills> llistaSkills = getLlistesSkillsValoracio(ValoracionesDocentes);
                        ArrayList<LlistaSkills> llistesTotals = new ArrayList<>(llistaSkills);


                        AdapterVerValoracionChartsDOCENTE avvc = new AdapterVerValoracionChartsDOCENTE(getContext(),llistesTotals,ValoracionesDocentes,ma);
                        ListListasSkills.setHasFixedSize(true);
                        ListListasSkills.setLayoutManager(new LinearLayoutManager(getContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false));

                        ListListasSkills.setAdapter(avvc);


                        break;

                    case 400:
                        Gson gson = new Gson();
                        MissatgeError missatgeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                        Toast.makeText(ma, missatgeError.getMessage(), Toast.LENGTH_LONG).show();

                        break;
                    case 404:
                        Toast.makeText(ma,"Registre no trobat", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<LlistaSkills>> call, Throwable t) {

            }
        });












        GrupService grupS = Api.getApi().create(GrupService.class);
        Call<Grup> grupCall = grupS.GetgrupsById(ma.idGrupo);

        //listaskill_valoraciones_item.xml
        grupCall.enqueue(new Callback<Grup>() {
            @Override
            public void onResponse(Call<Grup> call, Response<Grup> response) {
                switch (response.code()){
                    case 200:
                        grupselect = response.body();
                        RecyclerView ListListasSkills = view.findViewById(R.id.recycharts);
                        ArrayList<LlistaSkills> lls = new ArrayList<>();
                        for (Grups_has_llistes_skills ghls : grupselect.getGrups_has_llistes_skills()) {
                            if(!lls.contains(ghls.getLlistes_skills())){
                                lls.add(ghls.getLlistes_skills());
                            }

                        }


                        AdapterVerValoracionCharts avvc = new AdapterVerValoracionCharts(getContext(),lls,usuaris,ma);
                        ListListasSkills.setHasFixedSize(true);
                        ListListasSkills.setLayoutManager(new LinearLayoutManager(getContext(),
                                LinearLayoutManager.HORIZONTAL,
                                false));

                        ListListasSkills.setAdapter(avvc);

                        break;

                    case 400:
                        Gson gson = new Gson();
                        MissatgeError missatgeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                        Toast.makeText(ma, missatgeError.getMessage(), Toast.LENGTH_LONG).show();

                        break;
                    case 404:
                        Toast.makeText(ma,"Registre no trobat", Toast.LENGTH_LONG).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<Grup> call, Throwable t) {
                Toast.makeText(ma, "ERRRROR grupselect", Toast.LENGTH_LONG).show();
            }
        });

    }



    //Cargar todas las listas de skills donde se le a valorado a l'usuario
    public HashSet<LlistaSkills> getLlistesSkillsValoracio(List<Valoracio> valoracions)
    {
        HashSet<LlistaSkills> llistaSkills = new HashSet<>();

        for (LlistaSkills lS: llistesSkills)
        {
            for (Valoracio vals: valoracions)
            {
                if(vals.getLlistes_skills_id() == lS.getId())
                {
                    llistaSkills.add(lS);
                }
            }

        }

        return llistaSkills;
    }


    public List<Valoracio> cogerValoracionesDocentesGrupo(List<Valoracio> valsUsuari, List<Usuari> docentsG)
    {
        //Creo una Lista de valoraciones para coger las valoraciones que los docentes le han hecho
        List<Valoracio> valsDocent = new ArrayList<>();

        //Recorro todas las valoraciones del usuario seleccionado
        for (Valoracio vals: valsUsuari) {

            //Dentro de cada valoracion recorro la lista de docentes y cogo los docentes que le han valorado
            for (Usuari user: docentsG)
            {
                if (vals.getUsuari_pp_id() == user.getId())
                {
                    valsDocent.add(vals);
                }

            }

        }

        return valsDocent;
    }



    public int[] cogerColoresRandom(int cantidad, MainActivity ma)
    {
        MainActivity activity = ma;
        // Esta variable se usará para llenar el array en la posición correspondiente
        int index = 0;

        // array que guarda los números aleatorios
        int [] coloresRandom = new int[cantidad];

        // Nuestro primer bucle que se ejecutará hasta que hayamos llenado el arrary
        while(index < cantidad) {
            // Variable que guarda el número aleatorio del array de colores del main

            int RandomColor = activity.coloresGraficos[new Random().nextInt(activity.coloresGraficos.length)];
            // Variable que indica si el RandomColor está repetido
            // asumimos que aún no está repetido y la establecemos a false
            boolean repetido = false;
            //Segundo bucle que se ejecutará siempre que el número no esté repetido
            while(!repetido) {
                // Bucle que recorre el array comparando el RandomColor con
                // cada uno de los items del array
                for(int i=0; i<index; i++) {
                    //realizamos la comparación
                    if(RandomColor == coloresRandom[i]) {
                        // si el número se repite, establecemos repetido=true
                        repetido = true;
                    }
                }
                // verificamos el estado del valor repetido. Si es false, significa
                // que hemos recorrido el array hasta la posición index sin encontrar
                // coincidencias
                if(!repetido) {
                    // almacenamos el valor propuesto ya que no está repetido
                    // incrementamos el índice
                    coloresRandom[index] = RandomColor;
                    index++;
                }
            }

        }


        return coloresRandom;

    }





}