package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.ColoresGraficosFragment.Graficos;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;


public class BarGraficoFragment extends Fragment {

    private MainActivity activity;
    private BarChart barGrafico;
    private int[] RandomColors;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bar_grafico, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        barGrafico = view.findViewById(R.id.barGrafico);

        RandomColors = cogerColoresRandom(5);

        ArrayList<BarEntry> valoracionesFrase = new ArrayList<>();
        valoracionesFrase.add(new BarEntry(2014,636,"Flexibilitat"));
        valoracionesFrase.add(new BarEntry(2015,512,"Responsabilitat"));
        valoracionesFrase.add(new BarEntry(2016,779,"Autonomia"));
        valoracionesFrase.add(new BarEntry(2017,804,"Sociabilitat"));
        valoracionesFrase.add(new BarEntry(2018,900,"Excelencia"));

        BarDataSet barDataSet = new BarDataSet(valoracionesFrase,"Frase");
        barDataSet.setColors(RandomColors);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barGrafico.setFitBars(true);
        barGrafico.setData(barData);
        barGrafico.getDescription().setText("FRASE");
        barGrafico.animateY(2000);

    }


    public int[] cogerColoresRandom(int cantidad)
    {
        // Esta variable se usar?? para llenar el array en la posici??n correspondiente
        int index = 0;

        // array que guarda los n??meros aleatorios
        int [] coloresRandom = new int[cantidad];

        // Nuestro primer bucle que se ejecutar?? hasta que hayamos llenado el arrary
        while(index < cantidad) {
            // Variable que guarda el n??mero aleatorio del array de colores del main
            int RandomColor = activity.coloresGraficos[new Random().nextInt(activity.coloresGraficos.length)];
            // Variable que indica si el RandomColor est?? repetido
            // asumimos que a??n no est?? repetido y la establecemos a false
            boolean repetido = false;
            //Segundo bucle que se ejecutar?? siempre que el n??mero no est?? repetido
            while(!repetido) {
                // Bucle que recorre el array comparando el RandomColor con
                // cada uno de los items del array
                for(int i=0; i<index; i++) {
                    //realizamos la comparaci??n
                    if(RandomColor == coloresRandom[i]) {
                        // si el n??mero se repite, establecemos repetido=true
                        repetido = true;
                    }
                }
                // verificamos el estado del valor repetido. Si es false, significa
                // que hemos recorrido el array hasta la posici??n index sin encontrar
                // coincidencias
                if(!repetido) {
                    // almacenamos el valor propuesto ya que no est?? repetido
                    // incrementamos el ??ndice
                    coloresRandom[index] = RandomColor;
                    index++;
                }
            }

        }


        return coloresRandom;

    }



}