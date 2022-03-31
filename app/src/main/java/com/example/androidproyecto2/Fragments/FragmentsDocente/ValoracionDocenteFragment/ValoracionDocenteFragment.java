package com.example.androidproyecto2.Fragments.FragmentsDocente.ValoracionDocenteFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;


public class ValoracionDocenteFragment extends Fragment {

    MainActivity activity;

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

        if (activity.skillSelected != null)
        {
            Toast.makeText(activity, "IdLista: " + activity.idListaSelected + ", SkillSelecionada: " + activity.skillSelected.getNom() + ", IdUsuario: " + activity.idUsuariSelected, Toast.LENGTH_LONG).show();
        }

//        if (activity.listaSkillsSelected != null)
//        {
//            Toast.makeText(activity, "", Toast.LENGTH_LONG).show();
//        }




    }
}