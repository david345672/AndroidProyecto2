package com.example.androidproyecto2.Fragments.FragmentsAlumno.ValoracionAlumnoFragment;

import android.content.Context;
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

import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.sql.Array;
import java.util.ArrayList;

public class ValoracionAlumnoFragment extends Fragment {

    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_valoracion_alumno, container, false);
        return view;
    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        activity.layout = "HacerValoracion";
        TextView tx = activity.findViewById(R.id.titlekpi);
        tx.setText("EJEMPLO");

        String[] prova = {"hola","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka","suka"};
        LinearLayout layout = activity.findViewById(R.id.layout);


        RecyclerView rv = activity.findViewById(R.id.kpisRecycler);
        String[] provaS = prova;
        AdapterValoracions adapter = new AdapterValoracions(provaS);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));

        rv.setAdapter(adapter);
        adapter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                TextView LabelName = activity.findViewById(R.id.texto);
                LabelName.setText(prova[(rv.getChildAdapterPosition(view))]);

            }
        });

    }


}

