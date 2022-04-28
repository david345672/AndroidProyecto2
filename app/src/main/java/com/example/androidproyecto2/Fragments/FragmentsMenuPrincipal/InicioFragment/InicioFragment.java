package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.InicioFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.Grup;
import com.example.androidproyecto2.Clases.Grups_has_alumnes;
import com.example.androidproyecto2.Clases.Grups_has_docents;
import com.example.androidproyecto2.Fragments.MenuListasSkillsFragment.MenuListasSkillsFragment;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.GrupService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioFragment extends Fragment
{
    List<Grup> grups = new ArrayList<>();
    //ArrayList<Grup> Grups;
    FragmentManager mg;
    FragmentTransaction fragmentTransaction;
    MainActivity activity;
    ViewPager VpGrups;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inicio, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        mg = getActivity().getSupportFragmentManager();

        llenarGrupos();
    }


    public void llenarGrupos()
    {

        if (activity.esDocent)
        {
            for (Grups_has_docents gDocents: activity.usuariLogin.getGrups_has_docents()) {
                grups.add(gDocents.getGrups());
            }
        }
        else
        {
            for (Grups_has_alumnes gAlumnes: activity.usuariLogin.getGrups_has_alumnes()) {
                grups.add(gAlumnes.getGrups());
            }

        }

        cargarGrupos();
    }



    public void cargarGrupos()
    {
        VpGrups = view.findViewById(R.id.VpGrups);
        VpGrups.setClipToPadding(false);
        VpGrups.setPadding(100, 0, 100, 0);
        VpGrups.setPageMargin(100);

        GrupsAdapterViewPager grupsAdapterViewPager = new GrupsAdapterViewPager(getContext(),grups);
        VpGrups.setAdapter(grupsAdapterViewPager);

    }




}