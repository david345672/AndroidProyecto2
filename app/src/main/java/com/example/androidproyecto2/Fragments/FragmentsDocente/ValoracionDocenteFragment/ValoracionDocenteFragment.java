package com.example.androidproyecto2.Fragments.FragmentsDocente.ValoracionDocenteFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.LlistaSkills;
import com.example.androidproyecto2.Clases.Skill;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

import java.util.ArrayList;
import java.util.List;


public class ValoracionDocenteFragment extends Fragment {

    MainActivity activity;
    private List<Skill> skills = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_valoracion_docente, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();
        activity.layout = "HacerValoracion";

        Toast.makeText(activity, "LListaSkill: " + activity.llistaSkillSelected.getNom() + ", UsuariValorat: " + activity.usuariValorat.getNom()   , Toast.LENGTH_SHORT).show();

        if (activity.skillSelected != null)
        {
            skills.add(activity.skillSelected);
//            for (Skill skill: skills) {
//                Toast.makeText(activity,  "Skill a Valorar: " + skill.getNom(), Toast.LENGTH_SHORT).show();
//            }
        }
        else
        {
//            for (Skill skill: activity.llistaSkillSelected.getSkills()) {
//                Toast.makeText(activity, skill.getNom(), Toast.LENGTH_SHORT).show();
//            }
        }




    }
}