package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.ColoresGraficosFragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidproyecto2.Fragments.FragmentsDocente.ValoracionDocenteFragment.KpiAdapterValoracion;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ColoresGraficosFragment extends Fragment {

    private MainActivity activity;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9 ,
            btn10, btn11, btn12,btn13, btn14, btn15, btn16,btn17, btn18, btn19;

    private RecyclerView ListColores;

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

    }




}