package com.example.androidproyecto2.Fragments.FragmentsDocente.VerValoraciones.VerValoracionesDocenteFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproyecto2.R;

public class VerValoracionesDocenteFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ver_valoraciones_docente, container, false);
        return view;
    }
}