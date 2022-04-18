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


public class BarGraficoFragment extends Fragment {

    private MainActivity activity;
    private BarChart barGrafico;

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


        ArrayList<BarEntry> valoracionesFrase = new ArrayList<>();
        valoracionesFrase.add(new BarEntry(2014,236));
        valoracionesFrase.add(new BarEntry(2015,512));
        valoracionesFrase.add(new BarEntry(2016,779));
        valoracionesFrase.add(new BarEntry(2017,104));
        valoracionesFrase.add(new BarEntry(2018,900));

        BarDataSet barDataSet = new BarDataSet(valoracionesFrase,"Frase");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barGrafico.setFitBars(true);
        barGrafico.setData(barData);
        barGrafico.getDescription().setText("FRASE");
        barGrafico.animateY(2000);



    }
}