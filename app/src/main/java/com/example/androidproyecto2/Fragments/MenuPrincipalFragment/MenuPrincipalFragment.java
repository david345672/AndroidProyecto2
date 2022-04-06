package com.example.androidproyecto2.Fragments.MenuPrincipalFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.InicioFragment.InicioFragment;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.MenuConfiguracionFragment.MenuConfiguracionFragment;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.PerfilFragment.PerfilFragment;
import com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.TutorialFragment.TutorialFragment;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

public class MenuPrincipalFragment extends Fragment {

    MainActivity activity;
    private FragmentManager mgr;
    private FragmentTransaction fragmentTransaction;
    FragmentManager mg;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu_principal, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        activity.layout = "MenuPrincipal";
        irAInicio();

        RadioButton btnInicio = view.findViewById(R.id.btnInicio);
        RadioButton btnTutorial = view.findViewById(R.id.btnTutorial);
        RadioButton btnPerfil = view.findViewById(R.id.btnPerfil);
        RadioButton btnConfiguracion = view.findViewById(R.id.btnConfiguracion);


        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAInicio();
            }
        });

        btnTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irATutorial();
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAPerfil();
            }
        });

        btnConfiguracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAConfiguracion();
            }
        });


    }



    public void irAInicio()
    {
        mgr = getChildFragmentManager();

        fragmentTransaction = mgr.beginTransaction();

        InicioFragment inicioFragment = new InicioFragment();
        fragmentTransaction.replace(R.id.FrContentMenu, inicioFragment);
        fragmentTransaction.commit();

    }

    public void irATutorial()
    {
        mgr = getChildFragmentManager();

        fragmentTransaction = mgr.beginTransaction();

        TutorialFragment tutorialFragment = new TutorialFragment();
        fragmentTransaction.replace(R.id.FrContentMenu, tutorialFragment);
        fragmentTransaction.commit();
    }


    public void irAPerfil()
    {
        mgr = getChildFragmentManager();
        fragmentTransaction = mgr.beginTransaction();

        PerfilFragment perfilFragment = new PerfilFragment();
        fragmentTransaction.replace(R.id.FrContentMenu, perfilFragment);
        fragmentTransaction.commit();
    }


    public void irAConfiguracion()
    {
        mgr = getChildFragmentManager();
        fragmentTransaction = mgr.beginTransaction();

        MenuConfiguracionFragment menuConfiguracionFragment = new MenuConfiguracionFragment();
        fragmentTransaction.replace(R.id.FrContentMenu, menuConfiguracionFragment);
        fragmentTransaction.commit();
    }



}