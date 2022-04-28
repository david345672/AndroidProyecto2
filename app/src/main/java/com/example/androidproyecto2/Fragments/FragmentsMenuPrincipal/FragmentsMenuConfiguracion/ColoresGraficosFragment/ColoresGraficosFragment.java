package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.ColoresGraficosFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidproyecto2.Fragments.FragmentsDocente.ValoracionDocenteFragment.KpiAdapterValoracion;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.ColoresGraficosFragment.Graficos.BarGraficoFragment;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.ColoresGraficosFragment.Graficos.PieGraficoFragment;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.ColoresGraficosFragment.Graficos.RadarGraficoFragment;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.NotificacionesFragment.NotificacionesFragment;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ColoresGraficosFragment extends Fragment {

    private MainActivity activity;
    private RecyclerView ListColores;
    private RadarChart radarGrafico;

    private FragmentManager mgr;
    private FragmentTransaction fragmentTransaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_colores_graficos, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        ListColores = view.findViewById(R.id.ListColores);

        ColoresBotonesAdapter coloresBotonesAdapter = new ColoresBotonesAdapter(getContext(), activity.coloresGraficos, activity);
        ListColores.setHasFixedSize(true);
        ListColores.setLayoutManager(new GridLayoutManager(activity, 4));

        ListColores.setAdapter(coloresBotonesAdapter);

        irABarrasGrafico();

        Button btnBar = view.findViewById(R.id.btnBar);
        Button btnRadar = view.findViewById(R.id.btnRadar);
        Button btnPie = view.findViewById(R.id.btnPie);



        btnBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irABarrasGrafico();
            }
        });

        btnRadar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irARadarGrafico();
            }
        });

        btnPie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                irAPieGrafico();
            }
        });


    }



    public void irABarrasGrafico(){
        mgr = getChildFragmentManager();
        fragmentTransaction = mgr.beginTransaction();
        BarGraficoFragment barGraficoFragment = new BarGraficoFragment();
        fragmentTransaction.replace(R.id.FrContentGraficos, barGraficoFragment);
        fragmentTransaction.commit();
    }

    public void irARadarGrafico(){
        mgr = getChildFragmentManager();
        fragmentTransaction = mgr.beginTransaction();
        RadarGraficoFragment radarGraficoFragment = new RadarGraficoFragment();
        fragmentTransaction.replace(R.id.FrContentGraficos, radarGraficoFragment);
        fragmentTransaction.commit();
    }


    public void irAPieGrafico(){
        mgr = getChildFragmentManager();
        fragmentTransaction = mgr.beginTransaction();
        PieGraficoFragment pieGraficoFragment = new PieGraficoFragment();
        fragmentTransaction.replace(R.id.FrContentGraficos, pieGraficoFragment);
        fragmentTransaction.commit();
    }




}