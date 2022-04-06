package com.example.androidproyecto2.Fragments.LoginFragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidproyecto2.Clases.MissatgeError;
import com.example.androidproyecto2.Clases.Usuari;
import com.example.androidproyecto2.Fragments.MenuPrincipalFragment.MenuPrincipalFragment;
import com.example.androidproyecto2.MainActivity;
import com.example.androidproyecto2.R;
import com.example.androidproyecto2.api.Api;
import com.example.androidproyecto2.api.apiServices.UsuarisService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {
    EditText etUser;
    EditText etPassword;
    Button btnIniciarSesion;
    FragmentManager mgr;
    FragmentTransaction fragmentTransaction;
    MainActivity mainActivity;
    public List<Usuari> usuarisList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) getActivity();


        btnIniciarSesion = getActivity().findViewById(R.id.btnIniciarSesion);
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprovarDatosConAPI();

            }
        });


        //pasarFragment();






    }


    private void comprovarDatosConAPI(){
        etUser = getActivity().findViewById(R.id.etUser);
        etPassword = getActivity().findViewById(R.id.etPassword);

        UsuarisService userService = Api.getApi().create(UsuarisService.class);
        Call<List<Usuari>> listCall = userService.Getusuaris();

        listCall.enqueue(new Callback<List<Usuari>>() {
            @Override
            public void onResponse(Call<List<Usuari>> call, Response<List<Usuari>> response) {
                switch (response.code()){
                    case 200:
                        usuarisList = response.body();

                        for (Usuari userObject:usuarisList) {
                            if(userObject.getNomUsuari().equals(etUser.getText().toString())){
                            //Encriptar la contra k pone el user
                                //Comparar contrasenyas
                                mainActivity.usuariLogin=userObject;
                            }else{
                                Toast.makeText(mainActivity.getApplicationContext(), "Usuario y/o contrasenya incorrectos", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    case 400:
                        Gson gson = new Gson();
                        MissatgeError mensajeError = gson.fromJson(response.errorBody().charStream(),MissatgeError.class);
                        Toast.makeText(mainActivity.getApplicationContext(),mensajeError.getMessage(),Toast.LENGTH_LONG).show();
                        break;
                    case 404:
                        Toast.makeText(mainActivity.getApplicationContext(),"Registre no trobat",Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Usuari>> call, Throwable t) {

            }
        });
    }

    private void pasarFragment(){

        Button btnLogout = mainActivity.toolbar.findViewById(R.id.btnLogout);
        btnIniciarSesion = getActivity().findViewById(R.id.btnIniciarSesion);
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogout.setVisibility(View.VISIBLE);
                AppCompatActivity activity = (AppCompatActivity) getActivity();
                mgr = activity.getSupportFragmentManager();
                fragmentTransaction = mgr.beginTransaction();
                MenuPrincipalFragment menuPrincipalFragment = new MenuPrincipalFragment();
                fragmentTransaction.replace(R.id.FrContent,menuPrincipalFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }


}