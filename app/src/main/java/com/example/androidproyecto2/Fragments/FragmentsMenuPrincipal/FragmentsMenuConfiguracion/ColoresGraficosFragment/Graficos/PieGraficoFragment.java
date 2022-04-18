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
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieGraficoFragment extends Fragment {

    private MainActivity activity;
    private PieChart pieGrafico;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pie_grafico, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        pieGrafico = view.findViewById(R.id.pieGrafico);

        ArrayList<PieEntry> valoracionesFrase = new ArrayList<>();
        valoracionesFrase.add(new PieEntry(501,"Flexibilitat"));
        valoracionesFrase.add(new PieEntry(790,"Responsabilitat"));
        valoracionesFrase.add(new PieEntry(285,"Autonomia"));
        valoracionesFrase.add(new PieEntry(348,"Sociabilitat"));
        valoracionesFrase.add(new PieEntry(123,"Excel·lencia"));

        PieDataSet pieDataSet = new PieDataSet(valoracionesFrase,"FRASE");
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieGrafico.setData(pieData);
        pieGrafico.getDescription().setEnabled(false);
        pieGrafico.setCenterText("FRASE");
        pieGrafico.animate();


    }


}