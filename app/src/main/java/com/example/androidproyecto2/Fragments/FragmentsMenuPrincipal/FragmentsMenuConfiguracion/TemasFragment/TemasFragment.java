package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.TemasFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidproyecto2.R;

public class TemasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temas, container, false);
        return view;
    }
}