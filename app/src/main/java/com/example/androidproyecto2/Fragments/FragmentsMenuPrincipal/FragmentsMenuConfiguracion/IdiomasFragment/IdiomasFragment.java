package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.IdiomasFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.androidproyecto2.Clases.LocaleHelper;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

public class IdiomasFragment extends Fragment {

    Button btnCatalan;
    Button btnCastellano;
    Button btnIngles;
    MainActivity mainActivity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_idiomas, container, false);
        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) getActivity();

    }

    public void botones(View view){
        btnCastellano= view.findViewById(R.id.btnCastellano);
        btnCatalan= view.findViewById(R.id.btnCatalan);
        btnIngles=view.findViewById(R.id.btnIngles);


        btnIngles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Amb LocalHelper podem canviar l'idioma de l'aplicació indicant l'activity actual i el idioma que volem
                LocaleHelper.setLocale(getContext(), "en");
                //Necesari per veure els canviis
                mainActivity.recreate();
            }
        });
        btnCatalan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Amb LocalHelper podem canviar l'idioma de l'aplicació indicant l'activity actual i el idioma que volem
                LocaleHelper.setLocale(getContext(), "ca");
                //Necesari per veure els canviis
                mainActivity.recreate();
            }
        });

        btnCastellano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Amb LocalHelper podem canviar l'idioma de l'aplicació indicant l'activity actual i el idioma que volem
                LocaleHelper.setLocale(getContext(), "es");
                //Necesari per veure els canviis
                mainActivity.recreate();
            }
        });
    }

}