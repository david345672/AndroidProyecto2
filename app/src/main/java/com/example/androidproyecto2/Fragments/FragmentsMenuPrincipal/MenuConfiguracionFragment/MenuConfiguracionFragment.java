package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.MenuConfiguracionFragment;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.ColoresGraficosFragment.ColoresGraficosFragment;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.IdiomasFragment.IdiomasFragment;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.NotificacionesFragment.NotificacionesFragment;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.FragmentsMenuConfiguracion.TemasFragment.TemasFragment;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;



public class MenuConfiguracionFragment extends Fragment {
    MainActivity activity;
    private FragmentManager mgr;
    FragmentTransaction fragmentTransaction;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_menu_configuracion, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();

        irAnotificaciones();
        RadioButton RdbNotificacion = view.findViewById(R.id.RdbNotificacion);
        RadioButton RdbTemas = view.findViewById(R.id.RdbTemas);
        RadioButton RdbGraficos = view.findViewById(R.id.RdbGraficos);
        RadioButton RdbIdioma = view.findViewById(R.id.RdbIdioma);

        RdbNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAnotificaciones();
            }
        });
        RdbTemas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAtemas();
            }
        });

        RdbGraficos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAgraficos();
            }
        });

        RdbIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAidioma();
            }
        });



    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);


        int currentOrientation = getResources().getConfiguration().orientation;

        if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE){
            //irAConfiguracion();

        }
        else {


        }





    }



    public void irAConfiguracion()
    {
        mgr = getChildFragmentManager();
        fragmentTransaction = mgr.beginTransaction();

        MenuConfiguracionFragment menuConfiguracionFragment = new MenuConfiguracionFragment();
        fragmentTransaction.replace(R.id.FrContentMenu, menuConfiguracionFragment);
        fragmentTransaction.commit();
    }


    public void irAnotificaciones(){
        mgr = getChildFragmentManager();
        fragmentTransaction = mgr.beginTransaction();
        NotificacionesFragment notificacionesFragment = new NotificacionesFragment();
        fragmentTransaction.replace(R.id.FrContentMenuConfig, notificacionesFragment);
        fragmentTransaction.commit();
    }
    public void irAtemas(){
        mgr = getChildFragmentManager();
        fragmentTransaction = mgr.beginTransaction();
        TemasFragment temasFragment = new TemasFragment();
        fragmentTransaction.replace(R.id.FrContentMenuConfig, temasFragment);
        fragmentTransaction.commit();
    }
    public void irAgraficos(){
        mgr = getChildFragmentManager();
        fragmentTransaction = mgr.beginTransaction();
        ColoresGraficosFragment graficosFragment = new ColoresGraficosFragment();
        fragmentTransaction.replace(R.id.FrContentMenuConfig, graficosFragment);
        fragmentTransaction.commit();
    }
    public void irAidioma(){
        mgr = getChildFragmentManager();
        fragmentTransaction = mgr.beginTransaction();
        IdiomasFragment idiomasFragment = new IdiomasFragment();
        fragmentTransaction.replace(R.id.FrContentMenuConfig, idiomasFragment);
        fragmentTransaction.commit();
    }
}