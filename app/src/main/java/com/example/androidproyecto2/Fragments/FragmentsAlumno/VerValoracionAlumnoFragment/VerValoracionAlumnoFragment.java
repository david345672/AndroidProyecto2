package com.example.androidproyecto2.Fragments.FragmentsAlumno.VerValoracionAlumnoFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
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

import java.util.ArrayList;

public class VerValoracionAlumnoFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Button btnAtras = getActivity().findViewById(R.id.btnAtras);
        btnAtras.setVisibility(View.VISIBLE);
        View view = inflater.inflate(R.layout.fragment_ver_valoracion_alumno, container, false);
        RadarChart radarchart = view.findViewById(R.id.RADARCHART);
        //System.out.println(tx.getText()+" we did it boiz");
        ArrayList<RadarEntry> radarEntries = new ArrayList<>();

        radarEntries.add(new RadarEntry(0, 2));
        radarEntries.add(new RadarEntry(1, 2));
        radarEntries.add(new RadarEntry(2, 2));
        radarEntries.add(new RadarEntry(2, 2));
        radarEntries.add(new RadarEntry(3, 29));
        radarEntries.add(new RadarEntry(4, 62));
        RadarDataSet radarDataSet = new RadarDataSet(radarEntries,"labeldataset");
        radarDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        //radarDataSet.setValueTextColor(Color.BLACK);
        radarDataSet.setValueTextSize(18f);
        RadarData radarData = new RadarData(radarDataSet);
        radarchart.setData(radarData);
        RadarChart chart = radarchart;
// we configure the radar chart
        //chart.setBackgroundColor(Color.rgb(  60, 65,  82));
        chart.getDescription().setEnabled(false);
// useful to export your graph
        chart.setWebColor(Color.BLACK);
        chart.setWebLineWidth(1f);
        chart.setWebColorInner(Color.BLUE);
        chart.setWebAlpha(100);/*
        ArrayList<RadarEntry> employee1 = new ArrayList<>();
        ArrayList<RadarEntry> employee2 = new ArrayList<>();
        // we generate random values for the qualities of employees measured between 1 and 12
        for (int i = 0; i < NB_QUALITIES; i++) {
            float val1 = (int) (Math.random() * MAX) + MIN;
            employee1.add(new RadarEntry(val1));
            float val2 = (int) (Math.random() * MAX) + MIN;
            employee2.add(new RadarEntry(val2));
        }
        // we create two radar data sets objects with these data
        RadarDataSet set1  = new RadarDataSet (employee1,  ((MainActivity)getActivity()).usuariLogin.getNom()+((MainActivity)getActivity()).usuariLogin.getCognoms());
        set1.setColor(Color.RED);
        set1.setFillColor(Color.RED);
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightIndicators(false);
        set1.setDrawHighlightCircleEnabled (true);
        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
// we create Radar Data object which will be added to the Radar Chart for rendering
        RadarData data = new RadarData(sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.BLACK);
        radarchart.setData(data);
        radarchart.invalidate();
        radarchart.animateXY(1400, 1400, Easing.EaseInOutQuad, Easing.EaseInOutQuad);
// we define axis
        XAxis xaxis = radarchart.getXAxis();
        xaxis.setTextSize(9f);
        xaxis.setYOffset(0);
        xaxis.setXOffset(0);
        xaxis.setValueFormatter(new IndexAxisValueFormatter() {

            private String[] qualities = new String[] {"Communication", "Technical Knowledge", "Problem Solving", "Punctuality", "Team Player"};
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return qualities [(int) value % qualities.length];
            }
        });
        xaxis.setTextColor(Color.BLACK);
// Y axis
        YAxis yaxis = radarchart.getYAxis();
        yaxis.setLabelCount(NB_QUALITIES, true);
        yaxis.setTextSize(9f);
        yaxis.setAxisMinimum(MIN);
        yaxis.setAxisMaximum(MAX); // we define min and max for axis
        yaxis.setDrawLabels(true);
// we configure legend for our radar chart
        Legend l = radarchart.getLegend();
        l.setTextSize(15f);
        l.setVerticalAlignment (Legend.LegendVerticalAlignment. TOP);
        l.setHorizontalAlignment (Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation. HORIZONTAL);
        l.setDrawInside(true);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.BLACK);*/
        radarchart.invalidate();


        return view;
    }





    private ArrayList<RadarEntry> getEntries() {
        ArrayList<RadarEntry> radarEntries = new ArrayList<>();
        radarEntries.add(new RadarEntry(0, 0.21f));
        radarEntries.add(new RadarEntry(1, 0.12f));
        radarEntries.add(new RadarEntry(2, 0.20f));
        radarEntries.add(new RadarEntry(2, 0.52f));
        radarEntries.add(new RadarEntry(3, 0.29f));
        radarEntries.add(new RadarEntry(4, 0.62f));
        return radarEntries;
    }
}