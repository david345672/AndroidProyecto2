package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.TemasFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

public class TemasFragment extends Fragment {

    private MainActivity activity;
    private Button btnTemaClaro;
    private Button btnTemaOscuro;
    private SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temas, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        btnTemaClaro = view.findViewById(R.id.btnTemaClaro);
        btnTemaOscuro = view.findViewById(R.id.btnTemaOscuro);


        btnTemaClaro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTheme("DEFAULT", "#FFFFFF", "#4C6BFF");
            }
        });

        btnTemaOscuro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTheme("DARK", "#212121", "#747474");
            }
        });

    }


    public void updateTheme(String key, String c1, String c2)
    {
        SharedPreferences savePreferences = activity.getSharedPreferences("config_theme", Context.MODE_PRIVATE);
        SharedPreferences.Editor ObjEditor = savePreferences.edit();
        ObjEditor.putString("theme",key);
        ObjEditor.commit();

        activity.fondo.setBackgroundColor(Color.parseColor(c1));
        activity.toolbar.setBackgroundColor(Color.parseColor(c2));

    }



}