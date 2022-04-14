package com.example.androidproyecto2.Fragments.FragmentsMenuPrincipal.PerfilFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;

public class PerfilFragment extends Fragment {

    private MainActivity activity;

    TextView lblNombreUsuario;
    TextView lblNombre;
    TextView lblApellidos;
    TextView lblCorreo;
    TextView lblRol;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (MainActivity) getActivity();

        lblNombreUsuario = view.findViewById(R.id.lblNombreUsuario);
        lblNombre = view.findViewById(R.id.lblNombre);
        lblApellidos = view.findViewById(R.id.lblApellidos);
        lblCorreo = view.findViewById(R.id.lblCorreo);
        lblRol = view.findViewById(R.id.lblRol);

        lblNombreUsuario.setText(activity.usuariLogin.getNomUsuari());
        lblNombre.setText(activity.usuariLogin.getNom());
        lblApellidos.setText(activity.usuariLogin.getCognoms());
        lblCorreo.setText(activity.usuariLogin.getCorreo());
        if (activity.esDocent)
        {
            lblRol.setText("Docent");
        }
        else
        {
            lblRol.setText("Alumne");
        }




    }
}