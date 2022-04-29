package com.example.androidproyecto2.Fragments.FragmentsAlumno.ValoracionAlumnoFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.Kpi;
import com.example.androidproyecto2.Clases.Skill;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Clases.Valoracio;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ValoracionAlumnoFragment extends Fragment {

    MainActivity activity;
    List<Kpi> kpis;

    public ValoracionAlumnoFragment() {
        //Toast.makeText(activity, "", Toast.LENGTH_SHORT).show();

        // this.data = (ArrayList<Kpi>) s.getKpis();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_valoracion_alumno, container, false);
        return view;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        this.kpis = activity.skillSelected.getKpis();
        TextView nombreAvaluado = activity.findViewById(R.id.avaluatedName);
        nombreAvaluado.setText("Valorant a : "+activity.usuariValorat.getNom()+" "+activity.usuariValorat.getCognoms());
        activity.layout = "HacerValoracion";

        TextView tx = activity.findViewById(R.id.titlekpi);

        tx.setText(activity.skillSelected.getNom());
        RecyclerView rv = activity.findViewById(R.id.kpisRecycler);

        AdapterValoracions adapter = new AdapterValoracions(this.kpis,activity);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        rv.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                TextView LabelName = activity.findViewById(R.id.texto);
                //LabelName.setText(prova[(rv.getChildAdapterPosition(view))]);
                //Toast.makeText(activity, "prova", Toast.LENGTH_SHORT).show();

            }
        });

    }


}

