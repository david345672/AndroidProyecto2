package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.ColoresGraficosFragment.Graficos;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Random;

public class RadarGraficoFragment extends Fragment {

    private MainActivity activity;
    private RadarChart radarGrafico;
    private int[] RandomColors;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_radar_grafico, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        radarGrafico = view.findViewById(R.id.radarGrafico);

        RandomColors = cogerColoresRandom(2);

        ArrayList<RadarEntry> valoracionesAlumnos = new ArrayList<>();
        valoracionesAlumnos.add(new RadarEntry(236));
        valoracionesAlumnos.add(new RadarEntry(523));
        valoracionesAlumnos.add(new RadarEntry(156));
        valoracionesAlumnos.add(new RadarEntry(401));
        valoracionesAlumnos.add(new RadarEntry(310));

        RadarDataSet radarDataSetAlumnos = new RadarDataSet(valoracionesAlumnos,"Alumnos");
        radarDataSetAlumnos.setColor(RandomColors[0]);
        radarDataSetAlumnos.setLineWidth(2f);
        radarDataSetAlumnos.setValueTextColor(Color.BLACK);
        radarDataSetAlumnos.setValueTextSize(0f);


        ArrayList<RadarEntry> valoracionesDocente = new ArrayList<>();
        valoracionesDocente.add(new RadarEntry(703));
        valoracionesDocente.add(new RadarEntry(838));
        valoracionesDocente.add(new RadarEntry(756));
        valoracionesDocente.add(new RadarEntry(201));
        valoracionesDocente.add(new RadarEntry(110));

        RadarDataSet radarDataSetDocente = new RadarDataSet(valoracionesDocente,"Docente");
        radarDataSetDocente.setColor(RandomColors[1]);
        radarDataSetDocente.setLineWidth(2f);
        radarDataSetDocente.setValueTextColor(Color.BLACK);
        radarDataSetDocente.setValueTextSize(0f);



        RadarData radarData = new RadarData();
        radarData.addDataSet(radarDataSetAlumnos);
        radarData.addDataSet(radarDataSetDocente);

        String[] puntos = {"Flexibilitat", "Responsabilitat", "Autonomia", "Sociablilitat", "Escel·lencia"};

        XAxis xAxis = radarGrafico.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(puntos));

        radarGrafico.getDescription().setText("FRASE");
        radarGrafico.setData(radarData);


    }



    public int[] cogerColoresRandom(int cantidad)
    {
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