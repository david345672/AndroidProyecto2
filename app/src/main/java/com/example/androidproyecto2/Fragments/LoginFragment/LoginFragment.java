package com.example.androidproyecto2.Fragments.LoginFragment;

import android.content.Context;
import android.content.res.Configuration;
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

import com.example.androidproyecto2.BCrypt;
import com.example.androidproyecto2.Clases.LocaleHelper;
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
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {
    EditText etUser;
    EditText etPassword;
    Button btnIniciarSesion;
    FragmentManager fragmentManager;
    MainActivity mainActivity;
    Button btnIdioma;
    FragmentTransaction fragmentTransaction;
    Context context;
    LayoutInflater inflater;
    ViewGroup container;
    Bundle savedInstanceState;


    public List<Usuari> usuarisList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.inflater= inflater;
        this.container = container;
        this.savedInstanceState = savedInstanceState;
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) getActivity();


        prueba();
        btnIniciarSesion = view.findViewById(R.id.btnIniciarSesion);
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comprovarDatosConAPI();

            }
        });
    }


    public  void prueba() {
        Fragment fr = mainActivity.getSupportFragmentManager().findFragmentById(R.id.FrContent);
        FragmentTransaction tr = mainActivity.getSupportFragmentManager().beginTransaction();
        tr.attach(fr);
        tr.commit();


        btnIdioma = getActivity().findViewById(R.id.btnIdioma);
        btnIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Amb LocalHelper podem canviar l'idioma de l'aplicaci?? indicant l'activity actual i el idioma que volem
                LocaleHelper.setLocale(getContext(), "ca");
                //Necesari per veure els canviis



                mainActivity.recreate();


            }
        });

    }

    public void comprovarDatosConAPI() {
        etUser = getActivity().findViewById(R.id.etUser);
        etPassword = getActivity().findViewById(R.id.etPassword);
        UsuarisService userService = Api.getApi().create(UsuarisService.class);
        Call<List<Usuari>> listCall = userService.Getusuaris();
        listCall.enqueue(new Callback<List<Usuari>>() {
            @Override
            public void onResponse(Call<List<Usuari>> call, Response<List<Usuari>> response) {
                switch (response.code()) {
                    case 200:
                        usuarisList = response.body();
                        Button btnLogout = mainActivity.toolbar.findViewById(R.id.btnLogout);


                        for (Usuari userObject : usuarisList) {
                            if (userObject.getNomUsuari().equals(etUser.getText().toString())) {
                                try {
                                    String test = etPassword.getText().toString();
                                    if (BCrypt.checkpw(test, userObject.getContrasenya())) {
                                        Toast.makeText(getContext(), "aaaa", Toast.LENGTH_SHORT).show();
                                        mainActivity.usuariLogin = (Usuari) userObject;
                                        pasarFragment();
                                    } else {
                                        Toast.makeText(getContext(), "socorro", Toast.LENGTH_LONG).show();
                                    }
                                } catch (Exception e) {
                                    Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getContext(), "socorro", Toast.LENGTH_LONG).show();
                            }
                        }
                        break;
                    case 400:
                        Gson gson = new Gson();
                        MissatgeError mensajeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                        Toast.makeText(mainActivity.getApplicationContext(), mensajeError.getMessage(), Toast.LENGTH_LONG).show();
                        break;
                    case 404:
                        Toast.makeText(mainActivity.getApplicationContext(), "Registre no trobat", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<Usuari>> call, Throwable t) {
                Toast.makeText(mainActivity, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pasarFragment() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        fragmentManager = activity.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        MenuPrincipalFragment menuPrincipalFragment = new MenuPrincipalFragment();
        fragmentTransaction.replace(R.id.FrContent, menuPrincipalFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    private void setLocale(String lang, String loc) {
        Locale locale = new Locale(lang, loc);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        mainActivity.getBaseContext().getResources().updateConfiguration(config, mainActivity.getBaseContext().getResources().getDisplayMetrics());
    }



    public void getUsuario(int id)
    {
        UsuarisService userService = Api.getApi().create(UsuarisService.class);
        Call<Usuari> userCall = userService.Getusuaris(id);

        userCall.enqueue(new Callback<Usuari>() {
            @Override
            public void onResponse(Call<Usuari> call, Response<Usuari> response) {
                switch (response.code())
                {
                    case 200:

                        mainActivity.usuariLogin = response.body();

                        if (mainActivity.usuariLogin.getGrups_has_alumnes().size() != 0)
                        {
                            mainActivity.esDocent = false;
                        }
                        else
                        {
                            mainActivity.esDocent = true;
                        }


                        pasarFragment();
                        mainActivity.toolbar.findViewById(R.id.btnLogout).setVisibility(View.VISIBLE);

                        break;
                    case 400:
                        Gson gson = new Gson();
                        MissatgeError missatgeError = gson.fromJson(response.errorBody().charStream(), MissatgeError.class);
                        Toast.makeText(mainActivity, missatgeError.getMessage(), Toast.LENGTH_LONG).show();
                        break;
                    case 404:
                        Toast.makeText(mainActivity,"Registre no trobat", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<Usuari> call, Throwable t) {

            }
        });

    }



}
