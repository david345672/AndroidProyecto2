package com.example.androidproyecto2.Fragments.FragmentsAlumno.VerValoracionAlumnoFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproyecto2.BCrypt;
import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
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
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerValoracionAlumnoFragment extends Fragment {
    private MainActivity ma;
    public int[] cogerColoresRandom(int cantidad)
    {
        // Esta variable se usará para llenar el array en la posición correspondiente
        int index = 0;

        // array que guarda los números aleatorios
        int [] coloresRandom = new int[cantidad];

        // Nuestro primer bucle que se ejecutará hasta que hayamos llenado el arrary
        while(index < cantidad) {
            // Variable que guarda el número aleatorio del array de colores del main
            MainActivity activity = (MainActivity) getActivity();
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ver_valoracion_alumno, container, false);


        return view;
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ma = (MainActivity) getActivity();

        Button btnAtras = ma.findViewById(R.id.btnAtras);
        btnAtras.setVisibility(View.VISIBLE);
        if(ma == null ) {
            Toast.makeText(getContext(),"eror",Toast.LENGTH_LONG);
            return;
        }
        //get skills para coger kpis
        //get user.valoracions
        //boton valorar al valorar para enviar las valroraciones tantas como kpis 0 1
        //hacer la media
        //graficos uno por cada frase del user i tres tipos de grafs
         /*userService = Api.getApi().create(UsuarisService.class);
        Call<List<Usuari>> listCall = userService.Getusuaris();
        listCall.enqueue(new Callback<List<Usuari>>() {
            @Override
            public void onResponse(Call<List<Usuari>> call, Response<List<Usuari>> response) {
                switch (response.code()){
                    case 200:
                        usuarisList = response.body();


                        for (Usuari userObject:usuarisList) {
                            if(userObject.getNomUsuari().equals(etUser.getText().toString())){
                                try
                                {
                                    Toast.makeText(getContext(),"e",Toast.LENGTH_LONG).show();
                                    String test = etPassword.getText().toString();
                                    if (BCrypt.checkpw(test,userObject.getContrasenya())){

                                        getUsuario(userObject.getId());
                                    }
                                }
                                catch(Exception e)
                                {
                                    Toast.makeText(getContext(),e.toString(),Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                        break;
                    case 400:
                        Gson gson = new Gson();
                        MissatgeError mensajeError = gson.fromJson(response.errorBody().charStream(),MissatgeError.class);
                        Toast.makeText(mainActivity.getApplicationContext(),mensajeError.getMessage(),Toast.LENGTH_LONG).show();
                        break;
                    case 404:
                        Toast.makeText(mainActivity.getApplicationContext(),"Registre no trobat",Toast.LENGTH_LONG).show();
                        break;
                }
            }
            @Override
            public void onFailure(Call<List<Usuari>> call, Throwable t) {

            }
        });*/

        RadarChart radarchart = view.findViewById(R.id.RADARCHART);
        int RandomColors = cogerColoresRandom(1)[0];

        ArrayList<RadarEntry> valoracionesAlumnos = new ArrayList<>();
        List<Valoracio> v = ma.valoracions;
        for (int i = 0; i < ma.skillSelected.getKpis().size(); i++) {
            int puntuacio = 0;
            List<Valoracio> thisuservals = new ArrayList<>();
            for (Valoracio val : v) {
                if (val.getKpis() == ma.skillSelected.getKpis().get(i) && val.getUsuari_valorat_id() == ma.usuariLogin.getId()){
                    thisuservals.add(val);
                    puntuacio += val.getNota();
                }
            }
            valoracionesAlumnos.add(new RadarEntry(puntuacio / thisuservals.size()));
        }



        RadarDataSet radarDataSetAlumnos = new RadarDataSet(valoracionesAlumnos,"Companys de Grup");
        radarDataSetAlumnos.setColor(RandomColors);
        radarDataSetAlumnos.setLineWidth(2f);
        radarDataSetAlumnos.setValueTextColor(Color.BLACK);
        radarDataSetAlumnos.setValueTextSize(0f);


        ArrayList<RadarEntry> valoracionesDocente = new ArrayList<>();
        valoracionesDocente.add(new RadarEntry(703));
        valoracionesDocente.add(new RadarEntry(838));
        valoracionesDocente.add(new RadarEntry(756));
        valoracionesDocente.add(new RadarEntry(201));
        valoracionesDocente.add(new RadarEntry(110));

        RadarDataSet radarDataSetDocente = new RadarDataSet(valoracionesDocente,"Equip Docent");
        radarDataSetDocente.setColor(RandomColors);
        radarDataSetDocente.setLineWidth(2f);
        radarDataSetDocente.setValueTextColor(Color.BLACK);
        radarDataSetDocente.setValueTextSize(0f);



        RadarData radarData = new RadarData();
        radarData.addDataSet(radarDataSetAlumnos);
        radarData.addDataSet(radarDataSetDocente);

        String[] puntos = new String[ma.skillSelected.getKpis().size()];
        for (int i = 0; i < ma.skillSelected.getKpis().size(); i++) {
            puntos[i] = ma.skillSelected.getKpis().get(i).getNom();
        }


        XAxis xAxis = radarchart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(puntos));

        radarchart.getDescription().setText(ma.llistaSkillSelected.getNom());
        radarchart.setData(radarData);

    }









}